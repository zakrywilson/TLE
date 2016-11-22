package com.zakrywilson.astro.tle;

import org.junit.Assert;
import org.junit.Test;

public class Line1Test {

    private Line1 line = new Line1();

    @Test
    public void parse() throws Exception {
        String[] strings = {"1 25544U 98067A   08264.51782528 -.00002182  00000-0 -11606-4 0  2927",
                            "1 25692U 99020A   16307.17305942 -.00000181  00000-0 -30027-4 0  9999",
                            "1 39084U 13020A   16307.17302000  .00000099  00000-0  00000+0 0  9999"};
        for (String s : strings) {
            line.parse(s);
            if (!line.format().equals(s)) {
                Assert.fail(
                        String.format("Failed to parse and format line: expected '%s', received '%s'",
                                s, line.format()));
            }
        }
    }

    @Test
    public void formatSatelliteNumber() throws Exception {
        int[] numbers = {25544, 33, 1, 994};
        String[] strings = {"25544", "   33", "    1", "  994"};
        for (int i = 0; i < numbers.length; i++) {
            line.setSatelliteNumber(numbers[i]);
            if (!line.formatSatelliteNumber().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid satellite number: expected '%s', received '%s' for '%d'",
                                strings[i], line.formatSatelliteNumber(), numbers[i]));
            }
        }
    }

    @Test
    public void setSatelliteNumber() throws Exception {
        String[] strings = {"25544", "   33", "    1", "  994"};
        int[] numbers = {25544, 33, 1, 994};
        for (int i = 0; i < strings.length; i++) {
            line.setSatelliteNumber(strings[i]);
            if (line.getSatelliteNumber() != numbers[i]) {
                Assert.fail(
                        String.format("Invalid satellite number: expected '%d', received '%d' for '%s'",
                                numbers[i], line.getSatelliteNumber(), strings[i]));
            }
        }
    }

    @Test
    public void formatClassification() throws Exception {
        String[] strings = {"U  ", " U ", "  U", "C  ", "TS "};
        String[] targets = {"U  ", "U  ", "U  ", "C  ", "TS "};
        for (int i = 0; i < strings.length; i++) {
            line.setClassification(strings[i]);
            if (!line.formatClassification().equals(targets[i])) {
                Assert.fail(
                        String.format("Invalid classification: expected '%s', received '%s' for '%s'",
                                targets[i], line.getClassification(), strings[i]));
            }
        }
    }

    @Test
    public void setClassification() throws Exception {
        String[] strings = {"U  ", " U ", "  U", "C  ", "TS "};
        String[] targets = {"U", "U", "U", "C", "TS"};
        for (int i = 0; i < strings.length; i++) {
            line.setClassification(strings[i]);
            if (!line.getClassification().equals(targets[i])) {
                Assert.fail(
                        String.format("Invalid classification: expected '%s', received '%s' for '%s'",
                                targets[i], line.getClassification(), strings[i]));
            }
        }
    }

    @Test
    public void formatMeanMotionFirstDerivative() throws Exception {
        double[] doubles = {-.00002182, -.00000097, .00000081, .00000367};
        String[] strings = {"-.00002182", "-.00000097", " .00000081", " .00000367"};
        for (int i = 0; i < doubles.length; i++) {
            line.setMeanMotionFirstDerivative(doubles[i]);
            if (!line.formatMeanMotionFirstDerivative().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid mean motion (first derivative): expected '%s', received '%s' for '%s'",
                                strings[i], line.formatMeanMotionFirstDerivative(), doubles[i]));
            }
        }
    }

    @Test
    public void setMeanMotionFirstDerivative() throws Exception {
        String[] strings = {"-.00002182", "-.00000097", " .00000081", ".00000367"};
        double[] doubles = {-.00002182, -.00000097, .00000081, .00000367};
        for (int i = 0; i < strings.length; i++) {
            line.setMeanMotionFirstDerivative(strings[i]);
            if (Double.compare(line.getMeanMotionFirstDerivative(), doubles[i]) != 0) {
                Assert.fail(
                        String.format("Invalid mean motion (first derivative): expected '%s', received '%s' for '%s'",
                                doubles[i], line.getMeanMotionFirstDerivative(), strings[i]));
            }
        }
    }

    @Test
    public void formatMeanMotionSecondDerivative() throws Exception {
        double[] doubles = {.000021821, -.000021821, 0.0000012345, -0.0000012345, 0.0};
        String[] strings = {" 21821-4", "-21821-4", " 12345-5", "-12345-5", " 00000-0"};
        for (int i = 0; i < doubles.length; i++) {
            line.setMeanMotionSecondDerivative(doubles[i]);
            if (!line.formatMeanMotionSecondDerivative().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid mean motion (second derivative): expected '%s', received '%s' for '%s'",
                                strings[i], line.formatMeanMotionSecondDerivative(), doubles[i]));
            }
        }
    }

    @Test
    public void setMeanMotionSecondDerivative() throws Exception {
        String[] strings = {" 21821-4", "-21821-4", " 12345-5", "-12345-5", " 00000-0"};
        double[] doubles = {.000021821, -.000021821, 0.0000012345, -0.0000012345, 0.0};
        for (int i = 0; i < strings.length; i++) {
            line.setMeanMotionSecondDerivative(strings[i]);
            if (Double.compare(line.getMeanMotionSecondDerivative(), doubles[i]) != 0) {
                Assert.fail(
                        String.format("Invalid mean motion (second derivative): expected '%s', received '%s' for '%s'",
                                doubles[i], line.getMeanMotionSecondDerivative(), strings[i]));
            }
        }
    }

    @Test
    public void formatDragTerm() throws Exception {
        double[] doubles = {.000021821, -.000021821, 0.0000012345, -0.0000012345, 0.0};
        String[] strings = {" 21821-4", "-21821-4", " 12345-5", "-12345-5", " 00000+0"};
        for (int i = 0; i < doubles.length; i++) {
            line.setDragTerm(doubles[i]);
            if (!line.formatDragTerm().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid drag term: expected '%s', received '%s' for '%s'",
                                strings[i], line.formatDragTerm(), doubles[i]));
            }
        }
    }

    @Test
    public void setDragTerm() throws Exception {
        String[] strings = {" 21821-4", "-21821-4", " 12345-5", "-12345-5", " 00000+0"};
        double[] doubles = {.000021821, -.000021821, 0.0000012345, -0.0000012345, 0.0};
        for (int i = 0; i < strings.length; i++) {
            line.setDragTerm(strings[i]);
            if (Double.compare(line.getDragTerm(), doubles[i]) != 0) {
                Assert.fail(
                        String.format("Invalid drag term: expected '%s', received '%s' for '%s'",
                                doubles[i], line.getDragTerm(), strings[i]));
            }
        }
    }

    @Test
    public void formatEphemerisType() throws Exception {
        int[] integers = {0, 1, 2, 3};
        String[] strings = {"0", "1", "2", "3"};
        for (int i = 0; i < integers.length; i++) {
            line.setEphemerisType(integers[i]);
            if (!line.formatEphemerisType().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid ephemeris type: expected '%s', received '%s' for '%s'",
                                strings[i], line.formatEphemerisType(), integers[i]));
            }
        }
    }

    @Test
    public void setEphemerisType() throws Exception {
        String[] strings = {"0", "1", "2", "3"};
        int[] integers = {0, 1, 2, 3};
        for (int i = 0; i < strings.length; i++) {
            line.setEphemerisType(strings[i]);
            if (line.getEphemerisType() != integers[i]) {
                Assert.fail(
                        String.format("Invalid ephemeris type: expected '%s', received '%s' for '%s'",
                                integers[i], line.getEphemerisType(), strings[i]));
            }
        }
    }

    @Test
    public void formatElementSetNumber() throws Exception {
        int[] integers = {292, 4444, 20, 3};
        String[] strings = {" 292", "4444", "  20", "   3"};
        for (int i = 0; i < integers.length; i++) {
            line.setElementSetNumber(integers[i]);
            if (!line.formatElementSetNumber().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid element set number: expected '%s', received '%s' for '%s'",
                                strings[i], line.formatEphemerisType(), integers[i]));
            }
        }
    }

    @Test
    public void setElementSetNumber() throws Exception {
        String[] strings = {" 292", "4444", "  20", "   3"};
        int[] integers = {292, 4444, 20, 3};
        for (int i = 0; i < strings.length; i++) {
            line.setElementSetNumber(strings[i]);
            if (line.getElementSetNumber() != integers[i]) {
                Assert.fail(
                        String.format("Invalid element set number: expected '%s', received '%s' for '%s'",
                                integers[i], line.getEphemerisType(), strings[i]));
            }
        }
    }

    @Test
    public void formatChecksum() throws Exception {
        int[] integers = {0, 1, 2, 3};
        String[] strings = {"0", "1", "2", "3"};
        for (int i = 0; i < integers.length; i++) {
            line.setChecksum(integers[i]);
            if (!line.formatChecksum().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid checksum: expected '%s', received '%s' for '%s'",
                                strings[i], line.formatChecksum(), integers[i]));
            }
        }
    }

    @Test
    public void setChecksum() throws Exception {
        String[] strings = {"0", "1", "2", "3"};
        int[] integers = {0, 1, 2, 3};
        for (int i = 0; i < strings.length; i++) {
            line.setChecksum(strings[i]);
            if (line.getChecksum() != integers[i]) {
                Assert.fail(
                        String.format("Invalid checksum: expected '%s', received '%s' for '%s'",
                                integers[i], line.getChecksum(), strings[i]));
            }
        }
    }

    @Test
    public void getLineNumber() throws Exception {
        if (line.getLineNumber() != 1) {
            Assert.fail("Line number must be 1: " + line.getLineNumber());
        }
    }

}