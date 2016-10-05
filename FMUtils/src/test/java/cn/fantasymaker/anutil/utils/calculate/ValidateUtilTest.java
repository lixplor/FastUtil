package cn.fantasymaker.anutil.utils.calculate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created :  2016-09-06
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class ValidateUtilTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIsInt() throws Exception {
        String[] correct = new String[]{
                "1",
                "-1",
                "0",
                "9999999"
        };
        String[] wrong = new String[]{
                "",
                "0.1",
                "asd1",
                "1I",
                "001",
                "1O0"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isInt(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isInt(string));
        }
    }

    @Test
    public void testIsPositiveInt() throws Exception {
        String[] correct = new String[]{
                "1",
                "10",
                "9999999"
        };
        String[] wrong = new String[]{
                "",
                "0",
                "0.1",
                "asd1",
                "1I",
                "001",
                "1O0",
                "-1"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isPositiveInt(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isPositiveInt(string));
        }
    }

    @Test
    public void testIsNegativeInt() throws Exception {
        String[] correct = new String[]{
                "-1",
                "-10",
                "-9999999"
        };
        String[] wrong = new String[]{
                "",
                "-0.1",
                "asd1",
                "1I",
                "-001",
                "1O0",
                "0"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isNegativeInt(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isNegativeInt(string));
        }
    }

    @Test
    public void testIsFloat() throws Exception {
        String[] correct = new String[]{
                "0.1",
                "1.133",
                "-9.9",
                "3.1415926",
                "1.00000",
                "0.000000000000"
        };
        String[] wrong = new String[]{
                "",
                "-01.1",
                "asd1",
                "1I",
                "1O0",
                "0.0.1"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isFloat(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isFloat(string));
        }
    }

    @Test
    public void testIsChinese() throws Exception {
        String[] correct = new String[]{
                "你",
                "你好"
        };
        String[] wrong = new String[]{
                "",
                "。。。",
                ".,.,&*&*",
                "-01.1",
                "asd1",
                "♣",
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isChinese(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isChinese(string));
        }
    }

    @Test
    public void testIsDigital() throws Exception {
        String[] correct = new String[]{
                "1",
                "0",
                "001",
                "9999999"
        };
        String[] wrong = new String[]{
                "-1",
                "",
                "0.1",
                "asd1",
                "1I",
                "1O0"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isDigital(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isDigital(string));
        }
    }

    @Test
    public void testIsLetter() throws Exception {
        String[] correct = new String[]{
                "A",
                "aa",
                "azaa",
                "afsdfsdfsdfsfd"
        };
        String[] wrong = new String[]{
                "-1",
                "",
                "0.1",
                "asd1",
                "(&(*&(",
                "a--)(a"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isLetter(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isLetter(string));
        }
    }

    @Test
    public void testIsUppercaseLetter() throws Exception {
        String[] correct = new String[]{
                "A",
                "AA",
                "ADFSF",
        };
        String[] wrong = new String[]{
                "-1",
                "",
                "0.1",
                "asd1",
                "(&(*&(",
                "a--)(a",
                "aaa",
                "AAs"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isUppercaseLetter(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isUppercaseLetter(string));
        }
    }

    @Test
    public void testIsLowercaseLetter() throws Exception {
        String[] correct = new String[]{
                "a",
                "aa",
                "sdfsdfsdfsdf",
        };
        String[] wrong = new String[]{
                "-1",
                "",
                "0.1",
                "asd1",
                "(&(*&(",
                "a--)(a",
                "AAA",
                "AAs"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isLowercaseLetter(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isLowercaseLetter(string));
        }
    }

    @Test
    public void testIsDigitalAndLetter() throws Exception {
        String[] correct = new String[]{
                "a1a1",
                "a1S111aa",
                "1sdfs1dEsDfsdf",
                "123sdfs1dEsDfsdf3423",
        };
        String[] wrong = new String[]{
                "-1",
                "",
                "0.1",
                "(&(*&(",
                "a--)(a",
                "AAA",
                "AAs",
                "2341243123123"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isDigitalAndLetter(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isDigitalAndLetter(string));
        }
    }

    @Test
    public void testIsSymbol() throws Exception {
        String[] correct = new String[]{
                "%",
                "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~",
        };
        String[] wrong = new String[]{
                "-1",
                "",
                "0.1",
                "(&a(*&(",
                "a--)(a",
                "AAA",
                "AAs",
                "2341&*&*243123123"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isSymbol(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isSymbol(string));
        }
    }

    @Test
    public void testIsBlank() throws Exception {
        String[] correct = new String[]{
                " ",
                "\t",
                "\n",
                "\r",
                "\r\n\t ",
        };
        String[] wrong = new String[]{
                "",
                "dsf",
                " df ",
                "   f",
                "a   ",
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isBlank(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isBlank(string));
        }
    }

    @Test
    public void testIsDate() throws Exception {
        String[] correct = new String[]{
                "1970-01-01",
                "1970/01/01",
                "1970.01.01",
                "1000.01.01",
                "1000.1.1",
                "1-1-1",
                "9999-12-31",
        };
        String[] wrong = new String[]{
                "",
                "0978-01-01",
                "19899-01-01",
                "1970-00-01",
                "1970-13-01",
                "1970-12-00",
                "1970-12-32",
                "1970-1232",
                "1970-12-32-1",
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isDate(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isDate(string));
        }
    }

    @Test
    public void testIsTime() throws Exception {

    }

    @Test
    public void testIsZipCode() throws Exception {
        String[] correct = new String[]{
                "000000",
                "050000",
                "050061",
                "100000",
                "100086",
        };
        String[] wrong = new String[]{
                "",
                "0978-0-1",
                "1234567",
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isZipCode(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isZipCode(string));
        }
    }

    @Test
    public void testIsIDCard() throws Exception {
        String[] correct = new String[]{
                "131205198301031",
                "131205198301031713",
                "13120519830103171x",
                "13120519830103171X",
                "430123199901110111",
        };
        String[] wrong = new String[]{
                "",
                "12345678901234567",
                "0978-0-1",
                "1234567",
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isIDCard(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isIDCard(string));
        }
    }

    @Test
    public void testIsQQ() throws Exception {
        String[] correct = new String[]{
                "12345",
                "123456",
                "1234567",
                "12345678",
                "123456789",
                "1234567890",
        };
        String[] wrong = new String[]{
                "",
                "1",
                "12",
                "123",
                "1234",
                "01234",
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isQQ(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isQQ(string));
        }
    }

    @Test
    public void testIsURL() throws Exception {

    }

    @Test
    public void testIsIPv4() throws Exception {
        String[] correctIps = new String[]{
                "1.1.1.1",
                "0.0.0.0",
                "9.9.9.9",
                "1.12.123.123",
                "254.254.254.254",
                "255.255.255.255"

        };
        String[] wrongIps = new String[]{
                "255.255.255.255.",
                "256.256.256.256",
                "999.999.999.999.",
                "1.1.1.11111.",
                "1.a..0"
        };
        //test correct
        for (String ip : correctIps) {
            assertTrue(ip, ValidateUtil.isIPv4(ip));
        }
        //test wrong
        for (String ip : wrongIps) {
            assertFalse(ip, ValidateUtil.isIPv4(ip));
        }
    }

    @Test
    public void testIsDomainName() throws Exception {
        String[] correctUrls = new String[]{
                "http://www.baidu.com",
                "https://www.baidu.com",
                "ssh://www.baidu.com",
                "ftp://www.baidu.com",
                "a.b.cc",
                "b.cc",
                "1.2.3.4.5.6.7.cc",
                "www.sina.com.cn",
                "blog.fantasymaker.cn",
                "123.voip.sina.com.cn",
                "www.re-ge.com",
                "re-ge.com",
                "re-ge.com.cn",
                "0-----a.co.ao.com.co",
                "123.re-ge.com.cn"
        };
        String[] wrongUrls = new String[]{
                "1.1.1",
                "a.b.c",
                "http/http://www.baidu.com",
                "-aa.com",
                "www.-aa.com"

        };
        //Test correct urls
        for (String url : correctUrls) {
            assertTrue(url, ValidateUtil.isDomainName(url));
        }
        //Test wrong urls
        for (String url : wrongUrls) {
            assertFalse(url, ValidateUtil.isDomainName(url));
        }
    }

    @Test
    public void testIsMobile() throws Exception {
        String[] correct = new String[]{
                "13011111111",
                "13199999999",
                "13211111111",
                "13311111111",
                "13411111111",
                "13511111111",
                "13611111111",
                "13711111111",
                "13811111111",
                "13911111111",
                "15011111111",
                "15111111111",
                "15211111111",
                "15311111111",
                "15411111111",
                "15511111111",
                "15611111111",
                "15711111111",
                "15811111111",
                "15911111111",
                "17011111111",
                "17111111111",
                "17211111111",
                "17311111111",
                "17411111111",
                "17511111111",
                "17611111111",
                "17711111111",
                "17811111111",
                "17911111111",
                "18011111111",
                "18111111111",
                "18211111111",
                "18311111111",
                "18411111111",
                "18511111111",
                "18611111111",
                "18711111111",
                "18811111111",
                "18911111111",
        };
        String[] wrong = new String[]{
                "",
                "1",
                "12",
                "03011111111",
                "23199999999",
                "a18911111111"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isMobile(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isMobile(string));
        }
    }

    @Test
    public void testIsPhone() throws Exception {

    }

    @Test
    public void testIsEmail() throws Exception {
        String[] correct = new String[]{
                "1@139.cn",
                "a@139.cn",
                "a@z.cn",
                "aa@z.com",
                "aa@sina.com.cn",
                "aa_a@sina.com.cn",
                "aa.a@sina.com.cn",
                "aa.a_a@sina.com.cn"
        };
        String[] wrong = new String[]{
                "",
                "adf.com",
                "@139.cn",
                " a@139.cn",
                "a @z.cn",
                "a..a@z.com",
                "a__a@sina.com.cn",
                "aa._a@sina.com.cn",
                "aa.a@sina.c",
                "aa.a_a@sina",
                "aa.a_a@",
                "aa.a_a@.cn",
                "&^&aa.a_a@aaa.cn",
                "aa+a_a@aaa.cn"
        };
        //test correct
        for(String string : correct){
            assertTrue(string, ValidateUtil.isEmail(string));
        }
        //test wrong
        for(String string : wrong){
            assertFalse(string, ValidateUtil.isEmail(string));
        }
    }

    @Test
    public void testIsSuffixWith() throws Exception {

    }

    @Test
    public void testContainsDigital() throws Exception {

    }

    @Test
    public void testContainsLetter() throws Exception {

    }

    @Test
    public void testContainsChinese() throws Exception {

    }

    @Test
    public void testContainsSymbol() throws Exception {

    }

    @Test
    public void testComplyFormat() throws Exception {

    }
}