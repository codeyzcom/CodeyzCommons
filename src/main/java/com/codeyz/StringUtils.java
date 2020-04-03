package com.codeyz;

public class StringUtils {

    public static String bytesToHex(byte[] data) {
        if (data == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(2 * data.length);
        for (byte b : data) {
            int b1 = (b >> 4) & 0x0f;
            int b2 = b & 0x0F;
            int ch1 = (b1 >= 0x0A) ? 'A' + b1 - 0xA : '0' + b1;
            int ch2 = (b2 >= 0x0A) ? 'A' + b2 - 0xA : '0' + b2;
            sb.append((char) ch1);
            sb.append((char) ch2);
        }
        return sb.toString();
    }
}
