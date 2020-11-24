package com.meyoustu.amuse.gous.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/17 01:19
 */
public final class DateFormatter {
    private DateFormatter() {
    }


    private String text;

    public static DateFormatter format(String text) {
        DateFormatter dateFormatter = new DateFormatter();
        dateFormatter.text = text;
        return dateFormatter;
    }

    private final ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(
            () -> new SimpleDateFormat(text)
    );

    @Override
    public String toString() {
        return threadLocal.get().format(new Date());
    }
}

