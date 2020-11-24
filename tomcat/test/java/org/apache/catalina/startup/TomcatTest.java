package org.apache.catalina.startup;

import org.apache.tomcat.jni.OS;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/22 23:39
 */
class TomcatTest {

    @Test
    void start() {
        System.out.println(OS.IS_WIN64);
    }
}