package com.qishi.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.qishi.micromark.pojo.AccessToken;
import org.qishi.micromark.util.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qishi.entity.Activity;
import com.qishi.entity.JoinIn;
import com.qishi.entity.Prize;
import com.qishi.entity.UserBasic;
import com.qishi.service.UserLotteryService;
import com.qishi.util.CookieUtils;
import com.qishi.util.ServerConfig;

@Controller
public class UserLotteryController {
	
	@Autowired
	@Qualifier("UserLotteryService")
	private UserLotteryService userlotteryservice;
	
	public static String LotteryUrl = "promotion2";
	
	@RequestMapping(value="/promotion2",method = {RequestMethod.GET})
	public String  Lotterylogin(HttpServletRequest request,HttpServletResponse response,String activityId,String source) throws UnsupportedEncodingException{
		String urlip = request.getRequestURL().toString();
		System.out.println("controler:"+urlip);
		//活动url不拦截
		if (urlip.indexOf(ServerConfig.getUrlip()) > 0) {
			urlip = urlip.replace(ServerConfig.getUrlip(), ServerConfig.getBaseUrl());
			System.out.println("controler:if:"+urlip);
			Map param = request.getParameterMap();
			urlip += "?";
			Set<String> key = param.keySet();
			for (Iterator it = key.iterator(); it.hasNext();) {
				String s = (String) it.next();
				System.out.println(param.get(s));
				urlip += s +"=" + param.get(s) + "&";
			}
//			for (Object key : param.keySet()) {
//				System.out.println("key= "+ key + " and value= " + param.get(key));
//				urlip += key +"=" + param.get(key) + "&";
//			}
			System.out.println("controler:if:return:"+urlip);
			return "redirect:" + urlip;
		}
		String fileName=request.getSession().getServletContext().getRealPath("/")+ "product.xml";
		System.out.println("----11111111111111");
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		String openid = userBasic.getWX_OPENID();
		String log= CookieUtils.findCookie("choujiang", request);
		System.out.println(log);
		List<Activity> act = userlotteryservice.findActivity();
		if (activityId == null) {
			if(act.size() == 0){
				activityId = "0";
			}else{
				activityId = act.get(0).getActivityId()+"";
			}
		}
		String url = "http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+LotteryUrl+"?source="+openid+"&activityid="+activityId;
		//判断活动是否结束
		if(act.size()==0){
			List xmllist = new ArrayList();
			List tuijian = userlotteryservice.tuijianXml(fileName);
			Map map1 = (Map) tuijian.get(0);
			xmllist.addAll((List)map1.get("list"));
			request.setAttribute("tuijian",xmllist);
			return "/LuckyPrize/hdFin";
		}
		int flag = userlotteryservice.findLuckyPrize(openid,act.get(0).getActivityId()+"");
		System.out.println(flag);
		//判断是否抽过奖
		//已经抽过奖
		if (flag == 1) {
			request.setAttribute("msg", "您已抽过");
			//获得中奖信息
			List list = userlotteryservice.findPrizeInfoByopenId(openid,"4");
			//是否为商城用户
			List checkuser = userlotteryservice.checkuser(openid);
			
			List checkcardstate=userlotteryservice.checkcardstate(openid);
			System.out.println("第一步判断"+checkcardstate.get(0).toString());
			System.out.println(checkuser+"第二次打印");
			if (checkuser.size()==0) {
				
				request.setAttribute("remind", "绑定骑士账户使用红包");
			}else{
				//request.setAttribute("remind", "绑定骑士账户使用红包");
				if((Integer)checkcardstate.get(0)==0){
					
				request.setAttribute("remind", "绑定骑士账户使用红包");
				}else if((Integer)checkcardstate.get(0)==1){
					request.setAttribute("remind2", "去骑士商城");
				}
				
			}
			request.setAttribute("url", url);
			if(list.size()!=0){
				Map map = new HashMap(); 
				map.put("cardNo", list.get(0).toString());
			request.setAttribute("plist", map);
			System.out.println(list.get(0).toString());
			}
			request.setAttribute("openid", openid);
			return "/LuckyPrize/cjFin";
		}
		if(session.getAttribute("choujiang")!=null){
////			判断活动是否结束
//			if(act.size()==0){
//				return "/LuckyPrize/hdFin";
//			}
			//未执行初始化流程
			if (flag == -1){
				session.removeAttribute("choujiang");
				session.removeAttribute("begin");
				return "redirect:" + url;
			}
			//没有点击抽奖按钮再次进入
			String click = request.getParameter("click");
			if (null == click) {
				click = request.getParameter("click");
			}
			System.out.println("clickclickclickclickclickclick:"+click);
			if (!"1".equals(click)) {
				request.setAttribute("url", url);
				request.setAttribute("openid", openid);
				session.setAttribute("choujiang", "choujiang");
				return "/LuckyPrize/choujiang";
			}
			//开始抽奖
			String prizeId = userlotteryservice.sweepstaks(openid,act.get(0).getActivityId()+"",act.get(0).getPrizeCount());
			System.out.println(act.get(0).getPrizeCount());
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+prizeId);
			if (prizeId.equals("0") ) {
				return "/LuckyPrize/hdFin";
			}
			//TODO 每天抽奖次数
			if (prizeId.equals("a")){
				List xmllist = new ArrayList();
				List tuijian = userlotteryservice.tuijianXml(fileName);
				Map map1 = (Map) tuijian.get(0);
				xmllist.addAll((List)map1.get("list"));
				request.setAttribute("tuijian",xmllist);
				return "/LuckyPrize/toDayhdFin";
			}
			//获得中奖信息
			List<Prize> list = userlotteryservice.findPrizeInfoById(Integer.valueOf(prizeId));
			//是否为商城用户
			List checkuser = userlotteryservice.checkuser(openid);
			System.out.println(checkuser+"sdfdsfsfsdfsdfsdfsdfsdfsdfsdfsdfds");
			if (checkuser.size()==0) {
				request.setAttribute("remind", "绑定骑士账户使用红包");
			}else{
				request.setAttribute("remind", "去骑士商城使用红包");
			}
			request.setAttribute("url", url);
			if(list.size()!=0){
			request.setAttribute("p", list.get(0).getPrizeType());
			request.setAttribute("plist", list.get(0));
			}
			
			List xmllist = new ArrayList();
			List tuijian = userlotteryservice.tuijianXml(fileName);
			Map map1 = (Map) tuijian.get(0);
			xmllist.addAll((List)map1.get("list"));
			request.setAttribute("tuijian",xmllist);
			request.setAttribute("openid", openid);
			session.removeAttribute("choujiang");
			session.removeAttribute("begin");
			return "/LuckyPrize/cjFin";
		} else {
			System.out.println("进入初始流程");
			String get_userlist = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
			// 第三方用户唯一凭证
			String appId = ServerConfig.getAPP_ID();
			// 第三方用户唯一凭证密钥
			String appSecret = ServerConfig.getAPP_SECRET();
			// 调用接口获取access_token
			AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
			System.out.println(at.getToken());
			String token = at.getToken();
			if (activityId == null) {
				if(act.size() == 0){
					activityId = "0";
				}else{
					activityId = act.get(0).getActivityId()+"";
				}
			}
					url ="http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+LotteryUrl+"?source=" + openid
	+ "&activityid=" + activityId;
			System.out.println("!!!!!!!!!!!!!!!!!!!" + token);
			get_userlist = get_userlist.replace("ACCESS_TOKEN", token);
			get_userlist = get_userlist.replace("OPENID", openid);
			System.out.println("@@@@@@@@@@" + get_userlist);
			JSONObject jsonObject = WeixinUtil.httpRequest(get_userlist, "GET",
					null);
			Map map = jsonObject;
		System.out.println(map.toString());
			int a = 0;
			try{
				a = (int) map.get("subscribe");
			}catch(Exception e){
				a=0;
			}
			
			//判断活动是否结束
			if(act.size()==0){
				return "/LuckyPrize/hdFin";
			}
			//int a=1;
			System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqq"+a);
			if (a==1) {
				System.out.println("验证成功进入抽奖流程");
				JoinIn joinIn = new JoinIn();
				// 查询是否为第一次登陆
				List <Map>lotterylist = userlotteryservice.finduser(openid,act.get(0).getActivityId()+"");
				if (lotterylist.size() == 0) {
					//用户第一次登陆时已关注
					System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss111111111111");
					joinIn.setActivityId(Integer.parseInt(activityId));
					joinIn.setOpenId(openid);
					joinIn.setUrl(url);
					String sourcenew = (String) session.getAttribute("source");
					if (sourcenew != null&&!sourcenew.equals(openid)) {
						joinIn.setSource(sourcenew);
					}
					joinIn.setChecklog(1);
					// 保存主表信息
					userlotteryservice.save(joinIn);
					// 保存抽奖信息表
					userlotteryservice.saveLucyPrice(act.get(0).getActivityId()+"", openid, 0);
				}else {
					//用户第一次登陆时未关注，现在更新状态为已关注
					if((int)lotterylist.get(0).get("checklog")==0){
						userlotteryservice.updateLucyPrice(openid);
					}
				}
				if (session.getAttribute("begin") != null) {
					System.out.println("开始抽奖流程");
					request.setAttribute("url", url);
					request.setAttribute("openid", openid);
					session.setAttribute("choujiang", "choujiang");
					return "/LuckyPrize/choujiang";
				}
				session.setAttribute("begin", "begin");
				System.out.println(url);
				return "redirect:" + url;
			} else {
				JoinIn joinIn = new JoinIn();
				// 查询是否为第一次登陆
				List lotterylist = userlotteryservice.finduser(openid,act.get(0).getActivityId()+"");
				if (lotterylist.size() == 0) {
					System.out
							.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss22222222");
					joinIn.setActivityId(Integer.parseInt(activityId));
					joinIn.setOpenId(openid);
					joinIn.setUrl(url);
					String sourcenew = (String) session.getAttribute("source");
					if (sourcenew != null&&!sourcenew.equals(openid)) {
						joinIn.setSource(sourcenew);
					}
					joinIn.setChecklog(0);
					// 保存主表信息
					userlotteryservice.save(joinIn);
					// 保存抽奖信息表
					userlotteryservice.saveLucyPrice(act.get(0).getActivityId()+"", openid, 0);
				}
				// 没有绑定微信号
				return "redirect:http://mp.weixin.qq.com/s?__biz=MzA3ODIwNzcyMA==&mid=200298101&idx=1&sn=e0fdbbe43fee59bbf6c8217ce6bda9be#rd";
			}
		}
	}
	
	@RequestMapping(value="/bangding", method = {RequestMethod.POST})
	public String bangding(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");	//取用户ID
		String openId=userBasic.getWX_OPENID();
		List checkuser = userlotteryservice.checkuser(openId);		//验证是否为商城用户
		
		
		if (checkuser.size()==0) {
			return "redirect:login?url=bangding";
		}
		userlotteryservice.savaPrizeCardNo(openId);					//绑定奖品
		
		//更新奖品为已使用
//		userlotteryservice.updatePrizeCardState(cardno);
		
		return "redirect:index";
	}


@RequestMapping(value="/bangding", method = {RequestMethod.GET})
public String bangding2(HttpServletRequest request) {
	HttpSession session = request.getSession();
	UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
	String openId=userBasic.getWX_OPENID();
	
	userlotteryservice.savaPrizeCardNo(openId);			//保存用户绑定的骑士卡
	
	//更新奖品为已使用
//	userlotteryservice.updatePrizeCardState(cardno);
	
	return "redirect:index";
}

@RequestMapping(value="/tuijian", method = {RequestMethod.GET})
public String tuijian(HttpServletRequest request) {
	String fileName=request.getSession().getServletContext().getRealPath("/")+ "product.xml";
	List xmllist = new ArrayList();
	List tuijian = userlotteryservice.tuijianXml(fileName);
	Map map1 = (Map) tuijian.get(1);
	xmllist.addAll((List)map1.get("list"));
	request.setAttribute("tuijian",xmllist);
	return "/LuckyPrize/index";
}

}
