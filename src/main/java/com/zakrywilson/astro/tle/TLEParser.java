package com.zakrywilson.astro.tle;

/**
 * Utility class for parsing TLE (Two Line Element Set) lines into their respective elements.
 *
 * @author Zach Wilson
 */
final class TLEParser {

    private String title;
    private String line1;
    private String line2;

    private int    lineNumber1;
    private int    satelliteNumber;
    private char   classification;
    private String internationalDesignator;
    private int    epochYear;
    private double epochDay;
    private double firstDerivativeOfMeanMotion;
    private double secondDerivativeOfMeanMotion;
    private double dragTerm;
    private int    ephemerisType;
    private int    elementSetNumber;
    private int    checksumLine1;
    private int    lineNumber2;
    private double inclination;
    private double raan;
    private double eccentricity;
    private double argumentOfPerigee;
    private double meanAnomaly;
    private double meanMotion;
    private int    revolutions;
    private int    checksumLine2;

    /**
     * Constructs a new <tt>TLEParser</tt> with the title and lines 1 and 2 of the TLE.
     *
     * @param title the title line of the TLE to be set. A <tt>null</tt> value results in
     * <tt>title</tt> being set as an empty string.
     * @param line1 line 1 of the TLE to be set, not <tt>null</tt>
     * @param line2 line 2 of the TLE to be set, not <tt>null</tt>
     * @throws IllegalArgumentException if <tt>line1</tt> or <tt>line2</tt> are <tt>null</tt>
     */
    TLEParser(String title, String line1, String line2) {
        if (line1 == null || line2 == null) {
            throw new IllegalArgumentException("Lines cannot be null (except for title line)");
        }
        this.title = (title == null) ? "" : title;
        this.line1 = line1;
        this.line2 = line2;
        parse();
    }

    /**
     * Constructs a new <tt>TLEParser</tt> with lines 1 and 2 of the TLE.
     *
     * @param line1 line 1 of the TLE to be set, not <tt>null</tt>
     * @param line2 line 2 of the TLE to be set, not <tt>null</tt>
     */
    TLEParser(String line1, String line2) {
        this(null, line1, line2);
    }

    /**
     * Returns the title of the TLE.
     *
     * @return the title
     */
    String getTitle() {
        return title;
    }

    /**
     * Returns line 1 of the TLE.
     *
     * @return line 1
     */
    String getLine1() {
        return line1;
    }

    /**
     * Returns line 2 of the TLE.
     *
     * @return line 2
     */
    String getLine2() {
        return line2;
    }

    /**
     * Returns the line number from the first line (should be <tt>1</tt>).
     *
     * @return the line number
     */
    int getLineNumber1() {
        return lineNumber1;
    }

    /**
     * Returns the satellite number.
     *
     * @return the satellite number
     */
    int getSatelliteNumber() {
        return satelliteNumber;
    }

    /**
     * Returns the classification character from the TLE (either <tt>U</tt>, <tt>S</tt>, or
     * <tt>C</tt>).
     *
     * @return the classification
     */
    char getClassification() {
        return classification;
    }

    /**
     * Returns the international designator from the TLE.
     *
     * @return the international designator
     */
    String getInternationalDesignator() {
        return internationalDesignator;
    }

    /**
     * Returns the epoch year as a four-digit integer. Note that even though the epoch year in a TLE
     * is formatted as the last two digits of the calendar year (e.g., 98 meaning 1998), the value
     * returned from this method would be the latter (i.e., 1998).
     *
     * @return the epoch year
     */
    int getEpochYear() {
        return epochYear;
    }

    /**
     * Returns the epoch fractional Julian day.
     *
     * @return the epoch day
     */
    double getEpochDay() {
        return epochDay;
    }

    /**
     * Returns the first derivative of the mean motion divided by 2.
     *
     * @return the first derivative of the mean motion
     */
    double getFirstDerivativeOfMeanMotion() {
        return firstDerivativeOfMeanMotion;
    }

    /**
     * Returns the second derivative of the mean motion divided by 6.
     *
     * @return the second derivative of the mean motion
     */
    double getSecondDerivativeOfMeanMotion() {
        return secondDerivativeOfMeanMotion;
    }

    /**
     * Returns the BSTAR drag term.
     *
     * @return the BSTAR drag term
     */
    double getDragTerm() {
        return dragTerm;
    }

    /**
     * Returns the ephemeris type.
     *
     * @return the ephemeris type
     */
    int getEphemerisType() {
        return ephemerisType;
    }

    /**
     * Returns the element set number.
     *
     * @return the element set number
     */
    int getElementSetNumber() {
        return elementSetNumber;
    }

    /**
     * Returns the checksum (modulo 10) for line 1.
     *
     * @return the checksum for line 1
     */
    int getChecksumLine1() {
        return checksumLine1;
    }

    /**
     * Returns the line number from the second line (should be <tt>2</tt>).
     *
     * @return the line number
     */
    int getLineNumber2() {
        return lineNumber2;
    }

    /**
     * Returns the inclination in degrees.
     *
     * @return the inclination
     */
    double getInclination() {
        return inclination;
    }

    /**
     * Returns the right ascension of the ascending node (RAAN) in degrees.
     *
     * @return the RAAN
     */
    double getRaan() {
        return raan;
    }

    /**
     * Returns the eccentricity.
     *
     * @return the eccentricity
     */
    double getEccentricity() {
        return eccentricity;
    }

    /**
     * Returns the argument of perigee in degrees.
     *
     * @return the argument of perigee
     */
    double getArgumentOfPerigee() {
        return argumentOfPerigee;
    }

    /**
     * Returns the mean anomaly in degrees.
     *
     * @return the mean anomaly
     */
    double getMeanAnomaly() {
        return meanAnomaly;
    }

    /**
     * Returns the mean motion (number of revolutions per day).
     *
     * @return the mean motion
     */
    double getMeanMotion() {
        return meanMotion;
    }

    /**
     * Returns the revolutions number at epoch.
     *
     * @return the revolutions number
     */
    int getRevolutions() {
        return revolutions;
    }

    /**
     * Returns the checksum (modulo 10) for line 2.
     *
     * @return the checksum for line 2
     */
    int getChecksumLine2() {
        return checksumLine2;
    }

    /**
     * Parses the lines into the separate elements.
     */
    private void parse() {
        lineNumber1 = Integer.parseInt(line1.substring(0, 1).trim());
        satelliteNumber = Integer.parseInt(line1.substring(2, 7).trim());
        classification = line1.charAt(7);
        internationalDesignator = line1.substring(9, 17).trim();
        epochYear = convertEpochYear(Integer.parseInt(line1.substring(18, 20).trim()));
        epochDay = Double.parseDouble(line1.substring(20, 32).trim());
        firstDerivativeOfMeanMotion = Double.parseDouble(line1.substring(33, 43).trim());
        secondDerivativeOfMeanMotion = parseExponentialValue(line1.substring(44, 52));
        dragTerm = parseExponentialValue(line1.substring(53, 61));
        ephemerisType = Integer.parseInt(line1.substring(62, 63).trim());
        elementSetNumber = Integer.parseInt(line1.substring(64, 68).trim());
        checksumLine1 = Integer.parseInt(line1.substring(68, 69).trim());
        lineNumber2 = Integer.parseInt(line2.substring(0, 1).trim());
        inclination = Double.parseDouble(line2.substring(8, 16).trim());
        raan = Double.parseDouble(line2.substring(17, 25).trim());
        eccentricity = Double.parseDouble('.' + line2.substring(26, 33).trim());
        argumentOfPerigee = Double.parseDouble(line2.substring(34, 42).trim());
        meanAnomaly = Double.parseDouble(line2.substring(43, 51).trim());
        meanMotion = Double.parseDouble(line2.substring(52, 63).trim());
        revolutions = Integer.parseInt(line2.substring(63, 68).trim());
        checksumLine2 = Integer.parseInt(line2.substring(68, 69).trim());
    }

    private int convertEpochYear(int year) {
        // 70 and higher will be in the 20th century, i.e., 1970-1999
        // Anything else is the 21st century, i.e., 2000-2069
        return Integer.parseInt((year >= 70 ? "19" : "20") + String.format("%02d", year));
    }

    /**
     * Parses an exponential value in TLE format (e.g., <tt>00000-0</tt>).
     *
     * @param s the <tt>String</tt> to be parsed
     * @return the value of <tt>s</tt>
     */
    private double parseExponentialValue(String s) {
        // If a '+' sign is in the string, this means it is 0.0 for the drag term
        // Also, ' 00000-0' is very common; if that is found, return 0.0
        if (s.trim().equals("00000+0") || s.trim().equals("00000-0")) {
            return 0.0;
        }

        // Find the last '-', ignoring the possible preceding negative sign
        int index = s.lastIndexOf('-');

        // Find the exponent for performing 10^n
        int exponent = Integer.parseInt(s.substring(index + 1).trim());

        // Find the base
        String base = s.substring(1, index);

        // Add the zeros up
        String zeros = "";
        for (int i = 0; i < exponent; i++) {
            zeros += "0";
        }

        // Tack on the decimal point to the beginning of the value
        // Add the number of 0's, i.e., the power of 10
        // Add the base number at the end
        String number = '.' + zeros + base;

        // If there was a negative sign, add it now
        if (s.charAt(0) == '-') {
            number = '-' + number;
        }

        // Parse the string and get the double
        return Double.parseDouble(number);
    }

}
