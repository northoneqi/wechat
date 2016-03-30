package com.qishi.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
  private static String default_path = "/lime";
  private static int default_age = 60 * 60 * 24 * 2;
  
  
  public static void addCookie(String name,String value,int age,HttpServletResponse response) throws UnsupportedEncodingException{
	 Cookie cookie = 
		 new Cookie(name,URLEncoder.encode(value,"utf-8"));
	 cookie.setMaxAge(age);
	 cookie.setPath(default_path);
	 response.addCookie(cookie);
  }
  public static void addCookie(String name,String value,HttpServletResponse response) throws UnsupportedEncodingException{
	  addCookie(name,value,default_age,response);	 
  }  
  
  public static String findCookie(String name,HttpServletRequest request)
  throws UnsupportedEncodingException{
	String value = null;
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie c:cookies){
			if(c.getName().equals(name)){
				value = URLDecoder.decode(c.getValue(), "utf-8");
			}
		}
	}
	return value;
	
  }
  
  public static List findCookieAll(String name,HttpServletRequest request)
  throws UnsupportedEncodingException{
	List list = new ArrayList<>();
	String value=null;
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie c:cookies){
			String gname = c.getName().substring(0, 5);
			if(gname.equals(name.substring(0, 5))){
				value = URLDecoder.decode(c.getValue(), "utf-8");
				list.add(value);
			}
		}
	}
	return list;
  }
  
  //比较cookie的值有没有相同的
  public static String findCookieByValue(String value,HttpServletRequest request){
	  String name = null;
	  Cookie[] cookie = request.getCookies();
	  if(cookie != null){
		  for(Cookie c:cookie){
			  if(c.getValue().equals(value)){
				  name = c.getName();
			  }
		  }
	  }
	  return name;
  }

  public static void deleteCookie(String name,HttpServletResponse response){
	Cookie c = new Cookie(name,"");
	c.setMaxAge(0);
	c.setPath(default_path);
  }
  
}
