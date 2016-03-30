package com.qishi.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qishi.entity.T_CrowdFunding_OrderBasic_two;
import com.qishi.entity.T_CrowdFunding_PayInfo_two;
import com.qishi.entity.T_CrowdFunding_ProductInfo_two;
import com.qishi.entity.UserBasic;
import com.qishi.entity.WeixinPayInfo;
import com.qishi.entity.ZC_OrderBasic;
import com.qishi.entity.ZC_PayInfo;
import com.qishi.entity.ZC_ProductInfo;
import com.qishi.entity.ZC_UserInfo;
import com.qishi.server.CrowdfundingServer;
import com.qishi.service.UserBasicService;
import com.qishi.service.ZcPayOrderService;
import com.qishi.util.CookieUtils;
import com.qishi.util.OAuthAPI;
import com.qishi.util.ServerConfig;

import org.qishi.micromark.main.Delivernotifymain;
import org.qishi.micromark.main.MenuManager;
import org.qishi.micromark.main.OrderQueryMain;
import org.qishi.micromark.pojo.AccessToken;
import org.qishi.micromark.pojo.OrderResult;
import org.qishi.micromark.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ZcPayOrderController {
	
	
	@Autowired
	@Qualifier("userBasicService")
	private UserBasicService userBasicService;
	
	@Autowired
	@Qualifier("zcPayOrderService")
	private ZcPayOrderService zcPayOrderService;
	
	@Autowired
	private CrowdfundingServer crowdfundingServer;

	/**
	 * 众筹支付页面
	 * @param request
	 * @param zccode 众筹单号
	 * @return
	 * @throws Exception 
	 */
//	@RequestMapping("settle/toZcPay")
//	public String toZcPay(HttpServletRequest request,String zccode,HttpServletResponse response) throws Exception{
//		zccode = "1";
//		//存一天
//		CookieUtils.addCookie("zccode", zccode, 1000*60*60*24, response);
//		String states = this.InitUserBase(response, request);
//		if (!"ready".equals(states)) {
//			return states;
//		}
//		double money =  zcPayOrderService.checkProInfo(zccode);
//		
//		request.setAttribute("zccode", zccode);
//		request.setAttribute("money", money);
//		
//		return "/settle/zcOrder";
//	}
//	
	@RequestMapping("settle/updIsPayMent")
	@ResponseBody
	public int updIsPayMent(HttpServletRequest request, String zccode,String openId,String state){
		System.out.println(zccode);
		System.out.println(openId);
		String flag = zcPayOrderService.lockOrderBasicState(zccode,openId,state);
		System.out.println(flag);
		return Integer.parseInt(flag);
	}
	
	//获取openId
	public String InitUserBase(HttpServletResponse response,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		if (userBasic == null) {
			//o6Lh5t7dGSFiAvHFTDzRZvhl1fuQ
			String openid ="";
			try {
				openid = OAuthAPI.OAuthIfNesscary(request, response);
				if(openid.contains("https")){
					if(openid.contains("https")){
						 return "redirect:"+openid;
					 }
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "错误页面（待定）";
			}
			// openid未取到时
			if ("".equals(openid)) {
				return "错误页面（待定）";
			}
			userBasic = new UserBasic();
			userBasic.setWX_OPENID(openid);
			userBasic = userBasicService.findUser(userBasic);
			if (userBasic == null) {
				userBasic = new UserBasic();
				userBasic.setWX_OPENID(openid);
			}
			session.setAttribute("userinfo", userBasic);
		}
		return "ready";
	}
	
	
	@RequestMapping(value="/settle/zcSuccess" )
	@ResponseBody
	public String noticeUrl(String SkuFlag,String indexyingyang,HttpServletRequest request) throws Exception{
		Logger log = LoggerFactory.getLogger(MenuManager.class);
		
		String zccode = request.getParameter("zccode");
		String name=request.getParameter("name");
		System.out.println(name);
		name = java.net.URLDecoder.decode(name, "UTF-8");
		System.out.println(zccode+"---"+name);
		String openidpay = request.getParameter("openidpay");
		log.info("name==="+name+"openidpay="+openidpay);
		
		System.out.println(request.getRequestURL());
		Map map=request.getParameterMap(); 
        Set key = map.keySet(); 
        Map<String,String> noticeinfo =new HashMap<String, String>();     
        for(Object aaa: key.toArray()){ 
         String parakey = aaa.toString(); 
         String paravalue = ((String[])map.get(aaa))[0]; 
       
         noticeinfo.put(parakey, paravalue);
         log.info(parakey+"===="+paravalue);
        } 
        HttpSession session = request.getSession();
		UserBasic user =(UserBasic) session.getAttribute("userinfo");
		System.out.println("trade_state-----"+noticeinfo.get("trade_state"));
		System.out.println("out_trade_no-----"+noticeinfo.get("out_trade_no"));
		if (noticeinfo.get("trade_state").equals("0")) {
			
			//先保存订单信息
//			savedingdaninfo(openidpay,noticeinfo,riqi,shijian);
			// 第三方用户唯一凭证
			String appId =ServerConfig.getAPP_ID();
			// 第三方用户唯一凭证密钥
			String appSecret =ServerConfig.getAPP_SECRET();

			// 调用接口获取access_token
			AccessToken at = WeixinUtil.getAccessToken(appId,appSecret);
			System.out.println(at.getToken());
			if (null != at) {
				// 调用接口通知发货
				T_CrowdFunding_PayInfo_two payInfo = new T_CrowdFunding_PayInfo_two();
				
				payInfo.setCROWDFUNDING_CODE(zccode);
				int pir1 =Integer.parseInt(noticeinfo.get("total_fee"));
				System.out.println(Integer.parseInt(noticeinfo.get("total_fee"))+"----"+pir1);
				double pir = pir1/100.0; 
				System.out.println(pir);
				payInfo.setPAY_MONEY(pir);
				payInfo.setPAY_NAME(name);
				payInfo.setPAY_OPEN_ID(openidpay);
				payInfo.setADD_TIME(new Date().getTime());
				payInfo.setOUT_TRADE_NO(noticeinfo.get("out_trade_no"));
				payInfo.setTRANSPORT_FEE(Long.parseLong(noticeinfo.get("transport_fee")));
				payInfo.setTRADE_STATE(Long.parseLong(noticeinfo.get("trade_state")));
				payInfo.setTRADE_MODE(Long.parseLong(noticeinfo.get("trade_mode")));
				payInfo.setPARTNER(noticeinfo.get("partner"));
				payInfo.setBANK_TYPE(noticeinfo.get("bank_type"));
				payInfo.setBANK_BILLNO(noticeinfo.get("bank_billno"));
				payInfo.setTOTAL_FEE(Long.parseLong(noticeinfo.get("total_fee")));
				payInfo.setFEE_TYPE(Long.parseLong(noticeinfo.get("fee_type")));
				payInfo.setNOTIFY_ID(noticeinfo.get("notify_id"));
				payInfo.setTRANSACTION_ID(noticeinfo.get("transaction_id"));
				
				//string 转 date
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = sdf.parse(noticeinfo.get("time_end"));
				
				
				payInfo.setTIME_END(date.getTime());
				System.out.println(date+"-----!!!!");
				payInfo.setPRODUCT_FEE(Long.parseLong(noticeinfo.get("product_fee")));
				payInfo.setDISCOUNT(Long.parseLong(noticeinfo.get("discount")));
				List list=zcPayOrderService.findOutTradeNo(noticeinfo.get("out_trade_no"));
				if(list.size()==0){
					zcPayOrderService.savaPayInfo(payInfo);
				}
				
				int state = zcPayOrderService.checkOrderBasic(zccode);
				//1 众筹已完成
				if(state == 1){
					//更新众筹订单的状态，生成微信订单
					zcPayOrderService.upOrderBasicSt(zccode);
					List<T_CrowdFunding_ProductInfo_two> pro = zcPayOrderService.findProInfo(zccode);
					T_CrowdFunding_ProductInfo_two zc_pro = pro.get(0);
					List<T_CrowdFunding_OrderBasic_two> order = zcPayOrderService.findOrderBasic(zccode);
					T_CrowdFunding_OrderBasic_two zc_order = order.get(0);
					zcPayOrderService.upOrderBasicSt(zccode);
					String ordercode=crowdfundingServer.toOrder(zc_pro.getSKU()+"", zc_order.getOPEN_ID(), zc_pro.getPRICE()+"", zc_order.getBUY_NUMBER()+"","12", zccode);
					System.out.println(ordercode+"-------");
				} else if(state == -1){
					//打开标记
					zcPayOrderService.updateIsPayMent("1", zccode,0l);
				}
			}
			
		}
		return "success";
	}
	
	
	@RequestMapping("settle/fail")
	public String fail(HttpServletRequest request){
		HttpSession session = request.getSession();
		ZC_UserInfo uInfo = (ZC_UserInfo) session.getAttribute("uInfo");
		String zccode = uInfo.getZccode();
		zcPayOrderService.updateIsPayMent("1", zccode,0l);
		return "/settle/zcError";
	}
	
	@RequestMapping("toPage")
	public String toPage(HttpServletRequest request){
		return "/settle/zcFinish";
	}
	
}
