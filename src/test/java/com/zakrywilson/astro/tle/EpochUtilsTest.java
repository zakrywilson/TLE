package com.zakrywilson.astro.tle;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link EpochUtils}.
 *
 * @author Zach Wilson
 */
public class EpochUtilsTest {

    /**
     * Tests {@link EpochUtils#toMillisecondEpoch(int, double)}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void toMillisecondEpoch() throws Exception {
        long[] milliseconds = {1455343200000L, 913636800000L, 1000966420800L, 1010041881552L};
        int[] years = {2016, 1998, 2001, 2002};
        double[] days = {44.25000000, 348.50000000, 263.25950000, 3.29955500};
        for (int i = 0; i < milliseconds.length; i++) {
            long result = EpochUtils.toMillisecondEpoch(years[i], days[i]);
            if (result != milliseconds[i]) {
                Assert.fail(String.format(
                        "Invalid conversion from year and day epoch to millisecond epoch:" +
                                " expected='%d', received='%d' from epoch year '%d' and epoch day '%s'",
                        milliseconds[i], result, years[i], days[i]));
            }
        }

    }

    /**
     * Tests {@link EpochUtils#formatForTLE(long)}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void formatToTLE() throws Exception {
        long[] millisecond = {1455343200000L, 913636800000L, 1000966420800L, 1010041881552L};
        String[] formats = {"16 44.25000000", "98348.50000000", "01263.25950000", "02  3.29955500"};

        for (int i = 0; i < formats.length; i++) {
            String result = EpochUtils.formatForTLE(millisecond[i]);
            if (!result.equals(formats[i])) {
                Assert.fail(
                        String.format("Formatted epoch is invalid: expected='%s', received='%s'",
                                      formats[i], result));
            }
        }
    }

    /**
     * Tests {@link EpochUtils#getEpochYear(long)}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getEpochYear() throws Exception {
        int[] years = {2016, 1998, 2001, 2002};
        long[] milliseconds = {1455343200000L, 913636800000L, 1000966420800L, 1010041881552L};
        for (int i = 0; i < years.length; i++) {
            int result = EpochUtils.getEpochYear(milliseconds[i]);
            if (result != years[i]) {
                Assert.fail(String.format(
                        "Converted epoch year is invalid: expected='%d', received='%d'", years[i],
                        result));
            }
        }
    }

    /**
     * Tests {@link EpochUtils#getEpochJulianDay(long)}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getEpochJulianDay() throws Exception {
        double[] days = {44.25000000, 348.50000000, 263.25950000, 3.29955500};
        long[] milliseconds = {1455343200000L, 913636800000L, 1000966420800L, 1010041881552L};
        for (int i = 0; i < days.length; i++) {
            double result = EpochUtils.getEpochJulianDay(milliseconds[i]);
            if (Double.compare(result, days[i]) != 0) {
                Assert.fail(String.format(
                        "Converted epoch day is invalid: expected='%s', received='%s'", days[i],
                        result));
            }
        }
    }

}