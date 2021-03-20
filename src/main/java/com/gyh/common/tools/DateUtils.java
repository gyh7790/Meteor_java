package com.gyh.common.tools;

import org.apache.commons.lang3.time.DateFormatUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间 工具类
 * @author gyh
 * @Date 2020/6/12 22:38
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static Date now() {
        return new Date();
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前年份字数字 格式（yyyy）
     */
    public static int getYear(Date date) {
        return Integer.parseInt(formatDate(date, "yyyy"));
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获取两个日期之间的天数(时间不参加计算)
     * @param before
     * @param after
     * @return
     */
    public static int getDistanceDayOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime() / (1000 * 60 * 60 * 24);
        long afterTime = after.getTime() /(1000 * 60 * 60 * 24);
        return Math.toIntExact(afterTime - beforeTime);
    }

    public static Date formatDateTime(long timeMillis, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(format.format(timeMillis));
        } catch (ParseException e) {
            return new Date();
        }
    }

    // 获取指定日期之后的日期字符串 如 2007-04-15 后一天 就是 2007-04-16
    public static String getNextDay(String strDate, int day) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(parseDate(strDate));
        cal1.add(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(cal1.getTime());
    }

    public static String getNextMonth(String strDate, int monthAmount) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(parseDate(strDate));
        cal1.add(Calendar.MONTH, monthAmount);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        return formatter.format(cal1.getTime());
    }

    public static boolean isFirstDayOfMonth(String strDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(parseDate(strDate));
            cal.add(Calendar.MONTH, 0);
            cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            return format.parse(strDate).equals(cal.getTime());
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 将String型格式化,比如想要将2011-11-11格式化成2011年11月11日,就StringPattern("2011-11-11","yyyy-MM-dd","yyyy年MM月dd日").
     *
     * @param date
     * @param oldPattern
     * @param newPattern
     * @return
     */
    public static String formatDateStr(String date, String oldPattern, String newPattern) {
        if (date == null || oldPattern == null || newPattern == null) {
            return "";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern);
        Date d = null;
        try {
            d = sdf1.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sdf2.format(d);
    }

    public static String getDateByWeekInterval(String date, int interval) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date));
            cal.add(java.util.Calendar.DATE, 7 * interval); // 向前一周；如果需要向后一周，用正数即可
            System.out.println(sdf.format(cal.getTime()));
            return sdf.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return sdf.format(new Date());
        }
    }

    public static String getDateByMonthInterval(String date, int interval) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date));
            cal.add(java.util.Calendar.MONTH, interval); // 向前一月；如果需要向后一月，用正数即可
            System.out.println(sdf.format(cal.getTime()));
            return sdf.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return sdf.format(new Date());
        }
    }


    /**
     * 按天数前后移动
     * @param date
     * @param interval
     * @return
     */
    public static Date getDateByPeriodInterval(Date date, int interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, interval);
        return cal.getTime();
    }

    /**
     * 功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static int getDaySub(String beginDateStr, String endDateStr) {
        int day = 0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate;
        java.util.Date endDate;
        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
            day = (int) ((endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            // TODO 自动生成 catch 块
            day = -99999999;
        }
        return day;
    }


}
