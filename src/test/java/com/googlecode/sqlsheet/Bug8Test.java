package com.googlecode.sqlsheet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Klaus Hauschild
 */
public class Bug8Test {

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
        + ClassLoader.getSystemResource("bug8.xlsx").getFile() + "?readStreaming=true");
  }

  @Test
  public void testbug8() throws Exception {
    final Statement stmt = connection.createStatement();
    final ResultSet results = stmt.executeQuery("SELECT * FROM bug8");
    Assert.assertEquals(true, results.next());
    Assert.assertEquals(null, results.getString("PARENT"));
    Assert.assertEquals("Foo", results.getString("CHILD"));
    Assert.assertEquals(1, results.getInt("MIN"));
    Assert.assertEquals(1, results.getInt("MAX"));
    Assert.assertEquals(true, results.next());
    Assert.assertEquals("Foo", results.getString("PARENT"));
    Assert.assertEquals("Bar", results.getString("CHILD"));
    Assert.assertEquals(0, results.getInt("MIN"));
    Assert.assertEquals(3, results.getInt("MAX"));
  }

}
