# TLE

A package for parsing, formatting, and building TLEs [(Two Line Element Sets)](https://en.wikipedia.org/wiki/Two-line_element_set).

## Version 1.1.2 - 7/25/2017

[Full Change Log](https://github.com/zakrywilson/TLE/blob/master/CHANGELOG.md)

Fixed issue where strange exponential formats with trailing '+' signs were causing parsing exceptions  

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
```bash
mvn package  
cd target  
```
- Copy the JARs to your project.
- Add JARs to your project's classpath.

## How to Use

### Parsing a TLE

Say you have a TLE and you want to pull out the inclination, the BSTAR drag term, and the right ascension of the ascending node (RAAN).

```java
String title = "ASTROSAT                ";
String line1 = "1 40930U 15052A   16332.17311884  .00000840  00000-0  29196-4 0  9990";
String line2 = "2 40930   5.9978 234.6882 0008785 270.8436  89.0759 14.76065788 63051";
```

Pass the lines into the constructor.

```java
TLE tle = new TLE(title, line1, line2);
```

Now you can access any of the elements

```java
double inclination = tle.getInclination();
System.out.println(inclination);
// 5.9978

double raan = tle.getRaan();
System.out.println(raan);
// 234.6882

double dragTerm = tle.getDragTerm();
System.out.println(dragTerm);
// 2.9196E-5
```

### Building a TLE

You can build a TLE from individual elements.  

#### A Little About the Design

This builder enforces the correct construction of a TLE. The [TLEBuilder](https://github.com/zakrywilson/TLE/blob/master/src/main/java/com/zakrywilson/astro/tle/TLEBuilder.java) class implements a variant of the builder pattern which enforces the user to, at a minimum, pass in the required elements into the builder for valid TLE construction. Once the required elements have been received, the `BuildStep#build()` method will be available. Part of this builder pattern's implementation is the use of *steps* which is a polymorphic procedural mitigation of the TLE's construction. For example, `SatelliteNumberStep#setSatelliteNumber(int)` returns the one option of `InternationalDesignatorStep#setInternationalDesignator(String)` which returns two options of `EpochStep#setEpoch(int, double)` or `EpochStep#setEpoch(long)`, and so forth. Once all the required steps have been completed, `BuildStep#build()` will be available for the TLE to be built. When the TLE is allowed to be built, the optional elements will also be available to be set, such as *classification* and *ephemeris type*. 

Note: there is also no checksum step: checksums are calculated when the TLE lines are generated.

The list of steps in order (after calling `TLEBuilder#newBuilder()` or `TLEBuilder#newBuilder(String)`):

1. **Satellite number step**  
2. **International designator step**  
3. **Epoch step**  
4. **Element set number step**  
5. **Orbital elements step**  
6. **Revolutions at epoch step**  
7. **Mean motion (revolutions per day) step**  
8. **First time derivative of mean motion step**  
9. **Build step**   
    a. **Second time derivative of mean motion** (*optional, default = 0.0*)  
    b. **BSTAR drag term** (*optional, default = 0.0*)  
    c. **Classification** (*optional, default = 'U'*)  
    d. **Ephemeris type** (*optional, default = 0*)  
    e. **Build TLE**

As you can see, after the *checksums* step, the TLE can now be built or any number of the optional elements can be set before calling `BuildStep#build()`.

#### Examples

```java
// Say you need to construct a TLE from existing elements
int satelliteNumber = 40930;
String internationalDesignator = "15052A";
long epoch = 1480645924864L; // Milliseconds since Jan 1, 1970 00:00:00
// and so forth

// You can use the TLE builder to construct a TLE from these elements
TLE tle = TLEBuilder.newBuilder("ASTROSAT")
                    .setSatelliteNumber(40930)
                    .setInternationalDesignator("15052A")
                    .setEpoch(1480645924864L)
                    .setElementSetNumber(999)
                    .setOrbitalElements(5.9955, 199.5858, 0.0008223, 335.4454, 24.5477)
                    .setRevolutions(6378)
                    .setMeanMotion(14.76067908)
                    .setFirstDerivativeMeanMotion(.00000876)
                    .setDragTerm(.000034425)
                    .setClassification('S')
                    .build();
```

You can use the *toString* method to get the whole TLE:

```java
System.out.println(tle);
// ASTROSAT                
// 1 40930S 15052A   16337.10561185  .00000876  00000-0  34425-4 0  9999
// 2 40930   5.9955 199.5858 0008223 335.4454  24.5477 14.76067908 63780
```

Or you can access individual elements:

```java
System.out.println(tle.getLine1());
// 1 40930S 15052A   16337.10561185  .00000876  00000-0  34425-4 0  9999

System.out.println(tle.getEccentricity());
// 8.223E-4

System.out.println(tle.getDragTerm());
// 3.4425E-5
```

You can also use the checksum to verify the validity of your TLE:

```java
System.out.println(tle.isLine2Valid());
// true
```

## Testing

The main goal of these tests were to ensure the code's ability to (1) format individual TLE elements into their respective lines (2) parse existing TLEs to extract their individual elements (3) build "correct" TLEs from descrete elements, using the builder class. Other testing was to ensure the accuracy of converting to and from a *millisecond epoch* to a *two-digit year + fractional Julian day epoch* pair.

### Code coverage

- Classes: 100%
- Methods: 91%
- Lines: 78%

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
