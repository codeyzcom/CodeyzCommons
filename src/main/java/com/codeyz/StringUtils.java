package com.codeyz;

public class StringUtils {

    public static String bytesToHex(byte[] data) {
        if (data == null) {
            return null;
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

    public static int asciiToHex(char c) {
        if ((c >= 'A') && (c <= 'F')) {
            return (c - 'A' + 10);
        }
        if ((c >= '0') && (c <= '9')) {
            return (c - '0');
        }
        if ((c >= 'a') && (c <= 'f')) {
            return (c - 'a' + 10);
        }
        throw new IllegalArgumentException();
    }

    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        int blen = (len + 1) / 2;
        byte[] buffer = new byte[blen];

        for (--len, --blen; len > 0; len -= 2, --blen) {
            buffer[blen] = (byte) (asciiToHex(str.charAt(len - 1)) << 4 | asciiToHex(str.charAt(len)));
        }
        if (len == 0) {
            buffer[0] = (byte) asciiToHex(str.charAt(0));
        }
        return buffer;
    }
}
