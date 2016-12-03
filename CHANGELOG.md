# Change Log

---

## Version 1.1 - 12/02/2016

Extended checksum functionality

- TLEBuilder now generates checksums for the TLE lines instead of having the user calculate them manually  
- Added ChecksumUtils class for creating, verifying, and extracting checksums from TLE lines (not public)  
- Added `isLine1Valid()` and `isLine2Valid()` methods to TLE class that check if the lines match their respective checksum value  

Updated unit tests

- Updated previously existing unit tests to account for checksum functionality changes  
- Added unit tests for ChecksumUtils class  

Added CHANGELOG.md


## Version 1.0 - 11/26/2016

Created basic functionality of TLE package

- Added TLE.java
- Added TLEBuilder.java for building valid TLEs
- Added TLEParser.java (not public), used for parsing TLE lines
- Added TLEFormatter.java (not public), used for formatting TLE lines
- Added EpochUtils.java (not public), used for conversions between epoch milliseconds (since Jan 1, 1970 00:00:00) and TLE the TLE epoch format (*yyddd.dddddddd*)

Added unit tests for all classes

Added README.md

Added LICENSE.md (MIT license)