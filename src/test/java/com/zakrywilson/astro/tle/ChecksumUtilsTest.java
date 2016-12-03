package com.zakrywilson.astro.tle;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link ChecksumUtils}.
 *
 * @author Zach Wilson
 */
public class ChecksumUtilsTest {

    private static final int CHECKSUM_INDEX = 68;

    /**
     * Tests {@link ChecksumUtils#generateChecksum(String)}
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void generateChecksum() throws Exception {
        String[] lines = {"1 26411U 00041B   16338.86320548  .00000515  00000-0  00000+0 0  9996",
                          "2 26411 130.1447 332.6318 5309660 154.0187   0.1056  0.44222994 16710",
                          "1 26463U 00045A   16338.88048350  .00000562  00000-0  00000+0 0  9999",
                          "2 27370  38.0315 170.0187 0012960 251.6032 108.3329 15.27437219816093",
                          "1 26702U 01007A   16337.09896918  .00000547  00000-0  40838-4 0  9998",
                          "2 26702  97.6197 350.2593 0011406  12.9956 347.1563 15.06865598861961"};

        String input;
        int expected, received;
        for (String line : lines) {
            expected = Integer.parseInt(line.substring(CHECKSUM_INDEX));
            input = line.substring(0, CHECKSUM_INDEX);
            received = ChecksumUtils.generateChecksum(input);
            if (received != expected) {
                Assert.fail(String.format("Expected checksum of %d but received %d for line %s",
                                          expected, received, input));
            }
        }
    }

    /**
     * Tests {@link ChecksumUtils#generateChecksum(String)} invalid input.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void generateChecksum2() throws Exception {
        String[] lines = {"26411U 00041B   16338.86320548  .00000515  00000-0  00000+0 0  9996",
                          " 26411 130.1447 332.6318 5309660 154.0187   0.1056  0.44222994 16710",
                          "1  26463U 00045A   16338.88048350  .00000562  00000-0  00000+0 0  9999",
                          "",
                          "1 702U 01007A   16337.09896918  .00000547  00000-0  40838-4 0  9998",
                          null};

        for (String line : lines) {
            try {
                ChecksumUtils.generateChecksum(line);
                Assert.fail("Expected IllegalArgumentException for invalid input: " + line);
            } catch (IllegalArgumentException e) {
                // Good: this was expected
            }
        }
    }

    /**
     * Tests {@link ChecksumUtils#getChecksum(String)}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void getChecksum() throws Exception {
        // Test with valid input
        String[] lines = {"1 26411U 00041B   16338.86320548  .00000515  00000-0  00000+0 0  9996",
                          "2 26411 130.1447 332.6318 5309660 154.0187   0.1056  0.44222994 16710",
                          "1 26463U 00045A   16338.88048350  .00000562  00000-0  00000+0 0  9999",
                          "2 27370  38.0315 170.0187 0012960 251.6032 108.3329 15.27437219816093",
                          "1 26702U 01007A   16337.09896918  .00000547  00000-0  40838-4 0  9998",
                          "2 26702  97.6197 350.2593 0011406  12.9956 347.1563 15.06865598861961"};

        int expected, received;
        for (String line : lines) {
            expected = line.charAt(CHECKSUM_INDEX);
            received = ChecksumUtils.getChecksum(line);
            if (received != expected) {
                Assert.fail(String.format("Expected checksum of %c but received %c for line %s",
                                          expected, received, line));
            }
        }

        // Try with an empty String
        try {
            ChecksumUtils.getChecksum("");
            Assert.fail("Expected IllegalArgumentException for invalid input \"\"");
        } catch (IllegalArgumentException e) {
            // Good, this was expected
        }

        // Try with null
        try {
            ChecksumUtils.getChecksum(null);
            Assert.fail("Expected IllegalArgumentException for invalid input null");
        } catch (IllegalArgumentException e) {
            // Good, this was expected
        }
    }

    /**
     * Tests {@link ChecksumUtils#parseChecksum(String)}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void parseChecksum() throws Exception {
        // Try with valid input
        String[] lines = {"1 26411U 00041B   16338.86320548  .00000515  00000-0  00000+0 0  9996",
                          "2 26411 130.1447 332.6318 5309660 154.0187   0.1056  0.44222994 16710",
                          "1 26463U 00045A   16338.88048350  .00000562  00000-0  00000+0 0  9999",
                          "2 27370  38.0315 170.0187 0012960 251.6032 108.3329 15.27437219816093",
                          "1 26702U 01007A   16337.09896918  .00000547  00000-0  40838-4 0  9998",
                          "2 26702  97.6197 350.2593 0011406  12.9956 347.1563 15.06865598861961"};

        int expected, received;
        for (String line : lines) {
            expected = Integer.parseInt(line.substring(CHECKSUM_INDEX));
            received = ChecksumUtils.parseChecksum(line);
            if (received != expected) {
                Assert.fail(String.format("Expected checksum of %d but received %d for line %s",
                                          expected, received, line));
            }
        }

        // Try with an empty String
        try {
            ChecksumUtils.parseChecksum("");
            Assert.fail("Expected IllegalArgumentException for invalid input \"\"");
        } catch (IllegalArgumentException e) {
            // Good, this was expected
        }

        // Try with null
        try {
            ChecksumUtils.parseChecksum(null);
            Assert.fail("Expected IllegalArgumentException for invalid input null");
        } catch (IllegalArgumentException e) {
            // Good, this was expected
        }
    }

    /**
     * Tests {@link ChecksumUtils#isChecksumValid(String)}.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void isChecksumValid() throws Exception {
        String[] lines = {"1 26411U 00041B   16338.86320548  .00000515  00000-0  00000+0 0  9995",  // Invalid
                          "2 26411 130.1447 332.6318 5309660 154.0187   0.1056  0.44222994 16710",  // Valid
                          "1 26463U 00045A   16338.88048350  .00000562  00000-0  00000+0 0  9998",  // Invalid
                          "2 27370  38.0315 170.0187 0012960 251.6032 108.3329 15.27437219816093",  // Valid
                          "1 26702U 01007A   16337.09896918  .00000547  00000-0  40838-4 0  9994",  // Invalid
                          "2 26702  97.6197 350.2593 0011406  12.9956 347.1563 15.06865598861961"}; // Valid

        String line;
        boolean expected, received;
        for (int i = 0; i < lines.length; i++) {
            line = lines[i];
            expected = (i % 2) == 1;
            received = ChecksumUtils.isChecksumValid(line);
            if (received != expected) {
                Assert.fail(String.format("Expected boolean '%s' but received '%s' for line %s",
                                          expected, received, line));
            }
        }
    }

    /**
     * Tests {@link ChecksumUtils#isChecksumValid(String)} with invalid input.
     *
     * @throws Exception if an unexpected error should occur
     */
    @Test
    public void isChecksumValid2() throws Exception {
        String[] lines = {"1 26411U 00041B   16338.86320548  .00000515  00000-0  00000+0 0  99 95 ",
                          "2 26411 130.1447 332.6318 5309660 154.0187   0.1056  0.44222994 1671",
                          "1 26463U 00045A   16338.88048350  .00000562  00000-0  00000+0 0  99",
                          "2 27370  38.0315 170.0187 0012960 251.6032 108.3329 15.274372198160930",
                          "",
                          null};

        for (String line : lines) {
            try {
                ChecksumUtils.isChecksumValid(line);
                Assert.fail("Expected IllegalArgumentException for invalid input: " + line);
            } catch (IllegalArgumentException e) {
                // Good: this was expected
            }
        }
    }

}