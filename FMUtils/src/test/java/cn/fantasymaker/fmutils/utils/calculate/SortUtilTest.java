package cn.fantasymaker.fmutils.utils.calculate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created :  2016-10-09
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class SortUtilTest {

    private static final String[] mixStrArr = {
            "g", "a", "c", "e", "b", "f", "d"
    };
    private static final String[] sortedStrArr = {
            "a", "b", "c", "d", "e", "f", "g"
    };

    @Test
    public void less() throws Exception {

    }

    @Test
    public void exch() throws Exception {

    }

    @Test
    public void isSort() throws Exception {
        String[] sorted = sortedStrArr;
        String[] mixed = mixStrArr;
        assertTrue(SortUtil.isSort(sorted));
        assertFalse(SortUtil.isSort(mixed));
    }

    @Test
    public void reverse() throws Exception {
        // single
        String[] pos = {"a", "b", "c"};
        String[] neg = {"c", "b", "a"};
        SortUtil.reverse(pos);
        SortUtil.print(pos);
        assertArrayEquals(neg, pos);

        // double
        String[] posD = {"a", "b", "c", "d"};
        String[] negD = {"d", "c", "b", "a"};
        SortUtil.reverse(posD);
        SortUtil.print(posD);
        assertArrayEquals(negD, posD);
    }

    @Test
    public void selectionSort() throws Exception {
        String[] actual = mixStrArr;
        SortUtil.Selection.sort(actual);
        SortUtil.print(actual);
        assertTrue(SortUtil.isSort(actual));
        assertArrayEquals(sortedStrArr, actual);
    }

    @Test
    public void insertSort() throws Exception {
        String[] actual = mixStrArr;
        SortUtil.Insertion.sort(actual);
        SortUtil.print(actual);
        assertTrue(SortUtil.isSort(actual));
        assertArrayEquals(sortedStrArr, actual);
    }

    @Test
    public void shellSort() throws Exception {
        String[] actual = mixStrArr;
        SortUtil.Shell.sort(actual);
        SortUtil.print(actual);
        assertTrue(SortUtil.isSort(actual));
        assertArrayEquals(sortedStrArr, actual);
    }

    @Test
    public void upBottomSort() throws Exception {
        String[] actual = mixStrArr;
        SortUtil.Merge.upBottomSort(actual);
        SortUtil.print(actual);
        assertTrue(SortUtil.isSort(actual));
        assertArrayEquals(sortedStrArr, actual);
    }

    @Test
    public void bottomUpSort() throws Exception {
        String[] actual = mixStrArr;
        SortUtil.Merge.bottomUpSort(actual);
        SortUtil.print(actual);
        assertTrue(SortUtil.isSort(actual));
        assertArrayEquals(sortedStrArr, actual);
    }

    @Test
    public void quickSort() throws Exception {
        String[] actual = mixStrArr;
        SortUtil.Quick.sort(actual);
        SortUtil.print(actual);
        assertTrue(SortUtil.isSort(actual));
        assertArrayEquals(sortedStrArr, actual);
    }
}