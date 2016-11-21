package com.zakrywilson.astro;

public final class TLE {

    private final class Line1 {

        private final class InternationalDesignator {

            int year;
            int dayOfYear;
            char pieceOfLaunch;

            InternationalDesignator(String line) {
                year = Integer.parseInt(line.substring(9, 11));
                dayOfYear = Integer.parseInt(line.substring(11, 14));
                pieceOfLaunch = line.charAt(16);
            }

        }

        private final class Epoch {

            int year;
            float julianDay;

            Epoch(String line) {
                year = Integer.parseInt(line.substring(18, 20));
                julianDay = Float.parseFloat(line.substring(20, 32));
            }

        }

        String line;
        int satelliteNumber;
        char classification;
        InternationalDesignator internationalDesignator;
        Epoch epoch;
        double meanMotionFirstDerivative;
        double meanMotionSecondDerivative;
        double dragTerm;
        int ephemerisType;
        int checkSum;

        Line1(String line) {
            if (line == null || line.trim().length() > 1) {
                throw new IllegalArgumentException("Line cannot be blank");
            }
            if (!line.substring(0, 1).equals("1")) {
                throw new IllegalArgumentException("Line numbers do not match: expected line 1: " + line.substring(0, 1));
            }

            satelliteNumber = Integer.parseInt(line.substring(2, 7));
            classification = line.charAt(7);
            internationalDesignator = new InternationalDesignator(line);
            epoch = new Epoch(line);
            meanMotionFirstDerivative = Double.parseDouble(line.substring(33, 43));
            meanMotionSecondDerivative = parseExponent(line.substring(44, 52));
            dragTerm = parseExponent(line.substring(53, 61));
            ephemerisType = Integer.parseInt(line.substring(64, 68));
            checkSum = Integer.parseInt(line.substring(68, 69));

            this.line = line;
        }

        private double parseExponent(String s) {
            String[] parts = s.split("-");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Received invalid exponent: " + s);
            }
            int power = Integer.parseInt(parts[1]);
            String decimalAndZeros = "0.";
            for (int i = 0; i < power; i++) {
                decimalAndZeros += "0";
            }
            return Double.parseDouble(decimalAndZeros + parts[0]);
        }

    }

    private final class Line2 {

        String line;
        int satelliteNumber;
        float inclination;
        float rightAscension;
        float eccentricity;
        float argumentOfPerigee;
        float meanAnomaly;
        float meanMotion;
        int revolutions;
        int checkSum;

        Line2(String line) {
            if (line == null || line.trim().length() < 1) {
                throw new IllegalArgumentException("Line cannot be blank");
            }
            if (!line.substring(0, 1).equals("2")) {
                throw new IllegalArgumentException("Line numbers do not match: expected line 2: " + line.substring(0, 1));
            }

            satelliteNumber = Integer.parseInt(line.substring(2, 7));
            inclination = Float.parseFloat(line.substring(8, 16));
            rightAscension = Float.parseFloat(line.substring(17, 25));
            eccentricity = Float.parseFloat("." + line.substring(26, 33));
            argumentOfPerigee = Float.parseFloat(line.substring(34, 42));
            meanAnomaly = Float.parseFloat(line.substring(43, 51));
            meanMotion = Float.parseFloat(line.substring(52, 63));
            revolutions = Integer.parseInt(line.substring(63, 68));
            checkSum = Integer.parseInt(line.substring(68, 69));

            this.line = line;
        }

    }

    private String titleLine;
    private Line1  line1;
    private Line2  line2;

    public TLE(String tle) {
        String[] lines = tle.trim().split("(\r\n)|\n|\r");
        if (lines.length == 2) {
            initialize("", lines[0], lines[1]);
        }
        if (lines.length == 3) {
            initialize(lines[0], lines[1], lines[2]);
        }
        throw new IllegalArgumentException("Invalid number of lines in TLE: '" + tle + "'");
    }

    public TLE(String titleLine, String line1, String line2) {
        initialize(titleLine, line1, line2);
    }

    public TLE(String line1, String line2) {
        initialize("", line1, line2);
    }

    public String getTitleLine() {
        return titleLine;
    }

    public String getLine1() {
        return line1.line;
    }

    public String getLine2() {
        return line2.line;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s%n%s", titleLine, line1.line, line2.line);
    }

    private void initialize(String titleLine, String line1, String line2) {
        if (titleLine == null || line1 == null || line2 == null) {
            throw new IllegalArgumentException("Lines cannot be null");
        }
        if (line1.length() != 70) {
            throw new IllegalArgumentException("TLE line 1 must be 70 characters: " + line1.length());
        }
        if (line2.length() != 70) {
            throw new IllegalArgumentException("TLE line 2 must be 70 characters: " + line2.length());
        }

        this.titleLine = titleLine.trim();
        this.line1 = new Line1(line1.trim());
        this.line2 = new Line2(line2.trim());
    }

    private TLE() {}

}
