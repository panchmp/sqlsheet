package com.sqlsheet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateTableTest {

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
        + ClassLoader.getSystemResource("create_table_test.xlsx").getFile());
  }

  @Test
  public void test_quoted_column_name() throws Exception {
    final Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE \"table\" (\"column\" VARCHAR)");
    final ResultSet trs = stmt.executeQuery("SELECT * FROM \"table\"");
    String columnName = trs.getMetaData().getColumnName(1);
    Assert.assertEquals("column", columnName);
    stmt.execute("DROP TABLE \"table\"");
    stmt.close();
    trs.close();
    connection.close();
  }
  
  @Test
  public void test_truncated_table_name() throws Exception {
    final Statement stmt = connection.createStatement();
    stmt.executeUpdate("CREATE TABLE \"table with very very very very long name\" (\"column\" VARCHAR)");
    final ResultSet trs = stmt.executeQuery("SELECT * FROM \"table with very very very very long name\"");
    String columnName = trs.getMetaData().getColumnName(1);
    Assert.assertEquals("column", columnName);
    stmt.execute("DROP TABLE \"table with very very very very long name\"");
    stmt.close();
    trs.close();
    connection.close();
  }

}
