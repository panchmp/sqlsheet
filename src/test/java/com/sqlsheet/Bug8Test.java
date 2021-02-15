package com.sqlsheet;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * @author Klaus Hauschild
 */
public class Bug8Test {

  private Connection connection;

  @BeforeClass
  public static void loadDriverClass() throws ClassNotFoundException {
    Class.forName("com.sqlsheet.XlsDriver");
  }

  @After
  public void after() throws Exception {
    if (connection != null)
      connection.close();
  }

  @Before
  public void before() throws Exception {
    connection = DriverManager
    .getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("bug8.xlsx").getFile() + "?readStreaming=true");
  }

  @Test
  public void testbug8() throws Exception {
    final Statement stmt = connection.createStatement();
    final ResultSet results = stmt.executeQuery("SELECT * FROM bug8");
    assertTrue(results.next());
    assertNull(results.getString("PARENT"));
    assertEquals("Foo", results.getString("CHILD"));
    assertEquals(1, results.getInt("MIN"));
    assertEquals(1, results.getInt("MAX"));
    assertTrue(results.next());
    assertEquals("Foo", results.getString("PARENT"));
    assertEquals("Bar", results.getString("CHILD"));
    assertEquals(0, results.getInt("MIN"));
    assertEquals(3, results.getInt("MAX"));
  }

}
