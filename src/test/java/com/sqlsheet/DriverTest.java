package com.sqlsheet;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;
import java.util.Date;

import static org.junit.Assert.*;

public class DriverTest {

  @BeforeClass
  public static void loadDriverClass() throws ClassNotFoundException {
    Class.forName("com.sqlsheet.XlsDriver");
  }

  @Test
  public void testXlsSheetCRUD() throws Exception {
    Connection conn = DriverManager.
               getConnection("jdbc:xls:file:" + DriverTest.class.getResource("/test.xls").getFile());

    Statement create = conn.createStatement();
    create.executeUpdate("CREATE TABLE \"TEST_INSERT\"(\"COL1\" INT, COL2 VARCHAR(255), COL3 DATE)");
    PreparedStatement write = conn.prepareStatement("INSERT INTO TEST_INSERT(COL1, \"COL2\", COL3) VALUES(?,?,?)");
    for (int i = 0; i < 3; i++) {
      write.setDouble(1, i);
      write.setString(2, "Row" + i);
      write.setDate(3, new java.sql.Date(new Date().getTime()));
      write.execute();
    }
    processBaseResultset(conn, "SELECT * FROM \"TEST_INSERT\"");

    Statement drop = conn.createStatement();
    drop.execute("DROP TABLE TEST_INSERT");

    drop.close();
    create.close();
    write.close();
    conn.close();
  }

  @Test
  public void testXlsSheetNameQuotes() throws Exception {
    Connection conn = DriverManager.
               getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile());
    processBaseResultset(conn, "SELECT * FROM \"2009\"");
  }

  @Test
  public void testXlsSheetNameNoQuotes() throws Exception {
    Connection conn = DriverManager.
               getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile());
    processBaseResultset(conn, "SELECT * FROM SHEET1");
  }

  @Test
  public void testXlsStreamSheetNameQuotes() throws Exception {
    Connection conn = DriverManager
               .getConnection(
                       "jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile() + "?readStreaming=true");
    processBaseStreamingResultset(conn, "SELECT * FROM \"2009\"");
  }

  @Test
  public void testXlsStreamSheetNameNoQuotes() throws Exception {
    Connection conn = DriverManager
               .getConnection(
                       "jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile() + "?readStreaming=true");
    processBaseStreamingResultset(conn, "SELECT * FROM SHEET1");
  }

  @Test
  public void testXlsxSheetNameQuotes() throws Exception {
    Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").
                                                  getFile());
    processBaseResultset(conn, "SELECT * FROM \"2009\"");
  }

  @Test
  public void testXlsxSheetNameNoQuotes() throws Exception {
    Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").
                                                  getFile());
    processBaseResultset(conn, "SELECT * FROM SHEET1");
  }

  @Test
  public void testXlsxStreamSheetNameQuotes() throws Exception {
    Connection conn = DriverManager
               .getConnection(
                       "jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").getFile() + "?readStreaming=true");
    processBaseStreamingResultset(conn, "SELECT * FROM \"2009\"");
  }

  @Test
  public void testXlsxStreamSheetNameNoQuotes() throws Exception {
    Connection conn = DriverManager
               .getConnection(
                       "jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").getFile() + "?readStreaming=true");
    processBaseStreamingResultset(conn, "SELECT * FROM SHEET1");
  }

  @Test
  public void testBugNo7() throws Exception {
    Connection conn = DriverManager
               .getConnection(
                       "jdbc:xls:file:" + ClassLoader.getSystemResource("bug7.xlsx").getFile() + "?readStreaming=true");
    Statement stmt = conn.createStatement();
    ResultSet results = stmt.executeQuery("SELECT * FROM bug7");
    assertEquals(results.getMetaData().getColumnCount(), 13L);
    assertTrue(results.next());
    assertTrue(results.next());
    assertEquals(results.getString("Zone ID"), results.getString(1));

    conn = DriverManager
    .getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("bug7.xlsx").getFile() + "?readStreaming=no");
    stmt = conn.createStatement();
    results = stmt.executeQuery("SELECT * FROM bug7");
    assertEquals(results.getMetaData().getColumnCount(), 13L);
    assertTrue(results.next());
    assertTrue(results.next());
    assertEquals(results.getLong("Zone ID"), results.getLong(1));
  }

  @Test
  public void testBugNo6() throws Exception {
    Connection conn = DriverManager
               .getConnection(
                       "jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").getFile() + "?readStreaming=true");
    Statement stmt = conn.createStatement();
    ResultSet results = stmt.executeQuery("SELECT * FROM SHEET1");
    assertEquals(results.getMetaData().getColumnCount(), 3L);

    ResultSetMetaData resultSetMetaData = results.getMetaData();
    assertEquals("java.lang.Double", resultSetMetaData.getColumnTypeName(1));
    assertEquals("java.lang.String", resultSetMetaData.getColumnTypeName(2));
    assertEquals("java.util.Date", resultSetMetaData.getColumnTypeName(3));

  }

  private void processBaseResultset(Connection conn, String sql) throws SQLException {
    Statement stmt = conn.createStatement();
    ResultSet results = stmt.executeQuery(sql);
    assertEquals(results.getMetaData().getColumnCount(), 3L);
    long count = 0L;
    while (results.next()) {
      assertSame(Double.class, results.getObject(1).getClass());
      assertSame(String.class, results.getObject(2).getClass());
      assertSame(java.sql.Date.class, results.getObject(3).getClass());
      count++;
    }
    assertEquals(3L, count);
    results.close();
    stmt.close();
    conn.close();
  }

  private void processBaseStreamingResultset(Connection conn, String sql) throws SQLException {
    Statement stmt = conn.createStatement();
    ResultSet results = stmt.executeQuery(sql);
    assertEquals(results.getMetaData().getColumnCount(), 3L);
    long count = 0L;
    while (results.next()) {
      assertSame(Double.class, results.getObject(1).getClass());
      assertSame(String.class, results.getObject(2).getClass());
      assertSame(java.util.Date.class, results.getObject(3).getClass());
      count++;
    }
    assertEquals(3L, count);
    results.close();
    stmt.close();
    conn.close();
  }

}
