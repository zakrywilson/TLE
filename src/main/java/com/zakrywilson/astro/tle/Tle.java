package com.zakrywilson.astro.tle;

public final class Tle {

    private String titleLine = "";
    private Line1 line1 = new Line1();
    private Line2 line2 = new Line2();

    public Tle() {}

    public void parse(String tle) {
        String[] lines = tle.trim().split("(\r\n)|\n|\r");
        if (lines.length == 2) {
            parse("", lines[0], lines[1]);
        }
        if (lines.length == 3) {
            parse(lines[0], lines[1], lines[2]);
        }
        throw new IllegalArgumentException("Invalid number of lines in Tle: '" + tle + "'");
    }

    public void parse(String line1, String line2) {
        parse("", line1, line2);
    }

    public void parse(String titleLine, String line1, String line2) {
        if (titleLine == null || line1 == null || line2 == null) {
            throw new IllegalArgumentException("Lines cannot be null");
        }
        if (titleLine.length() > 24) {
            throw new IllegalArgumentException("Title line must not exceed 24 characters: " + titleLine.length());
        }

        this.titleLine = titleLine.trim();
        this.line1.parse(line1);
        this.line2.parse(line2);

        // Ensure satellite numbers match
        if (this.line1.getSatelliteNumber() != this.line2.getSatelliteNumber()) {
            throw new IllegalArgumentException(
                    String.format("Satellite numbers do not match across lines: Line 1=%d, Line 2=%d",
                            this.line1.getSatelliteNumber(), this.line2.getSatelliteNumber()));
        }

        // Ensure the checksums match
        if (this.line1.getChecksum() != this.line2.getChecksum()) {
            throw new IllegalArgumentException(
                    String.format("Checksum values do not match across lines: Line 1=%d, Line 2=%d",
                            this.line1.getChecksum(), this.line2.getChecksum()));
        }
    }

    public String getTitleLine() {
        return titleLine;
    }

    public void setTitleLine(String titleLine) {
        if (titleLine == null) {
            throw new IllegalArgumentException("Title line cannot be null");
        }
        this.titleLine = titleLine.trim();
    }

    public String getLine1() {
        return line1.toString();
    }

    public void setLine1(Line1 line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }
        this.line1 = line;
    }

    public void parseLine1(String line) {
        line1.parse(line);
    }

    public String getLine2() {
        return line2.toString();
    }

    public void setLine2(Line2 line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }
        this.line2 = line;
    }

    public void parseLine2(String line) {
        line2.parse(line);
    }

    @Override
    public String toString() {
        return String.format("%s%n%s%n%s", titleLine, line1, line2);
    }

}
