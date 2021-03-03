package com.meyoustu.amuse.gous.util;


import java.awt.Color;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toHexString;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/17 19:34
 */
public interface HexConvert {
    default String convertToHex(String string) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b & 0xff));
        }

        return sb.toString();
    }

    default String convertToHex(Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }

    default String parseHex(String hex) {
        int hexLen = hex.length();
        byte[] bts = new byte[hexLen / 2];
        for (int i = 0; i < hexLen; i += 2) {
            bts[i / 2] = (byte) parseInt(hex.substring(i, i + 2), 16);
        }
        return new String(bts);
    }

    default Color parseColorHex(String colorHex) {
        int colorHexLen = colorHex.length();
        colorHex = colorHex.replace("#", "");
        byte[] bts = new byte[colorHexLen / 2];
        for (short i = 0; i < colorHexLen; i += 2) {
            bts[i / 2] = (byte) (parseInt(colorHex.substring(i, i + 2), 16));
        }

        return new Color(bts[0] & 0xff, bts[1] & 0xff, bts[2] & 0xff);
    }

}
