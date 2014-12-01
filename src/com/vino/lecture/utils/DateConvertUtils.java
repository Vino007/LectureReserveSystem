package com.vino.lecture.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConvertUtils {
	/**
	 * 字符串转换成日期类型，并将日期的时间提前两小时
	 * @param time
	 * @return
	 */
	public static Date StringToDate(String time){
		Calendar calendar=Calendar.getInstance();
		/*
		 * M月份
		 *  D 一年中的天数
		 *   d一个月中天数
		 *   H 24小时制 h 12小时制
		 *   m分钟
		 *   */
		DateFormat df=null;
		if(time.indexOf("T")!=-1){
		 df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		}else
		 df=new SimpleDateFormat("yyyy.MM.dd HH:mm");
		Date date=null;
		try {
			date = df.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar.setTime(date);
		calendar.get(Calendar.HOUR_OF_DAY);
		calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)-2);
		date= calendar.getTime();
		return date;
	}
}
