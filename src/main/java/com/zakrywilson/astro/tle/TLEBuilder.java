package com.zakrywilson.astro.tle;

/**
 * Enforces the correct construction of a {@link TLE}.
 * <p>
 * This class implements a variant of the builder pattern which enforces the user to, at a minimum,
 * pass in the required elements into the builder for valid TLE construction. Once the required
 * elements have been received, the {@link BuildStep#build()} method will be available. Part of this
 * builder pattern's implementation is the use of <i>steps</i> which is a polymorphic procedural
 * mitigation of the TLE's construction. For example, {@link
 * SatelliteNumberStep#setSatelliteNumber(int)} returns the one option of {@link
 * InternationalDesignatorStep#setInternationalDesignator(String)} which returns two options of
 * {@link EpochStep#setEpoch(int, double)} or {@link EpochStep#setEpoch(long)}, and so forth.
 * Once all the required steps have been completed, {@link BuildStep#build()} will be available for
 * the TLE to be built. When the TLE is allowed to be built, the optional elements will also be
 * available to be set, such as <i>classification</i> and <i>ephemeris type</i>. There is also no
 * checksum step: checksums are calculated when the TLE lines are generated.
 * <p>
 * Note that there are constraints on the values for the TLE: most setters may throw an
 * {@link IllegalArgumentException} if the values are invalid.
 * <p>
 * The list of steps in order (after calling {@link TLEBuilder#newBuilder()} or
 * {@link TLEBuilder#newBuilder(String)}:
 * <ol>
 *   <li>Satellite number</li>
 *   <li>International designator</li>
 *   <li>Epoch</li>
 *   <li>Element set number</li>
 *   <li>Orbital elements</li>
 *   <li>Revolutions at epoch</li>
 *   <li>Mean motion (revolutions per day)</li>
 *   <li>First time derivative of mean motion</li>
 *   <li>
 *     Build step
 *     <ul>
 *       <li>Second time derivative of mean motion</li>
 *       <li>BSTAR drag term</li>
 *       <li>Classification</li>
 *       <li>Ephemeris type</li>
 *       <li>Build</li>
 *     </ul>
 *   </li>
 * </ol>
 * As you can see, after the <i>revolutions at epoch</i> step, the TLE can now be built or any
 * number of the optional elements can be set before calling {@link BuildStep#build()}.
 * <p>
 * Example usage:
 * <pre>
 * TLE tle = TLEBuilder.newBuilder()
 *                     .setSatelliteNumber(25544)
 *                     .setInternationalDesignator("98067A")
 *                     .setEpoch(8, 264.51782528)
 *                     .setElementSetNumber(292)
 *                     .setOrbitalElements(51.6416, 247.4627, .0006703, 130.5360, 325.0288)
 *                     .setRevolutions(56353)
 *                     .setMeanMotion(15.72125391)
 *                     .setFirstDerivativeMeanMotion(-.00000439)
 *                     .setClassification('U')
 *                     .setEphemerisType(8)
 *                     .build();
 * </pre>
 *
 * @author Zach Wilson
 */
public class TLEBuilder {

    /**
     * Private constructor.
     * <p>
     * Access to this constructor should only be through the two methods {@link
     * TLEBuilder#newBuilder()} and {@link TLEBuilder#newBuilder(String)}.
     */
    private TLEBuilder() {}

    /**
     * Returns the {@link SatelliteNumberStep}.
     *
     * @return the satellite number step
     */
    public static SatelliteNumberStep newBuilder() {
        return new Steps();
    }

    /**
     * Returns the {@link SatelliteNumberStep} and sets the title of the TLE.
     *
     * @param title the title of the TLE
     * @return the satellite number step
     */
    public static SatelliteNumberStep newBuilder(String title) {
        return new Steps(title);
    }

    /**
     * The satellite number step that returns the international designator step.
     */
    public interface SatelliteNumberStep {
        InternationalDesignatorStep setSatelliteNumber(int i);
        InternationalDesignatorStep setSatelliteNumber(String satelliteNumber);
    }

    /**
     * The international designator step that returns the epoch step.
     */
    public interface InternationalDesignatorStep {
        EpochStep setInternationalDesignator(String s);
    }

    /**
     * The epoch step that returns the element set number step.
     */
    public interface EpochStep {
        ElementSetNumberStep setEpoch(int year, double day);
        ElementSetNumberStep setEpoch(long epochMillisecond);
    }

    /**
     * The element set number step that returns the orbital elements step.
     */
    public interface ElementSetNumberStep {
        OrbitalElementsStep setElementSetNumber(int i);
    }

    /**
     * The orbital elements step that returns the revolutions step.
     */
    public interface OrbitalElementsStep {
        RevolutionsStep setOrbitalElements(double inclination, double raan, double eccentricity,
                                           double argumentOfPerigee, double meanAnomaly);
    }

    /**
     * The revolutions step that returns the mean motion step.
     */
    public interface RevolutionsStep {
        MeanMotionStep setRevolutions(int i);
    }

    /**
     * The mean motion step that returns the first time derivative of mean motion step.
     */
    public interface MeanMotionStep {
        FirstDerivativeMeanMotionStep setMeanMotion(double d);
    }

    /**
     * The first time derivative of the mean motion, divided by <code>2</code>, that returns the
     * build step.
     */
    public interface FirstDerivativeMeanMotionStep {
        BuildStep setFirstDerivativeMeanMotion(double d);
    }

    /**
     * The build step that is the end of the steps and allows for the optional elements to be set.
     */
    public interface BuildStep {
        BuildStep setSecondDerivativeMeanMotion(double d);
        BuildStep setDragTerm(double d);
        BuildStep setClassification(char c);
        BuildStep setEphemerisType(int i);
        TLE build();
    }

    /**
     * Implements all steps.
     */
    private static class Steps implements SatelliteNumberStep, InternationalDesignatorStep,
                                          EpochStep, ElementSetNumberStep, MeanMotionStep,
                                          RevolutionsStep, OrbitalElementsStep,
                                          FirstDerivativeMeanMotionStep, BuildStep {
        private String title = "";
        private String line1;
        private String line2;

        private int    satelliteNumber;
        private char   classification = 'U';               // Optional: default is 'U'
        private String internationalDesignator;
        private int    epochYear;
        private double epochDay;
        private double firstDerivativeOfMeanMotion;
        private double secondDerivativeOfMeanMotion = 0.0; // Optional: default is 0.0
        private double dragTerm = 0.0;                     // Optional: default is 0.0
        private int    ephemerisType = 0;                  // Optional: default is 0
        private int    elementSetNumber;
        private double inclination;
        private double raan;
        private double eccentricity;
        private double argumentOfPerigee;
        private double meanAnomaly;
        private double meanMotion;
        private int    revolutions;

        /**
         * Constructs a new <code>Steps</code> with a blank TLE title.
         */
        Steps() {}

        /**
         * Constructs a new <code>Steps</code> with the TLE title.
         *
         * @param title the title to be set
         */
        Steps(String title) {
            if (title != null) {
                this.title = title;
            }
        }

        /**
         * Sets the satellite number and returns the international designator step.
         * <p>
         * The satellite number must be between 1 and 99,999.
         *
         * @param i the satellite number to be set
         * @return the next step
         * @throws IllegalArgumentException if <code>s</code> is out of range (1 to 99,999)
         */
        @Override
        public InternationalDesignatorStep setSatelliteNumber(int i) {
            if (i < 1 || i > 99999) {
                throw new IllegalArgumentException("Satellite number out of range (1 to 99999): " + i);
            }
            this.satelliteNumber = i;
            return this;
        }

        /**
         * Sets the satellite number and returns the international designator step.
         * <p>
         * The satellite number must be between 1 and 99,999.
         *
         * @param s the satellite number to be set
         * @return the next step
         * @throws IllegalArgumentException if <code>s</code> number is <code>null</code> or is out
         * of range (1 to 99,999)
         */
        @Override
        public InternationalDesignatorStep setSatelliteNumber(String s) {
            if (s == null) {
                throw new IllegalArgumentException("Satellite number cannot be null");
            }
            s = s.trim();
            if (!s.matches("\\d{1,5}")) {
                throw new IllegalArgumentException("Satellite number must be 1-5 digits in length: " + s);
            }

            int satelliteNumber;
            try {
                satelliteNumber = Integer.parseInt(s);
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Satellite number is not a number: " + s);
            }
            return setSatelliteNumber(satelliteNumber);
        }

        /**
         * Sets the international designator and returns the epoch step.
         * <p>
         * The international designator must be the 2-digit year, joined with the 3-digit day of the
         * year, followed 1 to 3 characters, corresponding to the launch piece. For example
         * <code>98067A</code> is an international designator from February 1998.
         *
         * @param s the international designator to be set
         * @return the next step
         * @throws IllegalArgumentException if <code>s</code> is <code>null</code> or does not match
         * the expected format
         */
        @Override
        public EpochStep setInternationalDesignator(String s) {
            if (s == null) {
                throw new IllegalArgumentException("International designator cannot be null");
            }
            s = s.trim();
            String regex = "\\d{5}(([ ]{2}[A-Z])|([ ][A-Z]{2})|([A-Z]{1,3}))";
            if (!s.matches(regex)) {
                throw new IllegalArgumentException(String.format(
                        "International designator does not match regular expression \"%s\": %s",
                        regex, s));
            }
            this.internationalDesignator = s;
            return this;
        }

        /**
         * Sets the UTC epoch year and fractional Julian day and returns the element set number step.
         * <p>
         * The epoch year must be the 4-digit year instead of the 2-digit year (e.g.,
         * <code>2012</code>, not <code>12</code>). This is done to prevent ambiguity.
         * <p>
         * The fractional Julian day is the Julian day minus the epoch year (i.e., the epoch
         * fractional Julian day must not exceed 366).
         *
         * @param year the epoch 4-digit year to be set
         * @param day the epoch fractional Julian day of year to be set
         * @return the next step
         * @throws IllegalArgumentException if <code>year</code> is out of range (100 to 9,999) or
         * <code>day</code> is out of range (0 to 366)
         */
        @Override
        public ElementSetNumberStep setEpoch(int year, double day) {
            if (year < 100 || year > 9999) {
                throw new IllegalArgumentException("Epoch year out of range (0 to 9,999): " + year);
            }
            if (Double.compare(day, 0.0) < 0 || Double.compare(day, 366.0) > 0) {
                throw new IllegalArgumentException("Epoch Julian day out of range (0 to 366): " + day);
            }
            this.epochYear = year;
            this.epochDay = day;
            return this;
        }

        /**
         * Sets the epoch year and fractional Julian day with the epoch millisecond (from January 1,
         * 1970 00:00:00 UTC) and returns the element set number step.
         *
         * @param epochMillisecond the epoch millisecond to be set
         * @return the next step
         */
        @Override
        public ElementSetNumberStep setEpoch(long epochMillisecond) {
            this.epochYear = EpochUtils.getEpochYear(epochMillisecond);
            this.epochDay = EpochUtils.getEpochJulianDay(epochMillisecond);
            return this;
        }

        /**
         * Sets the element set number and returns the orbital elements step.
         *
         * @param i the element set number to be set
         * @return the next step
         * @throws IllegalArgumentException if <code>i</code> is out of range (0 to 9,999)
         */
        @Override
        public OrbitalElementsStep setElementSetNumber(int i) {
            if (i < 0 || i > 9999) {
                throw new IllegalArgumentException("Element set number out of range (0 to 9,999): " + i);
            }
            this.elementSetNumber = i;
            return this;
        }

        /**
         * Sets the orbital elements and returns the revolutions number step.
         *
         * @param inclination the inclination to be set (0 to 180 degrees)
         * @param raan the right ascension of the ascending node (RAAN) to be set (0 to 360 degrees)
         * @param eccentricity the eccentricity to be set (0 to 1)
         * @param argumentOfPerigee the argument of perigee to be set (0 to 360 degrees)
         * @param meanAnomaly the mean anomaly to be set (0 to 360 degrees)
         * @throws IllegalArgumentException if any one of the elements is out of range
         */
        @Override
        public RevolutionsStep setOrbitalElements(double inclination, double raan, double eccentricity,
                                                  double argumentOfPerigee, double meanAnomaly) {
            if (Double.compare(inclination, 0.0) < 0 || Double.compare(inclination, 180.0) > 0) {
                throw new IllegalArgumentException("Inclination is out of range (0 to 180): " + inclination);
            }
            if (Double.compare(raan, 0.0) < 0 || Double.compare(raan, 360.0) > 0) {
                throw new IllegalArgumentException("Right ascension of the ascending node (RAAN) is out of range (0 to 360): " + raan);
            }
            if (Double.compare(eccentricity, 0.0) < 0 || Double.compare(eccentricity, 1.0) > 0) {
                throw new IllegalArgumentException("Eccentricity is out of range (0 to 1): " + eccentricity);
            }
            if (Double.compare(argumentOfPerigee, 0.0) < 0 || Double.compare(argumentOfPerigee, 360.0) > 0) {
                throw new IllegalArgumentException("Argument of perigee is out of range (0 to 360): " + argumentOfPerigee);
            }
            if (Double.compare(meanAnomaly, 0.0) < 0 || Double.compare(meanAnomaly, 360.0) > 0) {
                throw new IllegalArgumentException("Mean anomaly is out of range (0 to 360): " + meanAnomaly);
            }
            this.inclination = inclination;
            this.raan = raan;
            this.eccentricity = eccentricity;
            this.argumentOfPerigee = argumentOfPerigee;
            this.meanAnomaly = meanAnomaly;
            return this;
        }

        /**
         * Sets the revolutions number at epoch and returns the mean motion step.
         *
         * @param i the revolutions number at epoch to be set
         * @return the next step
         * @throws IllegalArgumentException if <code>i</code> is out of range (0 to 99,999)
         */
        @Override
        public MeanMotionStep setRevolutions(int i) {
            if (i < 0 || i > 99999) {
                throw new IllegalArgumentException("Revolutions number out of range (0 to 99,999): " + i);
            }
            this.revolutions = i;
            return this;
        }

        /**
         * Sets the mean motion (revolutions per day) and returns the first time derivative of mean
         * motion step.
         *
         * @param d the mean motion (revolutions be day) to be set
         * @return the next step
         * @throws IllegalArgumentException if <code>d</code> is out of range (0-99)
         */
        @Override
        public FirstDerivativeMeanMotionStep setMeanMotion(double d) {
            if (Double.compare(d, 0.0) < 0 || Double.compare(d, 100.0) >= 0) {
                throw new IllegalArgumentException("Mean motion out of range (0 to 99): " + d);
            }
            this.meanMotion = d;
            return this;
        }

        /**
         * Sets the first time derivative of the mean motion, divided by <code>2</code> and returns
         * the build step.
         *
         * @param d the first time derivative of the mean motion, divided by <code>2</code>, to be
         * set
         * @return the last step
         * @throws IllegalArgumentException if <code>d</code> is not lie between -1.0 and 1.0
         */
        @Override
        public BuildStep setFirstDerivativeMeanMotion(double d) {
            if (Double.compare(d, -1.0) <= 0 || Double.compare(d, 1.0) >= 0) {
                throw new IllegalArgumentException(
                        "First time derivative of mean motion must be between -1.0 and 1.0: " + d);
            }
            this.firstDerivativeOfMeanMotion = d;
            return this;
        }

        /**
         * Sets the second time derivative of the mean motion, divided by <code>6</code>, and
         * returns the same step (the build step).
         *
         * @param d the second derivative of the mean motion, divided by <code>6</code>, to be set
         * @return this step (the build step)
         */
        @Override
        public BuildStep setSecondDerivativeMeanMotion(double d) {
            this.secondDerivativeOfMeanMotion = d;
            return this;
        }

        /**
         * Sets the BSTAR drag term and returns the same step (the build step).
         *
         * @param d the BSTAR drag term to be set
         * @return this step (the build step)
         */
        @Override
        public BuildStep setDragTerm(double d) {
            this.dragTerm = d;
            return this;
        }

        /**
         * Sets the classification character and return the same step (the build step).
         *
         * @param c the classification character to be set (either <code>U</code>, <code>S</code>,
         * or <code>C</code>)
         * @return this step (the build step)
         * @throws IllegalArgumentException if <code>c</code> is not <code>U</code>< <code>S</code>,
         * or <code>C</code>
         */
        @Override
        public BuildStep setClassification(char c) {
            if (c != 'U' && c != 'C' && c != 'S') {
                throw new IllegalArgumentException("Classification must be 'U', 'C' or 'S': " + c);
            }
            this.classification = c;
            return this;
        }

        /**
         * Sets the ephemeris type and returns the same step (the build step). The ephemeris type
         * must be a single-digit value, i.e., 0 through 9.
         *
         * @param i the ephemeris type to be set, generally <code>0</code>
         * @return this step (the build step)
         * @throws IllegalArgumentException if the ephemeris type is not between 0 and 9
         */
        @Override
        public BuildStep setEphemerisType(int i) {
            if (i < 0 || i > 9) {
                throw new IllegalArgumentException("Ephemeris type out of range (0 to 9): " + i);
            }
            this.ephemerisType = i;
            return this;
        }

        /**
         * Constructs a new TLE from the previously provided data and generates checksums for each
         * line.
         *
         * @return a new TLE
         */
        @Override
        public TLE build() {
            line1 = TLEFormatter
                    .formatLine1(satelliteNumber, classification, internationalDesignator,
                                 epochYear, epochDay, firstDerivativeOfMeanMotion,
                                 secondDerivativeOfMeanMotion, dragTerm, ephemerisType,
                                 elementSetNumber);
            line2 = TLEFormatter
                    .formatLine2(satelliteNumber, inclination, raan, eccentricity,
                                 argumentOfPerigee, meanAnomaly, meanMotion, revolutions);

            TLE tle = new TLE();
            tle.setTitle(title);
            tle.setLine1(line1);
            tle.setLine2(line2);
            tle.setSatelliteNumber(satelliteNumber);
            tle.setClassification(classification);
            tle.setInternationalDesignator(internationalDesignator);
            tle.setEpochYear(epochYear);
            tle.setEpochDay(epochDay);
            tle.setFirstDerivativeOfMeanMotion(firstDerivativeOfMeanMotion);
            tle.setSecondDerivativeOfMeanMotion(secondDerivativeOfMeanMotion);
            tle.setDragTerm(dragTerm);
            tle.setEphemerisType(ephemerisType);
            tle.setElementSetNumber(elementSetNumber);
            tle.setChecksumLine1(ChecksumUtils.parseChecksum(line1));
            tle.setInclination(inclination);
            tle.setRaan(raan);
            tle.setEccentricity(eccentricity);
            tle.setArgumentOfPerigee(argumentOfPerigee);
            tle.setMeanAnomaly(meanAnomaly);
            tle.setMeanMotion(meanMotion);
            tle.setRevolutions(revolutions);
            tle.setChecksumLine2(ChecksumUtils.parseChecksum(line2));
            return tle;
        }

    }

}
