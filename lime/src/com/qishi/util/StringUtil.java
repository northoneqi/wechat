package com.qishi.util;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil{
	
	public static int nullToNumber(String str, int defalut){
		if( isEmpty( str ) ){
			return defalut;
		}
		return Integer.parseInt(str);
	}
	
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str)&&!"null".equals(str);
	}
	
	public static String nullToBlank(Object obj){
		return obj != null ? obj.toString() : "";
	}
	
	public static String getFilePath(String fileName)
	{       
       String date = getFormatTime(new Date(), "yyyyMMdd-hhmmss");
       //String name = fileName.substring(fileName.lastIndexOf("\\")+1,fileName.lastIndexOf("."));  
       String name = fileName.substring(0,fileName.lastIndexOf("."));
       return name + date;
	}
	
	public static String getFilePath( String dir, String fileName )
	{       
    	String d = dir;
    	if( !fileName.endsWith( "\\" ) && !fileName.endsWith( "/" ) ){
    	   d += File.separator;
		}
	
		return d + fileName;
	}
	
	public static String getFileName(String fileName)
	{  
		if( fileName.indexOf( "\\" ) > -1 )
			return fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length());
		else if(fileName.indexOf( "/" ) > -1 ) {
			return fileName.substring(fileName.lastIndexOf("/")+1,fileName.length());
		}
		else{
			return fileName;
		}
	}
	
	public static String getFileEXTName(String fileName)
	{       
       return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
	}
	
	public static String getFileEXTName(String fileName, String newFilename )
	{       
       return newFilename + '.' + getFileEXTName( fileName );
	}
	
	public static String getFormatTime(Date date,String format)
	{
		SimpleDateFormat df = new SimpleDateFormat(format);
		if (date == null)
		{
			return df.format(new Date());
		}
		else
		{
			return df.format(date);
		}		
	}
	
	public static String getShowTime(Date date,String format)
	{
		SimpleDateFormat df = new SimpleDateFormat(format);
		if (date == null)
		{
			return "";
		}
		else
		{
			return df.format(date);
		}
	}
	
	public static String roundDoule(double value)
	{       
		DecimalFormat format = new DecimalFormat("0.00");		
		return format.format(value);
	}
	
	public static String[] split(String s, String separator) {
		if (s == null || s.trim().length() == 0)
			return null;
		String result[] = (String[]) null;
		String tmp[] = new String[500];
		String parseStr = s;
		int k = 0;
		int j = 0;
		for (int pos = 0; pos != -1;) {
			pos = parseStr.indexOf(separator);
			if (pos != -1) {
				tmp[k++] = parseStr.substring(j, pos);
				parseStr = parseStr.substring(pos + separator.length(),
						parseStr.length());
			}
		}

		tmp[k++] = parseStr.substring(j, parseStr.length());
		if (k == 0)
			return result;
		result = new String[k];
		for (int i = 0; i < k; i++)
			result[i] = tmp[i];

		return result;
	}
	
}
