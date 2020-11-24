package org.opencv.osgi;

import org.junit.jupiter.api.Test;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/24 11:02
 */
class OpenCVNativeLoaderTest {

    @Test
    void init() {
        new OpenCVNativeLoader().init();
    }
}