package com.zakrywilson.astro.tle;

import org.junit.Assert;
import org.junit.Test;

public class InternationalDesignatorTest {

    private InternationalDesignator id = new InternationalDesignator();

    @Test
    public void parse() throws Exception {
        String[] strings = {"98067AA ", "98067A  ", "98067AAA"};
        String[] targets = {"98067AA ", "98067A  ", "98067AAA"};
        for (int i = 0; i < strings.length; i++) {
            id.parse(strings[i]);
            if (!id.toString().equals(targets[i].trim())) {
                Assert.fail(
                        String.format("Failed to parse and produce correct toString international designator: " +
                                      "expected '%s', received '%s' for '%s'",
                                              targets[i], id.toString(), strings[i]));
            }
            if (!id.format().equals(targets[i])) {
                Assert.fail(
                        String.format("Failed to parse and produce formatted international designator: " +
                                      "expected '%s', received '%s' for '%s'",
                                              targets[i], id.format(), strings[i]));
            }
        }
    }

    @Test
    public void parseWholeLine() throws Exception {
        String[] strings = {"1 25544U 98067AA  08264.51782528 -.00002182  00000-0 -11606-4 0  2927",
                            "1 25692U 99020A   16307.17305942 -.00000181  00000-0 -30027-4 0  9999",
                            "1 39084U 13020AAA 16307.17302000  .00000099  00000-0  00000+0 0  9999"};
        String[] targets = {"98067AA ", "99020A  ", "13020AAA"};
        for (int i = 0; i < strings.length; i++) {
            id.parse(strings[i], true);
            if (!id.toString().equals(targets[i].trim())) {
                Assert.fail(
                        String.format("Failed to parse and produce correct toString international designator: " +
                                      "expected '%s', received '%s' for '%s'",
                                              targets[i], id.toString(), strings[i]));
            }
            if (!id.format().equals(targets[i])) {
                Assert.fail(
                        String.format("Failed to parse and produce formatted international designator: " +
                                      "expected '%s', received '%s' for '%s'",
                                              targets[i], id.format(), strings[i]));
            }
        }
    }

    @Test
    public void formatYear() throws Exception {
        int[] integers = {89, 98, 2};
        String[] strings = {"89", "98", "02"};
        for (int i = 0; i < integers.length; i++) {
            id.setYear(integers[i]);
            if (!id.formatYear().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid year: expected '%s', received '%s' for '%s'",
                                strings[i], id.formatYear(), integers[i]));
            }
        }
    }

    @Test
    public void setYear() throws Exception {
        String[] strings = {"83", "98", "02"};
        int[] integers = {83, 98, 2};
        for (int i = 0; i < strings.length; i++) {
            id.setYear(strings[i]);
            if (id.getYear() != integers[i]) {
                Assert.fail(
                        String.format("Invalid year: expected '%s', received '%s' for '%s'",
                                integers[i], id.getYear(), strings[i]));
            }
        }
    }

    @Test
    public void formatJulianDay() throws Exception {
        int[] integers = {344, 89, 5};
        String[] strings = {"344", "089", "005"};
        for (int i = 0; i < integers.length; i++) {
            id.setJulianDay(integers[i]);
            if (!id.formatJulianDay().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid Julian day: expected '%s', received '%s' for '%s'",
                                strings[i], id.formatJulianDay(), integers[i]));
            }
        }
    }

    @Test
    public void setJulianDay() throws Exception {
        String[] strings = {"344", "089", "005"};
        int[] integers = {344, 89, 5};
        for (int i = 0; i < strings.length; i++) {
            id.setJulianDay(strings[i]);
            if (id.getJulianDay() != integers[i]) {
                Assert.fail(
                        String.format("Invalid Julian day: expected '%s', received '%s' for '%s'",
                                integers[i], id.getJulianDay(), strings[i]));
            }
        }
    }

    @Test
    public void formatPieceOfLaunch() throws Exception {
        String[] strings = {"A", "AA", "AAA"};
        String[] targets = {"A  ", "AA ", "AAA"};
        for (int i = 0; i < strings.length; i++) {
            id.setPieceOfLaunch(strings[i]);
            if (!id.formatPieceOfLaunch().equals(targets[i])) {
                Assert.fail(
                        String.format("Invalid piece of launch: expected '%s', received '%s' for '%s'",
                                targets[i], id.formatPieceOfLaunch(), strings[i]));
            }
        }
    }

    @Test
    public void setPieceOfLaunch() throws Exception {
        String[] strings = {"A  ", "AA ", "AAA"};
        String[] targets = {"A", "AA", "AAA"};
        for (int i = 0; i < strings.length; i++) {
            id.setPieceOfLaunch(strings[i]);
            if (!id.getPieceOfLaunch().equals(targets[i])) {
                Assert.fail(
                        String.format("Invalid piece of launch: expected '%s', received '%s' for '%s'",
                                targets[i], id.getPieceOfLaunch(), strings[i]));
            }
        }
    }

}