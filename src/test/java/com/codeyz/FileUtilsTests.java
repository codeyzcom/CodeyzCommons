package com.codeyz;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class FileUtilsTests {

    @Test
    public void getFileExtensionNullTest() {
        Path path = Paths.get("opt", "project", "file");
        String result = FileUtils.getFileExtension(path.toString());
        Assert.assertNull(result);
    }

    @Test
    public void getFileExtensionIfCond1Test() {
        Path path = Paths.get("opt", "project", "file");
        String result = FileUtils.getFileExtension(path.toString() + File.separatorChar + "dir");
        Assert.assertNull(result);
    }

    @Test
    public void getFileExtensionSuccessTest() {
        Path path = Paths.get("opt", "project", "file.xml");
        String result = FileUtils.getFileExtension(path.toString());
        Assert.assertEquals(result, "xml");
    }
}
