package com.qishi.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BigorderRule {
		public static String rule(){
			
			//String rule = UUID.randomUUID().toString();
			String rd =String.valueOf((int)(Math.random()*(9999-1000+1)+1000));
			
			String rule = String.valueOf( new Date().getTime())+rd;
			return rule;
			
		}
}
