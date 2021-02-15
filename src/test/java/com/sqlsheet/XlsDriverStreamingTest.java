package com.sqlsheet;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.sql.*;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class XlsDriverStreamingTest {

  @BeforeClass
  public static void loadDriverClass() throws ClassNotFoundException {
    Class.forName("com.sqlsheet.XlsDriver");
  }

  @Test
  public void testConnect() throws Exception {
    Connection conn = DriverManager.
               getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile());
    Statement stmt = conn.createStatement();
    ResultSet results = stmt.executeQuery("SELECT * FROM \"2009\"");
    assertEquals(results.getMetaData().getColumnCount(), 3L);
    long count = 0L;
    while (results.next()) {
      assertSame(Double.class, results.getObject(1).getClass());
      assertSame(String.class, results.getObject(2).getClass());
      assertSame(java.sql.Date.class, results.getObject(3).getClass());
      count++;
    }
    assertEquals(count, 3L);
    results.close();
    stmt.close();
    conn.close();
  }

  @Test
  public void testXlsConnectReadStream() throws Exception {
    Connection conn = DriverManager
               .getConnection(
                       "jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile() + "?readStreaming=true");
    Statement stmt = conn.createStatement();
    ResultSet results = stmt.executeQuery("SELECT * FROM \"2009\"");
    assertEquals(3L, results.getMetaData().getColumnCount());
    long count = 0L;
    while (results.next()) {
      assertSame(Double.class, results.getObject(1).getClass());
      assertSame(String.class, results.getObject(2).getClass());
      assertSame(Date.class, results.getObject(3).getClass());
      count++;
    }
    assertEquals(3L, count);
    results.close();
    stmt.close();
    conn.close();
  }

  @Test
  public void testXlsxConnectReadStream() throws Exception {
    Connection conn = DriverManager
               .getConnection(
                       "jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").getFile() + "?readStreaming=true");
    Statement stmt = conn.createStatement();
    ResultSet results = stmt.executeQuery("SELECT * FROM \"2009\"");
    assertEquals(3L, results.getMetaData().getColumnCount());
    long count = 0L;
    while (results.next()) {
      assertSame(Double.class, results.getObject(1).getClass());
      assertSame(String.class, results.getObject(2).getClass());
      assertSame(Date.class, results.getObject(3).getClass());
      count++;
    }
    assertEquals(3L, count);
    results.close();
    stmt.close();
    conn.close();
  }

  @Test
  public void testXlsConnectReadStreamBigTable() throws Exception {
    Connection conn = DriverManager.getConnection(
               "jdbc:xls:file:" + ClassLoader.getSystemResource("big-grid.xls").getFile() + "?readStreaming=true");
    Statement stmt = conn.createStatement();
    ResultSet results = stmt.executeQuery("SELECT * FROM \"Big Grid\"");
    assertEquals(20L, results.getMetaData().getColumnCount());
    long count = 0L;
    while (results.next()) {
      assertSame(String.class, results.getObject(1).getClass());
      assertSame(Double.class, results.getObject(2).getClass());
      assertSame(Double.class, results.getObject(3).getClass());
      assertSame(Double.class, results.getObject(4).getClass());
      assertSame(Date.class, results.getObject(5).getClass());
      count++;
    }
    assertEquals(65535L, count);
    results.close();
    stmt.close();
    conn.close();
  }

  @Test
  public void testXlsxConnectReadStreamBigTable() throws Exception {
    Connection conn = DriverManager.getConnection(
               "jdbc:xls:file:" + ClassLoader.getSystemResource("big-grid.xlsx").getFile() + "?readStreaming=true");
    Statement stmt = conn.createStatement();
    ResultSet results = stmt.executeQuery("SELECT * FROM \"Big Grid\"");
    assertEquals(20L, results.getMetaData().getColumnCount());
    long count = 0L;
    while (results.next()) {
      assertSame(String.class, results.getObject(1).getClass());
      assertSame(Double.class, results.getObject(2).getClass());
      assertSame(Double.class, results.getObject(3).getClass());
      assertSame(Double.class, results.getObject(4).getClass());
      assertSame(Date.class, results.getObject(5).getClass());
      count++;
    }
    assertEquals(65535L, count);
    results.close();
    stmt.close();
    conn.close();
  }

  @Test
  public void testXlsConnectWriteStream() throws Exception {
    File test = File.createTempFile("testXlsConnectWriteStream", ".xlsx");
    FileUtils.copyFile(new File(ClassLoader.getSystemResource("test.xlsx").getFile()), test);

    Connection writeConnection = DriverManager.getConnection("jdbc:xls:file:" + test.getPath() + "?writeStreaming=true");
    Statement writeStatement = writeConnection.createStatement();
    writeStatement.executeUpdate("CREATE TABLE TEST_INSERT(COL1 INT, COL2 VARCHAR(255), COL3 DATE)");
    writeStatement.close();

    PreparedStatement writeStatement2 = writeConnection
                      .prepareStatement("INSERT INTO TEST_INSERT(COL1, COL2, COL3) VALUES(?,?,?)");
    for (int i = 0; i < 3; i++) {
      writeStatement2.setDouble(1, i);
      writeStatement2.setString(2, "Row" + i);
      writeStatement2.setDate(3, new java.sql.Date(new Date().getTime()));
      writeStatement2.execute();
    }
    writeStatement2.close();
    writeConnection.close();

    Connection readConnection = DriverManager.getConnection("jdbc:xls:file:" + test.getPath() + "?readStreaming=true");
    Statement readStatementstmt = readConnection.createStatement();
    ResultSet results = readStatementstmt.executeQuery("SELECT * FROM TEST_INSERT");
    assertEquals(3L, results.getMetaData().getColumnCount());
    long count = 0L;
    while (results.next()) {
      assertSame(Double.class, results.getObject(1).getClass());
      assertSame(String.class, results.getObject(2).getClass());
      assertSame(Date.class, results.getObject(3).getClass());
      count++;
    }
    assertEquals(3L, count);
    results.close();
    readStatementstmt.close();
    readConnection.close();

    Connection readConnection2 = DriverManager.getConnection("jdbc:xls:file:" + test.getPath());
    Statement readStatementstmt2 = readConnection.createStatement();
    ResultSet results2 = readStatementstmt.executeQuery("SELECT * FROM TEST_INSERT");
    assertEquals(3L, results2.getMetaData().getColumnCount());
    long count2 = 0L;
    while (results2.next()) {
      assertSame(Double.class, results2.getObject(1).getClass());
      assertSame(String.class, results2.getObject(2).getClass());
      assertSame(Date.class, results2.getObject(3).getClass());
      count2++;
    }
    assertEquals(3L, count2);
    results2.close();
    readStatementstmt2.close();
    readConnection2.close();
  }

}
