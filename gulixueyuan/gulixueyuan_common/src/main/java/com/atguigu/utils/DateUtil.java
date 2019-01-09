package com.atguigu.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间戳与字符串转换
 * @author Administrator
 *
 */
public class DateUtil {

	/**
	 * 默认日期格式
	 */
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";//Timestamp format must be yyyy-mm-dd hh:mm:ss

	/**
	 * 默认构造函数
	 */
	private DateUtil() {
	}

	/**
	 * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
	 * @param str 字符串
	 * @param format 日期格式
	 * @return 日期
	 * @throws ParseException
	 */
	public static Date str2Date(String str, String format){
		if (null == str || "".equals(str)) {
			return null;
		}
		// 如果没有指定字符串转换的格式，则用默认格式进行转换
		if (null == format || "".equals(format)) {
			format = DEFAULT_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date str2Date(String str){
		return str2Date(str, DEFAULT_FORMAT);
	}

	/** 日期转换为字符串
	 * @param date 日期
	 * @param format 日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, String format) {
		if (null == date) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 时间戳转换为字符串
	 * @param time
	 * @return
	 */
	public static String timestamp2Str(Timestamp time) {
		Date date = null;
		if(null != time){
			date = new Date(time.getTime());
		}
		return date2Str(date, DEFAULT_FORMAT);
	}

	/** 字符串转换时间戳
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {
		Date date = str2Date(str, DEFAULT_FORMAT);
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 字符串转Canlendar
	 * @param str
	 * @return
	 */
	public static Calendar str2Calendar(String str, String format) {
	 
		SimpleDateFormat sdf= new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar;
	}
	
	public static Calendar date2Calendar(Date date) {
		 
		Calendar cal=Calendar.getInstance();
		
		cal.setTime(date);
		
		return cal;
	}
	
	public static Date calendar2Date(Calendar cal) {
		 
		return cal.getTime();
	}
	
	public static String calendar2Str(Calendar cal, String format) {
		 
		
		if (null == cal.getTime()) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}
	
	
	public static Date getToday(){
		String stringNow = date2Str(new Date(), "yyyy-MM-dd");//当前日期（只有日期）
		Date dateNow = str2Date(stringNow, "yyyy-MM-dd");
		
		return dateNow;
	}
	
	public static Date getTomorrow(){
		
		String stringNow = date2Str(new Date(), "yyyy-MM-dd");//当前日期（只有日期）
		Date dateNow = str2Date(stringNow, "yyyy-MM-dd");
		Calendar c = DateUtil.date2Calendar(dateNow);
		c.add(Calendar.DAY_OF_MONTH, 1);
		Date tomorrow = c.getTime();
		
		return tomorrow;
	}
	
	/**
	 * 
	 * @param n 负数为n天之前，正数为n天之后
	 * @return
	 */
	public static Date getNDaysFromNow(int n){
		
		String stringNow = date2Str(new Date(), "yyyy-MM-dd");//当前日期（只有日期）
		Date dateNow = str2Date(stringNow, "yyyy-MM-dd");
		Calendar c = DateUtil.date2Calendar(dateNow);
		c.add(Calendar.DAY_OF_MONTH, n);
		Date day = c.getTime();
		//System.out.println("------------------------------------------" + day);
		return day;
	}
}