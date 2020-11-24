package com.meyoustu.amuse.gous.lang;

import com.meyoustu.amuse.gous.listen.ReadInputListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * @author Liangcheng Juves
 * Created at 2020/04/30 15:08
 */
public interface Stream {
    byte[] BYTES_1024 = new byte[1024];

    private void onReading(
            ReadInputListener readInputListener,
            byte[] bytes,
            int len,
            boolean finished
    ) throws IOException {
        readInputListener.onReading(Arrays.copyOf(bytes, len), finished);
    }

    default void readInputStream(InputStream is, ReadInputListener readInputListener)
            throws IOException {
        int len, endLen = 0;
        while ((len = is.read(BYTES_1024)) != -1) {
            onReading(readInputListener, BYTES_1024, len, false);
            endLen = len;
        }
        onReading(readInputListener, BYTES_1024, endLen, true);
    }

    default byte[] readAsBytes(InputStream is) {
        try {
            int totalLen = -1;
            while (totalLen == -1) {
                totalLen = is.available();
            }
            byte[] bytes = new byte[totalLen];

            readInputStream(is, new ReadInputListener() {
                private int pos;

                @Override
                public void onReading(byte[] bs, boolean finished) {
                    if (!finished) {
                        int bsLen = bs.length;
                        System.arraycopy(bs, 0, bytes, pos, bsLen);
                        pos += bsLen;
                    }
                }
            });
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    default String readAsString(InputStream is) {
        return new String(readAsBytes(is));
    }

    default void readAndWrite(InputStream is, OutputStream... outputStreams)
            throws IOException {
        synchronized (this) {
            ReadInputListener readInputListener = ((bytes, finished) -> {
                if (!finished) {
                    for (OutputStream os : outputStreams) {
                        if (os != null) {
                            os.write(bytes);
                        }
                    }
                } else {
                    release(outputStreams);
                }
            });
            readInputStream(is, readInputListener);
        }
    }


    private void release(OutputStream... outputStreams)
            throws IOException {
        synchronized (this) {
            for (OutputStream os : outputStreams) {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            }
        }
    }

}
