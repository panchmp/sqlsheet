[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://paypal.me/panchmp)


Simple, POI based JDBC driver for XLS/XLSX files. Currently supports basic SELECT ( 'select `*`' only, w/o where clause), CREATE,  INSERT operations. The driver is pure Java - no native Windows components are required. From version 6.1 streaming implemented to process large files.

Fork of http://www.pcal.net/downloads/sqlsheet/


First Header|Second Header
-------------|-------------
Database | XLS/XLSX file
Table | Sheet in XLS/XLSX file
Column | Column in sheet (first row must have column names)
Row | Row in sheet starting from second

## Usage example:
 * [Java code example](http://code.google.com/p/sqlsheet/wiki/HowToMaven)
 * [Scriptella driver](http://scriptella.javaforge.com/docs/api/scriptella/driver/xls/package-summary.html#package_description)
 * [driver](https://code.google.com/p/sqlsheet/source/browse/trunk/sqlsheet/src/test/resources/xlsload.xml) in [ unit test](https://code.google.com/p/sqlsheet/source/browse/trunk/sqlsheet/src/test/java/com/googlecode/sqlsheet/XlsDriverIntegrationTest.java)

```java
Class.forName("com.googlecode.sqlsheet.Driver");

Connection writeConnection = DriverManager.getConnection("jdbc:xls:file:test.xlsx");

Statement writeStatement = writeConnection.createStatement();

writeStatement.executeUpdate("CREATE TABLE TEST_INSERT(COL1 INT, COL2 VARCHAR(255), COL3 DATE)");

PreparedStatement writeStatement2 =
writeConnection.prepareStatement("INSERT INTO TEST_INSERT(COL1, COL2, COL3) VALUES(?,?,?)");

for(int i = 0; i<3;i++){
  writeStatement2.setDouble(1, i);
  writeStatement2.setString(2, "Row" + i);
  writeStatement2.setDate(3, new java.sql.Date(new Date().getTime()));
  writeStatement2.execute();
}

writeStatement.close();
writeStatement2.close();
writeConnection.close();
```

```xml
<!DOCTYPE etl SYSTEM "http://scriptella.javaforge.com/dtd/etl.dtd">
<etl>

  <connection id="xls" url="jdbc:xls:file:extracttest.xls"/>
  <connection id="xlsx" url="jdbc:xls:file:extracttest.xlsx"/>
  <connection id="java" driver="janino"/>

  <script connection-id="xls">
    CREATE TABLE "2009"(
      COL1 INT,
      COL2 VARCHAR(255),
      COL3 DATE
    );
  </script>

  <script connection-id="xlsx">
    CREATE TABLE "2009"(
      COL1 INT,
      COL2 VARCHAR(255),
      COL3 DATE
    );
  </script>

  <query connection-id="java">
    set("COL1", 1);
    set("COL2", "Test");
    set("COL3", new java.util.Date());
    next();
    <script connection-id="xls">
      INSERT INTO "2009" (COL1, COL2, COL3) VALUES(?COL1, ?COL2, ?COL3);
    </script>
    <script connection-id="xlsx">
      INSERT INTO "2009" (COL1, COL2, COL3) VALUES(?COL1, ?COL2, ?COL3);
    </script>
  </query>

</etl>
```

```xml
<!DOCTYPE etl SYSTEM "http://scriptella.javaforge.com/dtd/etl.dtd">
<etl>

  <connection id="xls" url="jdbc:xls:file:extracttest.xls"/>
  <connection id="xlsx" url="jdbc:xls:file:extracttest.xlsx"/>
  <connection id="java" driver="janino"/>

  <query connection-id="xls">
    SELECT * FROM "2009";
    <script connection-id="java">
      System.out.println(((Double)get("COL1")).intValue());
      System.out.println((String)get("COL2"));
      System.out.println((java.util.Date) get("COL3"));
    </script>
  </query>

  <query connection-id="xlsx">
    SELECT * FROM "2009";
    <script connection-id="java">
      System.out.println(((Double)get("COL1")).intValue());
      System.out.println((String)get("COL2"));
      System.out.println((java.util.Date) get("COL3"));
    </script>
  </query>

</etl>
```
[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://paypal.me/panchmp)
