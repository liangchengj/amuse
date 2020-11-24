package com.meyoustu.amuse.gous.safe;

import java.io.InputStream;

import static com.meyoustu.amuse.gous.WholeImpl.WHOLE;
import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/20 12:23
 */
public final class Base64 {


    private Base64() {
    }

    public static byte[] encodeToBytes(byte[] bytes) {
        return getEncoder().encode(bytes);
    }

    public static byte[] encodeToBytes(String string) {
        return encodeToBytes(string.getBytes());
    }

    public static byte[] encodeToBytes(InputStream is) {
        return encodeToBytes(WHOLE.readAsBytes(is));
    }

    public static String encodeToString(byte[] bytes) {
        return new String(encodeToBytes(bytes));
    }

    public static String encodeToString(String string) {
        return new String(encodeToBytes(string));
    }

    public static String encodeToString(InputStream is) {
        return new String(encodeToBytes(is));
    }

    public static byte[] decodeToBytes(byte[] bytes) {
        return getDecoder().decode(bytes);
    }

    public static byte[] decodeToBytes(String string) {
        return getDecoder().decode(string.getBytes());
    }

    public static byte[] decodeToBytes(InputStream is) {
        return getDecoder().decode(WHOLE.readAsBytes(is));
    }

    public static String decodeToString(byte[] bytes) {
        return new String(decodeToBytes(bytes));
    }

    public static String decodeToString(String string) {
        return new String(decodeToBytes(string));
    }

    public static String decodeToString(InputStream is) {
        return new String(decodeToBytes(is));
    }

}
