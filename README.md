# TLE

A package for parsing, formatting, and building TLEs [(Two Line Element Sets)](https://en.wikipedia.org/wiki/Two-line_element_set).

## Getting Started

### Prerequisites

* Java 8  
* Maven 3 

### Installing

#### Linux / macOS

- Run the following commands:
```bash
git clone https://github.com/zakrywilson/TLE.git  
cd TLE  
mvn package  
cd target  
```
- Copy the JARs to your project.
- Add JARs to your project's classpath.

#### Windows

- Download repository [here](https://github.com/zakrywilson/TLE/archive/master.zip).  
- Unzip the file.  
- Navigate to the unzipped file.  
- Run the following commands inside the root folder:
```dos
mvn package  
cd target  
```
- Copy the JARs to your project.
- Add JARs to your project's classpath.

## How to Use

### Parsing a TLE

```java
// Say you have a TLE
String title = "ASTROSAT                ";
String line1 = "1 40930U 15052A   16332.17311884  .00000840  00000-0  29196-4 0  9990";
String line2 = "2 40930   5.9978 234.6882 0008785 270.8436  89.0759 14.76065788 63051";

// And you want to pull out the inclination, the RAAN, the BSTAR drag term
// Pass the lines into the constructor
TLE tle = new TLE(title, line1, line2);

// Now you can access any of the elements
double inclination = tle.getInclination();
double raan = tle.getRaan();
double dragTerm = tle.getDragTerm();
```

### Building a TLE

You can build a TLE from individual elements.  

#### A Little About the Design

This builder enforces the correct construction of a TLE. The [TLEBuilder](https://github.com/zakrywilson/TLE/blob/master/src/main/java/com/zakrywilson/astro/tle/TLEBuilder.java) class implements a variant of the builder pattern which enforces the user to, at a minimum, pass in the required elements into the builder for valid TLE construction. Once the required elements have been received, the `BuildStep#build()` method will be available. Part of this builder pattern's implementation is the use of *steps* which is a polymorphic procedural mitigation of the TLE's construction. For example, `SatelliteNumberStep#setSatelliteNumber(int)` returns the one option of `InternationalDesignatorStep#setInternationalDesignator(String)` which returns two options of `EpochStep#setEpoch(int, double)` or `EpochStep#setEpoch(long)`, and so forth. Once all the required steps have been completed, `BuildStep#build()` will be available for the TLE to be built. When the TLE is allowed to be built, the optional elements will also be available to be set, such as *classification* and *ephemeris type*. 

The list of steps in order (after calling `TLEBuilder#newBuilder()` or `TLEBuilder#newBuilder(String)`):

1. Satellite number
2. International designator
3. Epoch
4. Orbital elements
5. First derivative of mean motion  
6. Element set number
7. Revolutions at epoch
8. Checksums
9. Build step  
    a. Classification (optional, default = 'U')  
    b. Second derivative of mean motion (optional, default = 0.0)  
    c. BSTAR drag term (optional, default = 0.0)  
    d. Ephemeris type (optional, default = 0)  
    e. Build TLE  

As you can see, after the *checksums* step, the TLE can now be built or any number of the optional elements can be set before calling `BuildStep#build()`.

#### Examples

```java
// Say you need to construct a TLE from existing elements
int satelliteNumber = 40930;
String internationalDesignator = "15052A";
long epoch = 1479427633502L;
// and so forth

// You can use the TLE builder to construct a TLE from these elements
TLE tle = TLEBuilder.newBuilder("ASTROSAT")
                    .setSatelliteNumber(40930)
                    .setInternationalDesignator("15052A")
                    .setEpoch(1479427633502L)
                    .setOrbitalElements(28.7962, 321.9886, 0.8143506, 131.4756, 333.0646, 1.00489359)
                    .setFirstDerivativeMeanMotion(-.00000439)
                    .setElementSetNumber(999)
                    .setRevolutions(619)
                    .setChecksums(2, 5)
                    .setClassification('S')
                    .build();
```

You can use the *toString* method to get the whole TLE:

```java
System.out.println(tle);
// ASTROSAT                
// 1 40930S 15052A   16323.00501738 -.00000439  00000-0  00000+0 0  9992
// 2 40930  28.7962 321.9886 8143506 131.4756 333.0646  1.00489359  6195
```

Or you can access individual elements:

```java
System.out.println(tle.getLine1());
// 1 40930S 15052A   16323.00501738 -.00000439  00000-0  00000+0 0  9992

System.out.println(tle.getEccentricity());
// 0.8143506
```

## Running the Tests

Nearly every method has a corresponding unit test and methods without explicit tests one are implicitly tested by others. The main goal of these tests were to ensure the code's ability to (1) format individual TLE elements into their respective lines (2) parse existing TLEs to extract their individual elements (3) build "correct" TLEs from descrete elements, using the builder class. Other testing was to ensure the accuracy of converting to and from a *millisecond epoch* to a *two-digit year + fractional Julian day epoch* pair.

### Using an IDE

If you're using an IDE such as Eclipse or IntelliJ, you can run the JUnit tests by right-clicking the test package (*src/test/java*) and having it run through all unit tests.

### Using the Command Line

If you're using the command line, issue the `mvn test` command at the project's root directory. This will kick off all the JUnit tests and print out the results.

## Author

**Zach Wilson**  
Email: zakrywilson@gmail.com  
URL: zakrywilson.com  

## License

This project is licensed under the MIT License. See the [LICENSE.md](https://github.com/zakrywilson/TLE/blob/master/LICENSE.md) file for details.
