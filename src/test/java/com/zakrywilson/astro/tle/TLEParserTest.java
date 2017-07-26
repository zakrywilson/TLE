package com.zakrywilson.astro.tle;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.zakrywilson.astro.tle.TLEElement.*;

/**
 * Tests {@link TLEParser}.
 *
 * @author Zach Wilson
 */
public class TLEParserTest {

    /**
     * List of hash maps where each hash map represents all elements to a given TLE.
     */
    private static final List<Map<TLEElement, String>> TLEs = TestTLEs.getTles();

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
                        "Line number 1 does not match expected value:" +
                                "expected=1, received='%d' for '%s'",
                        lineNumber1, line1));
            }

            // Check line number 2
            if (lineNumber2 != 2) {
                Assert.fail(String.format(
                        "Line number 2 does not match expected value:" +
                                "expected=2, received='%d' for '%s'",
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
                        "Satellite number does not match expected value:" +
                                "expected='%d', received='%d' for '%s'",
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
                        "Classification character does not match expected string:" +
                                "expected='%s', received='%s' for '%s'",
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
                        "International designator does not match expected string:" +
                                "expected='%s', received='%s' for '%s'",
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
                        "Epoch year does not match expected value:" +
                                "expected='%d', received='%d' for '%s'",
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
                        "Epoch day does not match expected value:" +
                                "expected='%s', received='%s' for '%s'",
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
                        "First derivative of the mean motion does not match expected value:" +
                                "expected='%s', received='%s' for '%s'",
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
                        "Second derivative of the mean motion does not match expected value:" +
                                "expected='%s', received='%s' for '%s'",
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
                        "BSTAR drag term does not match expected value:" +
                                "expected='%s', received='%s' for '%s'",
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
                        "Ephemeris type does not match expected value:" +
                                "expected='%d', received='%d' for '%s'",
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
                        "Element set number does not match expected value:" +
                                "expected='%d', received='%d' for '%s'",
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
                        "Checksum does not match expected value:" +
                                "expected='%d', received='%d' for '%s'",
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
                        "Checksum does not match expected value:" +
                                "expected='%d', received='%d' for '%s'",
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
                        "Inclination does not match expected value:" +
                                "expected='%s', received='%s' for '%s'",
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
                        "Eccentricity does not match expected value:" +
                                "expected='%s', received='%s' for '%s'",
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
                        "Argument of perigee does not match expected value:" +
                                "expected='%s', received='%s' for '%s'",
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
                        "Mean anomaly does not match expected value:" +
                                "expected='%s', received='%s' for '%s'",
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
                        "Mean motion does not match expected value:" +
                                "expected='%s', received='%s' for '%s'",
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
                        "Revolutions number does not match expected value:" +
                                "expected='%d', received='%d' for '%s'",
                        revolutionsExpected, revolutions, line1));
            }
        }
    }

}