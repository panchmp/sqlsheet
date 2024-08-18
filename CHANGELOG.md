# sqlsheet changelog

Changelog of sqlsheet.

## sqlsheet-7.2 (2024-08-16)

### Features

-  use `ExcelStreamingReader` for reading SXFFS and obey firstColumn and firstRow parameters ([23e76](https://github.com/manticore-projects/sqlsheet/commit/23e762dd3c4f731) Andreas Reichel)
-  ResultSetMetaData for Streaming PreparedStatement ([8ee1f](https://github.com/manticore-projects/sqlsheet/commit/8ee1fc5a26a34c2) Andreas Reichel)
-  Streaming DatabaseMetaData support based on ExcelStreamingReader ([e23f5](https://github.com/manticore-projects/sqlsheet/commit/e23f50ffeee8693) Andreas Reichel)

### Other changes

**Switch to Java 11**


[53640](https://github.com/manticore-projects/sqlsheet/commit/53640b72d3fbf40) Andreas Reichel *2024-05-17 07:46:43*

**Update README.md**


[31230](https://github.com/manticore-projects/sqlsheet/commit/31230f6cac13d6f) manticore-projects *2023-06-10 07:14:57*

**Update README.md**


[251c6](https://github.com/manticore-projects/sqlsheet/commit/251c6363fe890b7) manticore-projects *2023-06-10 07:14:23*

**Create gradle.yml**


[141dd](https://github.com/manticore-projects/sqlsheet/commit/141dd52455db32b) manticore-projects *2023-06-10 06:48:49*

**format code**


[d4c95](https://github.com/manticore-projects/sqlsheet/commit/d4c95849fc1c738) Andreas Reichel *2023-06-05 00:32:40*

**remove Travis CI**


[6da42](https://github.com/manticore-projects/sqlsheet/commit/6da4282d6656b36) Michael Panchenko *2021-02-16 08:01:33*

**Circle CI**


[c6670](https://github.com/manticore-projects/sqlsheet/commit/c6670deb970085c) Michael Panchenko *2021-02-16 08:00:28*

**Fix managing resources**


[1559d](https://github.com/manticore-projects/sqlsheet/commit/1559d533366c5dd) Michael Panchenko *2021-02-16 06:29:22*

**remove unused code**


[eff4e](https://github.com/manticore-projects/sqlsheet/commit/eff4e421f8760f3) Michael Panchenko *2021-02-16 06:25:50*

**Apply Java 8 migration changes**


[c5c83](https://github.com/manticore-projects/sqlsheet/commit/c5c83b4ae0183a3) Michael Panchenko *2021-02-16 06:25:50*

**Fix error handling**


[c2e86](https://github.com/manticore-projects/sqlsheet/commit/c2e86238251cb20) Michael Panchenko *2021-02-16 06:25:50*

**Clean code**


[a6b6f](https://github.com/manticore-projects/sqlsheet/commit/a6b6f7ec7dc548e) Michael Panchenko *2021-02-16 06:25:50*

**Clean code**


[5a776](https://github.com/manticore-projects/sqlsheet/commit/5a7769175ce4e3d) Michael Panchenko *2021-02-16 06:25:50*

**Apply Java 7 migration changes**


[38106](https://github.com/manticore-projects/sqlsheet/commit/381069ffb7b1f4e) Michael Panchenko *2021-02-16 06:25:50*

**fix typo**


[33a9d](https://github.com/manticore-projects/sqlsheet/commit/33a9d95cebad479) Michael Panchenko *2021-02-16 06:25:50*

**Apply Java 5 migration changes**


[010b8](https://github.com/manticore-projects/sqlsheet/commit/010b82c126c028f) Michael Panchenko *2021-02-16 06:25:50*

**optimize import**


[d7d2a](https://github.com/manticore-projects/sqlsheet/commit/d7d2a78a31483d2) Michael Panchenko *2021-02-16 04:24:54*

**Remove unnecessary throws declaration**


[3913e](https://github.com/manticore-projects/sqlsheet/commit/3913ead60c3bcdf) Michael Panchenko *2021-02-16 04:24:54*

**Refactor "Not supported yet"**


[28587](https://github.com/manticore-projects/sqlsheet/commit/2858776a962a44c) Michael Panchenko *2021-02-16 04:24:54*

**Migrate logging to slf4j**


[b98f1](https://github.com/manticore-projects/sqlsheet/commit/b98f1be7aa53768) Michael Panchenko *2021-02-16 00:34:16*

**Update maven plugins**


[ff3b5](https://github.com/manticore-projects/sqlsheet/commit/ff3b59bcd2c2445) Michael Panchenko *2021-02-15 23:57:58*

**refactor junit tests (#42)**


[a881e](https://github.com/manticore-projects/sqlsheet/commit/a881e867937a917) Michael *2021-02-15 23:46:21*

**Update Apache POI 5.0.0 (#41)**

* Update JSqlParser 4.0
* Format the java code with Google Code Formatter

[2c2d8](https://github.com/manticore-projects/sqlsheet/commit/2c2d83677734178) manticore-projects *2021-02-15 23:04:17*

**Update Apache POI 5.0.0**

* Update JSqlParser 4.0
* Format the java code with Google Code Formatter

[fa89d](https://github.com/manticore-projects/sqlsheet/commit/fa89de7f70bcb2e) Andreas Reichel *2021-01-20 07:23:04*

**remove travis instruction to build project on specific (not any more supported) JDK versions**

* we use TravisCI, CircleCI integration is not necessary

[d5e60](https://github.com/manticore-projects/sqlsheet/commit/d5e6031f33ee1cd) Klaus Hauschild *2020-11-27 18:53:36*

**fix the Java Doc**


[138e7](https://github.com/manticore-projects/sqlsheet/commit/138e7679f09da35) Andreas Reichel *2020-11-25 12:18:42*

**add Java Doc**


[e6b7e](https://github.com/manticore-projects/sqlsheet/commit/e6b7e33f6beaf45) Andreas Reichel *2020-11-25 12:08:51*

**Implement support for relative Connection URLs, such as**

* jdbc:xls:file://~/headline.xlsx  (tilde resolve to $HOME)
* jdbc:xls:file://${user.home}/headline.xlsx (system property resolves to $HOME}
* jdbc:xls:classpath:/headline.xlsx (resource in classpath)
* Resolves Issue #29
* Testcase provided

[f4719](https://github.com/manticore-projects/sqlsheet/commit/f471936cd5c31cd) Andreas Reichel *2020-11-25 11:54:28*

**Implement Close(), isClosed(), wasNull() and rewrite most of the getXXXX() methods accordingly (#36)**

* Update versions of POI and COMMONS IO
* Add GLOB for SQL like pattern matching
* Add test dependency ASCII Table for formatted test output
* Add author Andreas Reichel
* Implement DatabaseMetaData
* for some methods, throw SQLFeatureNotSupported instead of Not Yet Implemented so SQL Clients can continue instead of failing
* Provide separate DatabaseMetaData (work in progress)
* Test case for DatabaseMetaData
* Update Driver Class name and call it only before the test
* Reformat the tests
* exclude Netbeans config folders
* implement Statement.close(), Statement.isClosed() and Statement.closeOnCompletion()
* throw Unsupported SQL Feature Exception, where we won&#x27;t be able to implement a solution
* implement ResultSet.close(), ResultSet.isClosed()
* implement ResultSet.wasNull() because most methods return primitives on NULL
* rewrite most of the getXXXX() methods in order the set wasNull on empty rows and empty cells
* rewrite most of getXXXX() method parameters (using columnIndex and columnLabel from the API)
* avoid NPE on empty rows
* rewrite most of the getXXXX() methods returning Objects in order the set wasNull on empty rows and empty cells
* rewrite most of getXXXX() returning Objects method parameters (using columnIndex and columnLabel from the API)
* Testcase around Close(), isClosed(), closeOnCompletion, wasNull()
* Should close issue #1
* need to set wasNull&#x3D;false when value has been found
* avoid NPE in getTimestamp()
* enhance the Test and show the actual content
* Enhance test, particularly testing getXXXX(columnIndex) vs. getXXXX(columnLabel)
* Fix everything, we broke (sorry!)
* All tests passed.
* ensure Tests will have enough Memory available, avoid the large streaming tests to fail.
* fix JavaDoc
* Evaluate Cell formulas before reading the cell value

[38002](https://github.com/manticore-projects/sqlsheet/commit/38002e86df463be) manticore-projects *2020-11-21 15:18:17*

**Evaluate Cell formulas before reading the cell value**


[e4abd](https://github.com/manticore-projects/sqlsheet/commit/e4abdd5955a615b) Andreas Reichel *2020-11-21 12:23:41*

**fix JavaDoc**


[965b4](https://github.com/manticore-projects/sqlsheet/commit/965b4c0374fb452) Andreas Reichel *2020-10-27 12:46:46*

**ensure Tests will have enough Memory available, avoid the large streaming tests to fail.**


[7ee68](https://github.com/manticore-projects/sqlsheet/commit/7ee68e6b390e068) Andreas Reichel *2020-10-27 12:43:31*

**Enhance test, particularly testing getXXXX(columnIndex) vs. getXXXX(columnLabel)**

* Fix everything, we broke (sorry!)
* All tests passed.

[3e66e](https://github.com/manticore-projects/sqlsheet/commit/3e66e99c63b5ea2) Andreas Reichel *2020-10-27 12:20:03*

**enhance the Test and show the actual content**


[bb429](https://github.com/manticore-projects/sqlsheet/commit/bb429a18703b8e1) Andreas Reichel *2020-10-27 10:38:38*

**need to set wasNull=false when value has been found**

* avoid NPE in getTimestamp()

[0911b](https://github.com/manticore-projects/sqlsheet/commit/0911be325c6bcbd) Andreas Reichel *2020-10-27 10:35:24*

**Testcase around Close(), isClosed(), closeOnCompletion, wasNull()**

* Should close issue #1

[6d5f8](https://github.com/manticore-projects/sqlsheet/commit/6d5f89576a495a3) Andreas Reichel *2020-10-27 10:05:31*

**rewrite most of the getXXXX() methods returning Objects in order the set wasNull on empty rows and empty cells**

* rewrite most of getXXXX() returning Objects method parameters (using columnIndex and columnLabel from the API)

[d9d03](https://github.com/manticore-projects/sqlsheet/commit/d9d03613e5efc59) Andreas Reichel *2020-10-27 10:02:45*

**implement ResultSet.close(), ResultSet.isClosed()**

* implement ResultSet.wasNull() because most methods return primitives on NULL
* rewrite most of the getXXXX() methods in order the set wasNull on empty rows and empty cells
* rewrite most of getXXXX() method parameters (using columnIndex and columnLabel from the API)
* avoid NPE on empty rows

[54406](https://github.com/manticore-projects/sqlsheet/commit/54406f7fa19cd05) Andreas Reichel *2020-10-27 09:51:05*

**implement Statement.close(), Statement.isClosed() and Statement.closeOnCompletion()**

* throw Unsupported SQL Feature Exception, where we won&#x27;t be able to implement a solution

[d90fd](https://github.com/manticore-projects/sqlsheet/commit/d90fd9b031237c0) Andreas Reichel *2020-10-27 09:46:05*

**Implement DatabaseMetaData (#35)**

* Update versions of POI and COMMONS IO
* Add GLOB for SQL like pattern matching
* Add test dependency ASCII Table for formatted test output
* Add author Andreas Reichel
* Implement DatabaseMetaData
* for some methods, throw SQLFeatureNotSupported instead of Not Yet Implemented so SQL Clients can continue instead of failing
* Provide separate DatabaseMetaData (work in progress)
* Test case for DatabaseMetaData
* Update Driver Class name and call it only before the test
* Reformat the tests
* exclude Netbeans config folders

[418ec](https://github.com/manticore-projects/sqlsheet/commit/418ecebf9cff76a) manticore-projects *2020-10-27 03:48:51*

**exclude Netbeans config folders**


[e2ba5](https://github.com/manticore-projects/sqlsheet/commit/e2ba5e9d3961de1) Andreas Reichel *2020-10-26 12:29:05*

**Test case for DatabaseMetaData**

* Update Driver Class name and call it only before the test
* Reformat the tests

[a2b88](https://github.com/manticore-projects/sqlsheet/commit/a2b88a23d6a1a90) Andreas Reichel *2020-10-26 12:27:56*

**Provide separate DatabaseMetaData (work in progress)**


[0c325](https://github.com/manticore-projects/sqlsheet/commit/0c32534ca1ca836) Andreas Reichel *2020-10-26 12:26:59*

**Implement DatabaseMetaData**

* for some methods, throw SQLFeatureNotSupported instead of Not Yet Implemented so SQL Clients can continue instead of failing

[2644e](https://github.com/manticore-projects/sqlsheet/commit/2644ed8a9d36410) Andreas Reichel *2020-10-26 12:26:08*

**Update versions of POI and COMMONS IO**

* Add GLOB for SQL like pattern matching
* Add test dependency ASCII Table for formatted test output
* Add author Andreas Reichel

[b9623](https://github.com/manticore-projects/sqlsheet/commit/b96233a4e927156) Andreas Reichel *2020-10-26 12:20:50*

**Bump junit from 4.12 to 4.13.1 (#34)**

* Bumps [junit](https://github.com/junit-team/junit4) from 4.12 to 4.13.1.
* - [Release notes](https://github.com/junit-team/junit4/releases)
* - [Changelog](https://github.com/junit-team/junit4/blob/main/doc/ReleaseNotes4.12.md)
* - [Commits](https://github.com/junit-team/junit4/compare/r4.12...r4.13.1)
* Signed-off-by: dependabot[bot] &lt;support@github.com&gt;
* Co-authored-by: dependabot[bot] &lt;49699333+dependabot[bot]@users.noreply.github.com&gt;

[81fa8](https://github.com/manticore-projects/sqlsheet/commit/81fa8fe291655f8) dependabot[bot] *2020-10-12 23:02:07*

**Create FUNDING.yml**


[97638](https://github.com/manticore-projects/sqlsheet/commit/976381f2b952752) Michael *2020-06-25 22:00:22*

**add donation button**


[5af84](https://github.com/manticore-projects/sqlsheet/commit/5af849487a0bdb8) Michael *2020-01-11 17:07:00*


## sqlsheet-7.1 (2019-11-15)

### Other changes

**prepare for release**


[e3273](https://github.com/manticore-projects/sqlsheet/commit/e3273e84b1d0423) Michael Panchenko *2019-11-15 07:01:33*

**fix scm**


[23ca2](https://github.com/manticore-projects/sqlsheet/commit/23ca2d1cf564ed4) Michael Panchenko *2019-11-15 06:45:04*

**- update to JDK8**

* - update dependencies
* - update plugins

[1a053](https://github.com/manticore-projects/sqlsheet/commit/1a053f131df432e) Michael Panchenko *2019-10-21 23:48:04*

**read a numeric cell as Date whenever possible, even when the column type would indicate a text**


[44edc](https://github.com/manticore-projects/sqlsheet/commit/44edc3a9dacf834) Andreas Reichel *2019-10-21 04:03:19*

**update JSQLParser to 3.0**

* update POI to 4.1.1
* add a switch to skip tests

[6dcad](https://github.com/manticore-projects/sqlsheet/commit/6dcad19fa2b20de) Andreas Reichel *2019-10-21 02:47:05*

**update JSQLParser to 3.0**


[0928a](https://github.com/manticore-projects/sqlsheet/commit/0928aa689a791c5) Andreas Reichel *2019-10-21 02:46:08*

**add a test case for first column**


[8e5da](https://github.com/manticore-projects/sqlsheet/commit/8e5dab18d225ecd) Andreas Reichel *2019-10-21 02:45:42*

**fix reading doubles**


[75034](https://github.com/manticore-projects/sqlsheet/commit/7503496b0b24f0f) Andreas Reichel *2019-10-21 02:45:23*

**update POI to 4.1.1**


[432b0](https://github.com/manticore-projects/sqlsheet/commit/432b0accad0db67) Andreas Reichel *2019-10-21 02:44:21*

**update POI to 4.1.1**


[86902](https://github.com/manticore-projects/sqlsheet/commit/86902c6f9f96181) Andreas Reichel *2019-10-21 02:44:06*

**add firstColumn connection parameter**


[b6fac](https://github.com/manticore-projects/sqlsheet/commit/b6facd8855c1f43) Andreas Reichel *2019-10-21 02:43:43*

**add firstColumn connection parameter**

* update POI to 4.1.1

[36ffc](https://github.com/manticore-projects/sqlsheet/commit/36ffc350b3fa516) Andreas Reichel *2019-10-21 02:43:22*

**add firstColumn connection parameter**


[1d978](https://github.com/manticore-projects/sqlsheet/commit/1d9781cdecf86a3) Andreas Reichel *2019-10-21 02:42:42*

**add firstColumn connection parameter**


[1bf3d](https://github.com/manticore-projects/sqlsheet/commit/1bf3d02df665b4d) Andreas Reichel *2019-10-21 02:42:16*

**Deprecate old classes**


[140b6](https://github.com/manticore-projects/sqlsheet/commit/140b66a592d6f23) Andreas Reichel *2019-10-21 01:44:08*

**Fix the endless recursive loop on getObject(String columnName)**

* Fix reading double values

[86d80](https://github.com/manticore-projects/sqlsheet/commit/86d80e27e536b2d) Andreas Reichel *2019-10-21 01:40:47*

**Delete ReleaseProcedures.md**


[8a0a7](https://github.com/manticore-projects/sqlsheet/commit/8a0a7a83b6edb8f) Michael *2017-12-23 01:34:46*

**Move to more fitting package #26**

* restore SCM

[2a5ac](https://github.com/manticore-projects/sqlsheet/commit/2a5acf91618e26a) Klaus Hauschild *2017-05-22 17:24:23*

**Move to more fitting package #26**

* realign GAV coordinates to match new package

[56aa7](https://github.com/manticore-projects/sqlsheet/commit/56aa726d95f5fa2) Klaus Hauschild *2017-05-22 17:20:01*

**Move to more fitting package #26**

* fix compile and test failures
* reformat whole soruce code

[53a5e](https://github.com/manticore-projects/sqlsheet/commit/53a5e9860d476df) Klaus Hauschild *2017-05-22 17:20:00*

**Move to more fitting package #26**

* move XlsDriver to package com.sqlsheet

[caca4](https://github.com/manticore-projects/sqlsheet/commit/caca4abcb54346a) Klaus Hauschild *2017-05-22 17:18:30*

**fix scm**


[6ecd2](https://github.com/manticore-projects/sqlsheet/commit/6ecd2ef5a4401f2) Michael Panchenko *2017-05-15 05:54:31*

**fix developerConnection**


[bbfcd](https://github.com/manticore-projects/sqlsheet/commit/bbfcd7c715adc51) Michael Panchenko *2017-05-15 05:42:27*

**upgrade poi to 3.14 -> 3.16**


[18761](https://github.com/manticore-projects/sqlsheet/commit/18761dfe459d716) Michael Panchenko *2017-05-15 05:12:05*


## sqlsheet-6.7 (2017-05-15)

### Other changes

**switch git from https to git protocol**


[0b3be](https://github.com/manticore-projects/sqlsheet/commit/0b3be8387871f8e) Michael Panchenko *2017-05-15 05:02:15*

**prepare pom.xml for releasing**


[834ba](https://github.com/manticore-projects/sqlsheet/commit/834badc31d7843a) Michael Panchenko *2017-05-15 04:50:37*


## sqlsheet-6.6 (2017-05-15)

### Other changes

**downgrade poi to 3.16 -> 3.14**


[0ce25](https://github.com/manticore-projects/sqlsheet/commit/0ce257450d34621) Michael Panchenko *2017-05-15 04:04:24*

**update pom file**


[48b66](https://github.com/manticore-projects/sqlsheet/commit/48b665d40211e0f) Michael Panchenko *2017-05-15 03:39:29*

**Fixed javadoc issue**


[94310](https://github.com/manticore-projects/sqlsheet/commit/94310496fea2927) Stephen Rufle *2017-04-22 08:16:20*

**File backups on XlsConnection.close() breaks the CI builds #24**

* simplified peristing-to-file mechanism (deactivate backup strategy); may cause other problems but hopefully fix the CI builds

[aae89](https://github.com/manticore-projects/sqlsheet/commit/aae893fd4aefc30) Klaus Hauschild *2017-04-18 18:53:42*

**Missing artifact net.sf:jsqlparser:jar:0.8.0 #23**

* revert failed release attempt

[65e41](https://github.com/manticore-projects/sqlsheet/commit/65e410daa34873b) Klaus Hauschild *2017-04-18 17:45:10*

**Missing artifact net.sf:jsqlparser:jar:0.8.0 #23**

* update some plugin versions
* update urls

[3965f](https://github.com/manticore-projects/sqlsheet/commit/3965fefe41bb922) Klaus Hauschild *2017-04-18 17:38:38*

**add circle.yml**


[195e9](https://github.com/manticore-projects/sqlsheet/commit/195e96f881fd614) Michael *2016-04-01 07:02:24*

**enable XlsxSheetIteratorTest**


[f656a](https://github.com/manticore-projects/sqlsheet/commit/f656a724716b9db) Michael *2016-04-01 07:01:54*

**temporary disable XlsxSheetIteratorTest**


[32d5a](https://github.com/manticore-projects/sqlsheet/commit/32d5a2bf41fc6b9) Michael *2016-04-01 06:28:17*

**update maven plugins & dependencies**


[cbf1c](https://github.com/manticore-projects/sqlsheet/commit/cbf1cb36c48c665) Michael *2016-04-01 06:24:34*

**Build fixed and jsqlparser upgraded to 0.9.5**


[b3ecc](https://github.com/manticore-projects/sqlsheet/commit/b3ecc93364bcef4) kadeev *2016-03-24 14:26:58*

**Bug with quoted columns fixed and 31 symbol excel sheet truncation added**


[1b944](https://github.com/manticore-projects/sqlsheet/commit/1b94440f1ee320f) kadeev *2016-03-23 15:21:36*

**Determine the data type of each column by scanning the whole sheet, returning java.sql.date in place of java.util.Date**


[6c27b](https://github.com/manticore-projects/sqlsheet/commit/6c27b78ced0a2e2) Gerardnico *2015-12-01 20:51:04*

**Create unique header,column name if duplicates**


[48ad5](https://github.com/manticore-projects/sqlsheet/commit/48ad556afabe86b) Gerardnico *2015-12-01 14:00:37*

**Added help message when the sheet is not found**


[920b8](https://github.com/manticore-projects/sqlsheet/commit/920b8ed27a13b26) Gerardnico *2015-12-01 13:07:04*

**DataType consistency over the whole records**


[a1ef4](https://github.com/manticore-projects/sqlsheet/commit/a1ef45a1960181c) Gerardnico *2015-12-01 13:01:46*

**my bad: Maven couldnot refresh the resources file. The drop occurs now after the create table statement**


[f4b39](https://github.com/manticore-projects/sqlsheet/commit/f4b39b9efba6bdf) Gerardnico *2015-11-27 19:36:26*

**Added support for a drop table statement because of an error in DriverTest as the sheet name was already present**


[73d5c](https://github.com/manticore-projects/sqlsheet/commit/73d5cacc275c4ca) Gerardnico *2015-11-27 18:50:34*

**Last version of POI to resolve an undefined class problem with on org/apache/poi/UnsupportedFileFormatException**


[c68b1](https://github.com/manticore-projects/sqlsheet/commit/c68b188f1fc422e) Gerardnico *2015-11-27 18:49:23*

**Java doc problem resolved**


[70240](https://github.com/manticore-projects/sqlsheet/commit/7024065cdc9fac7) Gerardnico *2015-11-27 18:47:38*

**add travis-ci.org**


[fca87](https://github.com/manticore-projects/sqlsheet/commit/fca87b1518750ce) Michael Panchenko *2015-03-20 05:15:56*

**remove readme**


[702f4](https://github.com/manticore-projects/sqlsheet/commit/702f447a5171068) Michael Panchenko *2015-03-20 03:10:37*

**move gpg to release profile**


[65acc](https://github.com/manticore-projects/sqlsheet/commit/65accaa77cf9695) Michael Panchenko *2015-03-20 03:09:37*

**add travis-ci.org**


[e9ed6](https://github.com/manticore-projects/sqlsheet/commit/e9ed680f929b54d) Michael Panchenko *2015-03-20 03:04:02*

**add gitignore**


[5e0f2](https://github.com/manticore-projects/sqlsheet/commit/5e0f26bdb27935f) Michael Panchenko *2015-03-20 03:03:33*

**Remove additional subdirectory sqlsheet**


[06e1f](https://github.com/manticore-projects/sqlsheet/commit/06e1f56472b636a) Michael Panchenko *2015-03-20 03:01:43*

**Create HowToMaven.md**


[8e90e](https://github.com/manticore-projects/sqlsheet/commit/8e90ea2c208ccef) Michael *2015-03-14 01:07:18*

**Create ReleaseProcedures.md**


[2955f](https://github.com/manticore-projects/sqlsheet/commit/2955f9953174386) Michael *2015-03-14 01:04:52*

**Create HowToGetDependecies.md**


[420d5](https://github.com/manticore-projects/sqlsheet/commit/420d51136b2266a) Michael *2015-03-14 01:03:45*

**Create HowToProcessLargeFile.md**


[769e9](https://github.com/manticore-projects/sqlsheet/commit/769e9d21a9ad08a) Michael *2015-03-14 00:57:36*

**Update README.md**


[a53c5](https://github.com/manticore-projects/sqlsheet/commit/a53c581fb93d4f5) Michael *2015-03-14 00:54:19*

**Update HowToScriptella.md**


[11e29](https://github.com/manticore-projects/sqlsheet/commit/11e291111ec5af7) Michael *2015-03-14 00:47:29*

**Update HowToScriptella.md**


[33eec](https://github.com/manticore-projects/sqlsheet/commit/33eece7e986982a) Michael *2015-03-14 00:46:00*

**Update HowToScriptella.md**


[a79f1](https://github.com/manticore-projects/sqlsheet/commit/a79f1439be6e562) Michael *2015-03-14 00:43:22*

**Create HowToScriptella.md**


[699d4](https://github.com/manticore-projects/sqlsheet/commit/699d42d3bf45262) Michael *2015-03-14 00:37:59*

**Create README.md**


[18892](https://github.com/manticore-projects/sqlsheet/commit/188929f2320cdcc) Michael *2015-03-14 00:34:48*

**update version of jsqlparser**


[09114](https://github.com/manticore-projects/sqlsheet/commit/09114ecccbba631) vasilievip@gmail.com *2014-10-08 11:40:09*

**update plugins and dependencies**


[29230](https://github.com/manticore-projects/sqlsheet/commit/29230c10e7b1c95) klaus.hauschild.1984@gmail.com *2014-08-23 22:18:52*

**introduce the connection parameter "headLine" to define the index of the first row within a sheet (if the the parameter is not specified the default of 1 will be used)**


[17d75](https://github.com/manticore-projects/sqlsheet/commit/17d75f2022127e0) klaus.hauschild.1984@gmail.com *2014-08-23 22:13:30*

**add me as developer**


[57403](https://github.com/manticore-projects/sqlsheet/commit/574035d660c3301) klaus.hauschild.1984@gmail.com *2014-08-23 17:21:09*

**re-layout project structure**


[49237](https://github.com/manticore-projects/sqlsheet/commit/4923768708ebf01) klaus.hauschild.1984@gmail.com *2014-08-23 17:16:27*

**Avoid using if statements without curly braces**


[c94fd](https://github.com/manticore-projects/sqlsheet/commit/c94fd0d656a2f66) vasilievip@gmail.com *2013-06-09 07:45:16*

**These nested if statements could be combined**


[d4503](https://github.com/manticore-projects/sqlsheet/commit/d45035035da4600) vasilievip@gmail.com *2013-06-09 07:38:18*

**Dead store to brec in com.googlecode.sqlsheet.stream.XlsSheetIterator.processRecord(Record)**


[0b2fc](https://github.com/manticore-projects/sqlsheet/commit/0b2fc518ef30a69) vasilievip@gmail.com *2013-06-09 07:34:55*

**minor changes**


[4ac8e](https://github.com/manticore-projects/sqlsheet/commit/4ac8ef51ae4ada9) vasilievip@gmail.com *2013-06-09 07:22:38*

**(Fixes  Issue 8, Issue 9 ) + sonar cleanup**


[64124](https://github.com/manticore-projects/sqlsheet/commit/641243a64c753a0) vasilievip@gmail.com *2013-04-28 12:57:37*

**sonar cleanup**


[0446b](https://github.com/manticore-projects/sqlsheet/commit/0446b05a98d9e69) vasilievip@gmail.com *2013-04-27 22:14:38*

**(Fixes  issue 6 )**


[f0814](https://github.com/manticore-projects/sqlsheet/commit/f0814f202dbc0fe) vasilievip@gmail.com *2013-04-27 19:00:26*

**Test case and data for Issue 9**


[59965](https://github.com/manticore-projects/sqlsheet/commit/59965daaa7c9f2c) klaus.hauschild.1984@gmail.com *2013-04-27 18:15:45*

**Test case and data for Issue 8**


[615f8](https://github.com/manticore-projects/sqlsheet/commit/615f8efb3317c99) klaus.hauschild.1984@gmail.com *2013-04-27 18:06:54*

**fix Issue 7 and related test case**


[73bc5](https://github.com/manticore-projects/sqlsheet/commit/73bc521e838f917) klaus.hauschild.1984@gmail.com *2013-04-27 17:34:19*

**Issue 7, Issue 8 testcases**


[8d793](https://github.com/manticore-projects/sqlsheet/commit/8d79379c26b1c77) vasilievip@gmail.com *2013-04-27 13:02:14*

**Issue 5: 	Deploy to Maven Central Repository**


[593a8](https://github.com/manticore-projects/sqlsheet/commit/593a85cd290923b) Michael *2013-03-31 19:00:12*

**Issue 5: 	Deploy to Maven Central Repository**


[4c888](https://github.com/manticore-projects/sqlsheet/commit/4c888c731689a14) Michael *2013-03-31 18:44:27*

**Issue 5: 	Deploy to Maven Central Repository**


[39184](https://github.com/manticore-projects/sqlsheet/commit/3918442fce257a4) Michael *2013-03-31 11:14:56*

**fix backup creation**


[46d55](https://github.com/manticore-projects/sqlsheet/commit/46d55d56e08342f) vasilievip@gmail.com *2013-03-24 17:27:23*

**apply changes from Andreas Reichel**


[39cbc](https://github.com/manticore-projects/sqlsheet/commit/39cbc6a6e93869d) vasilievip@gmail.com *2013-03-24 08:18:57*

**Issue 5: 	Deploy to Maven Central Repository**


[390ef](https://github.com/manticore-projects/sqlsheet/commit/390efff57d3142c) Michael *2013-01-28 11:18:14*

**Issue 5: 	Deploy to Maven Central Repository**


[b2259](https://github.com/manticore-projects/sqlsheet/commit/b2259346b56e636) Michael *2013-01-28 09:33:53*

**sqlsheet latest**


[7172f](https://github.com/manticore-projects/sqlsheet/commit/7172f4200c2d399) vasilievip@gmail.com *2012-09-24 19:30:34*

**added testcase**


[f3122](https://github.com/manticore-projects/sqlsheet/commit/f3122bfb21940fa) vasilievip@gmail.com *2012-05-20 08:52:57*

**(Fixes issue 3)**

* - null cell detection fixed for XLSX

[0abdd](https://github.com/manticore-projects/sqlsheet/commit/0abdd58f144e1af) vasilievip@gmail.com *2012-05-19 08:55:45*

**minor changes**


[73064](https://github.com/manticore-projects/sqlsheet/commit/730646093ec104d) vasilievip@gmail.com *2012-05-03 11:34:07*

**minor changes**


[acc67](https://github.com/manticore-projects/sqlsheet/commit/acc67127cab2dd8) vasilievip@gmail.com *2012-05-03 11:30:20*

**fixed issues related to generic excel file processing which is not formatted as a table**


[c83bb](https://github.com/manticore-projects/sqlsheet/commit/c83bb6ac1c4f892) vasilievip@gmail.com *2012-05-03 10:56:20*

**(Fixes issue 1)**

* - read streaming implemented
* - write streaming implemented
* - minor bug fix

[dbc35](https://github.com/manticore-projects/sqlsheet/commit/dbc35bbb0916b10) vasilievip@gmail.com *2012-05-02 21:34:32*

**rename testcase to appropriate name**


[be56c](https://github.com/manticore-projects/sqlsheet/commit/be56cb6299b8902) vasilievip@gmail.com *2012-04-30 10:28:09*

**(Fixes issue 2)**

* - backup file before rewrite
* - detect that changes introduced and optionally skip rewrite

[77905](https://github.com/manticore-projects/sqlsheet/commit/77905a8d0da3472) vasilievip@gmail.com *2012-04-30 09:54:04*

**code cleanup**


[560f7](https://github.com/manticore-projects/sqlsheet/commit/560f7dcd7a9b2b8) vasilievip@gmail.com *2012-04-07 10:03:18*

**minor changes**


[4173a](https://github.com/manticore-projects/sqlsheet/commit/4173a1ff1c02dac) vasilievip@gmail.com *2012-04-07 09:48:30*

**fix build for jdk 1.6**


[63e6a](https://github.com/manticore-projects/sqlsheet/commit/63e6a785390631d) vasilievip@gmail.com *2012-04-06 20:22:07*

**fix build**


[1ca1a](https://github.com/manticore-projects/sqlsheet/commit/1ca1af9788fc2ec) vasilievip@gmail.com *2012-04-06 20:06:21*

**- testcase added**

* - system dep removed
* - new jsqlparser added

[f14f5](https://github.com/manticore-projects/sqlsheet/commit/f14f56475c8f45e) vasilievip@gmail.com *2012-04-06 18:42:32*

**code cleanup**


[31197](https://github.com/manticore-projects/sqlsheet/commit/31197b1b7aa3faf) vasilievip@gmail.com *2012-04-06 15:36:55*

**Remove ant files**


[9db42](https://github.com/manticore-projects/sqlsheet/commit/9db42994b26960e) Michael *2010-12-27 22:10:59*

**minor changes**


[932d7](https://github.com/manticore-projects/sqlsheet/commit/932d797c54fb399) vasilievip *2010-11-26 21:40:33*

**added offline dependency**


[1404d](https://github.com/manticore-projects/sqlsheet/commit/1404de4619a4e6d) vasilievip *2010-11-26 21:33:51*

**prepare for release**


[217e9](https://github.com/manticore-projects/sqlsheet/commit/217e9394f9cd831) Michael *2010-11-15 20:26:43*

**prepare for release**


[2c9af](https://github.com/manticore-projects/sqlsheet/commit/2c9afc9abb3c54e) Michael *2010-11-15 20:23:50*

**apply Ivan's changes**


[dcf6a](https://github.com/manticore-projects/sqlsheet/commit/dcf6a5c103b1de1) Michael *2010-11-15 19:49:17*

**Initial commit**


[6eaa4](https://github.com/manticore-projects/sqlsheet/commit/6eaa47d7ec4e8d8) Michael *2010-09-28 20:16:51*
