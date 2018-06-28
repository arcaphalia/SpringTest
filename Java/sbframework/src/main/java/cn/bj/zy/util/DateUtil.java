package cn.bj.zy.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static final SimpleDateFormat formatterStandard = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");


	/**
	 * 获取时间字符串
	 * @param date
	 * @return
	 */
	public static String getString(Date date) {
		if(null == date) return "";
		return formatterStandard.format(date);
	}

	/**
	 * 获取日期字符串
	 * @param date
	 * @return
	 */
	public static String getStringDate(Date date) {
		if(null == date) return "";
		return formatterDate.format(date);
	}

	/**
	 * 获取指定格式时间字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		if(null == date || null == format || format.isEmpty())return "";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date getDateWithStdFormat(String strDate) {
		try {
			return formatterStandard.parse(strDate);
		} catch (Exception ex) {
			System.out.println("请确认数据可以转换！"+":" + strDate);
		}
		return null;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param strDate
	 * @return date
	 * @throws ParseException
	 */
	public static Date getDateWithDateFormat(String strDate) throws ParseException {
		try {
			return formatterDate.parse(strDate);
		} catch (Exception ex) {
			System.out.println("请确认数据可以转换！"+":" + strDate);
		}
		return null;
	}
}
