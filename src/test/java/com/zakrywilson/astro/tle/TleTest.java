package com.zakrywilson.astro.tle;

import org.junit.Assert;
import org.junit.Test;

public class TleTest {

    private Tle tle = new Tle();

    @Test
    public void getTitleLine() throws Exception {
        String titleLine = "ISS (ZARYA)";
        tle.setTitleLine(titleLine);
        if (!tle.getTitleLine().equals(titleLine)) {
            Assert.fail(String.format("Invalid title string: expected '%s', received '%s'",
                    titleLine, tle.getTitleLine()));
        }
    }

}