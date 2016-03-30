package org.qishi.micromark.main;

import java.util.Date;

import org.qishi.micromark.pojo.AccessToken;
import org.qishi.micromark.pojo.Delivernotify;
import org.qishi.micromark.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qishi.util.EncryptUtil;

public class Delivernotifymain {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	public static void main(String[] args) throws Exception {
		// 第三方用户唯一凭证
				String appId ="wxdf56a63c63a3f762";
				// 第三方用户唯一凭证密钥
				String appSecret ="f8b67cb49c896422b8694605251676b6";
				String openid ="o6Lh5t7dGSFiAvHFTDzRZvhl1fuQ";
				String transid="1218461501201407023211603685";
				String out_trade_no="3012911226616088600";
				// 调用接口获取access_token
				AccessToken at = WeixinUtil.getAccessToken(appId,appSecret);
				System.out.println(at.getToken());
				if (null != at) {
					// 调用接口通知发货
					int result = WeixinUtil.delivernotify(getDeliver(openid, transid, out_trade_no),at.getToken());
					//int result = WeixinUtil.deleteMenu(at.getToken());
					// 判断菜单创建结果
					if (0 == result)
						log.info("发货成功！");
					else
						log.info("发货失败，错误码：" + result);
				}
				
	}
	
	public static Delivernotify getDeliver(String  openid,String transid,String out_trade_no) throws Exception{
		String  appid="wxdf56a63c63a3f762";
		String appkey ="Md6BO5atVwP2jl5aDAm9PCYiM7c2OhcFxkmrt5XYbQ4xgcLZQaHKRI7ITnhNHwvjWUNszOuiJASzmGBKVlAP0JA60VVV22h1t948wCsifVpGE7ZFu0xbVIR0Im8MVrSZ";
		
		String deliver_timestamp =String.valueOf(new Date().getTime()).substring(0, 10);
		String deliver_status="1";
		String deliver_msg ="ok";
		
		//String app_signaturestr="appid"+appid+"&appkey="+appkey+"&openid="+openid+"&transid="+transid+"&out_trade_no="+out_trade_no+"&deliver_timestamp="+deliver_timestamp+"&deliver_status="+deliver_status+"&deliver_msg="+deliver_msg;
		String app_signaturestr="appid="+appid+"&appkey="+appkey+"&deliver_msg="+deliver_msg+"&deliver_status="+deliver_status+"&deliver_timestamp="+deliver_timestamp+"&openid="+openid+"&out_trade_no="+out_trade_no+"&transid="+transid;
	
		String app_signature =EncryptUtil.getHashString( app_signaturestr, EncryptUtil.HashType_SHA1 );
//		System.out.println(app_signaturestr);
//		System.out.println(app_signature);
//		System.out.println(deliver_timestamp.substring(0, 10));
	
		Delivernotify deli = new Delivernotify(); 
		deli.setAppid(appid);
		deli.setOpenid(openid);
		deli.setTransid(transid);
		deli.setOut_trade_no(out_trade_no);
		deli.setDeliver_timestamp(deliver_timestamp);
		deli.setDeliver_status(deliver_status);
		deli.setDeliver_msg(deliver_msg);
		deli.setApp_signature(app_signature);
		deli.setSign_method("sha1");
		return deli;
		
	}
	
	

}
