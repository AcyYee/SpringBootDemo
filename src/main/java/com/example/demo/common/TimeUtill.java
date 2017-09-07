package com.example.demo.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author acy
 * @version 1.0.5
 */

public class TimeUtill {

	/**
	 * 获取当前时间
	 * */
	public static String getDateTimeOR(){
		String dateTime;
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyMMddHHmmssS");
		dateTime = format.format(date);
		System.out.println("当前时间精确到秒:"+dateTime);
		return dateTime;
	}

	/**
	 * 获取当前时间
	 * */
	public static String getDateTime(){
		String dateTime;
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTime = format.format(date);
		System.out.println("当前时间精确到秒:"+dateTime);
		return dateTime;
	}

	/**
	 * 获取当前时间戳
	 * */
	public static String getDateTimeStamp(){
		String dateTime;
		String temp;
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateTime = format.format(date);
		format = new SimpleDateFormat("S");
		temp = format.format(date);
		System.out.println("当前时间精确到毫秒:"+dateTime+"."+temp.charAt(0));
		return dateTime+"."+temp.charAt(0);
	}

	/**
	 * 获取当天的开始结束时间
	 * */
	public static String[] getTodayDateTime(){
		String[] dateTimes = new String[2];
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String temp = format.format(date);
		dateTimes[0] = temp +" 00:00:00";
		dateTimes[1] = temp +" 23:59:59";
		System.out.println("当天的开始时间:"+dateTimes[0]);
		System.out.println("当天的结束时间:"+dateTimes[1]);
		return dateTimes;
	}

	/**
	 * 获取的昨天的开始结束时间
	 * */
	public static String[] getYesterdayDateTime(){
		String[] dateTimes = new String[2];
		Calendar calendar =  Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int maxDay = getMonthLastDay(year,calendar.get(Calendar.MONTH));
		if(day==1){
			if(month==1){
				dateTimes[0] =(year-1)+"-12-31 00:00:00";
				dateTimes[1] =year+"-01-01 00:00:00";
			}else{
				dateTimes[0] =year+"-"+(month-1)+"-"+maxDay+" 00:00:00";
				dateTimes[1] =year+"-"+month+"-01 00:00:00";
			}
		}else{
			dateTimes[0] =year+"-"+month+"-"+(day-1)+" 00:00:00";
			dateTimes[1] =year+"-"+month+"-"+day+" 00:00:00";
		}
		System.out.println("昨天的开始时间:"+dateTimes[0]);
		System.out.println("昨天的结束时间:"+dateTimes[1]);
		return dateTimes;
	}

	/**
	 * 获取上周的开始结束时间
	 * */
	public static String[] getLastWeekTime() {
		String[] dateTimes = new String[2];
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
		int offset1 = 0 - dayOfWeek;
		int offset2 = 6 - dayOfWeek;
		calendar1.add(Calendar.DATE, offset1 - 7);
		calendar2.add(Calendar.DATE, offset2 - 7);
		dateTimes[0] = format.format(calendar1.getTime());
		dateTimes[1] = format.format(calendar2.getTime());
		return dateTimes;
	}

	/**
	 * 获取的昨天的开始结束时间
	 * */
	public static String getYesterday(){
		String dateTime;
		Calendar calendar =  Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int maxDay = getMonthLastDay(year,calendar.get(Calendar.MONTH));
		if(day==1){
			if(month==1){
				dateTime =(year-1)+"-12-31";
			}else{
				dateTime =year+"-"+(month-1)+"-"+maxDay;
			}
		}else{
			dateTime =year+"-"+month+"-"+(day-1);
		}
		return dateTime;
	}

	/**
	 * 获取当周的开始结束时间
	 * */
	public static String[] getThisWeek(Date date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] dateTimes = new String[2];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day - 1);
		dateTimes[0] = format.format(cal.getTime());
		cal.add(Calendar.DATE, 5);
		dateTimes[1] = format.format(cal.getTime());
		return dateTimes;
	}

	/**
	 * 获取上月的开始结束时间
	 * */
	public static String[] getLastMonthDateTime(){
		String[] dateTimes = new String[2];
		Calendar calendar =  Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		if(month == 0){
			month = 12;
			year -=1;
		}
		int maxDay = getMonthLastDay(year,month);
		dateTimes[0] =year+"-"+month+"-01 00:00:00";
		dateTimes[1] =year+"-"+month+"-"+(maxDay)+" 23:59:59";
		System.out.println("当月的开始时间:"+dateTimes[0]);
		System.out.println("当月的结束时间:"+dateTimes[1]);
		return dateTimes;
	}

	public static String[] getLastMonth(){
		String[] dateTimes = new String[2];
		Calendar calendar =  Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		if(month == 0){
			month = 12;
			dateTimes[0] =(year-1)+"-12-01";
			dateTimes[1] =year+"-01-01";
		}else{
			dateTimes[0] =year+"-"+month+"-01";
			dateTimes[1] =year+"-"+(month+1)+"-01";
		}
		System.out.println("当月的开始时间:"+dateTimes[0]);
		System.out.println("当月的结束时间:"+dateTimes[1]);
		return dateTimes;
	}

	/**
	 * 得到指定月的天数
	 * */
	public static int getMonthLastDay(int year, int month)
	{
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	public static String getDateAddTime(String myShopCardGetTime, int shopCardUseDay) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = simpleDateFormat.parse(myShopCardGetTime);
			long dateOR = date.getTime()+shopCardUseDay*24*60*60*1000;
			Date overDate = new Date(dateOR);
			return simpleDateFormat.format(overDate);
		}catch (Exception e){
			e.printStackTrace();
		}
		return myShopCardGetTime;
	}

	public static String getDateAddMouth(String dateTime, Integer summonth) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.MONTH,+summonth);
		return sdf.format(calendar.getTime());
	}

	public static String getDateAddMinute(String dateTime, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.MINUTE,+i);
		return sdf.format(calendar.getTime());
	}
	
	public static void main(String[] args) {
		System.out.println(getDateTime());
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		calendar.add(Calendar.MONTH,48);
		System.out.println(calendar.getTime());
		System.out.println(getDateAddMinute("2017-09-17 09:09:09", 1));
	}
	
}
