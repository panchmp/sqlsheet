package com.sqlsheet;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Klaus Hauschild
 * @since 6.6
 */
public class DataTypeTest {

  @BeforeClass
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
    assertEquals("We expect 7 columns", 7, results.getMetaData().getColumnCount());

    ResultSetMetaData resultSetMetaData = results.getMetaData();

    // Data Type
    assertEquals("We expect for the first VARCHAR", "VARCHAR", resultSetMetaData.getColumnTypeName(1));
    assertEquals("We expect for the second DATE", "DATE", resultSetMetaData.getColumnTypeName(2));
    assertEquals("We expect for the third DOUBLE", "DOUBLE", resultSetMetaData.getColumnTypeName(3));

    // Mixed data type
    assertEquals("We expect for the fourth VARCHAR", "VARCHAR", resultSetMetaData.getColumnTypeName(4));

    // Null first
    assertEquals("We expect for the fifth  DATE", "DATE", resultSetMetaData.getColumnTypeName(5));
    assertEquals("We expect for the sixth DOUBLE", "DOUBLE", resultSetMetaData.getColumnTypeName(6));
    assertEquals("We expect for the seventh VARCHAR", "VARCHAR", resultSetMetaData.getColumnTypeName(7));

    conn.close();
  }

}
