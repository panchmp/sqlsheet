package com.sqlsheet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * @author Klaus Hauschild
 * @since 6.6
 */
public class dataTypeTest {

  @BeforeAll
  public static void loadDriverClass() throws ClassNotFoundException {
    Class.forName("com.sqlsheet.XlsDriver");
  }

  @Test
  public void dataTypeTest() throws Exception {
    final Connection conn = DriverManager
                     .getConnection(
                             "jdbc:xls:file:" + ClassLoader.getSystemResource("dataType.xlsx").getFile() + "?readStreaming=no");
    Statement stmt = conn.createStatement();
    ResultSet results = stmt.executeQuery("SELECT * FROM datatype");
    Assertions.assertEquals( 7, results.getMetaData().getColumnCount(), "We expect 7 columns");

    ResultSetMetaData resultSetMetaData = results.getMetaData();

    // Data Type
    Assertions.assertEquals("VARCHAR", resultSetMetaData.getColumnTypeName(1), "We expect for the first VARCHAR");
    Assertions.assertEquals("DATE", resultSetMetaData.getColumnTypeName(2), "We expect for the second DATE");
    Assertions.assertEquals("DOUBLE", resultSetMetaData.getColumnTypeName(3), "We expect for the third DOUBLE");

    // Mixed data type
    Assertions.assertEquals("VARCHAR", resultSetMetaData.getColumnTypeName(4), "We expect for the fourth VARCHAR");

    // Null first
    Assertions.assertEquals("DATE", resultSetMetaData.getColumnTypeName(5), "We expect for the fifth  DATE");
    Assertions.assertEquals("DOUBLE", resultSetMetaData.getColumnTypeName(6), "We expect for the sixth DOUBLE");
    Assertions.assertEquals("VARCHAR", resultSetMetaData.getColumnTypeName(7), "We expect for the seventh VARCHAR");

    conn.close();
  }

}
