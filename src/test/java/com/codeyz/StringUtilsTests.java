package com.codeyz;

import org.junit.*;

public class StringUtilsTests {

    private byte[] expectedBytes = {18, -81, 119};

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
    public void hexToBytesSuccsessTest() {
        String expected = new String(expectedBytes);
        byte[] result = StringUtils.hexToBytes("12aF77");
        Assert.assertEquals(expected, new String(result));
    }

}
