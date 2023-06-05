package com.sqlsheet.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class XlsSheetIteratorTest {

    @Test
    public void testLastSheet() throws Exception {
        XlsSheetIterator xlsSheet = new XlsSheetIterator(ClassLoader.getSystemResource("test.xls"), "2009");
        Assertions.assertEquals(3, xlsSheet.getColumns().size());
        long counter = 0L;
        for (Object o : xlsSheet) {
            List<XlsSheetIterator.CellValueHolder> rowValues = (List<XlsSheetIterator.CellValueHolder>) o;
            Assertions.assertEquals(3, rowValues.size());
            for (XlsSheetIterator.CellValueHolder value : rowValues) {
                Assertions.assertNotNull(value.stringValue);
            }
            Assertions.assertNotNull(rowValues.get(0).doubleValue);
            Assertions.assertNotNull(rowValues.get(2).dateValue);
            counter++;
        }
        Assertions.assertEquals(3L, counter);

    }

    @Test
    public void testFirstSheet() throws Exception {
        XlsSheetIterator xlsSheet = new XlsSheetIterator(ClassLoader.getSystemResource("test.xls"), "SHEET1");
        Assertions.assertEquals(3, xlsSheet.getColumns().size());
        long counter = 0L;
        for (Object o : xlsSheet) {
            List<XlsSheetIterator.CellValueHolder> rowValues = (List<XlsSheetIterator.CellValueHolder>) o;
            Assertions.assertEquals(3, rowValues.size());
            for (XlsSheetIterator.CellValueHolder value : rowValues) {
                Assertions.assertNotNull(value.stringValue);
            }
            Assertions.assertNotNull(rowValues.get(0).doubleValue);
            Assertions.assertNotNull(rowValues.get(2).dateValue);

            counter++;
        }
        Assertions.assertEquals(3L, counter);

    }

    @Test
    public void testBigGrid() throws Exception {
        XlsSheetIterator xlsSheet = new XlsSheetIterator(ClassLoader.getSystemResource("big-grid.xls"), "Big Grid");
        Assertions.assertEquals(20, xlsSheet.getColumns().size());
        long counter = 0L;
        for (Object o : xlsSheet) {
            List<XlsSheetIterator.CellValueHolder> rowValues = (List<XlsSheetIterator.CellValueHolder>) o;
            Assertions.assertEquals(20, rowValues.size());
            for (XlsSheetIterator.CellValueHolder value : rowValues) {
                Assertions.assertNotNull(value.stringValue);
            }
            Assertions.assertNotNull(rowValues.get(1).doubleValue);
            Assertions.assertNotNull(rowValues.get(2).doubleValue);
            Assertions.assertNotNull(rowValues.get(3).doubleValue);
            Assertions.assertNotNull(rowValues.get(4).dateValue);
            counter++;
        }
        Assertions.assertEquals(65535L, counter);
    }

}
