package cn.fantasymaker.anutil.utils.calculate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created :  2016-08-22
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class WordUtilTest {

    private static String text = "Hi! 这是测试文字\n有30个中文, 有2个拉丁字母, 有6个数字, 有6个符号, 有个6空白字符!";

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCount() throws Exception {

    }

    @Test
    public void testTotal() throws Exception {

    }

    @Test
    public void testCountChinese() throws Exception {
        assertEquals(30, WordUtil.countChinese(text));
    }

    @Test
    public void testCountLatin() throws Exception {
        assertEquals(2, WordUtil.countLatin(text));
    }

    @Test
    public void testCountDigital() throws Exception {
        assertEquals(6, WordUtil.countDigital(text));
    }

    @Test
    public void testCountSymbol() throws Exception {
        assertEquals(6, WordUtil.countSymbol(text));
    }

    @Test
    public void testCountBlank() throws Exception {
        assertEquals(6, WordUtil.countBlank(text));
    }



    @Test
    public void testExtractChinese() throws Exception {
        ArrayList<String> list = WordUtil.extractChinese(text);
        StringBuffer stringBuffer = new StringBuffer();
        for(String string : list){
            stringBuffer.append(string);
        }
        String actual = stringBuffer.toString();
        String expected = "这是测试文字有个中文有个拉丁字母有个数字有个符号有个空白字符";
        assertEquals(expected, actual);
    }

    @Test
    public void testExtractLatin() throws Exception {
        ArrayList<String> list = WordUtil.extractLatin(text);
        StringBuffer stringBuffer = new StringBuffer();
        for(String string : list){
            stringBuffer.append(string);
        }
        String actual = stringBuffer.toString();
        String expected = "Hi";
        assertEquals(expected, actual);
    }

    @Test
    public void testExtractDigital() throws Exception {
        ArrayList<String> list = WordUtil.extractDigital(text);
        StringBuffer stringBuffer = new StringBuffer();
        for(String string : list){
            stringBuffer.append(string);
        }
        String actual = stringBuffer.toString();
        String expected = "302666";
        assertEquals(expected, actual);
    }

    @Test
    public void testExtractSymbol() throws Exception {
        ArrayList<String> list = WordUtil.extractSymbol(text);
        StringBuffer stringBuffer = new StringBuffer();
        for(String string : list){
            stringBuffer.append(string);
        }
        String actual = stringBuffer.toString();
        String expected = "!,,,,!";
        assertEquals(expected, actual);
    }

    @Test
    public void testExtractBlank() throws Exception {
        ArrayList<String> list = WordUtil.extractBlank(text);
        StringBuffer stringBuffer = new StringBuffer();
        for(String string : list){
            stringBuffer.append(string);
        }
        String actual = stringBuffer.toString();
        String expected = " \n    ";
        assertEquals(expected, actual);
    }
}