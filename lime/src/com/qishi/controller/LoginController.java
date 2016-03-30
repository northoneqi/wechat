package com.qishi.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.qishi.micromark.pojo.WeixinOauth2Token;
import org.qishi.micromark.util.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qishi.entity.UserBasic;
import com.qishi.service.OrderCartService;
import com.qishi.service.UserBasicService;
import com.qishi.util.EncryptUtil;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("userBasicService")
	private UserBasicService userBasicService;

	@Autowired
	@Qualifier("OrderCartService")
	private OrderCartService orderCartService;

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String loginpost(HttpServletRequest request, Model model)
			throws Exception {
		UserBasic userBasic = (UserBasic) request.getSession().getAttribute(
				"userinfo");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String Username = request.getParameter("Username");
		String UserPwd = request.getParameter("UserPwd");
		String redirct_url = request.getParameter("redirct_url");
		String lottery_url = request.getParameter("lotteryUrl");
		String ManyUrl = request.getParameter("ManyUrl");
		String cardno = request.getParameter("cardNo");
		String openid = request.getParameter("openId");
		System.out.println("?????????????????????????????????????????????????"+cardno);
		System.out.println("3333333333333333333333333333333"+openid);
		redirct_url = URLDecoder.decode(redirct_url, "utf-8");
		System.out.println(redirct_url + ";;;;;");
		if (UserPwd == "") {
			request.setAttribute("errmsg", "密码不能为空！");
			request.setAttribute("redirct_url", redirct_url);
			request.setAttribute("lotteryUrl", lottery_url);
			request.setAttribute("cardNo", cardno);
			request.setAttribute("openId", openid);
			return "/login";
		} else {
			UserPwd = EncryptUtil.getHashString(UserPwd,
					EncryptUtil.HashType_MD5).toUpperCase();
			//查询未绑定用户
			List<UserBasic> users = userBasicService
					.getUserByUsername(Username);
			System.out.println(users.toString());
			System.out.println(request.getRequestURI() + "=="
					+ request.getRequestURL());
			if (users.size() == 1) {
				UserBasic user = users.get(0);
				// System.out.println(user.getUserID()+"==="+user.getUserPwd());
				if (user.getUserPwd().equals(UserPwd)) {
					System.out.println("userBasic.getWX_OPENID()========"
							+ userBasic.getWX_OPENID());
					user.setWX_OPENID(userBasic.getWX_OPENID());
					userBasicService.saveOrUpdate(user);
					session.setAttribute("userinfo", user);
					userBasic = (UserBasic) session.getAttribute("userinfo");
					orderCartService.updateorder(userBasic.getUserID()
							.toString(), userBasic.getWX_OPENID());
					String path =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
					if(lottery_url!=null&&!lottery_url.equals("")){
						return "redirect:"+path+"/LuckPrize/" + lottery_url;
					}else if(ManyUrl!=null&&!ManyUrl.equals(""))
					{
						return "redirect:"+path+"/ManyPlan/" + ManyUrl;
					}
					else{
					return "redirect:" + redirct_url; 
					}
					
				} else {
					request.setAttribute("errmsg", "密码错误！");
					request.setAttribute("redirct_url", redirct_url);
					request.setAttribute("lotteryUrl", lottery_url);
					request.setAttribute("cardNo", cardno);
					request.setAttribute("openId", openid);
					return "/login";
				}

			} else if (users.size() == 0) {
				request.setAttribute("redirct_url", redirct_url);
				request.setAttribute("lotteryUrl", lottery_url);
				request.setAttribute("cardNo", cardno);
				request.setAttribute("openId", openid);
				request.setAttribute("errmsg", "用户名不存在！或已绑定其他微信账号");
				return "/login";
			} else {
				request.setAttribute("redirct_url", redirct_url);
				request.setAttribute("lotteryUrl", lottery_url);
				request.setAttribute("cardNo", cardno);
				request.setAttribute("openId", openid);
				request.setAttribute("errmsg", "用户名或密码错误！");
				return "/login";
			}

		}

	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String loginget(HttpServletRequest request, Model model,String url,String cardno,String openid) {
		System.out.println("++++++++++++++++++++++++"+url);
		System.out.println("++++++++++++++++++++++++"+cardno);
		System.out.println("++++++++++++++++++++++++"+openid);
		HttpSession session = request.getSession();
		String urlluck = (String) session.getAttribute("urlluck");
		String urlMany = (String) session.getAttribute("urlMany");
		System.out.println("*************************urlluck*******************"+urlluck);
		if(urlluck!=null&&!urlluck.equals("")){
			request.setAttribute("ManyUrl", urlMany);
			request.setAttribute("lotteryUrl", urlluck);
			request.setAttribute("cardNo", cardno);
			request.setAttribute("openId", openid);
		}
		
		return "/login";

	}

	@RequestMapping(value = "/wxlogin", method = { RequestMethod.GET })
	public String wxlogin(HttpServletRequest request,
			HttpServletResponse response, Model model,String url,String cardno,String openid) throws IOException {
		String redirct_url = request.getParameter("redirct_url");
		String lottery_url = request.getParameter("lotteryUrl");
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		userBasicService.save(userBasic);
		UserBasic user = userBasicService.findUser(userBasic);
		session.setAttribute("userinfo", user);
		userBasic = (UserBasic) session.getAttribute("userinfo");
		orderCartService.updateorder(userBasic.getUserID().toString(),
				userBasic.getWX_OPENID());
		if(redirct_url!=null){
		redirct_url = URLDecoder.decode(redirct_url, "utf-8");
		}
		url = (String) session.getAttribute("urlluck");
		String urlMany = (String) session.getAttribute("urlMany");
		String path =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!1123");
		System.out.println("*************************urlluck*******************"+url);
		if(url!=null&&!"".equals(url)){
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+path+"/LuckPrize/"+url);
			return "redirect:"+path+"/LuckPrize/"+url;
		 
		}
		if (redirct_url == null||redirct_url.equals("")) {
			redirct_url = "memberCenter";
		}
		if(urlMany!=null&&!"".equals(urlMany)){
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+path+"/ManyPlan/"+urlMany);
			return "redirect:"+path+"/ManyPlan/"+urlMany;
		 
		}
		return "redirect:" + redirct_url;

	}

	@RequestMapping(value = "/uoo", method = { RequestMethod.GET })
	public String bangding(HttpServletRequest request) {
		userBasicService.updateOpenid("dsadwqadwq", "78323");
		return null;

	}
}
