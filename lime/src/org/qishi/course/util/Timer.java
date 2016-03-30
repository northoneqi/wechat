package org.qishi.course.util;

import java.util.Calendar;

/**
 * @author dhzhanghailong
 *
 *@date 2014年6月12日 上午11:41:54 
 */
public class Timer {
	 public static long startTime = 0;
	 public Timer() {
	  Calendar c = Calendar.getInstance();
	  startTime = c.getTimeInMillis();
	  System.out.println("��ʱ��ʼ- " + startTime);
	 }
	 public static long end() {
	  Calendar c = Calendar.getInstance();
	  long endTime = c.getTimeInMillis();
	  System.out.println("��ʱ����- " + endTime);
	  return endTime - startTime;
	 }
	}