package com.qishi.controller;

import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qishi.entity.ShopCardNo;
import com.qishi.entity.ShopCardOrderDetail;
import com.qishi.entity.UserBasic;
import com.qishi.service.CardService;
/**
 * 
 * @author 骑士卡
 *
 */
@Controller
public class CardController {
	
	@Autowired
	@Qualifier("CardService")
	private CardService cardService;
	
	@RequestMapping("/showlist")
	public String showlist(HttpServletRequest request ){	//显示骑士卡方法
		
		HttpSession session = request.getSession();
		UserBasic userBasic =(UserBasic) session.getAttribute("userinfo");	//取当前用户ID
		System.out.println(userBasic.getUserID()+"微信ID"); 
		
		List<ShopCardOrderDetail> a=cardService.allCardList(userBasic.getUserID(),null);	//取卡列表
		request.setAttribute("qwe", a);
		return "/card/card";
	}
	
	@RequestMapping(value="/addCard" )
	public String showlist1(HttpServletRequest request,String url,String orderCodes){	//中转
		request.setAttribute("url", url);
		request.setAttribute("orderCodes", orderCodes);
		return "/card/CardNew";
	}
	@RequestMapping("/save")
	public String saveCard(ShopCardNo cardno,HttpServletRequest request){	//保存骑士卡方法
		
		HttpSession session = request.getSession();
		UserBasic userBasic =(UserBasic) session.getAttribute("userinfo");
		int noAdd=cardService.saveCard(cardno.getCardNo(), cardno.getCardPwd(),userBasic.getUserID());
		if(noAdd==0){
			request.setAttribute("zhuangtai", "卡号或密码错误");
			return "/card/CardNew";
		}else if(noAdd==2){
			request.setAttribute("moneyNull", "卡内余额为0不能绑定");		//骑士卡状态
			return "/card/CardNew";
		}else if(noAdd==3){
			request.setAttribute("bangding", "已被绑定");
			return "/card/CardNew";
		}

		List<ShopCardOrderDetail> a= cardService.allCardList(userBasic.getUserID(),null);	//重刷骑士卡列表
		request.setAttribute("qwe", a); 
		String repay_url = request.getParameter("url");
		String repay = request.getParameter("orderCodes");
		if(repay_url!=null&&!repay_url.equals("")){
			return "redirect:"+repay_url+"?dingdanhao="+repay;	
		}
		return "/card/card";
	}
	
}
