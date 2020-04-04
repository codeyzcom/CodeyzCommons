package com.codeyz;

import org.junit.*;

public class StringUtilsTests {

    private byte[] bytes = {18, -81, 119};

    @Test(expected = IllegalArgumentException.class)
    public void hexToByteErrorTest() {
        StringUtils.hexToBytes("QWERT");
    }

    @Test
    public void hexToBytesNullTest() {
        byte[] result = StringUtils.hexToBytes(null);
        Assert.assertNull(result);
    }

    @Test
    public void hexToBytesSuccessTest() {
        String expected = new String(bytes);
        byte[] result = StringUtils.hexToBytes("12aF77");
        Assert.assertEquals(expected, new String(result));
    }

    @Test
    public void bytesToHexNullTest() {
        String result = StringUtils.bytesToHex(null);
        Assert.assertNull(result);
    }

    @Test
    public void bytesToHexSuccessTest() {
        String result = StringUtils.bytesToHex(bytes);
        Assert.assertEquals(result, "12AF77");
    }

}
