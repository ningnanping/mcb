/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package com.xingzhe.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;

/**
 * 类DateUtil.java的实现描述：日期工具类
 * 
 * @author liulin 2012-2-10 下午05:47:00
 */
public class DateUtil
{
    
    public final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYY_MM = "yyyy-MM";
    public final static String YYYYMMDD = "yyyyMMdd";
    public final static String YYYYMMDDHH = "yyyyMMddHH";
    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public final static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public final static String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public final static String MM_DD = "MM-dd";
    
    /**
     * 判断传入的日期是否为同一天的日期
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameDay(Date d1, Date d2)
    {
        String dateString1 = dateToStr(d1, YYYYMMDD);
        String dateString2 = dateToStr(d2, YYYYMMDD);
        if (dateString1.equals(dateString2))
        {
            return true;
        }
        return false;
    }
    
    /**
     * 通过制定的格式，将日期字符串解析为java.util.Date对象
     * 
     * @param dateStr 日期字符串
     * @param formatStr 解析的格式
     * @return 转换后的结果：Date对象
     * @throws ParseException
     */
    public static Date strToDate(String dateStr, String formatStr)
    {
        Date date = null;
        if (dateStr != null && !"".equals(dateStr))
        {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            try
            {
                date = sdf.parse(dateStr);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        return date;
    }
    
    /**
     * 根据一个完整的日期，格式化成一个只到分钟的时间.yyyy-MM-dd HH:mm
     * 
     * @param date
     * @param formatFormat
     * @return
     */
    public static Date getMinuteDate(String date, String formatFormat)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(strToDate(date, formatFormat));
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }
    
    /**
     * 根据一个完整的日期，格式化成一个只到天的时间.yyyy-MM-dd
     * 
     * @param date
     * @param formatFormat
     * @return
     */
    public static Date getDayDate(String date, String formatFormat)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(strToDate(date, formatFormat));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }
    
    /**
     * 通过制定的格式，将Date对象格式化为字符串
     * 
     * @param date 需要转换的Date对象
     * @param formatStr 转换的格式
     * @return 转换之后的字符串
     */
    public static String dateToStr(Date date, String formatStr)
    {
        String result = null;
        if (date != null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            result = sdf.format(date);
        }
        return result;
    }
    
    /**
     * 将字符串,格式化.
     * 
     * @param date
     * @param format
     * @return
     */
    public static String parseDateStr(String date, String format)
    {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(format))
        {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat(format);
        
        return f.format(strToDate(date, format));
    }
    
    /**
     * 将一个字符串日期转换成另一个字符串日期.
     * 
     * @param date 日期对象
     * @param fromFormat 原来的日期格式
     * @param toFormat 转换后的日期格式
     * @return
     */
    public static String parseDateStr(String date, String fromFormat, String toFormat)
    {
        return dateToStr(strToDate(date, fromFormat), toFormat);
    }
    
    //
    /**
     * 获取当前系统时间
     * 
     * @return
     */
    public static Date getCurrDate()
    {
        return new Date();
    }
    
    /**
     * 获取当前指定格式 的系统时间，可以改变分钟的数值 ，加或者减，例如：-1，10.
     * 
     * @param formatStr 日期的格式化.
     * @param changeMinute 正数或者负数。
     * @return
     */
    public static String getCurrCustomMinuteDate(String formatStr, int changeMinute)
    {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getCurrDate());
        cal.add(Calendar.MINUTE, changeMinute);
        return dateToStr(cal.getTime(), formatStr);
    }
    
    /**
     * 得到当前日期的 指定时间
     * 
     * @param hour
     * @param min
     * @param sec
     * @return
     */
    public static Date getCurrDateFixTime(int hour, int min, int sec)
    {
        Calendar cal = new GregorianCalendar();
        
        cal.setTime(getCurrDate());
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, sec);
        
        return cal.getTime();
    }
    
    /**
     * 获取当前指定格式的系统时间
     * 
     * @param formatStr
     * @return
     */
    public static String getCurrDate(String formatStr)
    {
        return dateToStr(getCurrDate(), formatStr);
    }
    
    /**
     * 获取指定日期的小时 如果date为nul则返回-1.
     * 
     * @param date
     * @return
     */
    public static int getHour(Date date)
    {
        if (date == null)
        {
            return -1;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }
    
    /**
     * 获取指定日期的天,如果date为null则返回-1;
     * 
     * @param date
     * @return
     */
    public static int getDay(Date date)
    {
        if (date == null)
        {
            return -1;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 获取当月最大的天数.
     * 
     * @param date
     * @return
     */
    public static int getMonthMaxDay(Date date)
    {
        if (date == null)
        {
            return -1;
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DATE);
    }
    
    /**
     * 获取当前日期对应的上一个月的日期.
     * 
     * @return
     */
    public static Date getCurrUpMonthDate()
    {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }
    
    /**
     * 获取当前日期对应的前n个月的日期.
     * 
     * @param n
     * @return
     */
    public static Date getCurrUpMonthDate(int n)
    {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -n);
        return c.getTime();
    }
    
    /**
     * 获取指定日期的上一个月的日期
     * 
     * @return
     */
    public static Date getUpMonthDate(String date, String dateFormat)
    {
        Calendar c = new GregorianCalendar();
        c.setTime(strToDate(date, dateFormat));
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }
    
    /**
     * 获取当前日期对应的上一天的日期.
     * 
     * @return
     */
    public static Date getCurrUpDayDate()
    {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }
    
    /**
     * 获取当前日期对应的下一天的日期.
     * 
     * @return
     */
    public static Date getCurrNextDayDate()
    {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
    
    /**
     * 获取当前日期对应的前n天的日期.
     * 
     * @param n
     * @return
     */
    public static Date getCurrUpDayDate(int n)
    {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.DATE, -n);
        return c.getTime();
    }
    
    /**
     * 获取当前日期对应的后n天的日期.
     * 
     * @param n
     * @return
     */
    public static Date getCurrNextDayDate(int n)
    {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.DATE, n);
        return c.getTime();
    }
    
    /**
     * 获取当前日期对应的后n分钟的日期.
     * 
     * @param n
     * @return
     */
    public static Date getCurrNextMinuteDate(int n)
    {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, n);
        return c.getTime();
    }
    
    /**
     * 获取指定日期的上一天的日期。
     * 
     * @param date
     * @param dateFormat
     * @return
     */
    public static Date getUpDayDate(String date, String dateFormat)
    {
        Calendar c = new GregorianCalendar();
        c.setTime(strToDate(date, dateFormat));
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }
    
    /**
     * 获取指定日期的下一天的日期。
     * 
     * @param date
     * @param dateFormat
     * @return
     */
    public static Date getNextDayDate(Date date, String dateFormat)
    {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }
    
    /**
     * 获取指定日期的上n周的日期。
     * 
     * @param n
     * @return
     */
    public static Date getCurrUpWeekDate(int n)
    {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, -7 * n);
        return c.getTime();
    }
    
    /**
     * 获取指定日期的上一周的日期。
     * 
     * @param date
     * @param dateFormat
     * @return
     */
    public static Date getUpWeekDate(String date, String dateFormat)
    {
        Calendar c = new GregorianCalendar();
        c.setTime(strToDate(date, dateFormat));
        c.add(Calendar.DAY_OF_MONTH, -7);
        return c.getTime();
    }
    
    /**
     * 计算指定日期加上后面2个日期之间的差值的日期，精确到秒
     * 
     * @param d1 指定日期
     * @param d2 计算差值日期1
     * @param d3 计算差值日期2
     * @return
     */
    public static Date addDateInterval(Date d1, Date d2, Date d3)
    {
        long d1LongValue = d1.getTime();
        long d2LongValue = d2.getTime();
        long d3LongValue = d3.getTime();
        long lastDateValue = d1LongValue + Math.abs(d3LongValue - d2LongValue);
        Date newDate = new Date(lastDateValue);
        return newDate;
    }
    
    /**
     * 计算指定日期加上增量时间后的日期，精确到秒
     * 
     * @param date 待处理的日期
     * @param addition 增加的毫秒数
     * @return Date
     */
    public static Date addDate(Date date, long addition)
    {
        long dateLongValue = date.getTime();
        long lastDateValue = dateLongValue + addition;
        Date newDate = new Date(lastDateValue);
        return newDate;
    }
    
    /**
     * 检查传入日期是否在当前日期之前
     * 
     * @return boolean
     */
    public static boolean isLate(Date date)
    {
        return getCurrDate().after(date);
    }
    
    /**
     * 获取两个日期的时间差(单位：毫秒)
     * 
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static long getIntervalMilSeconds(String startDateStr, String endDateStr, String dateFormat) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Date startDate = format.parse(startDateStr);
        Date endDate = format.parse(endDateStr);
        return endDate.getTime() - startDate.getTime();
    }
    
    /**
     * 当月第一天
     * 
     * @return
     */
    public static String getFirstDay(boolean isNeedHH24MISS)
    {
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        if(isNeedHH24MISS){
            str.append(" 00:00:00");
        }
        return str.toString();
    }
    
    /**
     * 当月最后一天
     * @return
     */
    public static String getLastDay(boolean isNeedHH24MISS) {
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s);
        if(isNeedHH24MISS){
            str.append(" 23:59:59");
        }
        return str.toString();

    }
    
    public static void main(String[] args) throws ParseException
    {
        
        System.out.println(getFirstDay(true));
        System.out.println(getFirstDay(false));
        System.out.println(getLastDay(true));
        System.out.println(getLastDay(false));
        // System.out.println(addDateInterval(getCurrDate(), getCurrNextDayDate(), getCurrDate()));
        // System.out.println(isSameDay(new Date(), getCurrNextDayDate()));
        // System.out.println(getCurrDateFixTime(24, 00, 00));
        // String s = dateToStr(getCurrUpMonthDate(), YYYY_MM_DD_HH_MM_SS);
        // System.out.println(s);
        // System.out.println(getNextDayDate(new Date(), DateUtil.YYYY_MM_DD));
        // System.out.println(getCurrNextDayDate(3));
        
        //        Date comp = strToDate("2013-04-05 11:11:11", YYYY_MM_DD_HH_MM_SS);
        //        System.out.println(isLate(comp)); 
        //        comp = strToDate("2013-07-05 11:11:11", YYYY_MM_DD_HH_MM_SS);
        //        System.out.println(isLate(comp));
        //
        //        System.out.println(getIntervalMilSeconds("2012-02-11 2:02:36", "2012-02-11 3:02:36", DateUtil.YYYY_MM_DD_HH_MM_SS));
    }
}
