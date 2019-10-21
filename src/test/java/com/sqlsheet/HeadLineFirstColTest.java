package com.sqlsheet;

import java.sql.DriverManager;

import org.junit.Assert;
import org.junit.Test;

/**
 * @since 6.6
 * @author Klaus Hauschild
 */
public class HeadLineFirstColTest {

    @Test
    public void headLineTest() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        final XlsConnection connection = (XlsConnection) DriverManager
                .getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("headline_firstcol.xlsx").getFile() + "?headLine=3&firstColumn=1");
        final XlsStatement statement = (XlsStatement) connection.createStatement();
        final XlsResultSet resultSet = (XlsResultSet) statement.executeQuery("SELECT * FROM data");
        if (resultSet.next()) {
            Assert.assertEquals("loan_1", resultSet.getString("id_instrument"));
            Assert.assertEquals("loan", resultSet.getString("id_instrument_type"));
        }
        
        if (resultSet.next()) {
            Assert.assertEquals("loan_2", resultSet.getString("id_instrument"));
            Assert.assertEquals("loan", resultSet.getString("id_instrument_type"));
        }
        
        if (resultSet.next()) {
            Assert.assertEquals("loan_3", resultSet.getString("id_instrument"));
            Assert.assertEquals("loan", resultSet.getString("id_instrument_type"));
        }
        
        if (resultSet.next()) {
            Assert.assertEquals("loan_4", resultSet.getString("id_instrument"));
            Assert.assertEquals("loan", resultSet.getString("id_instrument_type"));
        }
        
        if (resultSet.next()) {
            Assert.assertEquals("loan_5", resultSet.getString("id_instrument"));
            Assert.assertEquals("loan", resultSet.getString("id_instrument_type"));
        }
        
        connection.close();
    }

}
