package com.zakrywilson.astro.tle;

public interface TleElement {

    void parse(String s);

    String format();

    String toString();

}
