package com.meyoustu.amuse.gous.util;

import org.junit.jupiter.api.Test;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/24 18:28
 */
class ConstantsTest {

    @Test
    void toCodeString() {
        System.out.println(Constants.againstDecompile(
                "\"null\""
        ));
    }
}