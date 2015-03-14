#How to get dependecies?=
You have several options:
* Use as [maven dependency](http://code.google.com/p/sqlsheet/wiki/HowToMaven)
* Use [POI](http://poi.apache.org/download.html) distribution and [check downloads](http://code.google.com/p/sqlsheet/downloads/list)
* Compile sources with [maven](http://maven.apache.org/download.html)
  *  [Chekout](http://tortoisesvn.net/downloads.html) [sources](http://code.google.com/p/sqlsheet/source/checkout)
  *  Compile
```bash
     mvn clean install
```
  * Go to ${sources}/target/libs
  * Grab jars

## Requirements for [sqlsheet 5+](http://code.google.com/p/sqlsheet/downloads)
 * java5+
 * [jsqlparser-0.6.2.a.jar](http://code.google.com/p/sqlsheet/downloads)
 * [Latest POI](http://poi.apache.org/download.html)

## Requirements for [sqlsheet 6+](http://code.google.com/p/sqlsheet/downloads)
 * java6+
 * [jsqlparser-0.7.0+](http://code.google.com/p/sqlsheet/downloads)
 * [Latest POI]{http://poi.apache.org/download.html)


### POI required libs for [XLS](http://poi.apache.org/download.html) 
 * poi-3.8.jar

### POI required libs for [XLSX](http://poi.apache.org/download.html)
 * poi-3.8.jar
 * dom4j-1.6.1.jar
 * poi-ooxml-3.8.jar
 * poi-ooxml-schemas-3.8.jar
 * stax-api-1.0.1.jar
 * xmlbeans-2.3.0.jar
