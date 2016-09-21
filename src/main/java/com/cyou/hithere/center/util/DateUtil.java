package com.cyou.hithere.center.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

public class DateUtil {
	private static final Logger __logger = Logger.getLogger(DateUtil.class);
	private static final Calendar calendar = GregorianCalendar.getInstance();
	public static final String DATE_ZH_CN = "yyyy-MM-dd HH:mm:ss";
	private static final int[] dayArray = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private static SimpleDateFormat sdf = new SimpleDateFormat();

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm");

	private static final SimpleDateFormat DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	private static final SimpleDateFormat ORA_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	private static final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");

	private static final SimpleDateFormat ORA_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 比较时间 （分钟）
	 * 
	 * @param date
	 * @param conftime
	 * @return true 超出限制 ，false 没有超出限制
	 */
	public static boolean checkDateBetween(Date date, Integer conftime) {
		if (conftime == 0) {
			return true;
		}
		if (date == null) {
			return true;
		}
		Date nowtime = new Date();
		long between = nowtime.getTime() - date.getTime();
		long day = between / (24 * 60 * 60 * 1000);
		long hour = (between / (60 * 60 * 1000) - day * 24);
		long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);

		if (min > conftime) {
			return true;
		}
		return false;
	}

	/**
	 * checkHourDateBetween(检查是否在小时内，是：true，否：false)
	 * 
	 * @param date
	 * @param conftime
	 * @return 返回类型：boolean
	 * @exception
	 * @since 1.0.0
	 */
	public static boolean checkHourDateBetween(Date date, Integer conftime) {
		if (conftime == 0) {
			return true;
		}
		if (date == null) {
			return true;
		}
		Date nowtime = new Date();
		long between = nowtime.getTime() - date.getTime();
		// long day = between / (24 * 60 * 60 * 1000);
		long hour = (between / (60 * 60 * 1000));
		// long min = ((between / (60 * 1000)));

		if (hour >= conftime) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws ParseException {
		// Long in = System.currentTimeMillis();
	}

	public static String getDate(String timeType) {
		String strTime = null;
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(timeType);
			strTime = simpledateformat.format(new Date());
		} catch (Exception ex) {
			__logger.error("格式化日期错误 : " + ex.getMessage());
		}
		return strTime;
	}

	public static String getDate(String timeType, Date date) {
		String strTime = null;
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(timeType);
			strTime = simpledateformat.format(date);
		} catch (Exception ex) {
			__logger.error("格式化日期错误 : " + ex.getMessage());
		}
		return strTime;
	}

	public static String getDate() {
		return getDate("yyyy-MM-dd HH:mm:ss");
	}

	public static boolean isToday(String comparedDate) {
		return getDate("yyyy-MM-dd").equals(comparedDate);
	}

	public static boolean isToday(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		return getDate("yyyy-MM-dd").equals(dateStr);
	}

	public static String getBeforeDate(String timePos, int day) {
		long dateLong = 0L;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if ((timePos == null) || ("".equals(timePos)))
			dateLong = new Date().getTime();
		else {
			try {
				dateLong = dateFormat.parse(timePos).getTime();
			} catch (ParseException e) {
				__logger.error("输入时间\"" + timePos + "\"不合法,parse时间错误 : " + e.getMessage());
				return "0000:00:00 00:00:00";
			}
		}
		dateLong -= day * 24 * 60 * 60 * 1000;
		return dateFormat.format(new Date(dateLong));
	}

	public static String getBeforeDate(String timeType, String timePos, int day) {
		long dateLong = 0L;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeType);

		if ((timePos == null) || ("".equals(timePos)))
			dateLong = new Date().getTime();
		else {
			try {
				dateLong = simpleDateFormat.parse(timePos).getTime();
			} catch (ParseException e) {
				__logger.error("请检查\"" + timePos + "\"和\"" + timeType + "\"的时间格式是否一样,parse时间错误 : " + e.getMessage());
				return "0000:00:00 00:00:00";
			}
		}
		dateLong -= day * 24 * 60 * 60 * 1000;
		return simpleDateFormat.format(new Date(dateLong));
	}

	public static String getAfterDate(String timeType, String timePos, int day) {
		long dateLong = 0L;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeType);

		if ((timePos == null) || ("".equals(timePos)))
			dateLong = new Date().getTime();
		else {
			try {
				dateLong = simpleDateFormat.parse(timePos).getTime();
			} catch (ParseException e) {
				__logger.error("请检查\"" + timePos + "\"和\"" + timeType + "\"的时间格式是否一样,parse时间错误 : " + e.getMessage());
				return "0000:00:00 00:00:00";
			}
		}
		dateLong += day * 24 * 60 * 60 * 1000;
		return simpleDateFormat.format(new Date(dateLong));
	}

	public static int getMinute(Date startDate, Date endDate) {
		long interval = endDate.getTime() - startDate.getTime();
		return (int) ((endDate.getTime() - startDate.getTime()) / 1000L / 60L);
	}

	public String getYear(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}

	public String getMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(date);
	}

	public String getDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return sdf.format(date);
	}

	public String getHour(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		return sdf.format(date);
	}

	public static synchronized Calendar getCalendar() {
		return Calendar.getInstance();
	}

	public static synchronized String getDateMilliFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateMilliFormat(cal);
	}

	public static Date getDateFbFormat(String strDate) {
		String pattern = "MM/dd/yyyy";
		return parseDateFormat(strDate, pattern);
	}

	public static Date getDateCMFormat(String strDate) {
		String pattern = "yyyy-MM-dd";
		return parseDateFormat(strDate, pattern);
	}

	public static synchronized String getDateMilliFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return getDateFormat(cal, pattern);
	}

	public static synchronized String getDateMilliFormat(Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return getDateFormat(date, pattern);
	}

	public static synchronized Calendar parseCalendarMilliFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return parseCalendarFormat(strDate, pattern);
	}

	public static synchronized Date parseDateMilliFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
		return parseDateFormat(strDate, pattern);
	}

	public static synchronized String getDateSecondFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateSecondFormat(cal);
	}

	public static synchronized String getDateSecondFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(cal, pattern);
	}

	public static synchronized String getDateSecondFormat(Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(date, pattern);
	}

	public static synchronized Calendar parseCalendarSecondFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	public static synchronized Date parseDateSecondFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseDateFormat(strDate, pattern);
	}

	public static synchronized String getDateMinuteFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateMinuteFormat(cal);
	}

	public static synchronized String getDateMinuteFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm";
		return getDateFormat(cal, pattern);
	}

	public static synchronized String getDateMinuteFormat(Date date) {
		String pattern = "yyyy-MM-dd HH:mm";
		return getDateFormat(date, pattern);
	}

	public static synchronized Calendar parseCalendarMinuteFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm";
		return parseCalendarFormat(strDate, pattern);
	}

	public static synchronized Date parseDateMinuteFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm";
		return parseDateFormat(strDate, pattern);
	}

	public static synchronized String getDateDayFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateDayFormat(cal);
	}

	public static synchronized String getDateDayFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd";
		return getDateFormat(cal, pattern);
	}

	public static synchronized String getDateDayFormat(Date date) {
		String pattern = "yyyy-MM-dd";
		return getDateFormat(date, pattern);
	}

	public static synchronized Calendar parseCalendarDayFormat(String strDate) {
		String pattern = "yyyy-MM-dd";
		return parseCalendarFormat(strDate, pattern);
	}

	public static synchronized Date parseDateDayFormat(String strDate) {
		String pattern = "yyyy-MM-dd";
		return parseDateFormat(strDate, pattern);
	}

	public static synchronized String getDateFileFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateFileFormat(cal);
	}

	public static synchronized String getDateFileFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return getDateFormat(cal, pattern);
	}

	public static synchronized String getDateFileFormat(Date date) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return getDateFormat(date, pattern);
	}

	public static synchronized Calendar parseCalendarFileFormat(String strDate) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return parseCalendarFormat(strDate, pattern);
	}

	public static synchronized Date parseDateFileFormat(String strDate) {
		String pattern = "yyyy-MM-dd_HH-mm-ss";
		return parseDateFormat(strDate, pattern);
	}

	public static synchronized String getDateW3CFormat() {
		Calendar cal = Calendar.getInstance();
		return getDateW3CFormat(cal);
	}

	public static synchronized String getDateW3CFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(cal, pattern);
	}

	public static synchronized String getDateW3CFormat(Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(date, pattern);
	}

	public static synchronized Calendar parseCalendarW3CFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	public static synchronized Date parseDateW3CFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseDateFormat(strDate, pattern);
	}

	public static synchronized String getDateFormat(Calendar cal) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(cal, pattern);
	}

	public static synchronized String getDateFormat(Date date) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return getDateFormat(date, pattern);
	}

	public static synchronized Calendar parseCalendarFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseCalendarFormat(strDate, pattern);
	}

	public static synchronized Date parseDateFormat(String strDate) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseDateFormat(strDate, pattern);
	}

	public static synchronized String getDateFormat(Calendar cal, String pattern) {
		return getDateFormat(cal.getTime(), pattern);
	}

	public static synchronized String getDateFormat(Date date, String pattern) {
		synchronized (sdf) {
			String str = null;
			try {
				sdf.applyPattern(pattern);
				str = sdf.format(date);
			} catch (Exception localException) {
			}
			return str;
		}
	}

	public static synchronized Calendar parseCalendarFormat(String strDate, String pattern) {
		synchronized (sdf) {
			Calendar cal = null;
			sdf.applyPattern(pattern);
			try {
				sdf.parse(strDate);
				cal = sdf.getCalendar();
			} catch (Exception localException) {
			}
			return cal;
		}
	}

	public static synchronized Date parseDateFormat(String strDate, String pattern) {
		synchronized (sdf) {
			Date date = null;
			sdf.applyPattern(pattern);
			try {
				date = sdf.parse(strDate);
			} catch (Exception localException) {
			}
			return date;
		}
	}

	public static synchronized int getLastDayOfMonth(int month) {
		if ((month < 1) || (month > 12)) {
			return -1;
		}
		int retn = 0;
		if (month == 2) {
			if (isLeapYear()) {
				retn = 29;
			} else {
				retn = dayArray[(month - 1)];
			}
		} else {
			retn = dayArray[(month - 1)];
		}
		return retn;
	}

	public static synchronized int getLastDayOfMonth(int year, int month) {
		if ((month < 1) || (month > 12)) {
			return -1;
		}
		int retn = 0;
		if (month == 2) {
			if (isLeapYear(year)) {
				retn = 29;
			} else {
				retn = dayArray[(month - 1)];
			}
		} else {
			retn = dayArray[(month - 1)];
		}
		return retn;
	}

	public static synchronized boolean isLeapYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(1);
		return isLeapYear(year);
	}

	public static synchronized boolean isLeapYear(int year) {
		if (year % 400 == 0)
			return true;
		if (year % 4 == 0) {
			return year % 100 != 0;
		}

		return false;
	}

	public static synchronized boolean isLeapYear(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		int year = gc.get(1);
		return isLeapYear(year);
	}

	public static synchronized boolean isLeapYear(Calendar gc) {
		int year = gc.get(1);
		return isLeapYear(year);
	}

	public static synchronized Date getPreviousWeekDay(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return getPreviousWeekDay(gc);
	}

	public static synchronized Date getPreviousWeekDay(Calendar gc) {
		switch (gc.get(7)) {
		case 2:
			gc.add(5, -3);
			break;
		case 1:
			gc.add(5, -2);
			break;
		default:
			gc.add(5, -1);
		}

		return gc.getTime();
	}

	public static synchronized Date getNextWeekDay(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(7)) {
		case 6:
			gc.add(5, 3);
			break;
		case 7:
			gc.add(5, 2);
			break;
		default:
			gc.add(5, 1);
		}

		return gc.getTime();
	}

	public static synchronized Calendar getNextWeekDay(Calendar gc) {
		switch (gc.get(7)) {
		case 6:
			gc.add(5, 3);
			break;
		case 7:
			gc.add(5, 2);
			break;
		default:
			gc.add(5, 1);
		}

		return gc;
	}

	public static synchronized Date getLastDayOfNextMonth(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(getNextMonth(gc.getTime()));
		gc.setTime(getLastDayOfMonth(gc.getTime()));
		return gc.getTime();
	}

	public static synchronized Date getLastDayOfNextWeek(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(getNextWeek(gc.getTime()));
		gc.setTime(getLastDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	public static synchronized Date getFirstDayOfNextMonth(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(getNextMonth(gc.getTime()));
		gc.setTime(getFirstDayOfMonth(gc.getTime()));
		return gc.getTime();
	}

	public static synchronized Calendar getFirstDayOfNextMonth(Calendar gc) {
		gc.setTime(getNextMonth(gc.getTime()));
		gc.setTime(getFirstDayOfMonth(gc.getTime()));
		return gc;
	}

	public static synchronized Date getFirstDayOfNextWeek(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.setTime(getNextWeek(gc.getTime()));
		gc.setTime(getFirstDayOfWeek(gc.getTime()));
		return gc.getTime();
	}

	public static synchronized Calendar getFirstDayOfNextWeek(Calendar gc) {
		gc.setTime(getNextWeek(gc.getTime()));
		gc.setTime(getFirstDayOfWeek(gc.getTime()));
		return gc;
	}

	public static synchronized Date getNextMonth(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(2, 1);
		return gc.getTime();
	}

	public static synchronized Calendar getNextMonth(Calendar gc) {
		gc.add(2, 1);
		return gc;
	}

	public static synchronized Date getNextDay(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, 1);
		return gc.getTime();
	}

	public static synchronized Calendar getNextDay(Calendar gc) {
		gc.add(5, 1);
		return gc;
	}

	public static synchronized Date getNextWeek(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, 7);
		return gc.getTime();
	}

	public static synchronized Calendar getNextWeek(Calendar gc) {
		gc.add(5, 7);
		return gc;
	}

	public static synchronized Date getLastDayOfWeek(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(7)) {
		case 1:
			gc.add(5, 6);
			break;
		case 2:
			gc.add(5, 5);
			break;
		case 3:
			gc.add(5, 4);
			break;
		case 4:
			gc.add(5, 3);
			break;
		case 5:
			gc.add(5, 2);
			break;
		case 6:
			gc.add(5, 1);
			break;
		case 7:
			gc.add(5, 0);
		}

		return gc.getTime();
	}

	public static synchronized Date getFirstDayOfWeek(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(7)) {
		case 1:
			gc.add(5, 0);
			break;
		case 2:
			gc.add(5, -1);
			break;
		case 3:
			gc.add(5, -2);
			break;
		case 4:
			gc.add(5, -3);
			break;
		case 5:
			gc.add(5, -4);
			break;
		case 6:
			gc.add(5, -5);
			break;
		case 7:
			gc.add(5, -6);
		}

		return gc.getTime();
	}

	public static synchronized Calendar getFirstDayOfWeek(Calendar gc) {
		switch (gc.get(7)) {
		case 1:
			gc.add(5, 0);
			break;
		case 2:
			gc.add(5, -1);
			break;
		case 3:
			gc.add(5, -2);
			break;
		case 4:
			gc.add(5, -3);
			break;
		case 5:
			gc.add(5, -4);
			break;
		case 6:
			gc.add(5, -5);
			break;
		case 7:
			gc.add(5, -6);
		}

		return gc;
	}

	public static synchronized Date getLastDayOfMonth(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		switch (gc.get(2)) {
		case 0:
			gc.set(5, 31);
			break;
		case 1:
			gc.set(5, 28);
			break;
		case 2:
			gc.set(5, 31);
			break;
		case 3:
			gc.set(5, 30);
			break;
		case 4:
			gc.set(5, 31);
			break;
		case 5:
			gc.set(5, 30);
			break;
		case 6:
			gc.set(5, 31);
			break;
		case 7:
			gc.set(5, 31);
			break;
		case 8:
			gc.set(5, 30);
			break;
		case 9:
			gc.set(5, 31);
			break;
		case 10:
			gc.set(5, 30);
			break;
		case 11:
			gc.set(5, 31);
		}

		if ((gc.get(2) == 1) && (isLeapYear(gc.get(1)))) {
			gc.set(5, 29);
		}
		return gc.getTime();
	}

	public static synchronized Calendar getLastDayOfMonth(Calendar gc) {
		switch (gc.get(2)) {
		case 0:
			gc.set(5, 31);
			break;
		case 1:
			gc.set(5, 28);
			break;
		case 2:
			gc.set(5, 31);
			break;
		case 3:
			gc.set(5, 30);
			break;
		case 4:
			gc.set(5, 31);
			break;
		case 5:
			gc.set(5, 30);
			break;
		case 6:
			gc.set(5, 31);
			break;
		case 7:
			gc.set(5, 31);
			break;
		case 8:
			gc.set(5, 30);
			break;
		case 9:
			gc.set(5, 31);
			break;
		case 10:
			gc.set(5, 30);
			break;
		case 11:
			gc.set(5, 31);
		}

		if ((gc.get(2) == 1) && (isLeapYear(gc.get(1)))) {
			gc.set(5, 29);
		}
		return gc;
	}

	public static synchronized Date getFirstDayOfMonth(Date date) {
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.set(5, 1);
		return gc.getTime();
	}

	public static synchronized Calendar getFirstDayOfMonth(Calendar gc) {
		gc.set(5, 1);
		return gc;
	}

	public static synchronized String toOraString(Date theDate, boolean hasTime) {
		DateFormat theFormat;
		if (hasTime) {
			theFormat = getOraDateTimeFormat();
		} else {
			theFormat = getOraDateFormat();
		}
		return toString(theDate, theFormat);
	}

	public static synchronized String toString(Date theDate, boolean hasTime) {
		DateFormat theFormat;
		if (hasTime) {
			theFormat = getDateTimeFormat();
		} else {
			theFormat = getDateFormat();
		}
		return toString(theDate, theFormat);
	}

	public static synchronized DateFormat getDateFormat() {
		SimpleDateFormat theDateFormat = (SimpleDateFormat) DATE_FORMAT.clone();
		theDateFormat.setLenient(false);
		return theDateFormat;
	}

	public static synchronized DateFormat getDateTimeFormat() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) DATE_TIME_FORMAT.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	public static synchronized DateFormat getOraDateFormat() {
		SimpleDateFormat theDateFormat = (SimpleDateFormat) ORA_DATE_FORMAT.clone();
		theDateFormat.setLenient(false);
		return theDateFormat;
	}

	public static synchronized DateFormat getOraDateTimeFormat() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_FORMAT.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	public static synchronized String toString(Date theDate, DateFormat theDateFormat) {
		if (theDate == null)
			return "";
		return theDateFormat.format(theDate);
	}

	public static synchronized String getDayOfWeek(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int dayOfWeek = ca.get(7);
		switch (dayOfWeek) {
		case 1:
			return "星期日";
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";
		}
		return "星期一";
	}

	public static synchronized String getMonthOfYear(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int month = ca.get(2);
		switch (month) {
		case 0:
			return "一月";
		case 1:
			return "二月";
		case 2:
			return "三月";
		case 3:
			return "四月";
		case 4:
			return "五月";
		case 5:
			return "六月";
		case 6:
			return "七月";
		case 7:
			return "八月";
		case 8:
			return "九月";
		case 9:
			return "十月";
		case 10:
			return "十一月";
		case 11:
			return "十二月";
		}
		return "一月";
	}

	public static synchronized int getDayOfMonth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(5);
	}

	/**
	 * 年份的加减
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date getDateByBir(Date date, Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}
}