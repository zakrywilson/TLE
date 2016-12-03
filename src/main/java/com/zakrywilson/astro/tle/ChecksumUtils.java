package com.zakrywilson.astro.tle;

/**
 * Thread-safe utility class for creating, verifying, and extracting checksums from TLE lines.
 *
 * @author Zach Wilson
 */
public final class ChecksumUtils {

    /**
     * The exact line length of a TLE line 1 and 2 (but not title line). Any line not meeting this
     * length requirement should be considered invalid.
     */
    private static final int LINE_LENGTH = 69;

    /**
     * The index of the checksum for both lines 1 and 2 of a TLE (but not the title line).
     */
    private static final int CHECKSUM_INDEX = 68;

    /**
     * Private constructor.
     * <p>
     * All members are static. No instance of this class should be created.
     */
    private ChecksumUtils() {}

    /**
     * Generates a checksum for an entire line of the TLE (omitting the checksum character).
     *
     * @param line the line of the TLE without the checksum digit
     * @return the checksum value
     * @throws IllegalArgumentException if <code>line</code> is <code>null</code> or not 68
     * characters in length
     */
    public static int generateChecksum(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }

        line = line.trim();
        int length = line.length();
        if (length != CHECKSUM_INDEX) {
            throw new IllegalArgumentException(
                    String.format("Invalid line length: must be %d characters, received %d",
                                  CHECKSUM_INDEX, length));
        }

        return calculateChecksum(line);
    }

    /**
     * Gets the checksum for the line as a <code>char</code>.
     *
     * @param line the line with a checksum digit
     * @return the checksum
     * @throws IllegalArgumentException if <code>line</code> is <code>null</code> or if
     * <code>line</code> is not 69 characters in length
     */
    public static char getChecksum(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }
        if (line.length() != LINE_LENGTH) {
            throw new IllegalArgumentException("Line must be 69 characters long: " + line.length());
        }

        return line.charAt(CHECKSUM_INDEX);
    }

    /**
     * Gets the checksum for the line and returns it as an <code>int</code>.
     *
     * @param line the line with a checksum digit
     * @return the checksum
     * @throws IllegalArgumentException if <code>line</code> is <code>null</code> or if
     * <code>line</code> is not 69 characters in length
     */
    public static int parseChecksum(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }
        if (line.length() != LINE_LENGTH) {
            throw new IllegalArgumentException("Line must be 69 characters long: " + line.length());
        }

        return Integer.parseInt(line.substring(CHECKSUM_INDEX));
    }

    /**
     * Verifies the checksum for a line in a TLE.
     *
     * @param line the entire line of the TLE (including the checksum digit)
     * @return <code>true</code> if the checksum if valid, <code>false</code> otherwise
     * @throws IllegalArgumentException if <code>line</code> is <code>null</code> or if the line is
     * not 69 characters in length
     */
    public static boolean isChecksumValid(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }

        line = line.trim();
        int length = line.length();
        if (length != LINE_LENGTH) {
            throw new IllegalArgumentException(
                    "Invalid line length: must be 69 characters, received " + length);
        }

        int checksum = Integer.parseInt(line.substring(CHECKSUM_INDEX));
        int calculatedChecksum = calculateChecksum(line.substring(0, CHECKSUM_INDEX));

        return checksum == calculatedChecksum;
    }

    /**
     * Calculates the checksum for a given line.
     * <p>
     * The checksum for the line is calculated by adding all numerical digits, including the line
     * number. 1 is added to the checksum for each negative sign (−) on that line. All other
     * non-digit characters are ignored.
     *
     * @param line the line to be computed for its checksum, omitting the checksum digit. If the
     * checksum digit is included in the line, the result will be invalid.
     * @return the checksum mod 10
     */
    private static int calculateChecksum(String line) {
        int checksum = 0;
        for (char c : line.toCharArray()) {
            switch (c) {
                case '-':
                    checksum += 1;
                    continue;
                case '1':
                    checksum += 1;
                    continue;
                case '2':
                    checksum += 2;
                    continue;
                case '3':
                    checksum += 3;
                    continue;
                case '4':
                    checksum += 4;
                    continue;
                case '5':
                    checksum += 5;
                    continue;
                case '6':
                    checksum += 6;
                    continue;
                case '7':
                    checksum += 7;
                    continue;
                case '8':
                    checksum += 8;
                    continue;
                case '9':
                    checksum += 9;
            }
        }
        return checksum % 10;
    }

}
