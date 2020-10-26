package com.sqlsheet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.*;

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
