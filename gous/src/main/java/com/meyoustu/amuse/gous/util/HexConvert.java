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
        String append = "";
        for (byte bt : string.getBytes()) {
            String hex = toHexString(bt & 0xff);
            append += chkUtil(hex);
        }
        return append.toUpperCase();
    }


    default String convertToHex(Color color) {
        int[] rgb = new int[3];
        rgb[0] = color.getRed();
        rgb[1] = color.getGreen();
        rgb[2] = color.getBlue();

        String append = "";
        for (int val : rgb) {
            String hex = toHexString(val);
            append += chkUtil(hex);
        }
        return ("#" + append).toUpperCase();
    }


    private String chkUtil(String hex) {
        return hex.length() == 1 ? "0" + hex : hex;
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
