package com.vino.lecture.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConvertUtils {
	/**
	 * �ַ���ת�����������ͣ��������ڵ�ʱ����ǰ��Сʱ
	 * @param time
	 * @return
	 */
	public static Date StringToDate(String time){
		Calendar calendar=Calendar.getInstance();
		/*
		 * M�·�
		 *  D һ���е�����
		 *   dһ����������
		 *   H 24Сʱ�� h 12Сʱ��
		 *   m����
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
