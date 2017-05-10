package com.uiiang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuliqiang on 2017/5/10.
 */
public class DateUtils {
    public static String dateTimeFormat1(Date date) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        return myFmt.format(date);
    }

    public static String dateTimeFormat2(Date date) {
        SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");//等价于now.toLocaleString()
        return myFmt2.format(date);
    }
}
