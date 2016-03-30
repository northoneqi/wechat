package com.qishi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderRule {
	public  String GetOrderCode(int orderFrom)
	{
	 
	    String rd =String.valueOf((int)(Math.random()*(99-10+1)+10));
	    Date date  = new Date();
	    SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmssSSS");
	    String time = sdf.format(date).substring(0,14);
	    if (orderFrom == 2) {
	    	return "T" + time + rd;
	    }
	    if (orderFrom == 3){
	    	return "S" + time + rd;
	    }
	    if (orderFrom == 4){
	    	return "TB" + time + rd;
	    }
	    if (orderFrom == 5) {
	    	return "WX" + time + rd;
	    }
	    return  time + rd;
	}  
}
