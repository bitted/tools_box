package com.cyou.hithere.center.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtils {

	private static Logger logger = Logger.getLogger(DateUtils.class);
	
	public static SimpleDateFormat SDF0 = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	/**
	 * yyyy-MM-dd
	 */
	public static SimpleDateFormat SDF1 = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * yyyy/MM/dd
	 */
	public static SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy/MM/dd");
	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	public static SimpleDateFormat SDF21 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	/**
	 * yyyy/MM/dd
	 */
	public static SimpleDateFormat SDF3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * yyMMddHHmmss
	 */
	public static SimpleDateFormat SDF4 = new SimpleDateFormat("yyyyMMddhhmmss");
	
	
	public static Date getStartTime(){  
		Calendar calendar = Calendar.getInstance();  
        //如果没有这种设定的话回去系统的当期的时间  
        calendar.set(Calendar.HOUR_OF_DAY, 24);  
        calendar.set(Calendar.MINUTE, 0);  
        calendar.set(Calendar.SECOND, 0);  
        calendar.set(Calendar.MILLISECOND, 0);  
        Date date = new Date(calendar.getTimeInMillis()); 		
        return date;  
    } 

	/**
	 * 仅比较年月日 相等
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isSameDate(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			logger.debug("isSameDate : 参数有空值，直接返回false");
			return false;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);

		Calendar c2 = Calendar.getInstance();
		c1.setTime(d2);

		return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DATE) == c2.get(Calendar.DATE);

	}

	/**
	 * 保留日期 ，把时间设置�?0 <br>
	 * HOUR_OF_DAY<br>
	 * MINUTE<br>
	 * SECOND<br>
	 * MILLISECOND<br>
	 * 
	 * @param d
	 * @return
	 */
	public static Date clearTime(Date d) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(d);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime();
	}

	public static String getDateTime() {
		return String.valueOf(new Date().getTime());
	}

	/**
	 * 增加天数(负�?为减)
	 * 
	 * @param d
	 * @param dayToAdd
	 * @return
	 */
	public static Date addDay(Date d, int dayToAdd) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(d);
		ca.add(Calendar.DAY_OF_MONTH, dayToAdd);
		return ca.getTime();
	}
	
	/**
	 * 增加分(负为减)
	 * @param d
	 * @param minToAdd
	 * @return
	 */
	public static Date addMin(Date d, int minToAdd) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(d);
		ca.add(Calendar.MINUTE, minToAdd);
		return ca.getTime();
	}
	/**
	 * 增加秒(负为减)
	 * @param d
	 * @param minToAdd
	 * @return
	 */
	public static Date addSecond(Date d, int minToAdd) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(d);
		ca.add(Calendar.SECOND, minToAdd);
		return ca.getTime();
	}
	/**
	 * 是否�?今天"
	 * 
	 * @param d
	 * @return
	 */
	public static boolean isToday(Date d) {
		return isSameDate(d, new Date());
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		if (date == null) {
			return "";
		}
		if (format == null) {
			format = "yyyy-MM-dd hh:mm:ss";
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 日期转字符串
	 * 12小时�?
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return df.format(date);
	}
	
	public static String dateToString0() {
		return SDF0.format(new Date());
	}

	public static String dateToString() {
		return SDF2.format(new Date());
	}

	public static String dateToString2() {
		return SDF1.format(new Date());
	}
	
	public static String dateToString3() {
		return SDF4.format(new Date());
	}

	/**
	 * add by Bill
	 * 日期转字符串
	 * 24小时�?
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date24ToString(Date date) {
		if (date == null) {
			return "";
		}		
		return SDF3.format(date);
	}

	/**
	 * 日期转时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static long dateToTimeMillis(Date date) {
		if (date == null) {
			return 0;
		}
		return date.getTime() / 1000;
	}

	public static Date StringToDate(String datestr) {
		Date dt = null;
		if (datestr == null || "".equals(datestr)) {
			dt = new Date();
		}
		try {
			dt = SDF21.parse(datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dt;
	}
	public static Date StringToDate(String datestr,SimpleDateFormat sdf) {
		Date dt = null;
		if (datestr == null || "".equals(datestr)) {
			dt = new Date();
		}
		try {
			dt = sdf.parse(datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dt;
	}

	public static void main(String[] args) {
		// System.out.println(clearTime(new Date()));

		// System.out.println(StringToDate(dateToString(new Date())));
		// System.out.println(date24ToString(new Date()));

		System.out.println(StringToDate("2016/05/10 20:46:00").getTime());
		System.out.println(StringToDate("2016/05/29 23:59:59").getTime());
//		System.out.println(getDateTime());
//		System.out.println(dateToTimeMillis(new Date()));
		
	}
}
