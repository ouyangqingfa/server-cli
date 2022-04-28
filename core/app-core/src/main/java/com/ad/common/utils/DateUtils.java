//package com.ad.common.utils;
//
//import com.ad.common.enums.EnumQuaque;
//import com.alibaba.fastjson.JSONArray;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.ZoneOffset;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class DateUtils {
//
//    public static final String DATE_PATTERN_TIME = "HH:mm:ss";
//    public static final String DATE_PATTERN_DATE = "yyyy-MM-dd";
//    public static final String DATE_PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
//    public static final String DATE_PATTERN_DATE_TIME_START = "yyyy-MM-dd 00:00:00";
//    public static final String DATE_PATTERN_DATE_TIME_END = "yyyy-MM-dd 23:59:59";
//
//    static Map<String, SimpleDateFormat> sdfMap = new ConcurrentHashMap<>();
//
//    static SimpleDateFormat getFormatter(String pattern) {
//        if (sdfMap.containsKey(pattern)) {
//            return sdfMap.get(pattern);
//        } else {
//            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//            sdfMap.put(pattern, sdf);
//            return sdf;
//        }
//    }
//
//    /**
//     * 根据时间转换为当天的时间
//     *
//     * @param time
//     * @return
//     */
//    public static Date getDateByTime(String time) throws NumberFormatException {
//        String[] tmp = time.split(":");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        int h = Integer.parseInt(tmp[0]);
//        int m = Integer.parseInt(tmp[1]);
//        int s = Integer.parseInt(tmp[2]);
//        calendar.set(Calendar.HOUR_OF_DAY, Math.min(23, Math.max(0, h)));
//        calendar.set(Calendar.MINUTE, Math.min(59, Math.max(0, m)));
//        calendar.set(Calendar.SECOND, Math.min(59, Math.max(0, s)));
//        return calendar.getTime();
//    }
//
//    /**
//     * 根据时间转换为当天的时间
//     *
//     * @param time
//     * @return
//     */
//    public static String getDateStrByTime(String time) throws ParseException {
//        return getFormatter(DATE_PATTERN_DATE_TIME).format(getDateByTime(time));
//    }
//
//    /**
//     * 根据周转换为时间
//     *
//     * @param week
//     * @return
//     */
//    public static Date getDateByWeek(int week) {
//        return getWeekDay(new Date(), week);
//    }
//
//    /**
//     * 根据周转换为时间
//     *
//     * @param week
//     * @return
//     */
//    public static String getDateStrByWeek(int week, String pattern) {
//        return getFormatter(pattern).format(getDateByWeek(week));
//    }
//
//    /**
//     * 根据天获取时间
//     *
//     * @param day
//     * @return
//     */
//    public static Date getDateByDay(int day) {
//        Calendar calendar = Calendar.getInstance();
//        Date today = new Date();
//        calendar.setTime(today);
//        calendar.add(Calendar.MONTH, 1);
//        calendar.set(Calendar.DAY_OF_MONTH, 0);
//        calendar.set(Calendar.DAY_OF_MONTH, Math.max(1, Math.min(calendar.get(Calendar.DAY_OF_MONTH), day)));
//        calendar.add(Calendar.MONTH, 0);
//        return calendar.getTime();
//    }
//
//    /**
//     * 获取一天的开始 00：00：00
//     *
//     * @param date
//     * @return
//     */
//    public static Date getStartOfDay(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        return calendar.getTime();
//    }
//
//    /**
//     * 获取一天的结束时间 23：59：59
//     *
//     * @param date
//     * @return
//     */
//    public static Date getEndOfDay(Date date) {
//        return getCalendarEndOfDay(date).getTime();
//    }
//
//    public static Calendar getCalendarEndOfDay(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.HOUR_OF_DAY, 23);
//        calendar.set(Calendar.MINUTE, 59);
//        calendar.set(Calendar.SECOND, 59);
//        return calendar;
//    }
//
//    /**
//     * 判断时间是否是当天的最后
//     *
//     * @param date
//     * @param offsetSecond
//     * @return
//     */
//    public static boolean isEndOfDay(Date date, int offsetSecond) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        return (getCalendarEndOfDay(date).getTimeInMillis() - calendar.getTimeInMillis()) / 1000 <= offsetSecond;
//    }
//
//    /**
//     * 获取上一天的同一时间
//     *
//     * @param date
//     * @return
//     */
//    public static Date getLastDayDate(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_YEAR, -1);
//        return calendar.getTime();
//    }
//
//    /**
//     * 获取指定时间的指定周几
//     *
//     * @param date
//     * @param day
//     * @return
//     */
//    public static Date getWeekDay(Date date, int day) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_YEAR, -1);
//        day = Math.min(Math.max(1, day), 7);//周一到周日
//        calendar.set(Calendar.DAY_OF_WEEK, day);
//        calendar.add(Calendar.DAY_OF_WEEK, 1);
//        return calendar.getTime();
//    }
//
//    /**
//     * 获取上周的同一天
//     *
//     * @param date
//     * @return
//     */
//    public static Date getLastWeekDay(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_YEAR, -7);
//        return calendar.getTime();
//    }
//
//    /**
//     * 获取以当前时间为基准多少周的周一0点
//     *
//     * @return
//     */
//    public static Date getMondayStartOfWeek(int week) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.WEEK_OF_YEAR, week);
//        return getStartOfDay(getWeekDay(calendar.getTime(), 1));
//    }
//
//    /**
//     * 获取以当前时间为基准多少周的周日23点
//     *
//     * @param week
//     * @return
//     */
//    public static Date getSundayEndOfWeek(int week) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.WEEK_OF_YEAR, week);
//        return getEndOfDay(getWeekDay(calendar.getTime(), 7));
//    }
//
//
//    /**
//     * 获取上一个周期的时间
//     *
//     * @param date
//     * @param quaque
//     * @return
//     */
//    public static Date getLastPeriodDate(Date date, EnumQuaque quaque) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        switch (quaque) {
//            case DAY:
//                calendar.add(Calendar.DAY_OF_YEAR, -1);
//                break;
//            case WEEK:
//                calendar.add(Calendar.WEEK_OF_YEAR, -1);
//                break;
//            case MONTH:
//                calendar.add(Calendar.MONTH, -1);
//                break;
//        }
//        return calendar.getTime();
//    }
//
//    /**
//     * 获取下一个周期的时间
//     *
//     * @param date
//     * @param quaque
//     * @return
//     */
//    public static Date getNextPeriodDate(Date date, EnumQuaque quaque) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        switch (quaque) {
//            case DAY:
//                calendar.add(Calendar.DAY_OF_YEAR, 1);
//                break;
//            case WEEK:
//                calendar.add(Calendar.WEEK_OF_YEAR, 1);
//                break;
//            case MONTH:
//                calendar.add(Calendar.MONTH, 1);
//                break;
//        }
//        return calendar.getTime();
//    }
//
//    /**
//     * @param date
//     * @return
//     */
//    public static String format(Date date) {
//        return getFormatter(DATE_PATTERN_DATE_TIME).format(date);
//    }
//
//
//    /**
//     * 根据天获取时间字符串
//     *
//     * @param day
//     * @param pattern
//     * @return
//     */
//    public static String getDateStrByDay(int day, String pattern) {
//        return getFormatter(pattern).format(getDateByDay(day));
//    }
//
//    public static Date[] getDateRange(String str, EnumQuaque quaque) throws Exception {
//        JSONArray array = JSONArray.parseArray(str);
//        if (array != null && array.size() == 2) {
//            Date[] dateRange = new Date[2];
//            switch (quaque) {
//                case DAY://每天
//                    dateRange[0] = getDateByTime(array.getString(0));
//                    dateRange[1] = getDateByTime(array.getString(1));
//                    break;
//                case WEEK://每周
//                    dateRange[0] = getStartOfDay(getDateByWeek(array.getInteger(0)));
//                    dateRange[1] = getEndOfDay(getDateByWeek(array.getInteger(1)));
//                    break;
//                case MONTH://每月
//                    dateRange[0] = getStartOfDay(getDateByDay(array.getInteger(0)));
//                    dateRange[1] = getEndOfDay(getDateByDay(array.getInteger(1)));
//                    break;
//            }
//            return dateRange;
//        } else {
//            throw new Exception("时间范围数据错误");
//        }
//
//    }
//
//    /**
//     * @param str
//     * @param quaque
//     * @return
//     */
//    public static String[] getDateStrRange(String str, EnumQuaque quaque) throws Exception {
//        Date[] dates = getDateRange(str, quaque);
//        String[] ranges = new String[2];
//        ranges[0] = getFormatter(DATE_PATTERN_DATE_TIME).format(dates[0]);
//        ranges[1] = getFormatter(DATE_PATTERN_DATE_TIME).format(dates[1]);
//        return ranges;
//    }
//
//
//    public static long getLocalMilliSecond(LocalDateTime dateTime) {
//        return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
//    }
//
//    public static long getLocalMilliSecond(String dateTime) throws ParseException {
//        return getFormatter(DATE_PATTERN_DATE_TIME).parse(dateTime).getTime();
//    }
//
//    public static LocalDateTime getLocalDateByMilli(long milliSecond) {
//        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSecond), ZoneId.systemDefault());
//    }
//
//    public static LocalDateTime getLocalDateBySecond(long second) {
//        return LocalDateTime.ofInstant(Instant.ofEpochSecond(second), ZoneId.systemDefault());
//    }
//
//    /**
//     * 获取过去的分钟
//     *
//     * @param date
//     * @return
//     */
//    public static long pastMinutes(Date date) {
//        long t = new Date().getTime() - date.getTime();
//        return t / (60 * 1000);
//    }
//
//}
