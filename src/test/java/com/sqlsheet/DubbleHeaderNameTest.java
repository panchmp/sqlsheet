package com.sqlsheet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.*;

/**
 * @author Klaus Hauschild
 */
public class DubbleHeaderNameTest {

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
    connection = DriverManager.getConnection(
    "jdbc:xls:file:" + ClassLoader.getSystemResource("duplicateHeaderName.xlsx").getFile() + "?readStreaming=false");
  }

  @Test
  public void testDuplicate() throws Exception {
    final Statement stmt = connection.createStatement();
    final ResultSet results = stmt.executeQuery("SELECT * FROM \"duplicate\""); // Duplicate is a keyword
    Assert.assertEquals("The column name must A_1", "A_1", results.getMetaData().getColumnName(2));
    Assert.assertTrue("The column names must not be the same",
            !results.getMetaData().getColumnName(2).equals(results.getMetaData().getColumnName(1)));

  }

}
