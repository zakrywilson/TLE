package com.zakrywilson.astro.tle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * A thread-safe epoch utility class for handling conversions between the TLE epoch (year +
 * fractional Julian day) and the millisecond epoch from January 1, 1970 00:00:00. It also provides
 * functionality for formatting a millisecond epoch to the TLE format <tt>yyddd.dddddddd</tt>.
 *
 * @author Zach Wilson
 */
final class EpochUtils {

    /**
     * UTC timezone.
     */
    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

    /**
     * Number of milliseconds in one day.
     */
    private static final double MILLIS_IN_A_DAY = 86400000.0;

    /**
     * Decimal formatter used to format the decimal values of the Julian epoch day.
     */
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(".00000000");

    static {
        DECIMAL_FORMAT.setRoundingMode(RoundingMode.HALF_UP);
    }

    /**
     * Private constructor since this is a static utility class.
     */
    private EpochUtils() {}

    /**
     * Converts a TLE epoch year and fractional Julian day into a millisecond epoch from January 1,
     * 1970 00:00:00.
     *
     * @param year the year to be converted
     * @param julianDay the fractional Julian day to be converted
     * @return the millisecond epoch
     */
    static synchronized long toMillisecondEpoch(int year, double julianDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(UTC_TIME_ZONE);

        // Set year
        calendar.set(Calendar.YEAR, year);

        // Set day
        int wholeDay = (int) julianDay;
        calendar.set(Calendar.DAY_OF_YEAR, wholeDay);

        // Set millisecond
        double dayFraction = julianDay - (double) wholeDay;
        BigDecimal ms = new BigDecimal(dayFraction * MILLIS_IN_A_DAY);
        int millisecond = ms.setScale(0, RoundingMode.HALF_UP).intValueExact();
        calendar.set(Calendar.MILLISECOND, millisecond);

        return calendar.getTimeInMillis();
    }

    /**
     * Formats a TLE with the millisecond from January 1, 1970 00:00:00.
     *
     * @param epochMillisecond the epoch millisecond to be formatted
     * @return the formatted epoch
     */
    static synchronized String formatForTLE(long epochMillisecond) {
        // Get the year and day
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(UTC_TIME_ZONE);
        calendar.setTimeInMillis(epochMillisecond);
        int year = calendar.get(Calendar.YEAR);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int twoDigitYear = year % 100;

        // Get a calendar that contains milliseconds accounted for by the year and day of year
        Calendar castCalendar = Calendar.getInstance();
        castCalendar.clear();
        castCalendar.setTimeZone(UTC_TIME_ZONE);
        castCalendar.set(Calendar.YEAR, year);
        castCalendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        // Get the fractional part of the day by subtracting out the year and day of year
        long remainingMilliseconds = epochMillisecond - castCalendar.getTimeInMillis();
        double fractionalDay = remainingMilliseconds / MILLIS_IN_A_DAY;

        // Format year and day for TLE epoch field
        return String.format("%02d%3d%-8s", twoDigitYear, dayOfYear, DECIMAL_FORMAT.format(fractionalDay));
    }

    /**
     * Extracts the epoch year from the epoch millisecond.
     *
     * @param epochMillisecond the number of milliseconds since January 1, 1970 00:00:00
     * @return the year
     */
    static synchronized int getEpochYear(long epochMillisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(UTC_TIME_ZONE);
        calendar.setTimeInMillis(epochMillisecond);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Extracts the fractional Julian day from the epoch millisecond.
     *
     * @param epochMillisecond the number of milliseconds since January 1, 1970 00:00:00
     * @return the fractional Julian day
     */
    static synchronized double getEpochJulianDay(long epochMillisecond) {
        // Get the year and day
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(UTC_TIME_ZONE);
        calendar.setTimeInMillis(epochMillisecond);
        int year = calendar.get(Calendar.YEAR);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        // Get a calendar that contains milliseconds accounted for by the year and day of year
        Calendar castCalendar = Calendar.getInstance();
        castCalendar.clear();
        castCalendar.setTimeZone(UTC_TIME_ZONE);
        castCalendar.set(Calendar.YEAR, year);
        castCalendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        // Get the fractional part of the day by subtracting out the year and day of year
        long remainingMilliseconds = epochMillisecond - castCalendar.getTimeInMillis();
        double fractionalDay = remainingMilliseconds / MILLIS_IN_A_DAY;

        // Format year and day for TLE epoch field
        return (double) dayOfYear + fractionalDay;
    }

}
