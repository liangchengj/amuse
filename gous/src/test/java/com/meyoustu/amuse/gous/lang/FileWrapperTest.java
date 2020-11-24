package com.meyoustu.amuse.gous.lang;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/23 11:44
 */
class FileWrapperTest {

    @Test
    void traverseDir() {
        File file = new File("C:\\Users\\LiangchengJ\\Desktop\\opencv_430\\org\\opencv");
        traverseDri(file);
    }

    void traverseDri(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                if (f.getName().endsWith(".class")) {
                    f.delete();
                } else if (f.isDirectory()) {
                    traverseDri(f);
                }
            }
        }
    }

    @Test
    void testTraverseDir() {
        FileWrapper fileWrapper =
                new FileWrapper("C:\\Users\\LiangchengJ\\Desktop\\opencv_430\\org\\opencv");
        fileWrapper.traverseDir(file -> System.out.println(file.getAbsolutePath()));
    }
}