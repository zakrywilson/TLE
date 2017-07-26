package com.zakrywilson.astro.tle;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.zakrywilson.astro.tle.TLEElement.*;

/**
 * Tests {@link TLE}.
 *
 * @author Zach Wilson
 */
public class TLETest {

    /**
     * List of hash maps where each hash map represents all elements to a given TLE.
     */
    private static List<Map<TLEElement, String>> TLEs = TestTLEs.getTles();

    /**
     * Tests {@link TLE#TLE(String, String)} and {@link TLE#TLE(String, String, String)} and all
     * other methods.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void test() throws Exception {
        String title;
        String line1;
        String line2;
        int satelliteNumber;
        char classification;
        String internationalDesignator;
        int epochYear;
        double epochDay;
        double firstDerMM;
        double sndDerMM;
        double dragTerm;
        int ephemerisType;
        int elementSetNumber;
        int checksumLine1;
        double inclination;
        double raan;
        double eccentricity;
        double argumentOfPerigee;
        double meanAnomaly;
        double meanMotion;
        int revolutions;
        int checksumLine2;

        for (Map<TLEElement, String> tle : TLEs) {
            title = tle.get(TITLE);
            line1 = tle.get(LINE_1);
            line2 = tle.get(LINE_2);
            satelliteNumber = Integer.parseInt(tle.get(SATELLITE_NUMBER));
            classification = tle.get(CLASSIFICATION).charAt(0);
            internationalDesignator = tle.get(INTERNATIONAL_DESIGNATOR);
            epochYear = Integer.parseInt(tle.get(EPOCH_YEAR));
            epochDay = Double.parseDouble(tle.get(EPOCH_DAY));
            firstDerMM = Double.parseDouble(tle.get(FIRST_DER));
            sndDerMM = Double.parseDouble(tle.get(SECOND_DER));
            dragTerm = Double.parseDouble(tle.get(DRAG));
            ephemerisType = Integer.parseInt(tle.get(EPHEMERIS_TYPE));
            elementSetNumber = Integer.parseInt(tle.get(ELEMENT_SET_NUMBER));
            checksumLine1 = Integer.parseInt(tle.get(CHECKSUM_1));
            inclination = Double.parseDouble(tle.get(INCLINATION));
            raan = Double.parseDouble(tle.get(RAAN));
            eccentricity = Double.parseDouble(tle.get(ECCENTRICITY));
            argumentOfPerigee = Double.parseDouble(tle.get(ARGUMENT_OF_PERIGEE));
            meanAnomaly = Double.parseDouble(tle.get(MEAN_ANOMALY));
            meanMotion = Double.parseDouble(tle.get(MEAN_MOTION));
            revolutions = Integer.parseInt(tle.get(REVOLUTIONS));
            checksumLine2 = Integer.parseInt(tle.get(CHECKSUM_2));

            TLE t = new TLE(title, line1, line2);

            if (!t.getTitle().equals(title)) {
                Assert.fail(String.format("Invalid title: expected='%s', received='%s'", title,
                                          t.getTitle()));
            }
            if (!t.getLine1().equals(line1)) {
                Assert.fail(String.format("Invalid line 1: expected='%s', received='%s'", line1,
                                          t.getLine1()));
            }
            if (!t.getLine2().equals(line2)) {
                Assert.fail(String.format("Invalid line 2: expected='%s', received='%s'", line2,
                                          t.getLine2()));
            }
            if (t.getSatelliteNumber() != satelliteNumber) {
                Assert.fail(String.format(
                        "Invalid satellite number: expected='%d', received='%d' from line='%s'",
                        satelliteNumber, t.getSatelliteNumber(), line1));
            }
            if (t.getClassification() != classification) {
                Assert.fail(String.format("Invalid classification: expected='%c', received='%c'",
                                          classification, t.getClassification()));
            }
            if (!t.getInternationalDesignator().equals(internationalDesignator)) {
                Assert.fail(String.format(
                        "Invalid international designator: expected='%s', received='%s' from line='%s'",
                        internationalDesignator, t.getInternationalDesignator(), line1));
            }
            if (t.getEpochYear() != epochYear) {
                Assert.fail(String.format(
                        "Invalid epoch year: expected='%d', received='%d' from line='%s'",
                        epochYear, t.getEpochYear(), line1));
            }
            if (Double.compare(t.getEpochDay(), epochDay) != 0) {
                Assert.fail(String.format(
                        "Invalid epoch day: expected='%s', received='%s' from line='%s'", epochDay,
                        t.getEpochDay(), line1));
            }
            if (Double.compare(t.getFirstDerivativeOfMeanMotion(), firstDerMM) != 0) {
                Assert.fail(String.format(
                        "Invalid first derivative mean motion: expected='%s', received='%s' from line='%s'",
                        firstDerMM, t.getFirstDerivativeOfMeanMotion(), line1));
            }
            if (Double.compare(t.getSecondDerivativeOfMeanMotion(), sndDerMM) != 0) {
                Assert.fail(String.format(
                        "Invalid second derivative mean motion: expected='%s', received='%s' from line='%s'",
                        sndDerMM, t.getSecondDerivativeOfMeanMotion(), line1));
            }
            if (Double.compare(t.getDragTerm(), dragTerm) != 0) {
                Assert.fail(String.format(
                        "Invalid drag term: expected='%s', received='%s' from line='%s'", dragTerm,
                        t.getDragTerm(), line1));
            }
            if (t.getEphemerisType() != ephemerisType) {
                Assert.fail(String.format(
                        "Invalid ephemeris type: expected='%d', received='%d' from line='%s'",
                        ephemerisType, t.getEphemerisType(), line1));
            }
            if (t.getElementSetNumber() != elementSetNumber) {
                Assert.fail(String.format(
                        "Invalid element set number: expected='%d', received='%d' from line='%s'",
                        elementSetNumber, t.getElementSetNumber(), line1));
            }
            if (t.getChecksumLine1() != checksumLine1) {
                Assert.fail(String.format(
                        "Invalid checksum line 1: expected='%d', received='%d' from line='%s'",
                        checksumLine1, t.getChecksumLine1(), line1));
            }
            if (Double.compare(t.getInclination(), inclination) != 0) {
                Assert.fail(String.format(
                        "Invalid inclination: expected='%s', received='%s' from line='%s'",
                        inclination, t.getInclination(), line2));
            }
            if (Double.compare(t.getRaan(), raan) != 0) {
                Assert.fail(
                        String.format("Invalid RAAN: expected='%s', received='%s' from line='%s'",
                                      raan, t.getRaan(), line2));
            }
            if (Double.compare(t.getEccentricity(), eccentricity) != 0) {
                Assert.fail(String.format(
                        "Invalid eccentricity: expected='%s', received='%s' from line='%s'",
                        eccentricity, t.getEccentricity(), line2));
            }
            if (Double.compare(t.getArgumentOfPerigee(), argumentOfPerigee) != 0) {
                Assert.fail(String.format(
                        "Invalid argument of perigee: expected='%s', received='%s' from line='%s'",
                        argumentOfPerigee, t.getEccentricity(), line2));
            }
            if (Double.compare(t.getMeanAnomaly(), meanAnomaly) != 0) {
                Assert.fail(String.format(
                        "Invalid mean anomaly: expected='%s', received='%s' from line='%s'",
                        meanAnomaly, t.getMeanAnomaly(), line2));
            }
            if (Double.compare(t.getMeanMotion(), meanMotion) != 0) {
                Assert.fail(String.format(
                        "Invalid mean motion: expected='%s', received='%s' from line='%s'",
                        meanMotion, t.getMeanMotion(), line2));
            }
            if (t.getRevolutions() != revolutions) {
                Assert.fail(String.format(
                        "Invalid revolutions: expected='%d', received='%d' from line='%s'",
                        revolutions, t.getRevolutions(), line2));
            }
            if (t.getChecksumLine2() != checksumLine2) {
                Assert.fail(String.format(
                        "Invalid checksum line 2: expected='%d', received='%d' from line='%s'",
                        checksumLine2, t.getChecksumLine2(), line2));
            }
        }
    }

    /**
     * Tests {@link TLE#isLine1Valid()}.
     */
    @Test
    public void isLine1Valid() {
        String[][] lines = {
                {"1 25994U 99068A   16337.32942296  .00000132  00000-0  39248-4 0  9992",  // Invalid
                 "2 25994  98.2080  49.1903 0000727  54.9890 305.1442 14.57110838902003"}, // Invalid

                {"1 26410U 00041A   16338.87776743  .00000588  00000-0  00000+0 0  9998",  // Valid
                 "2 26410 131.1327 331.0973 4738339 146.7532   0.1456  0.44222439 16636"}, // Valid

                {"1 26410U 00041A   16338.87776743  .00000588  00000-0  00000+0 0  9992",  // Invalid
                 "2 26410 131.1327 331.0973 4738339 146.7532   0.1456  0.44222439 16630"}, // Invalid

                {"1 31304U 07015A   16337.52075914  .00001467  00000-0  90550-4 0  9998",  // Valid
                 "2 31304  97.9852 141.4660 0006700  87.2971 272.9025 15.11616742525229"}, // Valid

                {"1 33401U 08051A   16340.82713322 -.00001587  00000-0  00000+0 0  9996",  // Invalid
                 "2 33401  46.3131  13.6335 4726500 201.2194   1.6370  0.11259557  3432"}, // Invalid

                {"1 36119U 09071A   16336.66311204  .00001257  00000-0  49093-4 0  9993",   // Valid
                 "2 36119  97.4689 358.2537 0001360 322.5335  37.5809 15.27785063386116"}   // Valid
        };
        String line1, line2;
        boolean expected, received;
        TLE tle;
        for (int i = 0; i < lines.length; i++) {
            line1 = lines[i][0];
            line2 = lines[i][1];
            tle = new TLE(line1, line2);
            expected = (i % 2) == 1;
            received = tle.isLine1Valid();
            if (received != expected) {
                Assert.fail(String.format("Expected boolean '%s' but received '%s' for line 1: %s",
                                          expected, received, line1));
            }
        }
    }

    /**
     * Tests {@link TLE#isLine2Valid()}.
     */
    @Test
    public void isLine2Valid() {
        String[][] lines = {
                {"1 25994U 99068A   16337.32942296  .00000132  00000-0  39248-4 0  9992",  // Invalid
                 "2 25994  98.2080  49.1903 0000727  54.9890 305.1442 14.57110838902008"}, // Invalid

                {"1 26410U 00041A   16338.87776743  .00000588  00000-0  00000+0 0  9998",  // Valid
                 "2 26410 131.1327 331.0973 4738339 146.7532   0.1456  0.44222439 16636"}, // Valid

                {"1 26410U 00041A   16338.87776743  .00000588  00000-0  00000+0 0  9992",  // Invalid
                 "2 26410 131.1327 331.0973 4738339 146.7532   0.1456  0.44222439 16630"}, // Invalid

                {"1 31304U 07015A   16337.52075914  .00001467  00000-0  90550-4 0  9998",  // Valid
                 "2 31304  97.9852 141.4660 0006700  87.2971 272.9025 15.11616742525229"}, // Valid

                {"1 33401U 08051A   16340.82713322 -.00001587  00000-0  00000+0 0  9996",  // Invalid
                 "2 33401  46.3131  13.6335 4726500 201.2194   1.6370  0.11259557  3432"}, // Invalid

                {"1 36119U 09071A   16336.66311204  .00001257  00000-0  49093-4 0  9993",   // Valid
                 "2 36119  97.4689 358.2537 0001360 322.5335  37.5809 15.27785063386116"}   // Valid
        };
        String line1, line2;
        boolean expected, received;
        TLE tle;
        for (int i = 0; i < lines.length; i++) {
            line1 = lines[i][0];
            line2 = lines[i][1];
            tle = new TLE(line1, line2);
            expected = (i % 2) == 1;
            received = tle.isLine2Valid();
            if (received != expected) {
                Assert.fail(String.format("Expected boolean '%s' but received '%s' for line 2: %s",
                                          expected, received, line2));
            }
        }
    }

}