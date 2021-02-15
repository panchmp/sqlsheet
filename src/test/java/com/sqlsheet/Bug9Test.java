package com.sqlsheet;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Klaus Hauschild
 */
public class Bug9Test {

  @BeforeClass
  public static void loadDriverClass() throws ClassNotFoundException {
    Class.forName("com.sqlsheet.XlsDriver");
  }

  @Test
  public void testWithoutStreaming() throws Exception {
    final Connection connection = DriverManager
                     .getConnection(
                             "jdbc:xls:file:" + ClassLoader.getSystemResource("bug9.xlsx").getFile() + "?readStreaming=false");
    final Statement statement = connection.createStatement();
    final ResultSet resultSet = statement.executeQuery("SELECT * FROM bug9");
    assertTrue(resultSet.next());
    assertEquals("9", resultSet.getString("BUG9"));
    connection.close();
  }

  @Test
  public void testWithStreaming() throws Exception {
    final Connection connection = DriverManager
                     .getConnection(
                             "jdbc:xls:file:" + ClassLoader.getSystemResource("bug9.xlsx").getFile() + "?readStreaming=true");
    final Statement statement = connection.createStatement();
    final ResultSet resultSet = statement.executeQuery("SELECT * FROM bug9");
    assertTrue(resultSet.next());
    assertEquals("9", resultSet.getString("BUG9"));
    connection.close();
  }

}
