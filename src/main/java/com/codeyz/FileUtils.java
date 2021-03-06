package com.codeyz;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class FileUtils {

    private static final int BUFF_SIZE = 100000;

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

    /**
     * @param src
     * @param dest
     * @param maxSize The maximum number of bytes. If -1, then write everything.
     * @return Number of bytes copied.
     * @throws IOException
     */
    public static int copy(InputStream src, OutputStream dest, int maxSize) throws IOException {
        if (maxSize == 0) {
            return 0;
        }

        final int packetSize = maxSize > 0 && BUFF_SIZE > maxSize ? maxSize : BUFF_SIZE;
        final byte[] buffer = new byte[packetSize];
        int res = 0;
        do {
            int len = src.read(buffer, 0, maxSize > 0 && packetSize + res > maxSize ? maxSize - res : packetSize);
            if (len <= 0) {
                break;
            }
            dest.write(buffer, 0, len);
            res += len;
            if (maxSize > 0 && res >= maxSize) {
                break;
            }
        } while (true);
        return res;
    }

    public static int copy(InputStream src, OutputStream dest) throws IOException {
        return copy(src, dest, -1);
    }

    public static void copy(File srcFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.getParentFile().mkdirs();
        }
        destFile.createNewFile();

        try (FileInputStream in = new FileInputStream(srcFile);
             FileOutputStream out = new FileOutputStream(destFile, false)) {
            copy(in, out);
        }
    }

    public static void copy(String src, String dest) throws IOException {
        copy(new File(src), new File(dest));
    }

    public static void clearDir(String path) throws IOException {
        File[] files = new File(path).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.exists() && !file.delete()) {
                    throw new IOException("Can't delete file: \"" + file.getAbsolutePath() + "\"");
                }
            }
        }
    }


    public static Map<String, String> readPropToMap(String resourceName) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        Map<String, String> result = new LinkedHashMap<>();

        InputStream stream = loader.getResourceAsStream(resourceName);
        if (stream == null) {
            return null;
        }
        properties.load(stream);

        properties.stringPropertyNames().forEach(key -> {
            String value = properties.getProperty(key);
            result.put(key, value);
        });

        return result;
    }

}
