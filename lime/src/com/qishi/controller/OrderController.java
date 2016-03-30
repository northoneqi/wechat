package com.qishi.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qishi.entity.OrderInCome;
import com.qishi.entity.UserBasic;
import com.qishi.service.OrderCartService;
import com.qishi.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	@Qualifier("OrderService")
	private OrderService orderService;
	@Autowired
	@Qualifier("OrderCartService")
	private OrderCartService orderCartService;

	/**
	 * 订单列表
	 * 
	 * @param request
	 * @param acceptType
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/orderlist", method = RequestMethod.GET)
	public String load(HttpServletRequest request, Integer userId, String code,
			String orderCodes) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		if (userBasic.getUserID() == null) {
			String redirct_url = request.getRequestURI();
			redirct_url = redirct_url.replace("/lime/", "");
			redirct_url = URLDecoder.decode(redirct_url, "utf-8");
			request.setAttribute("redirct_url", redirct_url);
			return "/login";

		} else {
			
			List orderlist = orderService.findlist(userBasic.getUserID(), code,
					orderCodes);
			System.out.println(orderlist.toString());
			if (orderlist.size() != 0) {
				// System.out.println(orderlist);
				request.setAttribute("list", orderlist);
				return "/order/list";
			} else {
				
				return "/order/none";
			}
		}

	}

	/**
	 * 订单详情
	 * 
	 * @param request
	 * @param acceptType
	 * @return
	 */
	@RequestMapping(value = "/orderdetail", method = RequestMethod.GET)
	public String orderDetail(HttpServletRequest request, String code,
			Integer userId) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		List orderlist = orderService.findlist(userBasic.getUserID(), null,
				code);
		List orderDetail = orderService.findOrderDetail(code);
		List orderlists=	orderService.getorderCodes(code);
		Double daofu=0.00;
		for (int i = 0; i < orderlists.size(); i++) {
			Map mapMoney=(Map) orderlists.get(i);
			System.out.println(mapMoney.get("OrderCode").toString()+"////////////////////////////////");
			List money=orderService.getMoney(mapMoney.get("OrderCode").toString());
			System.out.println("循环查询orderincom的money"+money.toString());
			int number=money.size();			
						
			for (int j = 0; j < money.size(); j++) {
				Map moneys=(Map) money.get(j);
				int type=(int) moneys.get("CardPayType");				
				if(type==8){
					daofu=daofu+(Double)moneys.get("money");
					
					//request.setAttribute("daofu", daofu);
				}
				
			}
			
			request.setAttribute("number", number);
			
			
		}
		request.setAttribute("daofu", daofu);
		System.out.println(daofu+"-----------------------------");
		request.setAttribute("orderInfo", orderlist.get(0)); // 订单列表信息
		request.setAttribute("detail", orderDetail); // 订单详细
		request.setAttribute("address", orderDetail.get(0)); // 订单地址详细
		// request.setAttribute("state", state);
		return "/order/detail";
	}

	/**
	 * 取消订单 修改订单状态 PayType=8 菜卡状态 AcceptType>1 订单状态不为未付款
	 * 
	 * @param request
	 * @param OrderCode
	 * @return
	 */
	@RequestMapping(value = "/orderCancle")
	public String orderCancle(HttpServletRequest request, String OrderCode,
			int PayType, int AcceptType) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		List type = orderService.findlist(userBasic.getUserID(), null,
				OrderCode);
		Map mapType=(Map) type.get(0);
		if((int)mapType.get("AcceptType")==12){ 
			return "redirect:orderlist";
		}else{
	List orderlist=	orderService.getorderCodes(OrderCode);
	for (int i = 0; i < orderlist.size(); i++) {
		Map map = (Map) orderlist.get(i);
		orderService.updateAccept(map.get("OrderCode").toString());
		orderCartService.savetoOrderAcceptLog(map.get("OrderCode").toString(), time,"已取消","12");
	}
		
		if (PayType == 8 && AcceptType > 1) {
		
			List findReimburse = orderService.findReimburse(OrderCode);
			
			System.out.println("size====="+findReimburse.size());
			for (int i = 0; i < findReimburse.size(); i++) {
				Map map = (Map) findReimburse.get(i);
				OrderInCome orderInCome = new OrderInCome();
				orderInCome.setOrderCode((String) map.get("OrderCode"));
				orderInCome.setCardPayType(9);
				orderInCome.setMoney(0 - (Double)map.get("money"));
				orderInCome.setCardNumber((String) map.get("CardNumber"));
				orderInCome.setOutOrInType(1);
				orderInCome.setRemark("已经退款到您的卡上！");
				orderInCome.setAddTime(new Date());
				orderService.save(orderInCome);
			}
		}
		return "redirect:orderlist";
		}
		

	}
	
	/**
	 * 订单状态查询
	 * 
	 * @param request
	 * @param OrderCode
	 * @return
	 */
	@RequestMapping(value = "/orderAccept")
	@ResponseBody
	public String orderAccepte(HttpServletRequest request, String OrderCode) {
		 	List orderlists=	orderService.getorderCodes(OrderCode);
	        Map ordermap = (Map) orderlists.get(0);
	        List accepttype=orderService.getorderAccepttype(ordermap.get("OrderCode").toString());
	        Map type = (Map) accepttype.get(0);
	        System.out.println(type.get("AcceptType").toString()+"bbbbbbbbbbbbbbbbbbbbbbbssssssssssssssss");
		
		return type.get("AcceptType").toString();
		}
}