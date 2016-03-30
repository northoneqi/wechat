package com.qishi.controller;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qishi.service.GoodsService;
import com.qishi.service.SearchService;
import com.qishi.util.CookieUtils;

/**
 * 搜索页
 * 
 * @author 刘宁
 * 
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchservice;
	
	@Autowired
	private GoodsService goodsService;

	/**
	 * 跳转至搜索页面
	 * 
	 * @param m
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/toSearchPage")
	public String toSearchPage(ModelMap m,HttpServletRequest request) throws Exception {
		System.out.println("00");
		List list = CookieUtils.findCookieAll("goodName", request);
		request.setAttribute("list", list);
		return "/search/search";
	}

	/**
	 * 返回搜索结果
	 * 
	 * @param goodname
	 * @param m
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/searchGood")
	public String searchGood(String searchName, ModelMap m,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//解码
		String name = java.net.URLDecoder.decode(searchName, "UTF-8");
		String good = null;
		//查找Cookie
		if (CookieUtils.findCookieByValue(searchName, request)==null) {
			good = "goodName" + System.currentTimeMillis();
			CookieUtils.addCookie(good, name, 60 * 60 * 24 * 2, response);
		}
		List goodslist = searchservice.search(name);
		
		if(goodslist.size()==0){
			m.put("searchName", searchName);
			return  ("redirect:nullSearch");
		}
		else{
			int size = goodslist.size();
			if (true) {

			}
			if (size % 2 != 0) {
				request.setAttribute("addsize", 1);
				System.out.println("商品长度加一");
			} else {
				request.setAttribute("addsize", 0);
				System.out.println("商品长度加零");
			}
			request.setAttribute("good", goodslist);
			request.setAttribute("size", size + 3);
			return "/good/list";
		}
		
	}
	
	/**
	 * 当搜索结果为空时到此方法
	 * @param map
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/nullSearch")
	public String nullSearch(ModelMap map,HttpServletRequest request) throws Exception{
		//获取skuid  //ColumnID  77-骑士特价    79-新品上架     80-限时抢购   78-销量排行
				String xinpinskuids = goodsService.indexgoods("79");
				//获得商品
				List xinpin = goodsService.indexGetSku(xinpinskuids);
				Collections.shuffle(xinpin);
				System.out.println(xinpin.toString());
				//新品
				request.setAttribute("xinpin", xinpin);
				
				
				//获取skuid  //ColumnID  77-骑士特价    79-新品上架     80-限时抢购    78-销量排行
				String tejiaskuids = goodsService.indexgoods("77");
				//获得商品
				List tejia = goodsService.indexGetSku(tejiaskuids);	
				Collections.shuffle(tejia);
				System.out.println(tejia.toString());
				//解码
				String name = java.net.URLDecoder.decode((String)request.getParameter("searchName"), "UTF-8");
				System.out.println(name);
				request.setAttribute("search",name);
				request.setAttribute("tejia", tejia);
		return "/search/index";
	}
}
