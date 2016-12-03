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
 *   <li>Orbital elements</li>
 *   <li>First derivative of mean motion</li>
 *   <li>Element set number</li>
 *   <li>Revolutions at epoch</li>
 *   <li>
 *     Build step
 *     <ul>
 *       <li>Classification</li>
 *       <li>Second derivative of mean motion</li>
 *       <li>BSTAR drag term</li>
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
 *                     .setInternationalDesignator("98067  A")
 *                     .setEpoch(8, 264.51782528)
 *                     .setOrbitalElements(51.6416, 247.4627, .0006703, 130.5360, 325.0288, 15.72125391)
 *                     .setFirstDerivativeMeanMotion(-.00000439)
 *                     .setElementSetNumber(292)
 *                     .setRevolutions(56353)
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
     * The epoch step that returns the orbital elements step.
     */
    public interface EpochStep {
        OrbitalElementsStep setEpoch(int year, double day);
        OrbitalElementsStep setEpoch(long epochMillisecond);
    }

    /**
     * The orbital elements step that returns the first derivative mean motion step.
     */
    public interface OrbitalElementsStep {
        FirstDerivativeMeanMotionStep setOrbitalElements(double inclination,
                                                         double raan,
                                                         double eccentricity,
                                                         double argumentOfPerigee,
                                                         double meanAnomaly,
                                                         double meanMotion);
    }

    /**
     * The first derivative of the mean motion that returns the element set step.
     */
    public interface FirstDerivativeMeanMotionStep {
        ElementSetNumberStep setFirstDerivativeMeanMotion(double d);
    }

    /**
     * The element set number step that returns the revolutions step.
     */
    public interface ElementSetNumberStep {
        RevolutionsStep setElementSetNumber(int i);
    }

    /**
     * The revolutions step that returns the build step.
     */
    public interface RevolutionsStep {
        BuildStep setRevolutions(int i);
    }

    /**
     * The build step that is the end of the steps and allows for the optional elements to be set.
     */
    public interface BuildStep {
        BuildStep setClassification(char c);
        BuildStep setSecondDerivativeMeanMotion(double d);
        BuildStep setDragTerm(double d);
        BuildStep setEphemerisType(int i);
        TLE build();
    }

    /**
     * Implements all steps.
     */
    private static class Steps implements SatelliteNumberStep, InternationalDesignatorStep,
                                          EpochStep, OrbitalElementsStep,
                                          FirstDerivativeMeanMotionStep, ElementSetNumberStep,
                                          RevolutionsStep, BuildStep {
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
         * Constructs a new <tt>Steps</tt> with a blank TLE title.
         */
        Steps() {}

        /**
         * Constructs a new <tt>Steps</tt> with the TLE title.
         *
         * @param title the title to be set
         */
        Steps(String title) {
            if (title != null) {
                this.title = title;
            }
        }

        /**
         * Sets the satellite number and returns the international designator step. The satellite
         * number must be between 1 and 99,999.
         *
         * @param i the satellite number to be set
         * @return the next step
         * @throws IllegalArgumentException if the satellite number is not between 1 and 99,999
         */
        @Override
        public InternationalDesignatorStep setSatelliteNumber(int i) {
            if (i < 1 || i > 99999) {
                throw new IllegalArgumentException("Satellite number out of range (1-99999): " + i);
            }
            this.satelliteNumber = i;
            return this;
        }

        /**
         * Sets the satellite number and returns the international designator step. The satellite
         * number must be between 1 and 99,999.
         *
         * @param s the satellite number to be set
         * @return the next step
         * @throws IllegalArgumentException if the satellite number is not between 1 and 99,999
         */
        @Override
        public InternationalDesignatorStep setSatelliteNumber(String s) {
            String satelliteString = s.trim();
            if (!satelliteString.matches("\\d{1,5}")) {
                throw new IllegalArgumentException(
                        "Satellite number must be 1-5 digits in length: " + satelliteString);
            }

            int satelliteNumber;
            try {
                satelliteNumber = Integer.parseInt(satelliteString);
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(
                        "Satellite number is not a number: " + satelliteString);
            }
            return setSatelliteNumber(satelliteNumber);
        }

        /**
         * Sets the international designator and returns the epoch step. The international
         * designator must be the 2-digit year, joined with the 3-digit day of the year, followed 1
         * to 3 characters, corresponding to the launch piece. For example <tt>98067  A</tt> is an
         * international designator from February 1998.
         *
         * @param s the international designator to be set
         * @return the next step
         * @throws IllegalArgumentException if the international designator does not match the
         * expected format
         */
        @Override
        public EpochStep setInternationalDesignator(String s) {
        String id = s.trim();
            String regex = "\\d{5}\\s{0,2}[A-Za-z]+";
            if (!id.matches(regex)) {
                throw new IllegalArgumentException(
                        "International designator does not match regular expression \"" + regex +
                                "\":" + id);
            }
            this.internationalDesignator = id;
            return this;
        }

        /**
         * Sets the epoch year and fractional Julian day and returns the orbital elements step. The
         * epoch year must be in the format such as <tt>2012</tt>, not <tt>12</tt>. Note that this
         * Julian day is the Julian day minus the epoch year, i.e., the epoch fractional Julian day
         * must not exceed 366.0.
         *
         * @param year the epoch year to be set
         * @param day the epoch day to be set
         * @return the next step
         * @throws IllegalArgumentException if the epoch day is not between 0.0 and 366.0
         */
        @Override
        public OrbitalElementsStep setEpoch(int year, double day) {
            if (day < 0.0 || day > 366.0) {
                throw new IllegalArgumentException(
                        "Epoch Julian day out of range (0.0-366.0): " + day);
            }
            this.epochYear = year;
            this.epochDay = day;
            return this;
        }

        /**
         * Sets the epoch year and fractional Julian day with the epoch millisecond and returns the
         * orbital elements step.
         *
         * @param epochMillisecond the epoch millisecond to be set
         * @return the next step
         */
        @Override
        public OrbitalElementsStep setEpoch(long epochMillisecond) {
            this.epochYear = EpochUtils.getEpochYear(epochMillisecond);
            this.epochDay = EpochUtils.getEpochJulianDay(epochMillisecond);
            return this;
        }

        /**
         * Sets the orbital elements and returns the first derivative mean motion step.
         *
         * @param inclination the inclination (in degrees) to be set
         * @param raan the right ascension of the ascending node (RAAN) (in degrees) to be set
         * @param eccentricity the eccentricity (in degrees) to be set
         * @param argumentOfPerigee the argument of perigee (in degrees) to be set
         * @param meanAnomaly the mean anomaly (in degrees) to be set
         * @param meanMotion the mean motion (revolutions per day) to be set
         * @return the next step
         */
        @Override
        public FirstDerivativeMeanMotionStep setOrbitalElements(double inclination,
                                                                double raan,
                                                                double eccentricity,
                                                                double argumentOfPerigee,
                                                                double meanAnomaly,
                                                                double meanMotion) {
            this.inclination = inclination;
            this.raan = raan;
            this.eccentricity = eccentricity;
            this.argumentOfPerigee = argumentOfPerigee;
            this.meanAnomaly = meanAnomaly;
            this.meanMotion = meanMotion;
            return this;
        }

        /**
         * Sets the first derivative of the mean motion, divided by <tt>2</tt> and returns the
         * element set number step.
         *
         * @param d the first derivative of the mean motion, divided by <tt>2</tt>, to be set
         * @return the next step
         */
        @Override
        public ElementSetNumberStep setFirstDerivativeMeanMotion(double d) {
            this.firstDerivativeOfMeanMotion = d;
            return this;
        }

        /**
         * Sets the element set number and returns the revolutions step.
         *
         * @param i the element set number to be set
         * @return the next step
         */
        @Override
        public RevolutionsStep setElementSetNumber(int i) {
            if (i < 0 || i > 9999) {
                throw new IllegalArgumentException(
                        "Element set number out of range (0-9999): " + i);
            }
            this.elementSetNumber = i;
            return this;
        }

        /**
         * Sets the revolutions number and returns the build step.
         *
         * @param i the revolutions number at epoch to be set
         * @return the next step
         */
        @Override
        public BuildStep setRevolutions(int i) {
            if (i < 0 || i > 99999) {
                throw new IllegalArgumentException(
                        "Revolutions number out of range (0-99999): " + i);
            }
            this.revolutions = i;
            return this;
        }

        /**
         * Sets the classification character and return the same step (the build step).
         *
         * @param c the classification character to be set (either <tt>U</tt>,
         * <tt>S</tt>, or <tt>C</tt>)
         * @return this step (the build step)
         * @throws IllegalArgumentException if the character is not <tt>U</tt>< <tt>S</tt>, or
         * <tt>C</tt>
         */
        @Override
        public BuildStep setClassification(char c) {
            if (c != 'U' && c != 'C' && c != 'S') {
                throw new IllegalArgumentException(
                        "Classification must be 'U', 'C' or 'S': " + c);
            }
            this.classification = c;
            return this;
        }

        /**
         * Sets the second derivative of the mean motion, divided by <tt>6</tt>, and returns the
         * same step (the build step).
         *
         * @param d the second derivative of the mean motion, divided by <tt>6</tt>, to be set
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
         * Sets the ephemeris type and returns the same step (the build step). The ephemeris type
         * must be a single-digit value, i.e., 0-9.
         *
         * @param i the ephemeris type to be set, generally <tt>0</tt>
         * @return this step (the build step)
         * @throws IllegalArgumentException if the ephemeris type is not between 0 and 9
         */
        @Override
        public BuildStep setEphemerisType(int i) {
            if (i < 0 || i > 9) {
                throw new IllegalArgumentException("Ephemeris type out of range (0-9): " + i);
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
            tle.setChecksumLine1(Integer.parseInt(line1.substring(68)));
            tle.setInclination(inclination);
            tle.setRaan(raan);
            tle.setEccentricity(eccentricity);
            tle.setArgumentOfPerigee(argumentOfPerigee);
            tle.setMeanAnomaly(meanAnomaly);
            tle.setMeanMotion(meanMotion);
            tle.setRevolutions(revolutions);
            tle.setChecksumLine2(Integer.parseInt(line1.substring(68)));
            return tle;
        }

    }

}
