package org.qishi.micromark.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.qishi.micromark.pojo.AccessToken;
import org.qishi.micromark.pojo.Delivernotify;
import org.qishi.micromark.pojo.Menu;
import org.qishi.micromark.pojo.OrderQuery;
import org.qishi.micromark.pojo.OrderResult;
import org.qishi.micromark.pojo.WeixinOauth2Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公众平台通用接口工具类
 * 
 * @author dhzhanghailong
 *
 *@date 2014年6月12日 上午11:48:35 
 */
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		System.setProperty("jsse.enableSNIExtension", "false");
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
	
	
	
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 获取access_token
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		
		AccessToken accessToken = null;
		System.out.println(appid+"-------------------"+appsecret);
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	
	
	
	
	
	
	// 菜单创建（POST） 限100（次/天）

	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;
    System.out.println("accessToken----"+accessToken);
		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println("menu-----"+jsonMenu);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	
	
	// 菜单创建（POST） 限100（次/天）

	public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int deleteMenu(String accessToken) {
		int result = 0;
		System.out.println("accessToken----"+accessToken);
		// 拼装创建菜单的url
		String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		
		
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "GET", null);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	//获得授权accesstoken
		public static WeixinOauth2Token getOauth2AccessToken(String appId,String appSecret,String code){
			WeixinOauth2Token wat = null;
			String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
			requestUrl = requestUrl.replace("APPID", appId);
			requestUrl = requestUrl.replace("SECRET", appSecret);
			requestUrl = requestUrl.replace("CODE", code);
			JSONObject jsObject =httpRequest(requestUrl, "GET", null);
			if (null!=jsObject) {
				try{
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsObject.getString("access_token"));
				wat.setExpiresIn(jsObject.getInt("expires_in"));
				wat.setRefreshToken(jsObject.getString("refresh_token"));
				wat.setOpenId(jsObject.getString("openid"));
				wat.setScope(jsObject.getString("scope"));
				}catch (Exception e) {
					wat = null;
					int errorCode = jsObject.getInt("errcode");
					String errorMsg = jsObject.getString("errmsg");
					log.error("获得网页授权凭证失败errcode:{} errmsg:{}",errorCode,errorMsg);
				}
				
			}
			return wat;
			
		}
		
		public static String delivernotify_url = "https://api.weixin.qq.com/pay/delivernotify?access_token=ACCESS_TOKEN";
		//发货通知
		public static int delivernotify(Delivernotify delivernotify, String accessToken) {
			int result = 0;
	    System.out.println("accessToken----"+accessToken);
			// 拼装创建菜单的url
			String url = delivernotify_url.replace("ACCESS_TOKEN", accessToken);
			System.out.println(url);
			// 将对象转换成json字符串
			String jsonMenu = JSONObject.fromObject(delivernotify).toString();
			System.out.println("menu-----"+jsonMenu);
			// 调用接口创建菜单
			JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					result = jsonObject.getInt("errcode");
					log.error("发货失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
				}
			}

			return result;
		}
	
	
		public static String orderquery_url = "https://api.weixin.qq.com/pay/orderquery?access_token=ACCESS_TOKEN";
		//发货通知
		public static OrderResult orderquery(OrderQuery orderQuery, String accessToken) {
			int result = 0;
			OrderResult orderResult =null;
	    System.out.println("accessToken----"+accessToken);
			// 拼装创建菜单的url
			String url = orderquery_url.replace("ACCESS_TOKEN", accessToken);
			System.out.println(url);
			// 将对象转换成json字符串
			String jsonMenu = JSONObject.fromObject(orderQuery).toString();
			jsonMenu = jsonMenu.replace("package_bak", "package");
			System.out.println("orderquer-----"+jsonMenu);
			// 调用接口创建菜单
			JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
			System.out.println("jsonObject=="+jsonObject);
			//JSONObject orderinfo =  jsonObject.getJSONObject("order_info");
			//System.out.println(orderinfo.get("transaction_id"));
			
			
			if (null != jsonObject) {
//				JSONObject orderjson =  jsonObject.getJSONObject("order_info");
				
				 orderResult = (OrderResult) JSONObject.toBean(jsonObject, OrderResult.class);
				if (0 != jsonObject.getInt("errcode")) {
					result = jsonObject.getInt("errcode");
					log.error("订单查询失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
				}
			}

			return orderResult;
		}
}