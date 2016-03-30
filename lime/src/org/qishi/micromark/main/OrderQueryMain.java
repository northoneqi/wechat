package org.qishi.micromark.main;

import java.util.Date;

import org.qishi.micromark.pojo.AccessToken;
import org.qishi.micromark.pojo.OrderQuery;
import org.qishi.micromark.pojo.OrderResult;
import org.qishi.micromark.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qishi.util.EncryptUtil;

public class OrderQueryMain {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	public static void main(String[] args) throws Exception {
		// 第三方用户唯一凭证
		String appId ="wxdf56a63c63a3f762";
		// 第三方用户唯一凭证密钥
		String appSecret ="f8b67cb49c896422b8694605251676b6";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId,appSecret);
		System.out.println(at.getToken());
		 String out_trade_no="3012911226616088600";
		if (null != at) {
			// 调用接口通知发货
			OrderResult result = WeixinUtil.orderquery(getorder(out_trade_no), at.getToken());
			//int result = WeixinUtil.deleteMenu(at.getToken());
			// 判断菜单创建结果
			System.out.println(result.getOrder_info().getBank_billno());
			if (0 == result.getErrcode())
				log.info("订单查询成功！");
			else
				log.info("订单查询失败，错误码：" + result);
		}
	}
	
	public static OrderQuery getorder(String out_trade_no) throws Exception{
		 String appid ="wxdf56a63c63a3f762";
		
		 String timestamp=String.valueOf(new Date().getTime()).substring(0, 10);
		 String appkey ="Md6BO5atVwP2jl5aDAm9PCYiM7c2OhcFxkmrt5XYbQ4xgcLZQaHKRI7ITnhNHwvjWUNszOuiJASzmGBKVlAP0JA60VVV22h1t948wCsifVpGE7ZFu0xbVIR0Im8MVrSZ";

		 String sign ="out_trade_no="+out_trade_no+"&partner=1218461501&key=2c86b292a95f610d914e51b608075c07";
		 sign = EncryptUtil.getHashString( sign, EncryptUtil.HashType_MD5 );
		 String package_bak="out_trade_no="+out_trade_no+"&partner=1218461501&sign="+sign;
		 String app_signaturestr ="appid="+appid+"&appkey="+appkey+"&package="+package_bak+"&timestamp="+timestamp;
		 String app_signature =EncryptUtil.getHashString( app_signaturestr, EncryptUtil.HashType_SHA1 );
		 
		OrderQuery order = new OrderQuery();
		order.setAppid(appid);
		order.setPackage_bak(package_bak);
		order.setTimestamp(timestamp);
		order.setApp_signature(app_signature);
		order.setSign_method("sha1");
		return order;
	}

}
