package com.codeyz;

import java.io.File;

public class FileUtils {

    private FileUtils() {
    }

    public static String getFileExtension(String fileName) {
        int k = fileName.lastIndexOf('.');
        int tmp = fileName.lastIndexOf(File.separatorChar);
        if (tmp > k) {
            k = tmp;
        }
        if (k > -1 && fileName.charAt(k) == '.') {
            return fileName.substring(k + 1);
        } else {
            return null;
        }
    }
}
