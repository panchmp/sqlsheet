#summary Page describes how to get this driver working with scriptella.
#labels Featured

= Introduction =

You need to download [http://scriptella.javaforge.com/download.html scriptella], [http://code.google.com/p/sqlsheet/downloads sqlsheet driver] and it's [HowToGetDependecies dependencies].


= Details =

Follow the steps:
  * Download [http://scriptella.javaforge.com/download.html scriptella] binary distribution
  * Unpack it and add a ${SCRIPTELLA_DIR}/bin to a system PATH variable
   # Use set PATH=%PATH%;SCRIPTELLA_DIR\bin for Windows
   # Use export PATH=${PATH}:SCRIPTELLA_DIR/bin for Unix
  * Check if JRE has been installed correctly by running:
```bash
java -version
```
  * Check if scriptella has been installed correctly by running:
```bash
scriptella -version
```
  * Put [http://code.google.com/p/sqlsheet/downloads sqlsheet] driver to  the ${SCRIPTELLA_DIR}/lib directory
  * Put sqlsheet driver [HowToGetDependecies dependencies] to the ${SCRIPTELLA_DIR}/lib directory
  * Put [http://dist.codehaus.org/janino/changelog.html janino.jar] and [http://dist.codehaus.org/janino/changelog.html commons-compiler.jar] to the ${SCRIPTELLA_DIR}/lib directory
  * Download [http://code.google.com/p/sqlsheet/downloads/detail?name=xls_test.xml test] script
  * Execute test script:
```bash
scriptella xls_test.xml
```
  * Check generated XLS/XLSX files
  * Enjoy! 
