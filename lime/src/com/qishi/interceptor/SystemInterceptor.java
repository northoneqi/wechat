package com.qishi.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qishi.entity.UserBasic;
import com.qishi.service.OrderCartService;
import com.qishi.service.UserBasicService;
import com.qishi.util.OAuthAPI;
import com.qishi.util.ServerConfig;

/**
 * 
 * @author dhsubinglun
 * @date 2014-06-23
 */
@Repository
public class SystemInterceptor extends HandlerInterceptorAdapter {
	
	

	@Autowired
	@Qualifier("userBasicService")
	private UserBasicService userBasicService;
	
	@Autowired
	@Qualifier("OrderCartService")
	private OrderCartService orderCartService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		String url = request.getRequestURL().toString();
		System.out.println(url);
		//活动url不拦截
		if (url.indexOf(ServerConfig.getUrlip()) > 0||url.indexOf("/toBoxOrder") > 0 ) {
			System.out.println("inner:"+url);
			return super.preHandle(request, response, handler);
		}
		
		//支付url
		if (url.indexOf("/settle") > 0 ||
				url.indexOf("/images") > 0 ||
				url.indexOf("/js") > 0) {
			System.out.println("sucess:"+url);
			return super.preHandle(request, response, handler);
		}
		
		
		String sku = request.getParameter("sku");
		System.out.println("***********************sku***************"+sku);
		if(sku!=null&&!sku.equals("")){
			session.setAttribute("sku2", sku);
			}
		
		String ActivityNo = request.getParameter("activityNo");
		System.out.println("***********************ActivityNo***************"+ActivityNo);
		if(sku!=null&&!sku.equals("")){
			session.setAttribute("activityNo", ActivityNo);
			}
		
		String urlluck = request.getParameter("urlluck");
		System.out.println("***********************urlluck***************"+urlluck);
		if(urlluck!=null&&!urlluck.equals("")){
			session.setAttribute("urlluck", urlluck);
		}
		String urlMany = request.getParameter("urlMany");
		System.out.println("***********************urlMany***************"+urlMany);
		if(urlMany!=null&&!urlMany.equals("")){
			session.setAttribute("urlMany", urlMany);
		}
		String source = request.getParameter("source");
		if(source!=null&&!source.equals("")){
		session.setAttribute("source", source);
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$:"+source);
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		if (userBasic == null) {
//			String openid = OAuthAPI.OAuthIfNesscary(request, response);
			String openid="o6Lh5t2oopCe9HxcnB9pWyBvAz6Y";
			// openid未取到时
			if ("".equals(openid)) {
				return false;
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
		String shopnum = null;
		List shopcartlist = new ArrayList();
		shopcartlist = orderCartService.findcartgoodinfo(String.valueOf(userBasic.getUserID()), userBasic.getWX_OPENID());
		session.setAttribute("cartnum", shopcartlist.size());

		return super.preHandle(request, response, handler);
	}
}
