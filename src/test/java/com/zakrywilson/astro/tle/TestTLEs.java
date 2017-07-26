package com.zakrywilson.astro.tle;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static com.zakrywilson.astro.tle.TLEElement.*;

/**
 * @author Zach Wilson
 */
class TestTLEs {

    private static final List<Map<TLEElement, String>> TLEs = new ArrayList<>();

    static {
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

        Map<TLEElement, String> tle5 = new EnumMap<>(TLEElement.class);
        tle5.put(TITLE, "0 WESTFORD NEEDLES");
        tle5.put(LINE_1, "1  2359U 63014J   17206.36072750 -.00000913 +00000-0 -33763+0 0  9995");
        tle5.put(LINE_2, "2  2359 087.1923 343.7678 0637449 064.2561 302.2681 08.68874989632466");
        tle5.put(SATELLITE_NUMBER, "2359");
        tle5.put(CLASSIFICATION, "U");
        tle5.put(INTERNATIONAL_DESIGNATOR, "63014J");
        tle5.put(EPOCH_YEAR, "2017");
        tle5.put(EPOCH_DAY, "206.36072750");
        tle5.put(FIRST_DER, "-.00000913");
        tle5.put(SECOND_DER, "0");
        tle5.put(DRAG, "-33763");
        tle5.put(EPHEMERIS_TYPE, "0");
        tle5.put(ELEMENT_SET_NUMBER, "999");
        tle5.put(CHECKSUM_1, "5");
        tle5.put(INCLINATION, "087.1923");
        tle5.put(RAAN, "343.7678");
        tle5.put(ECCENTRICITY, ".0637449");
        tle5.put(ARGUMENT_OF_PERIGEE, "064.2561");
        tle5.put(MEAN_ANOMALY, "302.2681");
        tle5.put(MEAN_MOTION, "08.68874989");
        tle5.put(REVOLUTIONS, "63246");
        tle5.put(CHECKSUM_2, "6");
        TLEs.add(tle5);

        Map<TLEElement, String> tle6 = new EnumMap<>(TLEElement.class);
        tle6.put(TITLE, "0 WESTFORD NEEDLES");
        tle6.put(LINE_1, "1  2359U 63014J   17206.36072750 -.00000913 +00000-0 +14901+0 0  9995");
        tle6.put(LINE_2, "2  2359 087.1923 343.7678 0637449 064.2561 302.2681 08.68874989632466");
        tle6.put(SATELLITE_NUMBER, "2359");
        tle6.put(CLASSIFICATION, "U");
        tle6.put(INTERNATIONAL_DESIGNATOR, "63014J");
        tle6.put(EPOCH_YEAR, "2017");
        tle6.put(EPOCH_DAY, "206.36072750");
        tle6.put(FIRST_DER, "-.00000913");
        tle6.put(SECOND_DER, "0");
        tle6.put(DRAG, "14901");
        tle6.put(EPHEMERIS_TYPE, "0");
        tle6.put(ELEMENT_SET_NUMBER, "999");
        tle6.put(CHECKSUM_1, "5");
        tle6.put(INCLINATION, "087.1923");
        tle6.put(RAAN, "343.7678");
        tle6.put(ECCENTRICITY, ".0637449");
        tle6.put(ARGUMENT_OF_PERIGEE, "064.2561");
        tle6.put(MEAN_ANOMALY, "302.2681");
        tle6.put(MEAN_MOTION, "08.68874989");
        tle6.put(REVOLUTIONS, "63246");
        tle6.put(CHECKSUM_2, "6");
        TLEs.add(tle6);
    }

    static List<Map<TLEElement, String>> getTles() {
        return TLEs;
    }

}
