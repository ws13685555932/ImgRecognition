package com.momo.imgrecognition.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    public static String timeStamp2Date(long time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(time);
        String timeString = dateFormat.format(date);
        return timeString;
    }

    public static long convertTimeStamp(String timeString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static int getYear(long time){
        String dateStr = timeStamp2Date(time);
        String year = dateStr.substring(0,4);
        return Integer.parseInt(year);
    }

    public static int getMonth(long time){
        String dateStr = timeStamp2Date(time);
        String month = dateStr.substring(5,7);
        return Integer.parseInt(month);
    }

    public static int getDay(long time){
        String dateStr = timeStamp2Date(time);
        String day = dateStr.substring(8,10);
        return Integer.parseInt(day);
    }

    public static int getHour(long time){
        String dateStr = timeStamp2Date(time);
        String hour = dateStr.substring(11,13);
        return Integer.parseInt(hour);
    }

    public static int getMinute(long time){
        String dateStr = timeStamp2Date(time);
        String min = dateStr.substring(14,16);
        return Integer.parseInt(min);
    }

    //判断两个时间戳是否为同一天
    public static boolean isSameDay(long firstTimeStamp, long secondTimeStamp){
        if(getYear(firstTimeStamp) == getYear(secondTimeStamp) &&
                getMonth(firstTimeStamp) == getMonth(secondTimeStamp)
                && getDay(firstTimeStamp) == getDay(secondTimeStamp)){
            return true;
        }
        return false;
    }

    public static boolean isYesterDay(long today, long yesterday){
        if(getYear(today) == getYear(yesterday) &&
                getMonth(today) == getMonth(yesterday)
                && getDay(today) == (getDay(yesterday)+1)){
            return true;
        }
        return false;
    }

    public static String getWeek(long time){
        String Week = "周";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//也可将此值当参数传进来
        Date date = new Date(time);
        String pTime = format.format(date);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        switch(c.get(Calendar.DAY_OF_WEEK)){
            case 1:
                Week += "日";
                break;
            case 2:
                Week += "一";
                break;
            case 3:
                Week += "二";
                break;
            case 4:
                Week += "三";
                break;
            case 5:
                Week += "四";
                break;
            case 6:
                Week += "五";
                break;
            case 7:
                Week += "六";
                break;
            default:
                break;
        }
        return Week;
    }

}