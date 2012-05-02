package com.googlecode.sqlsheet.stream;

import com.googlecode.sqlsheet.stream.XlsSheetIterator;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class XlsSheetIteratorTest {

    @Test
    public void testLastSheet() throws Exception {
        XlsSheetIterator xlsSheet = new XlsSheetIterator(ClassLoader.getSystemResource("test.xls"), "2009");
        Assert.assertEquals(3, xlsSheet.columns.size());
        long counter = 0L;
        for (Object o : xlsSheet) {
            List<XlsSheetIterator.CellValueHolder> rowValues = (List<XlsSheetIterator.CellValueHolder>)o;
            Assert.assertEquals(3, rowValues.size());
            for(XlsSheetIterator.CellValueHolder value : rowValues){
                Assert.assertNotNull(value.stringValue);
            }
            Assert.assertNotNull(rowValues.get(0).doubleValue);
            Assert.assertNotNull(rowValues.get(2).dateValue);
            counter++;
        }
        Assert.assertEquals(3L,counter);

    }

    @Test
    public void testFirstSheet() throws Exception {
        XlsSheetIterator xlsSheet = new XlsSheetIterator(ClassLoader.getSystemResource("test.xls"), "SHEET1");
        Assert.assertEquals(3, xlsSheet.columns.size());
        long counter = 0L;
        for (Object o : xlsSheet) {
            List<XlsSheetIterator.CellValueHolder> rowValues = (List<XlsSheetIterator.CellValueHolder>)o;
            Assert.assertEquals(3, rowValues.size());
            for(XlsSheetIterator.CellValueHolder value : rowValues){
                Assert.assertNotNull(value.stringValue);
            }
            Assert.assertNotNull(rowValues.get(0).doubleValue);
            Assert.assertNotNull(rowValues.get(2).dateValue);

            counter++;
        }
        Assert.assertEquals(3L,counter);

    }

    @Test
    public void testBigGrid() throws Exception {
        XlsSheetIterator xlsSheet = new XlsSheetIterator(ClassLoader.getSystemResource("big-grid.xls"), "Big Grid");
        Assert.assertEquals(20, xlsSheet.columns.size());
        long counter = 0L;
        for (Object o : xlsSheet) {
            List<XlsSheetIterator.CellValueHolder> rowValues = (List<XlsSheetIterator.CellValueHolder>)o;
            Assert.assertEquals(20, rowValues.size());
            for(XlsSheetIterator.CellValueHolder value : rowValues){
                Assert.assertNotNull(value.stringValue);
            }
            Assert.assertNotNull(rowValues.get(1).doubleValue);
            Assert.assertNotNull(rowValues.get(2).doubleValue);
            Assert.assertNotNull(rowValues.get(3).doubleValue);
            Assert.assertNotNull(rowValues.get(4).dateValue);
            counter++;
        }
        Assert.assertEquals(65535L,counter);
    }

}
