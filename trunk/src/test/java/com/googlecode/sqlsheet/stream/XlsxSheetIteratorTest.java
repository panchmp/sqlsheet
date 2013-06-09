package com.googlecode.sqlsheet.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class XlsxSheetIteratorTest {

    @Test
    public void testMain() throws Exception {
        XlsxSheetIterator xlsSheet = new XlsxSheetIterator(ClassLoader.getSystemResource("test.xlsx"), "2009");
        Assert.assertEquals(3, xlsSheet.getColumns().size());
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
        XlsxSheetIterator xlsSheet = new XlsxSheetIterator(ClassLoader.getSystemResource("big-grid.xlsx"), "Big Grid");
        Assert.assertEquals(20, xlsSheet.getColumns().size());
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

    @Test
    public void testBugNo3() throws Exception {
        XlsxSheetIterator xlsSheet = new XlsxSheetIterator(ClassLoader.getSystemResource("bug3.xlsx"), "bug3");
        Assert.assertEquals(13, xlsSheet.getColumns().size());
        long counter = 0L;
        for (Object o : xlsSheet) {
            List<XlsSheetIterator.CellValueHolder> rowValues = (List<XlsSheetIterator.CellValueHolder>)o;
            Assert.assertEquals(13, rowValues.size());
           // for(XlsSheetIterator.CellValueHolder value : rowValues){
               // Assert.assertNotNull(value.stringValue);
            //}
            counter++;
        }
        Assert.assertEquals(1,counter);
    }

}
