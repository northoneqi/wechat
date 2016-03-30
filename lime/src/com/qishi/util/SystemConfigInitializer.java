package com.qishi.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;



/**
 * 系统初始化加载配置信息
 * @author haiyang
 *
 */
public class SystemConfigInitializer implements ServletContextListener {

	/**
	 * log4j日志对象
	 */
	private Logger logger = Logger.getLogger(SystemConfigInitializer.class);
	/**
	 * 系统退出时执行
	 */
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
	}
	/**
	 * 系统启动时执行
	 * 把前台需要的message加载到getServletContext中，作为全区常量进行使用
	 */
	public void contextInitialized(ServletContextEvent event) {
		
		try{
			ServerConfig.initialize(); 
		} catch (Exception e) {
			logger.error("Load configuration get exception.",e);
			e.printStackTrace();
		}
		
   }
	
	
}

