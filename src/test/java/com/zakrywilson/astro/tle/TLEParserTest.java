package com.zakrywilson.astro.tle;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static com.zakrywilson.astro.tle.TLEParserTest.TLEElement.*;

/**
 * Tests {@link TLEParser}.
 *
 * @author Zach Wilson
 */
public class TLEParserTest {

    /**
     * Used to easily access elements in {@link TLEParserTest#TLEs}.
     */
    enum TLEElement {
        TITLE, LINE_1, LINE_2, SATELLITE_NUMBER, CLASSIFICATION, INTERNATIONAL_DESIGNATOR,
        EPOCH_YEAR, EPOCH_DAY, FIRST_DER, SECOND_DER, DRAG, EPHEMERIS_TYPE, ELEMENT_SET_NUMBER,
        CHECKSUM_1, INCLINATION, RAAN, ECCENTRICITY, ARGUMENT_OF_PERIGEE, MEAN_ANOMALY, MEAN_MOTION,
        REVOLUTIONS, CHECKSUM_2
    }

    /**
     * List of hash maps where each hash map represents all elements to a given TLE.
     */
    private static final List<Map<TLEElement, String>> TLEs = new ArrayList<>();

    /**
     * Create a list of hash maps that contain all the TLE elements, e.g., title, lines, RAANs,
     * inclinations, etc.
     */
    @BeforeClass
    public static void setup() {
        // TLE is unclassified
        // The classification character has been changed for testing purposes
        Map<TLEElement, String> tle0 = new EnumMap<>(TLEElement.class);
        tle0.put(TITLE, "AKEBONO (EXOS-D)        ");
        tle0.put(LINE_1, "1 19822C 89016A   16330.54185827  .00020730 -54173-7  42980-3 0  9995");
        tle0.put(LINE_2, "2 19822  75.0338 162.9721 1946869 124.1907 255.9297 11.60242208885551");
        tle0.put(SATELLITE_NUMBER, "19822");
        tle0.put(CLASSIFICATION, "C");
        tle0.put(INTERNATIONAL_DESIGNATOR, "89016A");
        tle0.put(EPOCH_YEAR, "2016");
        tle0.put(EPOCH_DAY, "330.54185827");
        tle0.put(FIRST_DER, ".00020730");
        tle0.put(SECOND_DER, "-.000000054173");
        tle0.put(DRAG, ".0004298");
        tle0.put(EPHEMERIS_TYPE, "0");
        tle0.put(ELEMENT_SET_NUMBER, "999");
        tle0.put(CHECKSUM_1, "5");
        tle0.put(INCLINATION, "75.0338");
        tle0.put(RAAN, "162.9721");
        tle0.put(ECCENTRICITY, ".1946869");
        tle0.put(ARGUMENT_OF_PERIGEE, "124.1907");
        tle0.put(MEAN_ANOMALY, "255.9297");
        tle0.put(MEAN_MOTION, "11.60242208");
        tle0.put(REVOLUTIONS, "88555");
        tle0.put(CHECKSUM_2, "1");
        TLEs.add(tle0);

        // TLE is unclassified
        // The classification character has been changed for testing purposes
        Map<TLEElement, String> tle1 = new EnumMap<>(TLEElement.class);
        tle1.put(TITLE, "HST                     ");
        tle1.put(LINE_1, "1 20580S 90037B   70331.22271991  .00000809  00000-0  39233-4 1 99992");
        tle1.put(LINE_2, "2 20580  28.4701 268.4992 0002921 147.0303  79.7254 15.08603912259602");
        tle1.put(SATELLITE_NUMBER, "20580");
        tle1.put(CLASSIFICATION, "S");
        tle1.put(INTERNATIONAL_DESIGNATOR, "90037B");
        tle1.put(EPOCH_YEAR, "1970");
        tle1.put(EPOCH_DAY, "331.22271991");
        tle1.put(FIRST_DER, ".00000809");
        tle1.put(SECOND_DER, "0.0");
        tle1.put(DRAG, ".000039233");
        tle1.put(EPHEMERIS_TYPE, "1");
        tle1.put(ELEMENT_SET_NUMBER, "9999");
        tle1.put(CHECKSUM_1, "2");
        tle1.put(INCLINATION, "28.4701");
        tle1.put(RAAN, "268.4992");
        tle1.put(ECCENTRICITY, ".0002921");
        tle1.put(ARGUMENT_OF_PERIGEE, "147.0303");
        tle1.put(MEAN_ANOMALY, "79.7254");
        tle1.put(MEAN_MOTION, "15.08603912");
        tle1.put(REVOLUTIONS, "25960");
        tle1.put(CHECKSUM_2, "2");
        TLEs.add(tle1);

        Map<TLEElement, String> tle2 = new EnumMap<>(TLEElement.class);
        tle2.put(TITLE, "POLAR                   ");
        tle2.put(LINE_1, "1 23802U 96013A   22331.41525705 -.00000380  74600-4  00000+0 2  9678");
        tle2.put(LINE_2, "2 23802  78.3477 275.1928 7458454 331.3390   2.5535  1.29841762 99521");
        tle2.put(SATELLITE_NUMBER, "23802");
        tle2.put(CLASSIFICATION, "U");
        tle2.put(INTERNATIONAL_DESIGNATOR, "96013A");
        tle2.put(EPOCH_YEAR, "2022");
        tle2.put(EPOCH_DAY, "331.41525705");
        tle2.put(FIRST_DER, "-.00000380");
        tle2.put(SECOND_DER, ".000074600");
        tle2.put(DRAG, "0.0");
        tle2.put(EPHEMERIS_TYPE, "2");
        tle2.put(ELEMENT_SET_NUMBER, "967");
        tle2.put(CHECKSUM_1, "8");
        tle2.put(INCLINATION, "78.3477");
        tle2.put(RAAN, "275.1928");
        tle2.put(ECCENTRICITY, ".7458454");
        tle2.put(ARGUMENT_OF_PERIGEE, "331.3390");
        tle2.put(MEAN_ANOMALY, "2.5535");
        tle2.put(MEAN_MOTION, "1.29841762");
        tle2.put(REVOLUTIONS, "9952");
        tle2.put(CHECKSUM_2, "1");
        TLEs.add(tle2);

        Map<TLEElement, String> tle3 = new EnumMap<>(TLEElement.class);
        tle3.put(TITLE, "SWAS                    ");
        tle3.put(LINE_1, "1 25560U 98071A   00330.55212799  .00000504  00000-0  56670-4 3 48733");
        tle3.put(LINE_2, "2 25560  69.9002  52.8454 0006133 157.9681 202.1740 14.93240737975146");
        tle3.put(SATELLITE_NUMBER, "25560");
        tle3.put(CLASSIFICATION, "U");
        tle3.put(INTERNATIONAL_DESIGNATOR, "98071A");
        tle3.put(EPOCH_YEAR, "2000");
        tle3.put(EPOCH_DAY, "330.55212799");
        tle3.put(FIRST_DER, ".00000504");
        tle3.put(SECOND_DER, "0.0");
        tle3.put(DRAG, ".000056670");
        tle3.put(EPHEMERIS_TYPE, "3");
        tle3.put(ELEMENT_SET_NUMBER, "4873");
        tle3.put(CHECKSUM_1, "3");
        tle3.put(INCLINATION, "69.9002");
        tle3.put(RAAN, "52.8454");
        tle3.put(ECCENTRICITY, ".0006133");
        tle3.put(ARGUMENT_OF_PERIGEE, "157.9681");
        tle3.put(MEAN_ANOMALY, "202.1740");
        tle3.put(MEAN_MOTION, "14.93240737");
        tle3.put(REVOLUTIONS, "97514");
        tle3.put(CHECKSUM_2, "6");
        TLEs.add(tle3);

        Map<TLEElement, String> tle4 = new EnumMap<>(TLEElement.class);
        tle4.put(TITLE, "ORSTED                  ");
        tle4.put(LINE_1, "1 25635U 99008B   89331.44324332  .00000046 -95738-5 -18414-4 4 32058");
        tle4.put(LINE_2, "2 25635  96.4728 278.0869 0139138 173.2587 187.0527 14.47945711936895");
        tle4.put(SATELLITE_NUMBER, "25635");
        tle4.put(CLASSIFICATION, "U");
        tle4.put(INTERNATIONAL_DESIGNATOR, "99008B");
        tle4.put(EPOCH_YEAR, "1989");
        tle4.put(EPOCH_DAY, "331.44324332");
        tle4.put(FIRST_DER, ".00000046");
        tle4.put(SECOND_DER, "-.0000095738");
        tle4.put(DRAG, "-.000018414");
        tle4.put(EPHEMERIS_TYPE, "4");
        tle4.put(ELEMENT_SET_NUMBER, "3205");
        tle4.put(CHECKSUM_1, "8");
        tle4.put(INCLINATION, "96.4728");
        tle4.put(RAAN, "278.0869");
        tle4.put(ECCENTRICITY, ".0139138");
        tle4.put(ARGUMENT_OF_PERIGEE, "173.2587");
        tle4.put(MEAN_ANOMALY, "187.0527");
        tle4.put(MEAN_MOTION, "14.47945711");
        tle4.put(REVOLUTIONS, "93689");
        tle4.put(CHECKSUM_2, "5");
        TLEs.add(tle4);
    }

    /**
     * Tests {@link TLEParser#TLEParser(String String, String)}, {@link TLEParser#getTitle()},
     * {@link TLEParser#getLine1()}, {@link TLEParser#getLine2()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getLines() throws Exception {
        String title, line1, line2;
        String titleExpected, line1Expected, line2Expected;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            titleExpected = tle.get(TITLE);
            line1Expected = tle.get(LINE_1);
            line2Expected = tle.get(LINE_2);

            parser = new TLEParser(titleExpected, line1Expected, line2Expected);
            title = parser.getTitle();
            line1 = parser.getLine1();
            line2 = parser.getLine2();

            // Check title
            if (!title.equals(titleExpected)) {
                Assert.fail(String.format(
                        "Title does not match expected string: expected='%s', received='%s'",
                        titleExpected, title));
            }

            // Check line 1
            if (!line1.equals(line1Expected)) {
                Assert.fail(String.format(
                        "Line 1 does not match expected string: expected='%s', received='%s'",
                        line1Expected, line1));
            }

            // Check line 2
            if (!line2.equals(line2Expected)) {
                Assert.fail(String.format(
                        "Line 2 does not match expected string: expected='%s', received='%s'",
                        line2Expected, line2));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getLineNumber1()}, {@link
     * TLEParser#getLineNumber2()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getLineNumbers() throws Exception {
        int lineNumber1, lineNumber2;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            lineNumber1 = parser.getLineNumber1();
            lineNumber2 = parser.getLineNumber2();

            // Check line number 1
            if (lineNumber1 != 1) {
                Assert.fail(String.format(
                        "Line number 1 does not match expected value: expected=1, received='%d' for '%s'",
                        lineNumber1, line1));
            }

            // Check line number 2
            if (lineNumber2 != 2) {
                Assert.fail(String.format(
                        "Line number 2 does not match expected value: expected=2, received='%d' for '%s'",
                        lineNumber2, line2));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getSatelliteNumber()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getSatelliteNumber() throws Exception {
        int satNumber, satNumberExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            satNumber = parser.getSatelliteNumber();
            satNumberExpected = Integer.parseInt(tle.get(SATELLITE_NUMBER));

            // Check satellite number
            if (satNumber != satNumberExpected) {
                Assert.fail(String.format(
                        "Satellite number does not match expected value: expected='%d', received='%d' for '%s'",
                        satNumberExpected, satNumber, line1));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getClassification()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getClassification() throws Exception {
        char classification, classificationExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            classification = parser.getClassification();
            classificationExpected = tle.get(CLASSIFICATION).charAt(0);

            // Check classification
            if (classification != classificationExpected) {
                Assert.fail(String.format(
                        "Classification character does not match expected string: expected='%s', received='%s' for '%s'",
                        classificationExpected, classification, line1));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#internationalDesignator}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getInternationalDesignator() throws Exception {
        String designator, designatorExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            designator = parser.getInternationalDesignator();
            designatorExpected = tle.get(INTERNATIONAL_DESIGNATOR);

            // Check international designator
            if (!designator.equals(designatorExpected)) {
                Assert.fail(String.format(
                        "International designator does not match expected string: expected='%s', received='%s' for '%s'",
                        designatorExpected, designator, line1));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#epochYear}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getEpochYear() throws Exception {
        int year, yearExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            year = parser.getEpochYear();
            yearExpected = Integer.parseInt(tle.get(EPOCH_YEAR));

            // Check epoch year
            if (year != yearExpected) {
                Assert.fail(String.format(
                        "Epoch year does not match expected value: expected='%d', received='%d' for '%s'",
                        yearExpected, year, line1));
            }
        }

    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#epochDay}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getEpochDay() throws Exception {
        double day, dayExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            day = parser.getEpochDay();
            dayExpected = Double.parseDouble(tle.get(EPOCH_DAY));

            // Check epoch day
            if (Double.compare(day, dayExpected) != 0) {
                Assert.fail(String.format(
                        "Epoch day does not match expected value: expected='%s', received='%s' for '%s'",
                        dayExpected, day, line1));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link
     * TLEParser#getFirstDerivativeOfMeanMotion()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getFirstDerivativeOfMeanMotion() throws Exception {
        double meanMotion, meanMotionExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            meanMotion = parser.getFirstDerivativeOfMeanMotion();
            meanMotionExpected = Double.parseDouble(tle.get(FIRST_DER));

            // Check first derivative of the mean motion
            if (Double.compare(meanMotion, meanMotionExpected) != 0) {
                Assert.fail(String.format(
                        "First derivative of the mean motion does not match expected value: expected='%s', received='%s' for '%s'",
                        meanMotionExpected, meanMotion, line1));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link
     * TLEParser#getSecondDerivativeOfMeanMotion()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getSecondDerivativeOfMeanMotion() throws Exception {
        double meanMotion, meanMotionExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            meanMotion = parser.getSecondDerivativeOfMeanMotion();
            meanMotionExpected = Double.parseDouble(tle.get(SECOND_DER));

            // Check second derivative of the mean motion
            if (Double.compare(meanMotion, meanMotionExpected) != 0) {
                Assert.fail(String.format(
                        "Second derivative of the mean motion does not match expected value: expected='%s', received='%s' for '%s'",
                        meanMotionExpected, meanMotion, line1));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getDragTerm()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getDragTerm() throws Exception {
        double dragTerm, dragTermExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            dragTerm = parser.getDragTerm();
            dragTermExpected = Double.parseDouble(tle.get(DRAG));

            // Check drag term
            if (Double.compare(dragTerm, dragTermExpected) != 0) {
                Assert.fail(String.format(
                        "BSTAR drag term does not match expected value: expected='%s', received='%s' for '%s'",
                        dragTermExpected, dragTerm, line1));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getEphemerisType()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getEphemerisType() throws Exception {
        int ephemerisType, ephemerisTypeExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            ephemerisType = parser.getEphemerisType();
            ephemerisTypeExpected = Integer.parseInt(tle.get(EPHEMERIS_TYPE));

            // Check ephemeris type
            if (ephemerisType != ephemerisTypeExpected) {
                Assert.fail(String.format(
                        "Ephemeris type does not match expected value: expected='%d', received='%d' for '%s'",
                        ephemerisTypeExpected, ephemerisType, line1));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getElementSetNumber()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getElementSetNumber() throws Exception {
        int elementSetNumber, elementSetNumberExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            elementSetNumber = parser.getElementSetNumber();
            elementSetNumberExpected = Integer.parseInt(tle.get(ELEMENT_SET_NUMBER));

            // Check element set number
            if (elementSetNumber != elementSetNumberExpected) {
                Assert.fail(String.format(
                        "Element set number does not match expected value: expected='%d', received='%d' for '%s'",
                        elementSetNumberExpected, elementSetNumber, line1));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getChecksumLine2()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getChecksumLine1() throws Exception {
        int checksum, checksumExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            checksum = parser.getChecksumLine1();
            checksumExpected = Integer.parseInt(tle.get(CHECKSUM_1));

            // Check checksum
            if (checksum != checksumExpected) {
                Assert.fail(String.format(
                        "Checksum does not match expected value: expected='%d', received='%d' for '%s'",
                        checksumExpected, checksum, line1));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getChecksumLine2()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getChecksumLine2() throws Exception {
        int checksum, checksumExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            checksum = parser.getChecksumLine2();
            checksumExpected = Integer.parseInt(tle.get(CHECKSUM_2));

            // Check checksum
            if (checksum != checksumExpected) {
                Assert.fail(String.format(
                        "Checksum does not match expected value: expected='%d', received='%d' for '%s'",
                        checksumExpected, checksum, line2));
            }
        }
    }
    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getInclination()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getInclination() throws Exception {
        double inclination, inclinationExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            inclination = parser.getInclination();
            inclinationExpected = Double.parseDouble(tle.get(INCLINATION));

            // Check inclination
            if (Double.compare(inclination, inclinationExpected) != 0) {
                Assert.fail(String.format(
                        "Inclination does not match expected value: expected='%s', received='%s' for '%s'",
                        inclinationExpected, inclination, line2));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getRaan()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getRaan() throws Exception {
        double raan, raanExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            raan = parser.getRaan();
            raanExpected = Double.parseDouble(tle.get(RAAN));

            // Check RAAN
            if (Double.compare(raan, raanExpected) != 0) {
                Assert.fail(String.format(
                        "RAAN does not match expected value: expected='%s', received='%s' for '%s'",
                        raanExpected, raan, line2));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getEccentricity()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getEccentricity() throws Exception {
        double eccentricity, eccentricityExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            eccentricity = parser.getEccentricity();
            eccentricityExpected = Double.parseDouble(tle.get(ECCENTRICITY));

            // Check eccentricity
            if (Double.compare(eccentricity, eccentricityExpected) != 0) {
                Assert.fail(String.format(
                        "Eccentricity does not match expected value: expected='%s', received='%s' for '%s'",
                        eccentricityExpected, eccentricity, line2));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getArgumentOfPerigee()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getArgumentOfPerigee() throws Exception {
        double argumentOfPerigee, argumentOfPerigeeExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            argumentOfPerigee = parser.getArgumentOfPerigee();
            argumentOfPerigeeExpected = Double.parseDouble(tle.get(ARGUMENT_OF_PERIGEE));

            // Check argument of perigee
            if (Double.compare(argumentOfPerigee, argumentOfPerigeeExpected) != 0) {
                Assert.fail(String.format(
                        "Argument of perigee does not match expected value: expected='%s', received='%s' for '%s'",
                        argumentOfPerigeeExpected, argumentOfPerigee, line2));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getMeanAnomaly()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getMeanAnomaly() throws Exception {
        double meanAnomaly, meanAnomalyExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            meanAnomaly = parser.getMeanAnomaly();
            meanAnomalyExpected = Double.parseDouble(tle.get(MEAN_ANOMALY));

            // Check mean anomaly
            if (Double.compare(meanAnomaly, meanAnomalyExpected) != 0) {
                Assert.fail(String.format(
                        "Mean anomaly does not match expected value: expected='%s', received='%s' for '%s'",
                        meanAnomalyExpected, meanAnomaly, line2));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getMeanMotion()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getMeanMotion() throws Exception {
        double meanMotion, meanMotionExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            meanMotion = parser.getMeanMotion();
            meanMotionExpected = Double.parseDouble(tle.get(MEAN_MOTION));

            // Check mean motion
            if (Double.compare(meanMotion, meanMotionExpected) != 0) {
                Assert.fail(String.format(
                        "Mean motion does not match expected value: expected='%s', received='%s' for '%s'",
                        meanMotionExpected, meanMotion, line2));
            }
        }
    }

    /**
     * Tests {@link TLEParser#TLEParser(String, String)}, {@link TLEParser#getRevolutions()}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getRevolutions() throws Exception {
        int revolutions, revolutionsExpected;
        String line1, line2;
        TLEParser parser;

        for (Map<TLEElement, String> tle : TLEs) {
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            parser = new TLEParser(line1, line2);
            revolutions = parser.getRevolutions();
            revolutionsExpected = Integer.parseInt(tle.get(REVOLUTIONS));

            // Check revolutions
            if (revolutions != revolutionsExpected) {
                Assert.fail(String.format(
                        "Revolutions number does not match expected value: expected='%d', received='%d' for '%s'",
                        revolutionsExpected, revolutions, line1));
            }
        }
    }

}