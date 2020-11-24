package com.meyoustu.amuse.gous.mey;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/17 13:43
 */
public final class JsonNull {
    private JsonNull() {
    }

    public static JsonNull INSTANCE = new JsonNull();

    @Override
    public String toString() {
        return com.meyoustu.amuse.gous.util.Constants.bytesToString(
                (byte) '"', (byte) 'n', (byte) 'u', (byte) 'l', (byte) 'l',
                (byte) '"'
        );
    }

    @Override
    public boolean equals(Object obj) {
        return obj == null || toString().equals(obj);
    }
}
