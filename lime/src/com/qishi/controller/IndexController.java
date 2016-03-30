package com.qishi.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qishi.service.GoodsService;

@Controller
public class IndexController {
	
	@Autowired
	@Qualifier("goodsService")
	private GoodsService goodsService;
	
	@RequestMapping(value="/index" ,method = {RequestMethod.GET})
	public String index(String ColumnId,HttpServletRequest request){
		System.out.println("访问首页-----");
		//
//		System.out.println(SkuFlag+"----===");
//		List tejia =goodsService.findgoods("", "1", "");
//		Collections.shuffle(tejia);
//		
//		
//		List yingyang =goodsService.findgoods("", "", "true");
//		Collections.shuffle(yingyang);
//		System.out.println("tejia---"+tejia.toString());
		
		
		List indexbanner = goodsService.indexbanner();
		System.out.println("index---"+indexbanner.toString());
		request.setAttribute("indexbanner", indexbanner);
		
		//获取skuid  //ColumnID  77-骑士特价    79-新品上架     80-限时抢购   78-销量排行
		String xinpinskuids = goodsService.indexgoods("79");
		//获得商品
		List xinpin = goodsService.indexGetSku(xinpinskuids);
		Collections.shuffle(xinpin);
		System.out.println(xinpin.toString()+"xinping商品");
		
		//新品
		request.setAttribute("xinpin", xinpin);
		
		
		//获取skuid  //ColumnID  77-骑士特价    79-新品上架     80-限时抢购    78-销量排行
		String tejiaskuids = goodsService.indexgoods("77");
		//获得商品
		List tejia = goodsService.indexGetSku(tejiaskuids);	
		Collections.shuffle(tejia);
		System.out.println(tejia.toString());
		
		//特价
		request.setAttribute("tejia", tejia);
		
		
		
		return "/index";
	}
	
	@RequestMapping(value="/settle/instace" ,method = {RequestMethod.GET})
	public String wxpay(String SkuFlag,String indexyingyang,HttpServletRequest request){
//		//同意授权后获得code
//				String code = request.getParameter("code");
//				System.out.println("code---"+code);
//				//同意授权
//				
//				if (!"authdeny".equals(code)) {
//					//获取网页授权access_token
//					WeixinOauth2Token weixinOauth2Token = WeixinUtil.getOauth2AccessToken("wxdf56a63c63a3f762", "f8b67cb49c896422b8694605251676b6",code);
//					String accessToken = weixinOauth2Token.getAccessToken();
//					//用户标识
//					String openId = weixinOauth2Token.getOpenId();
//					System.out.println("openid---"+openId);
//				}
//				System.out.println(request.getRequestURL()+"---"+request.getRequestURI());
		

		return "/settle/success";
	}
	
	@RequestMapping(value="/indexhref" ,method = {RequestMethod.GET})
	public String indexmenuhref(String ColumnId,HttpServletRequest request){
		List menugoods;
		if("82".equals(ColumnId)){
			return "redirect:good";
		}
		if("81".equals(ColumnId)){
			System.out.println("----81");
			menugoods = goodsService.findHealthGood(); 
		}
		else{
			System.out.println("---qit");
			String xinpinskuids = goodsService.indexgoods(ColumnId);
			//获得商品
			menugoods = goodsService.indexGetSku(xinpinskuids);
			
		}
		int size=menugoods.size();
		System.out.println(size+"awwwawawawawwwwwwwwwwwwwww");
		//特价
		if(size%2!=0){
			
			List goodslist2 =  goodsService.findgoods("428","","");
			if(goodslist2.size()>0){
			menugoods.add(goodslist2.get(0));
			System.out.println("测试数据！！！！！"+goodslist2.toString());
			
			}
		}
		request.setAttribute("good", menugoods);
		request.setAttribute("size", menugoods.size()+3);
		return "/indexmenuhref";
	}
	
	@RequestMapping(value="/yingyang" ,method = {RequestMethod.GET})
	public String yingyangtaocan(String ColumnId,HttpServletRequest request){
		List menugoods;	
		menugoods = goodsService.findHealthGood(); 
		request.setAttribute("good", menugoods);
		request.setAttribute("size", menugoods.size()+3);
		return "/indexmenuhref";
	}
	@RequestMapping(value="/yijiatejia" ,method = {RequestMethod.GET})
	public String yijiatejia(HttpServletRequest request){
		String xinpinskuids = goodsService.indexgoods("77");
		//获得商品
		List menugoods = goodsService.indexGetSku(xinpinskuids);
			
		
		request.setAttribute("good", menugoods);
		request.setAttribute("size", menugoods.size()+3);
		return "/indexmenuhref";
	}
	
	
	@RequestMapping(value="/xinpinshangjia" ,method = {RequestMethod.GET})
	public String xinpinshangjia(HttpServletRequest request){
		String xinpinskuids = goodsService.indexgoods("79");
		//获得商品
		List menugoods = goodsService.indexGetSku(xinpinskuids);
		request.setAttribute("good", menugoods);
		request.setAttribute("size", menugoods.size()+3);
		return "/indexmenuhref";
	}
	@RequestMapping(value="/xianshiqianggou" ,method = {RequestMethod.GET})
	public String xianshiqianggou(HttpServletRequest request){
		String xinpinskuids = goodsService.indexgoods("80");
		//获得商品
		List menugoods = goodsService.indexGetSku(xinpinskuids);
		request.setAttribute("good", menugoods);
		request.setAttribute("size", menugoods.size()+3);
		return "/indexmenuhref";
	}
	
	@RequestMapping(value="/test/a" ,method = {RequestMethod.GET})
	public String test(String SkuFlag,String indexyingyang,HttpServletRequest request){
	
		return "/a";
		}
}
