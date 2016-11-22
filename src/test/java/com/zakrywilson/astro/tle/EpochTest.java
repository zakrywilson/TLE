package com.zakrywilson.astro.tle;

import org.junit.Assert;
import org.junit.Test;

public class EpochTest {

    private Epoch epoch = new Epoch();

    @Test
    public void parse() throws Exception {
        String[] strings = {"08264.51782528", "16126.50000000", "16307.08394503"};
        for (String s : strings) {
            epoch.parse(s);
            if (!epoch.toString().equals(s)) {
                Assert.fail(
                        String.format("Failed to parse and format epoch: expected '%s', received '%s'",
                                s, epoch));
            }
        }
    }

    @Test
    public void parseWholeLine() throws Exception {
        String[] strings = {"1 25544U 98067A   08264.51782528 -.00002182  00000-0 -11606-4 0  2927",
                            "1 25692U 99020A   16307.17305942 -.00000181  00000-0 -30027-4 0  9999",
                            "1 39084U 13020A   16307.17302000  .00000099  00000-0  00000+0 0  9999"};
        String[] targets = {"08264.51782528", "16307.17305942", "16307.17302000"};
        for (int i = 0; i < strings.length; i++) {
            epoch.parse(strings[i], true);
            if (!epoch.toString().equals(targets[i])) {
                Assert.fail(
                        String.format("Failed to parse and format epoch:" +
                                        "expected '%s', received '%s' for '%s'",
                                targets[i], epoch, strings[i]));
            }
        }
    }

    @Test
    public void formatYear() throws Exception {
        int[] integers = {89, 98, 2};
        String[] strings = {"89", "98", "02"};
        for (int i = 0; i < integers.length; i++) {
            epoch.setYear(integers[i]);
            if (!epoch.formatYear().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid year: expected '%s', received '%s' for '%s'",
                                strings[i], epoch.formatYear(), integers[i]));
            }
        }
    }

    @Test
    public void setYear() throws Exception {
        String[] strings = {"83", "98", "02"};
        int[] integers = {83, 98, 2};
        for (int i = 0; i < strings.length; i++) {
            epoch.setYear(strings[i]);
            if (epoch.getYear() != integers[i]) {
                Assert.fail(
                        String.format("Invalid year: expected '%s', received '%s' for '%s'",
                                integers[i], epoch.getYear(), strings[i]));
            }
        }
    }

    @Test
    public void formatJulianDay() throws Exception {
        double[] doubles = {344.12345678, 89.98346734, 5.84849840};
        String[] strings = {"344.12345678", "089.98346734", "005.84849840"};
        for (int i = 0; i < doubles.length; i++) {
            epoch.setJulianDay(doubles[i]);
            if (!epoch.formatJulianDay().equals(strings[i])) {
                Assert.fail(
                        String.format("Invalid Julian day: expected '%s', received '%s' for '%s'",
                                strings[i], epoch.formatJulianDay(), doubles[i]));
            }
        }
    }

    @Test
    public void setJulianDay() throws Exception {
        String[] strings = {"344.12345678", "089.98346734", "005.84849840"};
        double[] doubles = {344.12345678, 89.98346734, 5.84849840};
        for (int i = 0; i < strings.length; i++) {
            epoch.setJulianDay(strings[i]);
            if (Double.compare(epoch.getJulianDay(), doubles[i]) != 0) {
                Assert.fail(
                        String.format("Invalid Julian day: expected '%s', received '%s' for '%s'",
                                doubles[i], epoch.getJulianDay(), strings[i]));
            }
        }
    }

}