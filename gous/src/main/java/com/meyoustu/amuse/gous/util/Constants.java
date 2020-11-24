package com.meyoustu.amuse.gous.util;

import com.meyoustu.amuse.gous.mey.Share;

import java.io.File;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/20 15:41
 */
public final class Constants {

    private Constants() {
    }

    public static final String getExecPath() {
        return new File(bytesToString()).getAbsolutePath();
    }

    public static String againstDecompile(String string) {
        if (string == null) {
            return null;
        }
        char[] cs = string.toCharArray();
        if (cs.length == 0) {
            return bytesToString(
                    (byte) 'c', (byte) 'o', (byte) 'm', (byte) '.', (byte) 'm',
                    (byte) 'e', (byte) 'y', (byte) 'o', (byte) 'u', (byte) 's',
                    (byte) 't', (byte) 'u', (byte) '.', (byte) 'a', (byte) 'm',
                    (byte) 'u', (byte) 's', (byte) 'e', (byte) '.', (byte) 'g',
                    (byte) 'o', (byte) 'u', (byte) 's', (byte) '.', (byte) 'u',
                    (byte) 't', (byte) 'i', (byte) 'l', (byte) '.', (byte) 'C',
                    (byte) 'o', (byte) 'n', (byte) 's', (byte) 't', (byte) 'a',
                    (byte) 'n', (byte) 't', (byte) 's', (byte) '.', (byte) 'b',
                    (byte) 'y', (byte) 't', (byte) 'e', (byte) 's', (byte) 'T',
                    (byte) 'o', (byte) 'S', (byte) 't', (byte) 'r', (byte) 'i',
                    (byte) 'n', (byte) 'g', (byte) '(', (byte) ')', (byte) ';'
            );
        }

        String append = bytesToString();
        String convertType = bytesToString(
                (byte) '(', (byte) 'b', (byte) 'y', (byte) 't', (byte) 'e',
                (byte) ')', (byte) ' '
        );
        int count = 1;
        for (char ch : cs) {
            if (isChinese(ch)) {
                for (byte bt : (bytesToString() + ch).getBytes()) {
                    append += convertType + String.valueOf(bt) + bytesToString((byte) ',', (byte) ' ');
                    append += (count % 5 == 0) ?
                            bytesToString((byte) '\n', (byte) '\t') :
                            bytesToString();
                    count++;
                }
            } else {
                if (8 == (byte) ch) { // '\b'
                    // "'\\b"
                    append += convertType + bytesToString((byte) '\'', (byte) '\\', (byte) 'b');
                } else if (12 == (byte) ch) { // '\f'
                    // "'\\f"
                    append += convertType + bytesToString((byte) '\'', (byte) '\\', (byte) 'f');
                } else if (10 == (byte) ch) { // '\n'
                    // "'\\n"
                    append += convertType + bytesToString((byte) '\'', (byte) '\\', (byte) 'n');
                } else if (13 == (byte) ch) { // '\r'
                    // "'\\r"
                    append += convertType + bytesToString((byte) '\'', (byte) '\\', (byte) 'r');
                } else if (9 == (byte) ch) { // '\t'
                    // "'\\t"
                    append += convertType + bytesToString((byte) '\'', (byte) '\\', (byte) 't');
                } else if (92 == (byte) ch) { // '\\'
                    // "'\\\\"
                    append += convertType + bytesToString((byte) '\'', (byte) '\\', (byte) '\\');
                } else if (39 == (byte) ch) { // '\''
                    // "'\\'"
                    append += convertType + bytesToString((byte) '\'', (byte) '\\', (byte) '\'');
                } else if (34 == (byte) ch) { // '"'
                    // "'\""
                    append += convertType + bytesToString((byte) '\'', (byte) '"');
                } else if (0 == (byte) ch) { // '\0'
                    // "'\\0"
                    append += convertType + bytesToString((byte) '\'', (byte) '\\', (byte) '0');
                } else {
                    // "'"
                    append += convertType + bytesToString((byte) '\'') + ch;
                }
                // "', "
                append += bytesToString((byte) '\'', (byte) ',', (byte) ' ');
                append += (count % 5 == 0) ?
                        bytesToString((byte) '\n', (byte) '\t') :
                        bytesToString();
                count++;
            }
        }

        append = append.endsWith(bytesToString((byte) '\n', (byte) '\t')) ?
                Share.subEndBack(append, 2) :
                append;

        append = append.endsWith(bytesToString((byte) ',', (byte) ' ')) ?
                Share.subEndBack(append, 2) :
                append;

        end:
        return bytesToString(
                (byte) 'c', (byte) 'o', (byte) 'm', (byte) '.', (byte) 'm',
                (byte) 'e', (byte) 'y', (byte) 'o', (byte) 'u', (byte) 's',
                (byte) 't', (byte) 'u', (byte) '.', (byte) 'a', (byte) 'm',
                (byte) 'u', (byte) 's', (byte) 'e', (byte) '.', (byte) 'g',
                (byte) 'o', (byte) 'u', (byte) 's', (byte) '.', (byte) 'u',
                (byte) 't', (byte) 'i', (byte) 'l', (byte) '.', (byte) 'C',
                (byte) 'o', (byte) 'n', (byte) 's', (byte) 't', (byte) 'a',
                (byte) 'n', (byte) 't', (byte) 's', (byte) '.', (byte) 'b',
                (byte) 'y', (byte) 't', (byte) 'e', (byte) 's', (byte) 'T',
                (byte) 'o', (byte) 'S', (byte) 't', (byte) 'r', (byte) 'i',
                (byte) 'n', (byte) 'g', (byte) '(', (byte) '\n', (byte) '\t'
        ) + append + bytesToString((byte) '\n', (byte) ')', (byte) ';');
    }

    public static String bytesToString(byte... bytes) {
        return new String(bytes);
    }

    private static boolean isChinese(char ch) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ||
                ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS ||
                ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A ||
                ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B ||
                // "。"
                ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ||
                // "，"
                ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ||
                // "“"
                ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

}
