package com.sptpc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//日期转化工厂 把html5 date类型的如2018/03/04 转换成2018-03-04
public class DateExchangeUtil {
    //字符串转化为日期
    public static Date StringToDate(String str1) throws ParseException {
        String[] buffer = str1.split("/");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < buffer.length; i++) {
            builder.append(buffer[i]);
            if (i != buffer.length - 1) {
                builder.append("-");
            }
        }
        System.out.println(builder.toString());
        SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");

        return date1.parse(builder.toString());
    }

    //将日期转化为字符串
    public static String DateToString(Date date) {
        SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
        return date1.format(date);

    }

    //得到两个时间的相差的天数
    public static long GetDayFormDate(Date date1, Date date2) {
        long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    //距离多少天后的日期
    public static Date GetDateFromDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }


}
