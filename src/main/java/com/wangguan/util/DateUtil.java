package com.wangguan.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @Modify 2014-10-16
 */
public class DateUtil {

	public static final long ONE_DAY = 86400000l;
	public static SimpleDateFormat Y = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat YM = new SimpleDateFormat("yyyy-MM");
	public static SimpleDateFormat Mdhm = new SimpleDateFormat("MM-dd HH:mm");
	public static SimpleDateFormat Mdhm_cn = new SimpleDateFormat("M月d日 HH:mm");
	public static SimpleDateFormat YmdHM_cn = new SimpleDateFormat("yyyy-M月d日 HH:mm");
	public static SimpleDateFormat yMd = new SimpleDateFormat("yy-MM-dd");
	public static SimpleDateFormat YMd = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat YMdhms_noSpliSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public static SimpleDateFormat YMd_cn = new SimpleDateFormat("yyyy年MM月dd日");
	public static SimpleDateFormat YMdhm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat yMdhm = new SimpleDateFormat("yy-MM-dd HH:mm");
	public static SimpleDateFormat YMdhm_cn = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	public static SimpleDateFormat YMdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat yMdhms = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	public static SimpleDateFormat YMdhmsS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static SimpleDateFormat YMdhmsS_cn = new SimpleDateFormat("yyyy-MM-dd E, HH:mm:ss.SSS", Locale.CHINA);
	public static SimpleDateFormat YMdhmsS_en = new SimpleDateFormat("yyyy-MM-dd E, HH:mm:ss.SSS", Locale.ENGLISH);
	public static SimpleDateFormat YMdhms_noSpli = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat YMdhm_noSpli = new SimpleDateFormat("yyyyMMddHHmm");
	public static SimpleDateFormat Emdhmszy_us = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
	public static SimpleDateFormat YMd_noSpli = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat YMdhm_con = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
	public static SimpleDateFormat Md = new SimpleDateFormat("MM月dd日");
	public static SimpleDateFormat HHmmss = new SimpleDateFormat("HHmmss");

	private static SimpleDateFormat[] sdfs = { Mdhm, Mdhm_cn, YMdhms, yMdhms, YMdhm, yMdhm, YMd, yMd, YMd_cn, Md };

   /**
	* 日期累计方法
	* @param date
	* @param type 1年；2月；3周
	* @param value
	* @return
	*/
	public static Date add(Date date, int type, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (type) {
		case 1:
			calendar.add(Calendar.YEAR, value);
			break;
		case 2:
			calendar.add(Calendar.MONTH, value);
			break;
		case 3:
			calendar.add(Calendar.WEDNESDAY, value);
			break;
		default:
			calendar.add(Calendar.MONTH, value);
			break;
		}
		return calendar.getTime();
	}

	public static long getDateSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long seconds = calendar.getTimeInMillis() / 1000;
		return seconds;
	}

	public static long getSecondBetweenDates(String sDate, String eDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date startDate = sdf.parse(sDate);
			Date endDate = sdf.parse(sDate);
			return getSecondBetweenDates(startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}

	public static long getSecondBetweenDates(Date sDate, Date eDate) {
		return (eDate.getTime() - sDate.getTime()) / 1000;
	}

	public static Date getDateBySe(long second) {
		return new Date(second * 1000);
	}

	public static String getFmtDateBySe(long second) {
		return DateUtil.YMdhms.format(new Date(second * 1000));
	}

	public static Date strToDate(String str) {
		Date date = null;
		if (str != null)
			str = str.trim();
		else
			return date;
		if (str.length() > 2) {
			for (int i = 0; i < sdfs.length; i++)
				try {
					if (i == 0) {
						date = YMdhm.parse(Y.format(new Date()) + "-" + str);
					} else if (i == 1)
						date = YmdHM_cn.parse(Y.format(new Date()) + "-" + str);
					else
						date = sdfs[i].parse(str);
					return date;
				} catch (ParseException e) {
				}
			if (date == null)
				date = specStr2Date(str);
		}
		return date;
	}

	public static Date specStr2Date(String str) {
		Date date = null;
		long num = 0;
		Pattern pat = Pattern.compile("([0-9]+?)分钟前");
		Matcher mthpat = pat.matcher(str);
		if (mthpat.find())
			num = CodeUtil.s2i(mthpat.group(1));
		if (num > 0) {
			date = add(new Date(), -num * 60);
			return date;
		}
		pat = Pattern.compile("([0-9]+?)小时前");
		mthpat = pat.matcher(str);
		if (mthpat.find())
			num = CodeUtil.s2i(mthpat.group(1));
		if (num > 0) {
			date = add(new Date(), -num * 3600);
			return date;
		}
		pat = Pattern.compile("([0-9]+?)天前");
		mthpat = pat.matcher(str);
		if (mthpat.find())
			num = CodeUtil.s2i(mthpat.group(1));
		if (num > 0) {
			date = add(new Date(), (int) (-num));
			return date;
		}
		return date;
	}

	public static String getDate(SimpleDateFormat sdf) {
		return sdf.format(new Date());
	}

	public static String getLocalDate() {
		return getDate(YMdhmsS);
	}

	public static Date strToDate(String str, String format) {
		Date date = null;
		if (str != null) {
			try {
				SimpleDateFormat df = new SimpleDateFormat(format);
				date = df.parse(str);
			} catch (Exception e) {
				System.out.println("DateParse or DateFormat Error!");
			}
		}
		return date;
	}

	public static Date strToDate(String str, SimpleDateFormat sdf) {
		Date date = null;
		if (str != null) {
			try {
				date = sdf.parse(str);
			} catch (Exception e) {
				System.out.println("DateParse or DateFormat Error!");
			}
		}
		return date;
	}

	public static String dateToStr(Date date, String format) {
		String str = null;
		if (date != null)
			try {
				SimpleDateFormat df = new SimpleDateFormat(format);
				str = df.format(date);
			} catch (Exception e) {
				System.out.println("DateFormat Error!");
			}
		return str;
	}

	public static String dateToStr(Date date, SimpleDateFormat df) {
		String str = null;
		if (date != null)
			try {
				str = df.format(date);
			} catch (Exception e) {
				System.out.println("DateFormat Error!");
			}
		return str;
	}

	/**
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date add(Date date, int i) {
		date = new Date(date.getTime() + i * ONE_DAY);
		return date;
	}

	/**
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date add(Date date, long sec) {
		date = new Date(date.getTime() + sec * 1000);
		return date;
	}

	/**
	 * @param date
	 * @return
	 */
	public static Date add(Date date) {
		return add(date, 1);
	}

	/**
	 * @param date
	 * @return
	 */
	public static Date sub(Date date) {
		return add(date, -1);
	}

	public static int getMonthDayNum(String str) {
		int re = 0;
		if (str != null)
			try {
				int month = Integer.parseInt(str.substring(5));
				month = month == 12 ? 1 : (month + 1);
				str = str.substring(0, 5) + month + "-01";
				Date date = YMd.parse(str);
				date = sub(date);
				str = dateToStr(date, YMd);
				str = str.substring(8);
				re = Integer.parseInt(str);
			} catch (Exception e) {
				System.out.println("DateParse or DateFormat Error!");
			}
		return re;
	}

	/**
	 * 根据当前的年份和月份取得开始日期和截止日期
	 * 每月的第一天和最后一天
	 * @param year
	 * @param weekOfYear
	 * @return
	 */
	public static List<Date> getDatesOfMonth() {
		List<Date> list = new ArrayList<Date>();

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		list.add(cal.getTime());

		cal.set(Calendar.DATE, cal.getMaximum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 998);
		list.add(cal.getTime());

		return list;
	}

	/**
	 * 根据年份和月份取得开始日期和截止日期
	 * 每月的第一天和最后一天
	 * @param year
	 * @param weekOfYear
	 * @return
	 */
	public static List<Date> getDatesOfMonth(Integer year, Integer monthOfYear) {
		List<Date> list = new ArrayList<Date>();

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, monthOfYear - 1);

		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		list.add(cal.getTime());

		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 998);
		list.add(cal.getTime());

		return list;
	}

	public static Date obj2Date(Object obj) {
		if (obj != null && obj instanceof Date) {
			Date date = (Date) obj;
			return date;
		} else {
			return null;
		}
	}

	public static Timestamp obj2Timestamp(Object obj) {
		if (obj != null && obj instanceof Timestamp) {
			Timestamp date = (Timestamp) obj;
			return date;
		} else {
			return null;
		}
	}

	/**
	 * 文字转时间
	 * @param type
	 * @return
	 */
	public static String wordToDate(String type) {
		Date date = null;
		if ("1".equals(type)) {
			date = new Date();
		} else if ("2".equals(type)) {
			date = add(new Date(), -7);
		} else if ("3".equals(type)) {
			date = getTime(Calendar.MONTH, -1);
		} else if ("4".equals(type)) {
			date = getTime(Calendar.MONTH, -6);
		} else {
			date = new Date();
		}
		String s = dateToStr(date, "yyyyMMdd");
		return s;
	}

	/**
	 * @param field
	 * @param up
	 * @return
	 */
	public static Date getTime(int field, int up) {
		Calendar c = Calendar.getInstance();
		//int t = c.get(field)+add;
		//c.set(field, t);
		//c.roll(field, up);
		c.add(field, up);
		//System.out.println(format.format(c.getTime()));
		return new Date(c.getTimeInMillis());
	}

	/**
	 * mysql数据库createtime,updatetime varchar(19)专用方法
	 * @param date
	 * @return
	 */
	public static String getDBTime(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(date);
	}

	public static int timeToSecond(long time){
		int s = new Long(time/1000).intValue();
		return s;
	}
	
	public static int stringToSecond(String str, String pattern) {
		long t = stringToMilliSecond(str, pattern);
		int s = new Long(t/1000).intValue();
		return s;
	}
	
	public static long stringToMilliSecond(String str, String pattern) {
		try {
			SimpleDateFormat f = new SimpleDateFormat(pattern);
			Date date = f.parse(str);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 获取某天的零点时间
	 * @param time
	 * @return
	 */
	public static long getDayStart(long time) {
		long t = (time+28800000) % 86400000;
		return time-t;
	}
	
	/**
	 * java比较时间大小
	 * @param s1
	 * @param s2
	 * @return s1>s2返回true；否则返回false
	 */
	public static boolean compareForTime(String s1, String s2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(s1));
			c2.setTime(df.parse(s2));
		} catch (ParseException e) {
			System.out.println("时间格式不正确." + e.getMessage());
		}
		int result = c1.compareTo(c2);
		if (result == 0) {
			System.out.println("s1相等s2");
			return false;
		} else if (result < 0) {
			System.out.println("s1小于s2");
			return false;
		} else {
			System.out.println("s1大于s2");
			return true;
		}
	}
	
	/**
	 * java比较日期大小
	 * @param s1
	 * @param s2
	 * @return s1>s2返回true；否则返回false
	 */
	public static boolean compareForDate(String s1, String s2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(s1));
			c2.setTime(df.parse(s2));
		} catch (ParseException e) {
			System.out.println("日期格式不正确." + e.getMessage());
		}
		int result = c1.compareTo(c2);
		if (result == 0) {
			System.out.println("s1相等s2");
			return false;
		} else if (result < 0) {
			System.out.println("s1小于s2");
			return false;
		} else {
			System.out.println("s1大于s2");
			return true;
		}
	}
	
	public static void main(String[] args) {
		//wordToDate("4");
		String s1 = "2000-01-22 09:12:11";
		String s2 = "2008-01-29 09:12:11";
		compareForTime(s1, s2);
		String s3 = "2005-01-22";
		String s4 = "2003-01-22";
		compareForDate(s3, s4);
	}
	
}
