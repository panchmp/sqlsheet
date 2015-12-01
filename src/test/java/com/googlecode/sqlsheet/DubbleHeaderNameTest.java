package com.googlecode.sqlsheet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Klaus Hauschild
 */
public class DubbleHeaderNameTest {

  private Connection connection;

  @After
  public void after() throws Exception {
    if (connection != null) {
      connection.close();
    }
  }

  @Before
  public void before() throws Exception {
    Class.forName("com.googlecode.sqlsheet.Driver");
    connection = DriverManager.getConnection("jdbc:xls:file:"
        + ClassLoader.getSystemResource("duplicateHeaderName.xlsx").getFile() + "?readStreaming=false");
  }

  @Test
  public void testDuplicate() throws Exception {
    final Statement stmt = connection.createStatement();
    final ResultSet results = stmt.executeQuery("SELECT * FROM duplicate");
    Assert.assertEquals("The column name must A_1", "A_1", results.getMetaData().getColumnName(2));
    Assert.assertTrue("The column names must not be the same", !results.getMetaData().getColumnName(2).equals(results.getMetaData().getColumnName(1)));

  }

}
