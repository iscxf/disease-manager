package com.app.manager.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenxf
 * Created on 2019/12/3
 */
@Slf4j
public class DateUtil {

    private static SimpleDateFormat FORMAT =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat DATE_FORMAT =new SimpleDateFormat("yyyy-MM-dd");

    public static String dateToString(long ms){
        return DateFormatUtils.format(ms, "yyyy-MM-dd");
    }

    public static String dateTimeToString(long ms){
        return DateFormatUtils.format(ms, "yyyy-MM-dd HH:mm:ss");
    }

    public static String dateToString(Date date){
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * string 转换long 时间（前端页面时间组件用）
     * @param dateTime
     * @return
     */
    public static Long stringDateTimeToLong(String dateTime){
        if (StringUtils.isEmpty(dateTime)){
            return System.currentTimeMillis();
        }
        try {
            //设置要读取的时间字符串格式
            Date date = FORMAT.parse(dateTime);
            //转换为Date类
            return date.getTime();
        }catch (Exception e){
            log.error("trace conversion time stringDateTimeToLong error! dateTime:[{}], e=",dateTime, e);
            return null;
        }
    }

    /**
     * string 转换long 时间
     * @param date
     * @return
     */
    public static Long stringDateToLong(String date){
        if (StringUtils.isEmpty(date)){
            return System.currentTimeMillis();
        }
        try {
            //设置要读取的时间字符串格式
            Date dateTem = DATE_FORMAT.parse(date);
            //转换为Date类
            return dateTem.getTime();
        }catch (Exception e){
            log.error("trace conversion time stringDateToLong error! date:[{}], e=",date, e);
            return null;
        }
    }

    /**
     * string 转换long 时间
     * @param date
     * @return
     */
    public static Date stringToDate(String date){
        try {
            //设置要读取的时间字符串格式
            Date dateTem = DATE_FORMAT.parse(date);
            //转换为Date类
            return dateTem;
        }catch (Exception e){
            log.error("trace conversion time stringDateToLong error! date:[{}], e=",date, e);
            return new Date();
        }
    }
}
