package com.meyoustu.amuse.gous.listen;

import java.io.IOException;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/20 10:18
 */
public interface ReadInputListener {
    void onReading(byte[] bytes, boolean finished)
            throws IOException;
}
