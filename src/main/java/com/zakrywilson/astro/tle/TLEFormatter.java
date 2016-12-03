package com.zakrywilson.astro.tle;

import java.text.DecimalFormat;

/**
 * Utility class used for formatting elements of a Two Line Element Set (TLE) into their respective
 * two lines.
 *
 * @author Zach Wilson
 */
final class TLEFormatter {

    /**
     * Private constructor.
     * <p>
     * This class only contains static members. No instances of this class should be created.
     */
    private TLEFormatter() {}

    /**
     * Returns line 1 of a TLE, formatted with the provided fields and a newly generated checksum.
     *
     * @param satelliteNumber the satellite number to be set
     * @param classification the classification character to be set. Must be one of the following:
     * <code>U</code>, <code>C</code>, or <code>S</code>.
     * @param internationalDesignator the international designator to be set
     * @param epochYear the epoch year of the launch to be set. Must be in <code>YYYY</code> format.
     * @param epochDay the fractional Julian day of the launch to be set
     * @param firstDerivativeOfMeanMotion the 1st derivative of the mean motion (divided by 2) to be set
     * @param secondDerivativeOfMeanMotion the 2nd derivative of the mean motion (divided by 6) to be set
     * @param dragTerm the BSTAR drag term to be set
     * @param ephemerisType the ephemeris type to be set
     * @param elementSetNumber the element set number to be set
     * @return the line
     */
    static String formatLine1(int satelliteNumber, char classification,
                              String internationalDesignator, int epochYear, double epochDay,
                              double firstDerivativeOfMeanMotion,
                              double secondDerivativeOfMeanMotion, double dragTerm,
                              int ephemerisType, int elementSetNumber) {
        String line = String.format("1 %s%s %s %s %s %s %s %s %s",
                             TLEFormatter.formatSatelliteNumber(satelliteNumber), classification,
                             TLEFormatter.formatInternationalDesignator(internationalDesignator),
                             TLEFormatter.formatEpoch(epochYear, epochDay),
                             TLEFormatter.formatMeanMotionFirstDerivative(firstDerivativeOfMeanMotion),
                             TLEFormatter.formatMeanMotionSecondDerivative(secondDerivativeOfMeanMotion),
                             TLEFormatter.formatDragTerm(dragTerm), ephemerisType,
                             TLEFormatter.formatElementSetNumber(elementSetNumber));
        return line + ChecksumUtils.generateChecksum(line);
    }

    /**
     * Returns line 2 of a TLE, formatted with the provided fields and a newly generated checksum.
     *
     * @param satelliteNumber the satellite number to be set
     * @param inclination the inclination (in degrees) to be set
     * @param raan the right ascension of the ascending node (RAAN) (in degrees) to be set
     * @param eccentricity the eccentricity to be set
     * @param argumentOfPerigee the argument of perigee (in degrees) to be set
     * @param meanAnomaly the mean anomaly (in degrees) to be set
     * @param meanMotion the mean motion (in degrees) to be set
     * @param revolutions the revolutions number at epoch to be set
     * @return the line
     */
    static String formatLine2(int satelliteNumber, double inclination, double raan,
                              double eccentricity, double argumentOfPerigee, double meanAnomaly,
                              double meanMotion, int revolutions) {
        String line =  String.format("2 %s %s %s %s %s %s %s%s",
                             TLEFormatter.formatSatelliteNumber(satelliteNumber),
                             TLEFormatter.formatInclination(inclination),
                             TLEFormatter.formatRaan(raan),
                             TLEFormatter.formatEccentricity(eccentricity),
                             TLEFormatter.formatArgumentOfPerigee(argumentOfPerigee),
                             TLEFormatter.formatMeanAnomaly(meanAnomaly),
                             TLEFormatter.formatMeanMotion(meanMotion),
                             TLEFormatter.formatRevolutions(revolutions));
        return line + ChecksumUtils.generateChecksum(line);
    }

    private static String formatSatelliteNumber(int i) {
        return String.format("%5d", i);
    }

    private static String formatInternationalDesignator(String s) {
        return String.format("%-8s", s);
    }

    private static String formatEpoch(int year, double day) {
        DecimalFormat dayFormatter = new DecimalFormat("##0.00000000");
        return String.format("%02d%12s", (year % 100), dayFormatter.format(day));
    }

    private static String formatMeanMotionFirstDerivative(double d) {
        DecimalFormat formatter = new DecimalFormat(" .00000000;-.00000000");
        return formatter.format(d);
    }

    private static String formatMeanMotionSecondDerivative(double d) {
        if (Double.compare(d, 0.0) == 0) {
            return " 00000-0";
        }
        return formatExponentialValue(d);
    }

    private static String formatDragTerm(double d) {
        if (Double.compare(d, 0.0) == 0) {
            return " 00000+0";
        }
        return formatExponentialValue(d);
    }

    private static String formatElementSetNumber(int i) {
        return String.format("%4d", i);
    }

    private static String formatInclination(double d) {
        return formatStandardDouble(d);
    }

    private static String formatRaan(double d) {
        return formatStandardDouble(d);
    }

    private static String formatEccentricity(double d) {
        DecimalFormat formatter = new DecimalFormat(".0000000");
        return formatter.format(d).replace(".", "");
    }

    private static String formatArgumentOfPerigee(double d) {
        return formatStandardDouble(d);
    }

    private static String formatMeanAnomaly(double d) {
        return formatStandardDouble(d);
    }

    private static String formatMeanMotion(double d) {
        DecimalFormat formatter = new DecimalFormat("00.00######");
        String s = formatter.format(d);
        if (s.startsWith("0")) {
            s = s.replaceFirst("0", " ");
        }
        return s;
    }

    private static String formatRevolutions(int i) {
        return String.format("%5d", i);
    }

    private static String formatStandardDouble(double d) {
        DecimalFormat formatter = new DecimalFormat("##0.0000");
        return String.format("%8s", formatter.format(d));
    }

    private static String formatExponentialValue(double d) {
        DecimalFormat formatter = new DecimalFormat(" .00000E0;-.00000E0");
        return formatter.format(d).replace(".", "").replace("E", "");
    }

}
