package com.qishi.util;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;


public class ServerConfig implements Serializable {

	private static final long serialVersionUID = 19386536267359832L;

	private static Logger logger = Logger.getLogger(ServerConfig.class);
	
	private static String baseUrl ="";
	private static String context ="";
	private static String APP_ID ="";
	private static String APP_SECRET ="";
	private static String partnerId ="";
	private static String urlip ="";
	/**
	 * 系统配置文件初始化方法，整个应用运行过程中只初始化一次
	 * @return xml引擎实例
	 * @throws Exception
	 */
	public synchronized static void initialize() throws Exception {
		try {
			URL path = ServerConfig.class.getClassLoader().getResource("/");
			String classPath = "";
			if (null == path ){
				File directory = new File("");//设定为当前文件夹
				try{
				    System.out.println(directory.getCanonicalPath());//获取标准的路径
				    classPath = directory.getCanonicalPath() + "\\src";
				}catch(Exception e){
					e.printStackTrace();
				}
			} else {
				classPath = path.getPath();
				
			}
			String filePath = classPath + "/conf" + File.separator
					+ "serverConfig.xml";
			logger.debug("配置文件路径: " + filePath);
			File configFile = new File(filePath);
			Document document = null;
			if (configFile.exists()) {
				InputStream in = ConfigHelper.getResourceFromFile(configFile);
				SAXReader reader = new SAXReader();
				document = reader.read(in);

				baseUrl = (String) document.getRootElement().element("base-url").getText();
				context = (String) document.getRootElement().element("context").getText();
				APP_ID = (String) document.getRootElement().element("APP_ID").getText();
				APP_SECRET = (String) document.getRootElement().element("APP_SECRET").getText();
				partnerId = (String) document.getRootElement().element("partnerId").getText();
				urlip = (String) document.getRootElement().element("base-urlip").getText();
			} else {
				throw new Exception("系统加载配置文件异常，找不到serverConfig文件");
			}
		} catch (Exception e) {
			logger.debug(
					"Error occur during system config initialize.\n", e);
			e.printStackTrace();
		}
	}
	
	public static String getAPP_ID() {
		return APP_ID;
	}
	public static String getAPP_SECRET() {
		return APP_SECRET;
	}
	public static String getBaseUrl() {
		return baseUrl;
	}

	public static String getContext() {
		return context;
	}

	public static String getPartnerId() {
		return partnerId;
	}

	public static String getUrlip() {
		return urlip;
	}

	



	

}
