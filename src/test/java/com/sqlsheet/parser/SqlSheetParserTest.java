package com.sqlsheet.parser;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SqlSheetParserTest {

    @Test
    void parse() throws SQLException {
        String sqlString = "SELECT * from konven";
        SqlSheetParser parser = new SqlSheetParser();
        parser.parse(sqlString);
    }
}
