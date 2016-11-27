package com.zakrywilson.astro.tle;

/**
 * Represents a TLE (<a href="https://en.wikipedia.org/wiki/Two-line_element_set">Two Line Element
 * Set</a>).
 * <p>
 * Creating a TLE can be done by providing the TLE lines to one of the public constructors or by
 * using {@link com.zakrywilson.astro.tle.TLEBuilder} to construct a TLE manually. The builder is
 * useful if you have individual elements that need to be formatted into a TLE.
 *
 * @author Zach Wilson
 */
public final class TLE {

    private String title;
    private String line1;
    private String line2;

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
    private double inclination;
    private double raan;
    private double eccentricity;
    private double argumentOfPerigee;
    private double meanAnomaly;
    private double meanMotion;
    private int    revolutions;
    private int    checksumLine2;

    /**
     * Constructs a new TLE from the title line and lines 1 and 2 of the TLE.
     *
     * @param title the title to be set, can be <tt>null</tt>
     * @param line1 line 1 to be set, not <tt>null</tt>
     * @param line2 line 2 to be set, not <tt>null</tt>
     */
    public TLE(String title, String line1, String line2) {
        parse(title, line1, line2);
    }

    /**
     * Constructs a new TLE from lines 1 and 2 of a TLE.
     *
     * @param line1 line 1 to be set, not <tt>null</tt>
     * @param line2 line 2 to be set, not <tt>null</tt>
     */
    public TLE(String line1, String line2) {
        this("", line1, line2);
    }

    /**
     * Constructs a new TLE with all fields initializes to their data types' default value.
     */
    TLE() {}

    public String getTitle() {
        return title;
    }

    /**
     * Returns line 1 of the TLE.
     *
     * @return the first line
     */
    public String getLine1() {
        return line1;
    }

    /**
     * Returns line 2 of the TLE.
     *
     * @return the second line
     */
    public String getLine2() {
        return line2;
    }

    /**
     * Returns the satellite number of the TLE.
     *
     * @return the satellite number
     */
    public int getSatelliteNumber() {
        return satelliteNumber;
    }

    /**
     * Returns the classification of the TLE.
     *
     * @return the classification
     */
    public char getClassification() {
        return classification;
    }

    /**
     * Returns the international designator of the TLE.
     *
     * @return the international designator
     */
    public String getInternationalDesignator() {
        return internationalDesignator;
    }

    /**
     * Returns the 4-digit epoch year of the TLE.
     *
     * @return the epoch year
     */
    public int getEpochYear() {
        return epochYear;
    }

    /**
     * Returns the fractional Julian day epoch of the TLE.
     *
     * @return the epoch day
     */
    public double getEpochDay() {
        return epochDay;
    }

    /**
     * Returns the epoch millisecond from TLE epoch.
     * <p>
     * Although there is no epoch millisecond in a TLE, this is the conversion from the epoch year
     * and fractional day to milliseconds.
     *
     * @return the epoch millisecond
     */
    public long getEpochMillisecond() {
        return EpochUtils.toMillisecondEpoch(epochYear, epochDay);
    }

    /**
     * Returns the first derivative of the mean motion, divided by <tt>2</tt>, of the TLE.
     *
     * @return the first derivative of the mean motion
     */
    public double getFirstDerivativeOfMeanMotion() {
        return firstDerivativeOfMeanMotion;
    }

    /**
     * Returns the second derivative of the mean motion, divided by <tt>6</tt>, of the TLE.
     *
     * @return the second derivative of the mean motion
     */
    public double getSecondDerivativeOfMeanMotion() {
        return secondDerivativeOfMeanMotion;
    }

    /**
     * Returns the BSTAR drag term of the TLE.
     *
     * @return the drag term
     */
    public double getDragTerm() {
        return dragTerm;
    }

    /**
     * Returns the ephemeris type of the TLE.
     *
     * @return the ephemeris type
     */
    public int getEphemerisType() {
        return ephemerisType;
    }

    /**
     * Returns the element set number of the TLE.
     *
     * @return the element set number
     */
    public int getElementSetNumber() {
        return elementSetNumber;
    }

    /**
     * Returns the checksum (modulo 10) for line 1 of the TLE.
     *
     * @return the checksum for line 1
     */
    public int getChecksumLine1() {
        return checksumLine1;
    }

    /**
     * Returns the inclination (in degrees) of the TLE.
     *
     * @return the inclination
     */
    public double getInclination() {
        return inclination;
    }

    /**
     * Returns the right ascension of the ascending node (RAAN) (in degrees) of the TLE.
     *
     * @return the RAAN
     */
    public double getRaan() {
        return raan;
    }

    /**
     * Returns the eccentricity (in degrees) of the TLE.
     *
     * @return the eccentricity
     */
    public double getEccentricity() {
        return eccentricity;
    }

    /**
     * Returns the argument of perigee (in degrees) of the TLE.
     *
     * @return the argument of perigee
     */
    public double getArgumentOfPerigee() {
        return argumentOfPerigee;
    }

    /**
     * Returns the mean anomaly (in degrees) of the TLE.
     *
     * @return the mean anonamly
     */
    public double getMeanAnomaly() {
        return meanAnomaly;
    }

    /**
     * Returns the mean motion of the TLE.
     *
     * @return the mean motion
     */
    public double getMeanMotion() {
        return meanMotion;
    }

    /**
     * Returns the revolutions at epoch of the TLE.
     *
     * @return the revolutions
     */
    public int getRevolutions() {
        return revolutions;
    }

    /**
     * Returns the checksum (modulo 10) for line 2 of the TLE.
     *
     * @return the checksum for line 2
     */
    public int getChecksumLine2() {
        return checksumLine2;
    }

    /**
     * Sets the title line of the TLE.
     *
     * @param title the title to be set
     */
    void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets line 1 of the TLE.
     *
     * @param line1 the first line to be set
     */
    void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * Sets line 2 of the TLE.
     *
     * @param line2 the second line to be set
     */
    void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * Sets the satellite number of the TLE.
     *
     * @param satelliteNumber the satellite number to be set
     */
    void setSatelliteNumber(int satelliteNumber) {
        this.satelliteNumber = satelliteNumber;
    }

    /**
     * Sets the classification of the TLE, either (<tt>U</tt>, <tt>S</tt>, or <tt>C</tt>).
     *
     * @param classification the classification to be set
     */
    void setClassification(char classification) {
        this.classification = classification;
    }

    /**
     * Sets the international designator of the TLE.
     *
     * @param internationalDesignator the international designator to be set
     */
    void setInternationalDesignator(String internationalDesignator) {
        this.internationalDesignator = internationalDesignator;
    }

    /**
     * Sets the epoch year of the TLE.
     *
     * @param epochYear the epoch year to be set
     */
    void setEpochYear(int epochYear) {
        this.epochYear = epochYear;
    }

    /**
     * Sets the fractional Julian day of the TLE.
     *
     * @param epochDay the epoch day to be set
     */
    void setEpochDay(double epochDay) {
        this.epochDay = epochDay;
    }

    /**
     * Sets the first derivative of the mean motion, divided by <tt>2</tt>, of the TLE.
     *
     * @param firstDerivativeOfMeanMotion the first derivative of the mean motion to be set
     */
    void setFirstDerivativeOfMeanMotion(double firstDerivativeOfMeanMotion) {
        this.firstDerivativeOfMeanMotion = firstDerivativeOfMeanMotion;
    }

    /**
     * Sets the second derivative of the mean motion, divided by <tt>6</tt>, of the TLE.
     *
     * @param secondDerivativeOfMeanMotion the second derivative of the mean motion to be set
     */
    void setSecondDerivativeOfMeanMotion(double secondDerivativeOfMeanMotion) {
        this.secondDerivativeOfMeanMotion = secondDerivativeOfMeanMotion;
    }

    /**
     * Sets the BSTAR drag term of the TLE.
     *
     * @param dragTerm the BSTAR drag term to be set
     */
    void setDragTerm(double dragTerm) {
        this.dragTerm = dragTerm;
    }

    /**
     * Sets the ephemeris type of the TLE.
     *
     * @param ephemerisType the ephemeris type to be set
     */
    void setEphemerisType(int ephemerisType) {
        this.ephemerisType = ephemerisType;
    }

    /**
     * Sets the element set number of the TLE.
     *
     * @param elementSetNumber the element set number to be set
     */
    void setElementSetNumber(int elementSetNumber) {
        this.elementSetNumber = elementSetNumber;
    }

    /**
     * Sets the checksum (modulo 10) for line 1 of the TLE.
     *
     * @param checksum the checksum for line 1 to be set
     */
    void setChecksumLine1(int checksum) {
        this.checksumLine1 = checksum;
    }

    /**
     * Sets the inclination (in degrees) of the TLE.
     *
     * @param inclination the inclination to be set
     */
    void setInclination(double inclination) {
        this.inclination = inclination;
    }

    /**
     * Sets the right ascension of the ascending node (RAAN) (in degrees) of the TLE.
     *
     * @param raan the RAAN to be set
     */
    void setRaan(double raan) {
        this.raan = raan;
    }

    /**
     * Sets the eccentricity of the TLE.
     *
     * @param eccentricity the eccentricity to be set
     */
    void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    /**
     * Sets the argument of perigee (in degrees) of the TLE.
     *
     * @param argumentOfPerigee the argument of perigee to be set
     */
    void setArgumentOfPerigee(double argumentOfPerigee) {
        this.argumentOfPerigee = argumentOfPerigee;
    }

    /**
     * Sets the mean anomaly (in degrees) of the TLE.
     *
     * @param meanAnomaly the mean anomaly to be set
     */
    void setMeanAnomaly(double meanAnomaly) {
        this.meanAnomaly = meanAnomaly;
    }

    /**
     * Sets the mean motion of the TLE.
     *
     * @param meanMotion the mean motion to be set
     */
    void setMeanMotion(double meanMotion) {
        this.meanMotion = meanMotion;
    }

    /**
     * Sets the revolution number at epoch of the TLE.
     *
     * @param revolutions the revolution number to be set
     */
    void setRevolutions(int revolutions) {
        this.revolutions = revolutions;
    }

    /**
     * Sets the checksum (modulo 10) for line 2 of the TLE.
     *
     * @param checksum the checksum for line 2 to be set
     */
    void setChecksumLine2(int checksum) {
        this.checksumLine2 = checksum;
    }

    /**
     * Parses the title, line 1, and line 2 of the TLE.
     *
     * @param title the title to be set
     * @param line1 line 1 to be parsed
     * @param line2 line 2 to be parsed
     */
    private void parse(String title, String line1, String line2) {
        TLEParser parser = new TLEParser(title, line1, line2);
        this.title = parser.getTitle();
        this.line1 = parser.getLine1();
        this.line2 = parser.getLine2();
        this.satelliteNumber = parser.getSatelliteNumber();
        this.classification = parser.getClassification();
        this.internationalDesignator = parser.getInternationalDesignator();
        this.epochYear = parser.getEpochYear();
        this.epochDay = parser.getEpochDay();
        this.firstDerivativeOfMeanMotion = parser.getFirstDerivativeOfMeanMotion();
        this.secondDerivativeOfMeanMotion = parser.getSecondDerivativeOfMeanMotion();
        this.dragTerm = parser.getDragTerm();
        this.ephemerisType = parser.getEphemerisType();
        this.elementSetNumber = parser.getElementSetNumber();
        this.checksumLine1 = parser.getChecksumLine1();
        this.inclination = parser.getInclination();
        this.raan = parser.getRaan();
        this.eccentricity = parser.getEccentricity();
        this.argumentOfPerigee = parser.getArgumentOfPerigee();
        this.meanAnomaly = parser.getMeanAnomaly();
        this.meanMotion = parser.getMeanMotion();
        this.revolutions = parser.getRevolutions();
        this.checksumLine2 = parser.getChecksumLine2();
    }

    /**
     * Indicates whether some other TLE is "equal to" this one.
     * <p>
     * Compares all of the TLEs' separate attributes <b>except</b> for the <tt>Strings</tt>, lines 1
     * and 2. Instead, the individual elements that make up the lines are compared. This will ensure
     * that two TLEs with slightly different formatting but the same data to be considered equal.
     * Some such cases are when values in a TLE may lead with <tt>0</tt>'s instead of whitespaces.
     * <p>
     * The list of elements to be compared: title, satellite number, classification type, epoch
     * year, epoch day, first derivative of mean motion, second derivative of mean motion, drag
     * term, ephemeris type, element set number, checksum for line 1 and 2, inclination, right
     * ascension of the ascending node, argument of perigee, mean anomaly, mean motion, and
     * revolutions at epoch.
     *
     * @param obj the reference object with which to compare
     * @return <tt>true</tt> if this object is the same as <tt>obj</tt>, <tt>false</tt> if otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        TLE tle = (TLE) obj;

        if (satelliteNumber != tle.satelliteNumber) {
            return false;
        }
        if (classification != tle.classification) {
            return false;
        }
        if (epochYear != tle.epochYear) {
            return false;
        }
        if (Double.compare(tle.epochDay, epochDay) != 0) {
            return false;
        }
        if (Double.compare(tle.firstDerivativeOfMeanMotion, firstDerivativeOfMeanMotion) != 0) {
            return false;
        }
        if (Double.compare(tle.secondDerivativeOfMeanMotion, secondDerivativeOfMeanMotion) != 0) {
            return false;
        }
        if (Double.compare(tle.dragTerm, dragTerm) != 0) {
            return false;
        }
        if (ephemerisType != tle.ephemerisType) {
            return false;
        }
        if (elementSetNumber != tle.elementSetNumber) {
            return false;
        }
        if (checksumLine1 != tle.checksumLine1) {
            return false;
        }
        if (Double.compare(tle.inclination, inclination) != 0) {
            return false;
        }
        if (Double.compare(tle.raan, raan) != 0) {
            return false;
        }
        if (Double.compare(tle.eccentricity, eccentricity) != 0) {
            return false;
        }
        if (Double.compare(tle.argumentOfPerigee, argumentOfPerigee) != 0) {
            return false;
        }
        if (Double.compare(tle.meanAnomaly, meanAnomaly) != 0) {
            return false;
        }
        if (Double.compare(tle.meanMotion, meanMotion) != 0) {
            return false;
        }
        if (revolutions != tle.revolutions) {
            return false;
        }
        if (checksumLine2 != tle.checksumLine2) {
            return false;
        }
        if (title != null ? !title.equals(tle.title) : tle.title != null) {
            return false;
        }
        return internationalDesignator != null ? internationalDesignator
                .equals(tle.internationalDesignator) : tle.internationalDesignator == null;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        result = 31 * result + satelliteNumber;
        result = 31 * result + (int) classification;
        result = 31 * result + (internationalDesignator != null ? internationalDesignator.hashCode() : 0);
        result = 31 * result + epochYear;
        temp = Double.doubleToLongBits(epochDay);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(firstDerivativeOfMeanMotion);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(secondDerivativeOfMeanMotion);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dragTerm);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + ephemerisType;
        result = 31 * result + elementSetNumber;
        result = 31 * result + checksumLine1;
        temp = Double.doubleToLongBits(inclination);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(raan);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(eccentricity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(argumentOfPerigee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(meanAnomaly);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(meanMotion);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + revolutions;
        result = 31 * result + checksumLine2;
        return result;
    }

    /**
     * Returns the formatted TLE. If the title is blank, its line will be omitted.
     * <p>
     * An example of a formatted TLE:
     * <pre>
     * ISS (ZARYA)
     * 1 25544U 98067A   08264.51782528 -.00002182  00000-0 -11606-4 0  2927
     * 2 25544  51.6416 247.4627 0006703 130.5360 325.0288 15.72125391563537
     * </pre>
     *
     * @return the formatted TLE
     */
    @Override
    public String toString() {
        if (title != null && title.trim().length() > 0) {
            return String.format("%-24s%n%s%n%s", title, line1, line2);
        }
        return String.format("%s%n%s", line1, line2);
    }

}
