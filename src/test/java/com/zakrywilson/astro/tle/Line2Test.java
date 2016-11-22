package com.zakrywilson.astro.tle;

import org.junit.Assert;
import org.junit.Test;

public class Line2Test {

    private Line2 line = new Line2();

    @Test
    public void parse() throws Exception {
        String[] strings = {"2 25544 051.6416 247.4627 0006703 130.5360 325.0288 15.72125391563537",
                            "2 39074 098.2125 012.6956 0001165 064.7410 056.5153  2.00568984 52836",
                            "2 49070 004.7803 328.9128 0011564 282.2115 077.4873  1.00291540 13843"};
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
    public void setInclination() throws Exception {
        String[] strings = {"097.2790", "005.9985", "100.4549", "051.6230"};
        double[] doubles = {97.2790, 5.9985, 100.4549, 051.6230};
        for (int i = 0; i < strings.length; i++) {
            line.setInclination(strings[i]);
            if (Double.compare(line.getInclination(), doubles[i]) != 0) {
                Assert.fail(
                        String.format("Invalid inclination: expected '%s', received '%s' for '%s'",
                                doubles[i], line.getInclination(), strings[i]));
            }
        }
    }

    @Test
    public void setRightAscension() throws Exception {
        String[] strings = {"097.2790", "005.9985", "100.4549", "051.6230"};
        double[] doubles = {97.2790, 5.9985, 100.4549, 051.6230};
        for (int i = 0; i < strings.length; i++) {
            line.setRightAscension(strings[i]);
            if (Double.compare(line.getRightAscension(), doubles[i]) != 0) {
                Assert.fail(
                        String.format("Invalid right ascension: expected '%s', received '%s' for '%s'",
                                doubles[i], line.getRightAscension(), strings[i]));
            }
        }
    }

    @Test
    public void setEccentricity() throws Exception {
        String[] strings = {"0007332", "0009135", "0054812", "0001039"};
        double[] doubles = {.0007332, .0009135, .0054812, .0001039};
        for (int i = 0; i < strings.length; i++) {
            line.setEccentricity(strings[i]);
            if (Double.compare(line.getEccentricity(), doubles[i]) != 0) {
                Assert.fail(
                        String.format("Invalid eccentricity: expected '%s', received '%s' for '%s'",
                                doubles[i], line.getEccentricity(), strings[i]));
            }
        }
    }

    @Test
    public void setArgumentOfPerigee() throws Exception {
        String[] strings = {"097.2790", "005.9985", "100.4549", "051.6230"};
        double[] doubles = {97.2790, 5.9985, 100.4549, 051.6230};
        for (int i = 0; i < strings.length; i++) {
            line.setArgumentOfPerigee(strings[i]);
            if (Double.compare(line.getArgumentOfPerigee(), doubles[i]) != 0) {
                Assert.fail(
                        String.format("Invalid argument of perigee: expected '%s', received '%s' for '%s'",
                                doubles[i], line.getArgumentOfPerigee(), strings[i]));
            }
        }
    }

    @Test
    public void setMeanAnomaly() throws Exception {
        String[] strings = {"097.2790", "005.9985", "100.4549", "051.6230"};
        double[] doubles = {97.2790, 5.9985, 100.4549, 051.6230};
        for (int i = 0; i < strings.length; i++) {
            line.setMeanAnomaly(strings[i]);
            if (Double.compare(line.getMeanAnomaly(), doubles[i]) != 0) {
                Assert.fail(
                        String.format("Invalid mean anomaly: expected '%s', received '%s' for '%s'",
                                doubles[i], line.getMeanAnomaly(), strings[i]));
            }
        }
    }

    @Test
    public void setMeanMotion() throws Exception {
        String[] strings = {"14.30827640", " 2.00563455", "15.35621714", "16.00430493"};
        double[] doubles = {14.30827640,  2.00563455, 15.35621714, 16.00430493};
        for (int i = 0; i < strings.length; i++) {
            line.setMeanMotion(strings[i]);
            if (Double.compare(line.getMeanMotion(), doubles[i]) != 0) {
                Assert.fail(
                        String.format("Invalid mean motion: expected '%s', received '%s' for '%s'",
                                doubles[i], line.getMeanMotion(), strings[i]));
            }
        }
    }

    @Test
    public void setRevolutions() throws Exception {
        String[] strings = {" 2789", "99999", " 7389", "73921"};
        int[] integers = {2789, 99999, 7389, 73921};
        for (int i = 0; i < strings.length; i++) {
            line.setRevolutions(strings[i]);
            if (line.getRevolutions() != integers[i]) {
                Assert.fail(
                        String.format("Invalid revolutions number: expected '%s', received '%s' for '%s'",
                                integers[i], line.getRevolutions(), strings[i]));
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
    public void formatInclination() throws Exception {
        double[] doubles = {170.5930, 300.9454, 98.0936};
        String[] strings = {"170.5930", "300.9454", "098.0936"};
        for (int i = 0; i < doubles.length; i++) {
            line.setInclination(doubles[i]);
            if (!line.formatInclination().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid inclination: expected '%s', received '%s' for '%s'",
                                strings[i], line.formatInclination(), doubles[i]));
            }
        }
    }

    @Test
    public void formatRightAscension() throws Exception {
        double[] doubles = {170.5930, 300.9454, 98.0936};
        String[] strings = {"170.5930", "300.9454", "098.0936"};
        for (int i = 0; i < doubles.length; i++) {
            line.setRightAscension(doubles[i]);
            if (!line.formatRightAscension().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid right ascension: expected '%s', received '%s' for '%s'",
                                strings[i], line.formatRightAscension(), doubles[i]));
            }
        }
    }

    @Test
    public void formatEccentricity() throws Exception {
        double[] doubles = {0.0000799, 0.0150745, 0.0001151};
        String[] strings = {"0000799", "0150745", "0001151"};
        for (int i = 0; i < doubles.length; i++) {
            line.setEccentricity(doubles[i]);
            if (!line.formatEccentricity().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid eccentricity: expected '%s', received '%s' for '%s'",
                                strings[i], line.formatEccentricity(), doubles[i]));
            }
        }
    }

    @Test
    public void formatArgumentOfPerigee() throws Exception {
        double[] doubles = {170.5930, 300.9454, 98.0936};
        String[] strings = {"170.5930", "300.9454", "098.0936"};
        for (int i = 0; i < doubles.length; i++) {
            line.setArgumentOfPerigee(doubles[i]);
            if (!line.formatArgumentOfPerigee().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid argument of perigee: expected '%s', received '%s' for '%s'",
                                strings[i], line.formatArgumentOfPerigee(), doubles[i]));
            }
        }
    }

    @Test
    public void formatMeanAnomaly() throws Exception {
        double[] doubles = {170.5930, 300.9454, 98.0936};
        String[] strings = {"170.5930", "300.9454", "098.0936"};
        for (int i = 0; i < doubles.length; i++) {
            line.setMeanAnomaly(doubles[i]);
            if (!line.formatMeanAnomaly().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid mean anomaly: expected '%s', received '%s' for '%s'",
                                strings[i], line.formatMeanAnomaly(), doubles[i]));
            }
        }
    }

    @Test
    public void formatMeanMotion() throws Exception {
        double[] doubles = {14.75988185,  2.00568984,  2.0056898};
        String[] strings = {"14.75988185", " 2.00568984", " 2.00568980"};
        for (int i = 0; i < doubles.length; i++) {
            line.setMeanMotion(doubles[i]);
            if (!line.formatMeanMotion().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid mean motion: expected '%s', received '%s' for '%s'",
                                strings[i], line.formatMeanMotion(), doubles[i]));
            }
        }
    }

    @Test
    public void formatRevolutionNumber() throws Exception {
        int[] integers = {12429, 13337, 9270, 9018};
        String[] strings = {"12429", "13337", " 9270", " 9018"};
        for (int i = 0; i < integers.length; i++) {
            line.setRevolutions(integers[i]);
            if (!line.formatRevolutionNumber().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid revolutions: expected '%s', received '%s' for '%s'",
                                strings[i], line.formatRevolutionNumber(), integers[i]));
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
    public void getLineNumber() throws Exception {
        if (line.getLineNumber() != 2) {
            Assert.fail("Line number must be 2: " + line.getLineNumber());
        }
    }

}