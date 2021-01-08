package com.example.util;

import java.util.Date;

public class DateUtil {
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    
    /**格式化日期 yyyy-MM-dd HH:mm:ss*/
    public static String format(Date date) {
        return cn.hutool.core.date.DateUtil.format(date, FORMAT_DATETIME);
    }
    /**格式化日期*/
    public static String format(Date date, String format) {
        return cn.hutool.core.date.DateUtil.format(date, format);
    }
    
    /**解析日期*/
    public static Date parse(String dateString, String format) {
        return cn.hutool.core.date.DateUtil.parse(dateString, format);
    }
    /**解析日期 yyyy-MM-dd HH:mm:ss*/
    public static Date parse(String dateString) {
        return cn.hutool.core.date.DateUtil.parse(dateString, FORMAT_DATETIME);
    }
    
    /**返回日期开始*/
    public static Date beginOfDay(Date date) {
        return cn.hutool.core.date.DateUtil.beginOfDay(date);
    }
    
    /**返回日期结束*/
    public static Date endOfDay(Date date) {
        return cn.hutool.core.date.DateUtil.endOfDay(date);
    }
    
    /**返回月份开始*/
    public static Date beginOfMonth(Date date) {
        return cn.hutool.core.date.DateUtil.beginOfMonth(date);
    }
    
    /**返回月份结束*/
    public static Date endOfMonth(Date date) {
        return cn.hutool.core.date.DateUtil.endOfMonth(date);
    }
    
    /**返回年开始*/
    public static Date beginOfYear(Date date) {
        return cn.hutool.core.date.DateUtil.beginOfYear(date);
    }
    
    /**返回月份结束*/
    public static Date endOfYear(Date date) {
        return cn.hutool.core.date.DateUtil.endOfYear(date);
    }
    
    /**偏移日期*/
    public static Date offsetDay(Date date, int offset) {
        return cn.hutool.core.date.DateUtil.offsetDay(date, offset);
    }
    /**偏移月*/
    public static Date offsetMonth(Date date, int offset) {
        return cn.hutool.core.date.DateUtil.offsetMonth(date, offset);
    }

}
