package com.qishi.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qishi.entity.UserBasic;
import com.qishi.service.MemberService;

@Controller
public class MemberCenterController {
	@Autowired
	@Qualifier("MemberService")
	private MemberService memberservice;

	/**
	 * 会员个人详细信息
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/memberCenter")
	public String load(HttpServletRequest request) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		if (userBasic.getUserID()==null ) {
			String redirct_url = request.getRequestURI();
			redirct_url = redirct_url.replace("/lime/", "");
			redirct_url = URLDecoder.decode(redirct_url, "utf-8");
			request.setAttribute("redirct_url", redirct_url);
			return "/login";
		
		}else{
		List memberInfo = memberservice.memberInfo(userBasic.getUserID());
		//System.out.println(memberInfo.toString());
		request.setAttribute("info", memberInfo);
		return "/member/member";
		}
	}
}
