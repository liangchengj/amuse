package com.meyoustu.amuse.gous.lang;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/20 10:54
 */
class IOTest implements Stream {

    @Test
    void readAsBytes()
            throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hello".getBytes());
        System.out.println(new String(readAsBytes(byteArrayInputStream)));
        readAndWrite(byteArrayInputStream, System.err);
    }
}