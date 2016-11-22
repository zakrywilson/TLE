package com.zakrywilson.astro.tle;

import java.text.DecimalFormat;

public final class Line2 extends Line {

    private int satelliteNumber;
    private double inclination;
    private double rightAscension;
    private double eccentricity;
    private double argumentOfPerigee;
    private double meanAnomaly;
    private double meanMotion;
    private int revolutions;
    private int checksum;

    public Line2() {
        super();
    }

    public void parse(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }
        setSatelliteNumber(line.substring(2, 7));
        setInclination(line.substring(8, 16));
        setRightAscension(line.substring(17, 25));
        setEccentricity(line.substring(26, 33));
        setArgumentOfPerigee(line.substring(34, 42));
        setMeanAnomaly(line.substring(43, 51));
        setMeanMotion(line.substring(52, 63));
        setRevolutions(line.substring(63, 68));
        setChecksum(line.substring(68, 69));
    }

    public int getSatelliteNumber() {
        return satelliteNumber;
    }

    public void setSatelliteNumber(int satelliteNumber) {
        if (satelliteNumber < 0 || satelliteNumber > 99999) {
            throw new IllegalArgumentException("Satellite number is out of range: " + satelliteNumber);
        }
        this.satelliteNumber = satelliteNumber;
    }

    public void setSatelliteNumber(String satelliteNumber) {
        if (satelliteNumber == null) {
            throw new IllegalArgumentException("Satellite number cannot be null");
        }
        satelliteNumber = satelliteNumber.trim();
        if (satelliteNumber.length() < 1 || satelliteNumber.length() > 5) {
            throw new IllegalArgumentException("Invalid satellite number length: " + satelliteNumber.length());
        }
        this.satelliteNumber = Integer.parseInt(satelliteNumber);
    }

    public double getInclination() {
        return inclination;
    }

    public void setInclination(double inclination) {
        this.inclination = inclination;
    }

    public void setInclination(String inclination) {
        if (inclination == null) {
            throw new IllegalArgumentException("Inclination cannot be null");
        }
        inclination = inclination.trim();
        if (inclination.length() < 1 || inclination.length() > 8) {
            throw new IllegalArgumentException("Invalid inclination length: " + inclination.length());
        }
        this.inclination = Double.parseDouble(inclination);
    }

    public double getRightAscension() {
        return rightAscension;
    }

    public void setRightAscension(double rightAscension) {
        this.rightAscension = rightAscension;
    }

    public void setRightAscension(String rightAscension) {
        if (rightAscension == null) {
            throw new IllegalArgumentException("Right ascension of the ascending node cannot be null");
        }
        rightAscension = rightAscension.trim();
        if (rightAscension.length() < 1 || rightAscension.length() > 8) {
            throw new IllegalArgumentException("Invalid right ascension of the ascending node length: " + rightAscension.length());
        }
        this.rightAscension = Double.parseDouble(rightAscension);
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public void setEccentricity(String eccentricity) {
        if (eccentricity == null) {
            throw new IllegalArgumentException("Eccentricity cannot be null");
        }
        eccentricity = eccentricity.trim();
        if (eccentricity.length() < 1 || eccentricity.length() > 7) {
            throw new IllegalArgumentException("Invalid eccentricity length: " + eccentricity.length());
        }
        this.eccentricity = Double.parseDouble("." + eccentricity);
    }

    public double getArgumentOfPerigee() {
        return argumentOfPerigee;
    }

    public void setArgumentOfPerigee(double argumentOfPerigee) {
        this.argumentOfPerigee = argumentOfPerigee;
    }

    public void setArgumentOfPerigee(String argumentOfPerigee) {
        if (argumentOfPerigee == null) {
            throw new IllegalArgumentException("Argument of perigee cannot be null");
        }
        argumentOfPerigee = argumentOfPerigee.trim();
        if (argumentOfPerigee.length() < 1 || argumentOfPerigee.length() > 8) {
            throw new IllegalArgumentException("Invalid argument of perigee length: " + argumentOfPerigee.length());
        }
        this.argumentOfPerigee = Double.parseDouble(argumentOfPerigee);
    }

    public double getMeanAnomaly() {
        return meanAnomaly;
    }

    public void setMeanAnomaly(double meanAnomaly) {
        this.meanAnomaly = meanAnomaly;
    }

    public void setMeanAnomaly(String meanAnomaly) {
        if (meanAnomaly == null) {
            throw new IllegalArgumentException("Mean anomaly cannot be null");
        }
        meanAnomaly = meanAnomaly.trim();
        if (meanAnomaly.length() < 1 || meanAnomaly.length() > 8) {
            throw new IllegalArgumentException("Invalid mean anomaly length: " + meanAnomaly.length());
        }
        this.meanAnomaly = Double.parseDouble(meanAnomaly);
    }

    public double getMeanMotion() {
        return meanMotion;
    }

    public void setMeanMotion(double meanMotion) {
        this.meanMotion = meanMotion;
    }

    public void setMeanMotion(String meanMotion) {
        if (meanMotion == null) {
            throw new IllegalArgumentException("Mean motion cannot be null");
        }
        meanMotion = meanMotion.trim();
        if (meanMotion.length() < 1 || meanMotion.length() > 11) {
            throw new IllegalArgumentException("Invalid mean motion length: " + meanMotion.length());
        }
        this.meanMotion = Double.parseDouble(meanMotion);
    }

    public int getRevolutions() {
        return revolutions;
    }

    public void setRevolutions(int revolutions) {
        if (revolutions < 0 || revolutions > 99999) {
            throw new IllegalArgumentException("Revolutions out of range: " + revolutions);
        }
        this.revolutions = revolutions;
    }

    public void setRevolutions(String revolutions) {
        if (revolutions == null) {
            throw new IllegalArgumentException("Revolutions cannot be null");
        }
        revolutions = revolutions.trim();
        if (revolutions.length() < 1 || revolutions.length() > 5) {
            throw new IllegalArgumentException("Invalid revolutions length: " + revolutions.length());
        }
        this.revolutions = Integer.parseInt(revolutions);
    }

    public int getChecksum() {
        return checksum;
    }

    public void setChecksum(int checkSum) {
        if (checkSum < 0 || checkSum > 9) {
            throw new IllegalArgumentException("Checksum is out of range: " + checkSum);
        }
        this.checksum = checkSum;
    }

    public void setChecksum(String checksum) {
        if (checksum == null) {
            throw new IllegalArgumentException("Checksum cannot be null");
        }
        checksum = checksum.trim();
        if (checksum.length() != 1) {
            throw new IllegalArgumentException("Invalid checksum length: " + checksum.length());
        }
        this.checksum = Integer.parseInt(checksum);
    }

    String formatSatelliteNumber() {
        return String.format("%5d", satelliteNumber);
    }

    String formatInclination() {
        return new DecimalFormat("000.0000").format(inclination);
    }

    String formatRightAscension() {
        return new DecimalFormat("000.0000").format(rightAscension);
    }

    String formatEccentricity() {
        String s = String.format("%.07f", eccentricity);
        return s.substring(s.indexOf('.') + 1);
    }

    String formatArgumentOfPerigee() {
        return new DecimalFormat("000.0000").format(argumentOfPerigee);
    }

    String formatMeanAnomaly() {
        return new DecimalFormat("000.0000").format(meanAnomaly);
    }

    String formatMeanMotion() {
        String s = new DecimalFormat("00.00000000").format(meanMotion);
        if (s.charAt(0) == '0') {
            s = s.replaceFirst("0", " ");
        }
        return s;
    }

    String formatRevolutionNumber() {
        return String.format("%5d", revolutions);
    }

    String formatChecksum() {
        return String.valueOf(checksum);
    }

    @Override
    public String toString() {
        return format();
    }

    @Override
    public String format() {
        return String.format("%s %s %s %s %s %s %s %s%s%s",
                getLineNumber(), formatSatelliteNumber(), formatInclination(),
                formatRightAscension(), formatEccentricity(), formatArgumentOfPerigee(),
                formatMeanAnomaly(), formatMeanMotion(), formatRevolutionNumber(), formatChecksum());
    }

    @Override
    protected int getLineNumber() {
        return 2;
    }

}
