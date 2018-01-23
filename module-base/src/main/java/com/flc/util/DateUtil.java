package com.flc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

public class DateUtil {

	/**
	 * 转成存在数据库里的日期格式(总长度25)
	 * 2014-05-07 14:20:00 +0800
	 * @param datestr 2014-05-07 14:20:00
	 * @return 2014-05-07 14:20:00 +0800
	 */
	public static String formatDBDate(String datestr) {
		Date date = null;
		String ret = null;
		if(datestr == null || datestr.equals("") || datestr.length() < 19) return null;
		datestr = datestr.substring(0, 19);
		try {
			SimpleDateFormat secondFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			date = secondFormat.parse(datestr);
			SimpleDateFormat secondFormat1 = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss Z");
			ret = secondFormat1.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 转成存在数据库里的日期格式(总长度25)
	 * 2014-05-07 14:20:00 +0800
	 * @param date
	 * @return
	 */
	public static String formatDBDate(Date date) {
		String sDate = "";
		if (date == null)
			date = new Date();
		try {
			SimpleDateFormat secondFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss Z");
			sDate = secondFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sDate;
	}
	
	/**
	 * 时间戳转成在数据库里的日期格式(总长度19)
	 * 2014-05-07 14:20:00
	 * @param timestamp
	 * @return
	 */
	public static String formatTimestamp(long timestamp) {
		Date date = new Date(timestamp);
		String sDate = "";
		try {
			SimpleDateFormat secondFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			sDate = secondFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sDate;
	}
	
	//获取当天开始，即00:00:00的时间戳
	public static long getBeginingTimestampToday() {
		LocalDateTime begining = new LocalDateTime().withMillisOfDay(0);
		String beString = begining.toString("yyyy-MM-dd HH:mm:ss");
		return java.sql.Timestamp.valueOf(beString).getTime();
	}
	
	//获取明天开始，即00:00:00的时间戳
	public static long getBeginingTimestampTomorrow() {
		LocalDateTime begining = new LocalDateTime().withMillisOfDay(0).plus(Period.days(1));
		String beString = begining.toString("yyyy-MM-dd HH:mm:ss");
		return java.sql.Timestamp.valueOf(beString).getTime();
	}
	
	/**
	 * 获取GMT时间
	 * @return GMT Date
	 * @throws ParseException 
	 */
	public static Date getGMT() throws ParseException {
		Date d = getGMT(formatDBDate(new Date()));
		return d;
	}
	
	/**
	 * 获取GMT时间
	 * @param timestring_withtimezone 例如：2016-10-21 21:59:59 +0800
	 * @return GMT Date
	 * @throws ParseException 
	 */
	public static Date getGMT(String timestring_withtimezone) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//2016-10-21 21:59:59 +0800 => 2016-10-21 21:59:59
		String str = timestring_withtimezone.substring(0, timestring_withtimezone.lastIndexOf(" "));
		//2016-10-21 21:59:59 +0800 => +0800
		String timezonemsg = timestring_withtimezone.substring(timestring_withtimezone.lastIndexOf(" ") + 1, timestring_withtimezone.length());
		//+0800 => +
		String symbol = timezonemsg.substring(0, 1);
		//+0800 => 08
		String hourval = timezonemsg.substring(1, timezonemsg.length() - 2);
		//获取时差绝对值。08 => 8 OR 10 => 10
		int val = Integer.parseInt(hourval.indexOf("0") == 0 ? hourval.substring(1, hourval.length()) : hourval.substring(0, hourval.length()));
		//加减时差值，获得格林威治时间（GMT）
		LocalDateTime dt_fromdb = new LocalDateTime(sdf.parse(str).getTime()); 
		LocalDateTime dt_fromdb_gmt = symbol.equals("+") ? 
				dt_fromdb.minusHours(val) : 
					dt_fromdb.plusHours(val);
		
		Date d = dt_fromdb_gmt.toDate();
		
		return d;
	}
	
	/**
	 * 获取GMT时间
	 * @param timestring_withtimezone 例如："2016-10-21 21:59:59 +0800"
	 * @return GMT 时间戳
	 * @throws ParseException 
	 */
	public static long getGMT_Timestamp(String timestring_withtimezone) throws ParseException {
		Date d = getGMT(timestring_withtimezone);
		return d.getTime();
	}
	
	/**
	 * 获取GMT时间
	 * @param timestring_withtimezone 例如："2016-10-21 21:59:59 +0800"
	 * @param format_pattern 例如："yyyy-MM-dd HH:mm:ss"
	 * @return GMT 日期字符串 "2016-10-21 23:59:59"
	 * @throws ParseException 
	 */
	public static String getGMT_FormatTimeString(String timestring_withtimezone, String format_pattern) throws ParseException {
		Date d = getGMT(timestring_withtimezone);
		return new SimpleDateFormat(format_pattern).format(d);
	}
	
	/**
	 * 根据时间字符串获取时间戳
	 * @param time_string 时间字符串
	 * @param format_pattern 该时间字符串的格式
	 * @return timestamp
	 * @throws ParseException
	 */
	public static long getTimsStamp(String time_string, String format_pattern) throws ParseException {
		return new SimpleDateFormat(format_pattern).parse(time_string).getTime();
	}
	
	/**
	 * 获取当前时间
	 * @return 2014-05-07 14:20:00 +0800
	 */
	public static String getCurDate(){
		return formatDBDate(new Date());
	}

	
}
