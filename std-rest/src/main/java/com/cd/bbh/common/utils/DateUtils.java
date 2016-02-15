package com.cd.bbh.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.exception.ApplicationException;

public class DateUtils {
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";
	public static final String YEAR_PATTERN = "yyyy";
	public static final String MONTH_PATTERN = "MM";

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static Date getCurrDate() {
		return new Date();
	}

	/**
	 * 获取日起字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String format(Date date) {
		return new SimpleDateFormat(DATE_PATTERN).format(date);
	}

	/**
	 * 获取日期
	 * 
	 * @param str
	 *            yyyy-MM-dd
	 * @return
	 */
	public static Date parse(String str) {
		try {
			return new SimpleDateFormat(DATE_PATTERN).parse(str);
		} catch (ParseException e) {
			throw new ApplicationException(ResultEnum.DATE_PATTERN_ERROR, e);
		}
	}

	/**
	 * 获取日起字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateTime(Date date) {
		return new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
	}

	/**
	 * 获取日期
	 * 
	 * @param str
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parseDateTime(String str) {
		try {
			return new SimpleDateFormat(DATE_TIME_PATTERN).parse(str);
		} catch (ParseException e) {
			throw new ApplicationException(ResultEnum.DATE_PATTERN_ERROR, e);
		}
	}

	/**
	 * 获取日起字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss SSS
	 */
	public static String formatTimestamp(Date date) {
		return new SimpleDateFormat(DATE_TIMESTAMP_PATTERN).format(date);
	}

	/**
	 * 获取日期
	 * 
	 * @param str
	 *            yyyy-MM-dd HH:mm:ss SSS
	 * @return
	 */
	public static Date parseTimestamp(String str) {
		try {
			return new SimpleDateFormat(DATE_TIMESTAMP_PATTERN).parse(str);
		} catch (ParseException e) {
			throw new ApplicationException(ResultEnum.DATE_PATTERN_ERROR, e);
		}
	}

	/**
	 * 获取年龄
	 * 
	 * @param birthday
	 * @return
	 */
	public static int getAge(Date birthday) {
		int yearAge = (Integer.parseInt(new SimpleDateFormat(YEAR_PATTERN).format(new Date())) - Integer.parseInt(new SimpleDateFormat(YEAR_PATTERN).format(birthday)));
		int monthAge = (Integer.parseInt(new SimpleDateFormat(MONTH_PATTERN).format(new Date())) - Integer.parseInt(new SimpleDateFormat(MONTH_PATTERN).format(birthday)));
		return (monthAge > 0 ? yearAge + 1 : yearAge);
	}

	/**
	 * 获取年龄详情
	 * 
	 * @param birthday
	 * @return x岁x个月
	 */
	public static String getAgeDetail(Date birthday) {
		int yearAge = (Integer.parseInt(new SimpleDateFormat(YEAR_PATTERN).format(new Date())) - Integer.parseInt(new SimpleDateFormat(YEAR_PATTERN).format(birthday)));
		int monthAge = (Integer.parseInt(new SimpleDateFormat(MONTH_PATTERN).format(new Date())) - Integer.parseInt(new SimpleDateFormat(MONTH_PATTERN).format(birthday)));
		if (monthAge < 0) {
			yearAge = yearAge - 1;
			monthAge = 12 + monthAge;
		}
		if(yearAge == 0) {
			if(monthAge == 0) {
				return String.format("%d个月", 1);
			} else {
				return String.format("%d个月", monthAge);
			}
		} else {
			if(monthAge == 0) {
				return String.format("%d岁", yearAge);
			} else {
				return String.format("%d岁%d个月", yearAge, monthAge);
			}
		}
	}

	/**
	 * 获取初生日期
	 * 
	 * @param age
	 *            当前年龄
	 * @return
	 */
	public static Date getBirthday(int age) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - age);
		return calendar.getTime();
	}

	/**
	 * 得到本周周一
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getMonday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		day_of_week = (day_of_week == 0 ? 7 : day_of_week);
		calendar.add(Calendar.DATE, -day_of_week + 1);
		return format(calendar.getTime());
	}

	/**
	 * 得到本周周日
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getSunday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		day_of_week = (day_of_week == 0 ? 7 : day_of_week);
		calendar.add(Calendar.DATE, -day_of_week + 7);
		return format(calendar.getTime());
	}

	/**
	 * 获取一天之中最后一秒的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar getDayOfEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar;
	}

	/**
	 * 获取一天之中开始一秒的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar getDayOfBeginTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar;
	}
}
