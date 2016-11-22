package com.zakrywilson.astro.tle;

public final class InternationalDesignator implements TleElement {

    private int year;
    private int julianDay;
    private String pieceOfLaunch = "";

    public InternationalDesignator() {}

    public void parse(String s) {
        if (s == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        setYear(s.substring(0, 2));
        setJulianDay(s.substring(2, 5));
        setPieceOfLaunch(s.substring(5));
    }

    public void parse(String s, boolean wholeLine) {
        if (s == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        if (wholeLine) {
            setYear(s.substring(9, 11));
            setJulianDay(s.substring(11, 14));
            setPieceOfLaunch(s.substring(14, 17));
        } else {
            setYear(s.substring(0, 2));
            setJulianDay(s.substring(2, 5));
            setPieceOfLaunch(s.substring(5));
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
            throw new IllegalArgumentException("Invalid 2-digit year: " + year);
        }
        this.year = Integer.parseInt(year);
    }

    public int getJulianDay() {
        return julianDay;
    }

    public void setJulianDay(int julianDay) {
        if (julianDay < 0 || julianDay > 366) {
            throw new IllegalArgumentException("Invalid day of year: " + julianDay);
        }
        this.julianDay = julianDay;
    }

    public void setJulianDay(String julianDay) {
        if (julianDay == null) {
            throw new IllegalArgumentException("Julian day cannot be null");
        }
        julianDay = julianDay.trim();
        if (julianDay.length() < 1 || julianDay.length() > 3) {
            throw new IllegalArgumentException("Invalid day of year: " + julianDay);
        }
        this.julianDay = Integer.parseInt(julianDay);
    }

    public String getPieceOfLaunch() {
        return pieceOfLaunch;
    }

    public void setPieceOfLaunch(String pieceOfLaunch) {
        if (pieceOfLaunch == null) {
            throw new IllegalArgumentException("Piece of launch cannot be null");
        }
        pieceOfLaunch = pieceOfLaunch.trim();
        if (pieceOfLaunch.length() < 1) {
            throw new IllegalArgumentException("Piece of launch cannot be blank");
        }
        this.pieceOfLaunch = pieceOfLaunch;
    }

    @Override
    public String format() {
        return formatYear() + formatJulianDay() + formatPieceOfLaunch();
    }

    @Override
    public String toString() {
        return format().trim();
    }

    String formatYear() {
        return String.format("%02d", year);
    }

    String formatJulianDay() {
        return String.format("%03d", julianDay);
    }

    String formatPieceOfLaunch() {
        return String.format("%-3s", pieceOfLaunch);
    }

}
