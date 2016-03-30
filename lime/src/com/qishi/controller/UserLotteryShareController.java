package com.qishi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qishi.entity.UserBasic;
import com.qishi.service.UserLotteryService;

@Controller
public class UserLotteryShareController {
	@Autowired
	@Qualifier("UserLotteryService")
	private UserLotteryService userlotteryservice;

	@RequestMapping(value = "/uesrUrl", method = { RequestMethod.GET })
	public String SelectUrl(HttpServletRequest request) {				

		// 返回用户的URL
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		String openId = userBasic.getWX_OPENID();
		String userUrl = userlotteryservice.selectUrl(openId);			//查询抽奖用户URL
		List isprice = userlotteryservice.checkisprice(openId);			//查询是否抽奖

		if (isprice.size() == 0) {
			return "redirect:"+UserLotteryController.LotteryUrl;		
		}
		request.setAttribute("url", userUrl);
		System.out.println("!!!!!!!!!!!!!!qijin!!!!!!!!!!!!!!!!!!" + userUrl);
		List yijiaka = userlotteryservice.findyijika(openId);			//查询用户未绑定的骑士卡

		int urlNum = userlotteryservice.urlShore(openId);				//用户生成的链接被分享了几次
		int prizeNum = urlNum / 10;										//用户分享的URL次数/10
		int prizeNot = 10 - (urlNum % 10);								//用户分享的URL次数还剩几次够十条
		request.setAttribute("openid", openId);
		request.setAttribute("urlNumber", urlNum);
		request.setAttribute("prizeNumber", prizeNum);
		request.setAttribute("noPrizeNum", prizeNot);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!" + yijiaka.size());
		if (yijiaka.size() != 0) {
			System.out.println("?????????????????????" + yijiaka.size());
			request.setAttribute("yijiaka", yijiaka.get(0));
			request.setAttribute("yijiakasize", yijiaka.size());
		} else {
			request.setAttribute("yijiakasize", 0);
		}

		System.out.println("!!!!!!!!!!!!!!分享了的次数!!!!!!!!!!!!!!!!!!!" + urlNum);

		return "/LuckyPrize/cjFinout";
	}

}
