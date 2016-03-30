package com.qishi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qishi.entity.UserBasic;
import com.qishi.service.CardService;
import com.qishi.service.GoodsService;
import com.qishi.service.OrderCartService;
import com.qishi.service.OrderInComeService;
import com.qishi.service.OrderService;
import com.qishi.service.SearchService;
import com.qishi.service.UserBasicService;
import com.qishi.util.BigorderRule;
import com.qishi.util.OrderRule;

@Controller
public class GrabBoxController {

	@Autowired
	@Qualifier("OrderCartService")
	private OrderCartService orderCartService;

	@Autowired
	@Qualifier("CardService")
	private CardService cardService;

	@Autowired
	private SearchService searchservice;

	@Autowired
	@Qualifier("userBasicService")
	private UserBasicService userBasicService;

	@Autowired
	private GoodsService goodsservice;
	
	@Autowired
	@Qualifier("OrderInComeService")
	private OrderInComeService orderInComeService;
	
	@Autowired
	@Qualifier("OrderService")
	private OrderService orderService;
	/*
	 * 抽奖盒子生成订单和配送信息
	 * 
	 **/
	@RequestMapping(value = "/toBoxOrder", method = RequestMethod.GET)
	public String toOrder(String SKU, String openId,String activityId,HttpServletRequest request) {
		System.out.println("获取盒子发送的"+SKU+openId+"·······················"+"----"+activityId);
		//openId="oLrN6uCCGGcKMVY9C490lv0FfUcs";
		//SKU="78";  //
		//activityId="6";
		try{
			// 查询userid
			List chou=orderService.isChou(openId, SKU);
			if(chou!=null && chou.size()>0) {
				String path =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
				return "redirect:"+path+"/LuckPrize/success?msg=1&openid="+openId;
			}
			UserBasic userBasic = new UserBasic();
			userBasic.setWX_OPENID(openId);
			userBasic = userBasicService.findUser(userBasic);
			String UserId = String.valueOf(userBasic.getUserID());
			OrderRule rule = new OrderRule();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfwx = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			String timewx = sdfwx.format(date);
			//List<String> orderClasslist = new ArrayList();
			//StringBuffer zhifu = new StringBuffer();
			BigorderRule bigrule = new BigorderRule();
			// 生成微信订单号
			String big = bigrule.rule();
			//生成盒子的唯一值
			String WeixinOrderId="WX"+openId+"@"+activityId;
			// 生成订单号
			String orderCode = rule.GetOrderCode(5);
			//orderClasslist.add(orderCode);
			// 查找保存订单需要的全部信息
			List toorderlist = goodsservice.findgoodInfo(SKU);
			for (int i = 0; i < toorderlist.size(); i++) {
				Map ordermap = (Map) toorderlist.get(i);
				String SKUName = String.valueOf(ordermap.get("ProName"));
				String ProNum = "1";
				int num = 1;
				//String SellPrice = String.valueOf(ordermap.get("SellPrice"));
				String SellPrice = "0";
				Double price = 0.00;
				String OrderClass = String.valueOf(ordermap.get("OrderSplit"));
				SKU = String.valueOf(ordermap.get("SKU"));
				String Unit = String.valueOf(ordermap.get("UnitMent"));
				String sub = String.valueOf(num * price);
				
				// 绑定微信订单信息
				orderCartService.saveweixinorderbox(WeixinOrderId, big, orderCode, timewx);
				
				// 订单总表
				orderCartService.savetoOrderBasic(UserId, openId, orderCode, time,
						OrderClass);
				// 订单详细
				orderCartService.savetoOrderDetail(orderCode, UserId, openId, SKU,
						ProNum, Unit, SellPrice, SKUName, sub);
	
				// }
				
				// 订单受理日志信息
				orderCartService.savetoOrderAcceptLog(orderCode, time, "此订单是小确幸盒子",
						"1");
				// 绑定受理表信息
				orderCartService.savetoOrderAccept(orderCode, time);
				
	
			}
			
			//绑定订单配送信息
			List orderAddress = orderService.findOrderAddress(openId,activityId);
			for (int i = 0; i < orderAddress.size(); i++) {
				Map map = (Map) orderAddress.get(i);
				String OConsignee=map.get("Name").toString();
				String Address=map.get("Address").toString();
				String Area=map.get("Area").toString();
				String OMbile=map.get("Telephone").toString();
				String riqi=map.get("PeisongTime").toString();
				String shijian=map.get("PeisongMoment").toString();
				String OTelephone="";
			orderInComeService.savepeisong(OConsignee,Address,Area,OMbile,riqi,shijian,orderCode,userBasic.getUserID(),OTelephone);
			orderInComeService.updateOrderinfo(orderCode);
			orderCartService.savetoOrderAcceptLog(orderCode, time,"已支付","2");
			orderService.updatePayType(2, "'"+orderCode+"'");
			}
			
		}catch(Exception ex){
			String path =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
			return "redirect:"+path+"/LuckPrize/success?msg=1&openid="+openId;
		}
		String path =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		return "redirect:"+path+"/LuckPrize/success?msg=1&openid="+openId;
		
	}
}
