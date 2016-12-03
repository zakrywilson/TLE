package com.zakrywilson.astro.tle;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static com.zakrywilson.astro.tle.TLETest.TLEElement.*;

/**
 * Tests {@link TLE}.
 *
 * @author Zach Wilson
 */
public class TLETest {

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
        String[][] lines = {{"1 25994U 99068A   16337.32942296  .00000132  00000-0  39248-4 0  9992",  // Invalid
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
                             "2 36119  97.4689 358.2537 0001360 322.5335  37.5809 15.27785063386116"}}; // Valid
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
        String[][] lines = {{"1 25994U 99068A   16337.32942296  .00000132  00000-0  39248-4 0  9992",  // Invalid
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
                             "2 36119  97.4689 358.2537 0001360 322.5335  37.5809 15.27785063386116"}}; // Valid
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