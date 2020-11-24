package com.meyoustu.amuse.gous.mey;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/23 11:50
 */
public abstract class Share {
    private Share() {
    }


    public static String subEndBack(String string, int backStep) {
        return string != null ?
                string.isEmpty() ?
                        string :
                        string.substring(0, string.length() - backStep) :
                null;
    }

    public static String subEndBackOne(String string) {
        return subEndBack(string, 1);
    }

    public static String subWithLastIndex(String string, String ch, int gotoStep) {
        return string != null ?
                string.isEmpty() ?
                        string :
                        string.substring(0, string.lastIndexOf(ch) + gotoStep) :
                null;
    }

    public static String subWithLastIndex(String string, String ch) {
        return subWithLastIndex(string, ch, 1);
    }


}
