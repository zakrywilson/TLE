package com.zakrywilson.astro.tle;

import java.text.DecimalFormat;

public final class Line1 extends Line {

    private int satelliteNumber;
    private String classification = "";
    private InternationalDesignator internationalDesignator = new InternationalDesignator();
    private Epoch epoch = new Epoch();
    private double meanMotionFirstDerivative;
    private double meanMotionSecondDerivative;
    private double dragTerm;
    private int ephemerisType;
    private int elementSetNumber;
    private int checkSum;

    public Line1() {
        super();
    }

    @Override
    public void parse(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }
        setSatelliteNumber(line.substring(2, 7));
        setClassification(line.substring(7, 8));
        setInternationalDesignator(line.substring(9, 17));
        setEpoch(line.substring(18, 32));
        setMeanMotionFirstDerivative(line.substring(33, 43));
        setMeanMotionSecondDerivative(line.substring(44, 52));
        setDragTerm(line.substring(53, 61));
        setEphemerisType(line.substring(62, 63));
        setElementSetNumber(line.substring(64, 68));
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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        if (classification == null) {
            throw new IllegalArgumentException("Classification cannot be null");
        }
        classification = classification.trim();
        if (classification.length() < 1) {
            throw new IllegalArgumentException("Classification cannot be blank");
        }
        this.classification = classification;
    }

    public InternationalDesignator getInternationalDesignator() {
        return internationalDesignator;
    }

    public void setInternationalDesignator(InternationalDesignator internationalDesignator) {
        if (internationalDesignator == null) {
            throw new IllegalArgumentException("International designator cannot be null");
        }
        this.internationalDesignator = internationalDesignator;
    }

    public void setInternationalDesignator(String internationalDesignator) {
        this.internationalDesignator.parse(internationalDesignator);
    }

    public Epoch getEpoch() {
        return epoch;
    }

    public void setEpoch(Epoch epoch) {
        if (epoch == null) {
            throw new IllegalArgumentException("Epoch cannot be null");
        }
        this.epoch = epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch.parse(epoch);
    }

    public double getMeanMotionFirstDerivative() {
        return meanMotionFirstDerivative;
    }

    public void setMeanMotionFirstDerivative(double meanMotionFirstDerivative) {
        this.meanMotionFirstDerivative = meanMotionFirstDerivative;
    }

    public void setMeanMotionFirstDerivative(String meanMotionFirstDerivative) {
        if (meanMotionFirstDerivative == null) {
            throw new IllegalArgumentException("Mean motion first derivative cannot be null");
        }
        meanMotionFirstDerivative = meanMotionFirstDerivative.trim();
        if (meanMotionFirstDerivative.length() < 1 || meanMotionFirstDerivative.length() > 10) {
            throw new IllegalArgumentException("Invalid mean motion first derivative length: " + meanMotionFirstDerivative.length());
        }
        this.meanMotionFirstDerivative = Double.parseDouble(meanMotionFirstDerivative);
    }

    public double getMeanMotionSecondDerivative() {
        return meanMotionSecondDerivative;
    }

    public void setMeanMotionSecondDerivative(double meanMotionSecondDerivative) {
        this.meanMotionSecondDerivative = meanMotionSecondDerivative;
    }

    public void setMeanMotionSecondDerivative(String meanMotionSecondDerivative) {
        if (meanMotionSecondDerivative == null) {
            throw new IllegalArgumentException("Mean motion second derivative cannot be null");
        }
        this.meanMotionSecondDerivative = parseExponentialValue(meanMotionSecondDerivative);
    }

    public double getDragTerm() {
        return dragTerm;
    }

    public void setDragTerm(double dragTerm) {
        this.dragTerm = dragTerm;
    }

    public void setDragTerm(String dragTerm) {
        if (dragTerm == null) {
            throw new IllegalArgumentException("Drag term cannot be null");
        }
        this.dragTerm = parseExponentialValue(dragTerm);
    }

    public int getEphemerisType() {
        return ephemerisType;
    }

    public void setEphemerisType(int ephemerisType) {
        if (ephemerisType < 0 || ephemerisType > 9) {
            throw new IllegalArgumentException("Ephemeris type is out of range: " + ephemerisType);
        }
        this.ephemerisType = ephemerisType;
    }

    public void setEphemerisType(String ephemerisType) {
        if (ephemerisType == null) {
            throw new IllegalArgumentException("Drag term cannot be null");
        }
        ephemerisType = ephemerisType.trim();
        if (ephemerisType.length() != 1) {
            throw new IllegalArgumentException("Invalid ephemeris type length: " + ephemerisType.length());
        }
        this.ephemerisType = Integer.parseInt(ephemerisType);
    }

    public int getElementSetNumber() {
        return elementSetNumber;
    }

    public void setElementSetNumber(int elementSetNumber) {
        if (elementSetNumber < 0 || elementSetNumber > 9999) {
            throw new IllegalArgumentException("Element set number is out of range: " + elementSetNumber);
        }
        this.elementSetNumber = elementSetNumber;
    }

    public void setElementSetNumber(String elementSetNumber) {
        if (elementSetNumber == null) {
            throw new IllegalArgumentException("Element set number cannot be null");
        }
        elementSetNumber = elementSetNumber.trim();
        if (elementSetNumber.length() < 1 || elementSetNumber.length() > 4) {
            throw new IllegalArgumentException("Invalid element set number length: " + elementSetNumber.length());
        }
        this.elementSetNumber = Integer.parseInt(elementSetNumber);
    }

    public int getChecksum() {
        return checkSum;
    }

    public void setChecksum(int checkSum) {
        if (checkSum < 0 || checkSum > 9) {
            throw new IllegalArgumentException("Checksum is out of range: " + checkSum);
        }
        this.checkSum = checkSum;
    }

    public void setChecksum(String checksum) {
        if (checksum == null) {
            throw new IllegalArgumentException("Checksum cannot be null");
        }
        checksum = checksum.trim();
        if (checksum.length() != 1) {
            throw new IllegalArgumentException("Invalid checksum length: " + checksum.length());
        }
        this.checkSum = Integer.parseInt(checksum);
    }

    @Override
    public String toString() {
        return format();
    }

    @Override
    public String format() {
        return String.format("%s %s%s %s %s %s %s %s %s %s%s",
                getLineNumber(), formatSatelliteNumber(), getClassification(),
                internationalDesignator.format(), epoch.format(),
                formatMeanMotionFirstDerivative(), formatMeanMotionSecondDerivative(),
                formatDragTerm(), formatEphemerisType(), formatElementSetNumber(),
                formatChecksum());
    }

    @Override
    protected int getLineNumber() {
        return 1;
    }

    String formatSatelliteNumber() {
        return String.format("%5d", satelliteNumber);
    }

    String formatClassification() {
        return String.format("%-3s", classification);
    }

    String formatMeanMotionFirstDerivative() {
        return new DecimalFormat(" .00000000;-.00000000").format(meanMotionFirstDerivative);
    }

    String formatMeanMotionSecondDerivative() {
        if (Double.compare(meanMotionSecondDerivative, 0.0) == 0) {
            return " 00000-0";
        }
        return formatExponentialValue(meanMotionSecondDerivative);
    }

    String formatDragTerm() {
        if (Double.compare(dragTerm, 0.0) == 0) {
            return " 00000+0";
        }
        return formatExponentialValue(dragTerm);
    }

    String formatEphemerisType() {
        return String.valueOf(ephemerisType);
    }

    String formatElementSetNumber() {
        return String.format("%4d", elementSetNumber);
    }

    String formatChecksum() {
        return String.valueOf(checkSum);
    }

    private String formatExponentialValue(double d) {
        DecimalFormat formatter = new DecimalFormat(" .00000E0;-.00000E0");
        return formatter.format(d).replace(".", "").replace("E", "");
    }

    private double parseExponentialValue(String s) {
        // If a '+' sign is in the string, this means it is 0.0 for the drag term
        if (s.contains("+")) {
            return 0.0;
        }

        // Find the last '-', ignoring the possible preceding negative sign
        int index = s.lastIndexOf('-');

        // Tack on the decimal point to the beginning of the value and parse it
        double number = Double.parseDouble("." + s.substring(1, index));

        // If the number is zero, there is no point in doing further parsing: it's zero
        if (number == 0.0) {
            return 0.0;
        }

        // Find the exponent for performing 10^n
        int exponent = Integer.parseInt(s.substring(index + 1));

        // Divide by the power
        double value = (number / Math.pow(10, exponent));

        // If there was a negative sign, add it
        if (s.charAt(0) == '-') {
            value = -value;
        }

        return value;
    }

}
