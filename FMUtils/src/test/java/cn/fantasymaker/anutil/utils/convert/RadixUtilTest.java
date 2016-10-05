package cn.fantasymaker.anutil.utils.convert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created :  2016-09-09
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class RadixUtilTest {

    @Test
    public void testToBinaryString() throws Exception {

    }

    @Test
    public void testToOctalString() throws Exception {

    }

    @Test
    public void testToHexString() throws Exception {

    }

    @Test
    public void testToHexString1() throws Exception {

    }

    @Test
    public void testToHexString2() throws Exception {

    }

    @Test
    public void testToHex() throws Exception {

    }

    @Test
    public void testByteArrayToHexString() throws Exception {

    }

    @Test
    public void testBytesToHex() throws Exception {
        byte[] data = {-10, 0, 1, 2, 10};
        String expect = "0001020A";
        String actual = RadixUtil.bytesToHex(data);
        assertEquals(expect, actual);
    }

    @Test
    public void testByteToBinaryString() throws Exception {

    }

    @Test
    public void testByteToBinaryString1() throws Exception {

    }

    @Test
    public void testIntToString() throws Exception {

    }

    @Test
    public void testIntToBinaryString() throws Exception {

    }

    @Test
    public void testIntToOctalString() throws Exception {

    }

    @Test
    public void testIntToHexString() throws Exception {

    }

    @Test
    public void testToHexString3() throws Exception {

    }
}