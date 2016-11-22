package com.zakrywilson.astro.tle;

import java.text.DecimalFormat;

public final class Epoch implements TleElement {

    private int year;
    private double julianDay;

    public Epoch() {}

    public void parse(String s) {
        if (s == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        setYear(s.substring(0, 2));
        setJulianDay(s.substring(2));
    }

    public void parse(String s, boolean wholeLine) {
        if (s == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        if (wholeLine) {
            setYear(s.substring(18, 20));
            setJulianDay(s.substring(20, 32));
        } else {
            setYear(s.substring(0, 2));
            setJulianDay(s.substring(2));
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 0 || year > 99) {
            throw new IllegalArgumentException("Invalid 2-digit year: " + year);
        }
        this.year = year;
    }

    public void setYear(String year) {
        if (year == null) {
            throw new IllegalArgumentException("Year cannot be null");
        }
        year = year.trim();
        if (year.length() < 1 || year.length() > 2) {
            throw new IllegalArgumentException("invalid 2-digit year: " + year);
        }
        this.year = Integer.parseInt(year);
    }

    public double getJulianDay() {
        return julianDay;
    }

    public void setJulianDay(double julianDay) {
        if (julianDay < 0.0f || julianDay > 366.0f) {
            throw new IllegalArgumentException("Invalid day of year: " + julianDay);
        }
        this.julianDay = julianDay;
    }

    public void setJulianDay(String julianDay) {
        if (julianDay == null) {
            throw new IllegalArgumentException("Julian day cannot be null");
        }
        julianDay = julianDay.trim();
        if (julianDay.length() < 1 || julianDay.length() > 12) {
            throw new IllegalArgumentException("Invalid day of year: " + julianDay);
        }
        this.julianDay = Double.parseDouble(julianDay);
    }

    @Override
    public String format() {
        return formatYear() + formatJulianDay();
    }

    @Override
    public String toString() {
        return format().trim();
    }

    String formatYear() {
        return String.format("%02d", year);
    }

    String formatJulianDay() {
        return new DecimalFormat("000.00000000").format(julianDay);
    }

}
