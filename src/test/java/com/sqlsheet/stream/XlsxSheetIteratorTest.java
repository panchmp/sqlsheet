package com.sqlsheet.stream;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XlsxSheetIteratorTest {

    @Test
    public void testMain() throws Exception {
        XlsxSheetIterator xlsSheet = new XlsxSheetIterator(ClassLoader.getSystemResource("test.xlsx"), "2009");
        assertEquals(3, xlsSheet.getColumns().size());
        long counter = 0L;
        for (Object o : xlsSheet) {
            List<XlsSheetIterator.CellValueHolder> rowValues = (List<XlsSheetIterator.CellValueHolder>) o;
            assertEquals(3, rowValues.size());
            for (XlsSheetIterator.CellValueHolder value : rowValues) {
                assertNotNull(value.stringValue);
            }
            assertNotNull(rowValues.get(0).doubleValue);
            assertNotNull(rowValues.get(2).dateValue);
            counter++;
        }
        assertEquals(3L, counter);
    }

    @Test
    public void testBigGrid() throws Exception {
        XlsxSheetIterator xlsSheet = new XlsxSheetIterator(ClassLoader.getSystemResource("big-grid.xlsx"), "Big Grid");
        assertEquals(20, xlsSheet.getColumns().size());
        long counter = 0L;
        for (Object o : xlsSheet) {
            List<XlsSheetIterator.CellValueHolder> rowValues = (List<XlsSheetIterator.CellValueHolder>) o;
            assertEquals(20, rowValues.size());
            for (XlsSheetIterator.CellValueHolder value : rowValues) {
                assertNotNull(value.stringValue);
            }
            assertNotNull(rowValues.get(1).doubleValue);
            assertNotNull(rowValues.get(2).doubleValue);
            assertNotNull(rowValues.get(3).doubleValue);
            assertNotNull(rowValues.get(4).dateValue);
            counter++;
        }
        assertEquals(65535L, counter);
    }

    @Test
    public void testBugNo3() throws Exception {
        XlsxSheetIterator xlsSheet = new XlsxSheetIterator(ClassLoader.getSystemResource("bug3.xlsx"), "bug3");
        assertEquals(13, xlsSheet.getColumns().size());
        long counter = 0L;
        for (Object o : xlsSheet) {
            List<XlsSheetIterator.CellValueHolder> rowValues = (List<XlsSheetIterator.CellValueHolder>) o;
            assertEquals(13, rowValues.size());
            // for(XlsSheetIterator.CellValueHolder value : rowValues){
            // assertNotNull(value.stringValue);
            // }
            counter++;
        }
        assertEquals(1, counter);
    }

}
