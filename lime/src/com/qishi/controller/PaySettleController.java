package com.qishi.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Request;

import org.qishi.micromark.main.Delivernotifymain;
import org.qishi.micromark.main.MenuManager;
import org.qishi.micromark.main.OrderQueryMain;
import org.qishi.micromark.pojo.AccessToken;
import org.qishi.micromark.pojo.OrderResult;
import org.qishi.micromark.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qishi.entity.BankTransaction;
import com.qishi.entity.OrderInCome;
import com.qishi.entity.ShopCardOrderDetail;
import com.qishi.entity.T_CrowdFunding_BackInfo_two;
import com.qishi.entity.T_CrowdFunding_OrderBasic_two;
import com.qishi.entity.T_CrowdFunding_ProductInfo_two;
import com.qishi.entity.User;
import com.qishi.entity.UserBasic;
import com.qishi.entity.WeixinPayInfo;
import com.qishi.entity.ZC_OrderBasic;
import com.qishi.entity.ZC_ProductInfo;
import com.qishi.entity.ZC_UserInfo;
import com.qishi.service.CardService;
import com.qishi.service.OrderAddressService;
import com.qishi.service.OrderCartService;
import com.qishi.service.OrderCommonAddressService;
import com.qishi.service.OrderInComeService;
import com.qishi.service.OrderService;
import com.qishi.service.UserActivityService;
import com.qishi.service.UserBasicService;
import com.qishi.service.WeixinPayInfoService;
import com.qishi.service.ZcPayOrderService;
import com.qishi.util.CookieUtils;
import com.qishi.util.OAuthAPI_PAY;
import com.qishi.util.ServerConfig;
import com.qishi.util.StringUtil;

@Controller
public class PaySettleController {
	@Autowired
	@Qualifier("OrderService")
	private OrderService orderService;

	@Autowired
	@Qualifier("OrderAddressService")
	private OrderAddressService addressService;

	@Autowired
	@Qualifier("OrderCommonAddressService")
	private OrderCommonAddressService orderCommonAddressService;

	@Autowired
	@Qualifier("OrderInComeService")
	private OrderInComeService orderInComeService;

	@Autowired
	@Qualifier("userBasicService")
	private UserBasicService userBasicService;

	@Autowired
	@Qualifier("CardService")
	private CardService cardService;

	@Autowired
	@Qualifier("OrderCartService")
	private OrderCartService orderCartService;

	@Autowired
	@Qualifier("UserActivityService")
	private UserActivityService userActivityService;

	@Autowired
	@Qualifier("WeixinPayInfoService")
	private WeixinPayInfoService weixinPayInfoService;

	@Autowired
	@Qualifier("zcPayOrderService")
	private ZcPayOrderService zcPayOrderService;

	@RequestMapping(value = "/settle/settlement", method = { RequestMethod.GET })
	public String loginget(HttpServletRequest request, Model model,
			String dingdanhao, String oAddressId, String split, String hongbao)
			throws UnsupportedEncodingException {
		String type1 = request.getParameter("type");
		System.out.println(type1 + "==================type1");

		HttpSession session = request.getSession();

		String type = (String) session.getAttribute("type");
		System.out.println(type + "-------------------------type");

		if (StringUtil.isEmpty(type1)) {
			session.removeAttribute("type");
		}
		if (StringUtil.isEmpty(type)) {

			UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
			// 绑定的骑士卡总信息
			List<ShopCardOrderDetail> carlist = cardService.allListinfo(Integer
					.valueOf(userBasic.getUserID()));

			String zongjine = "0";
			for (int k = 0; k < carlist.size(); k++) {
				Map map = (Map) carlist.get(k);
				if ("sum".equals(map.get("CardNo"))) {
					zongjine = String.valueOf(map.get("Balance"));
					break;
				}

			}

			// 获取骑士卡总金额
			double Balancedou = 0;
			if (StringUtil.isNotEmpty(zongjine)) {
				Balancedou = Double.parseDouble(zongjine);
			}

			// 获取子订单
			List weixinordercodes = orderService.getorderCodes(dingdanhao);
			if (userBasic.getUserID() == null) {
				String redirct_url = request.getRequestURI();
				redirct_url = redirct_url.replace("/lime/", "");
				redirct_url = URLDecoder.decode(redirct_url, "utf-8");
				request.setAttribute("redirct_url", redirct_url);
				return "/login";

			} else {
				String sumPay = orderService.payOrderSum(dingdanhao);
				int sumPays = (int) (Double.parseDouble(sumPay) * 100);
				request.setAttribute("sumPays", sumPays);
				request.setAttribute("sumPay", sumPay);
				// 返回骑士卡卡号
				List cartlist = orderService.findallCart(userBasic.getUserID()
						.toString());
				System.out
						.println("Qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqllllllllllllll"
								+ cartlist);
				request.setAttribute("carlist", cartlist);
				List orderaddresslist;
				if (oAddressId != null && !"".equals(oAddressId)) {
					orderaddresslist = orderCommonAddressService
							.findSetAddress(oAddressId);
				} else {
					orderaddresslist = addressService.getDefAddess(userBasic
							.getUserID().toString(), "1");
				}
				System.out.println(orderaddresslist.toString());
				Map map = null;
				if (orderaddresslist.size() != 0) {
					map = (Map) orderaddresslist.get(0);
				}
				request.setAttribute("WeixinOrderCode", dingdanhao);
				System.out.println("dingdanhao---" + dingdanhao);
				if (weixinordercodes.size() == 1) {
					Map weixinmap = (Map) weixinordercodes.get(0);
					dingdanhao = weixinmap.get("OrderCode").toString();
				}
				System.out.println(userBasic.getUserID() + "用户ID");
				List hongList = cardService.allHongCardList(userBasic
						.getUserID());
				System.out.println("1212121212mmmmmmmmmmmmmmmmm"
						+ (hongList.size()) + hongList.toString());
				// String honglistjosn =
				// JSONObject.fromObject(hongList).toString();
				request.setAttribute("hongList", hongList);
				request.setAttribute("hongSize", hongList.size());
				request.setAttribute("orderaddrs", map);
				request.setAttribute("orderCodes", dingdanhao);
				request.setAttribute("ordercodelength", weixinordercodes.size());
				request.setAttribute("split", split);
				// 红包支付标记
				request.setAttribute("hongbao", hongbao);
				request.setAttribute("Balancedou", Balancedou);
				return "/settle/settlement";

			}

		} else {
			return "/settle/wxpay";
		}

	}

	// 回调支付页面
	@RequestMapping(value = "/add_adress", method = { RequestMethod.GET })
	public String loginget(HttpServletRequest request, String orderCodes) {
		System.out.println("进入方法++++++++++++++++++++++++++++++++++++");
		return "redirect:to_Add_Page?url=settle/settlement&orderCodes="
				+ orderCodes;

	}

	@RequestMapping(value = "/show_adress", method = { RequestMethod.GET })
	public String loginget1(HttpServletRequest request, String orderCodes) {
		System.out.println("进入方法++++++++++++++++++++++++++++++++++++");
		return "redirect:show_address?url=settle/settlement&orderCodes="
				+ orderCodes;

	}

	@RequestMapping(value = "/paysubmit", method = { RequestMethod.POST })
	public String paysucess(HttpServletRequest request) throws Exception {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		String CarPayType = request.getParameter("CarPayType");
		String WeixinOrderCode = request.getParameter("WeixinOrderCode");
		System.out.println("奇葩的都比qqqqq");
		// String CardNo=request.getParameter("CardNo");
		String paymoney = request.getParameter("paymoney");
		String shijian = request.getParameter("shijian");
		String riqi = request.getParameter("riqi");
		String OConsignee = request.getParameter("OConsignee");
		String OMbile = request.getParameter("OMbile");
		String Province = request.getParameter("Province");
		String City = request.getParameter("City");
		String Area = request.getParameter("Area");
		String Address = request.getParameter("Address");
		String OTelephone = request.getParameter("OTelephone");
		String hongCard = request.getParameter("hongId");
		System.out.println(hongCard
				+ "22222222222222222222222222222222222yyyssssss");
		String hongMoney = request.getParameter("hongbaomoneyId");
		System.out
				.println(hongMoney + "22222222222222222222222222222222222yyy");
		String out_trade_code = request.getParameter("out_trade_code");
		request.setAttribute("wxCode", WeixinOrderCode);
		List orderlists = orderService.getorderCodes(WeixinOrderCode);
		Map ordermap = (Map) orderlists.get(0);
		List accepttype = orderService.getorderAccepttype(ordermap.get(
				"OrderCode").toString());
		Map type = (Map) accepttype.get(0);
		System.out.println(type.get("AcceptType").toString()
				+ "bbbbbbbbbbbbbbbbbbbbbbbssssssssssssssss");
		if (type.get("AcceptType").toString().equals("1")
				& !type.get("AcceptType").toString().equals("12")) {
			// 获取子订单
			List weixinodercodes = orderService.getorderCodes(WeixinOrderCode);

			double paymoneydou = 0;
			if (StringUtil.isNotEmpty(paymoney)) {
				paymoneydou = Double.parseDouble(paymoney);
			}
			HttpSession session = request.getSession();
			UserBasic user = (UserBasic) session.getAttribute("userinfo");
			System.out.println(CarPayType);
			// weixin微信支付 huodao货到付款 caika菜卡支付
			if (CarPayType.equals("weixin")) {
				System.out.println("weixinapy-------------------------");
				return "/search/shopping_error";
			} else if (CarPayType.equals("huodao")) {
				System.out
						.println("WeixinOrderCode++++++++++++++++++++++++++++++"
								+ WeixinOrderCode);
				for (int i = 0; i < weixinodercodes.size(); i++) {
					Map weixinordercode = (Map) weixinodercodes.get(i);
					orderService.updatePayType(2,
							weixinordercode.get("OrderCode").toString());
				}
			} else if (CarPayType.equals("caika")) {
				// 判断用户是否绑定骑士卡
				if (checkRedirect(user)) {
					return "redirect:addCard?url=settle/settlement&orderCodes="
							+ WeixinOrderCode;
				}

				String result = cardPay(WeixinOrderCode, user, paymoneydou);
				// 绑定收货信息&&跟新订单状态
				for (int i = 0; i < weixinodercodes.size(); i++) {
					Map orderCodemap = (Map) weixinodercodes.get(i);
					String orderCode = orderCodemap.get("OrderCode").toString();
					orderInComeService.savepeisong(OConsignee, Address, Area,
							OMbile, riqi, shijian, orderCode, user.getUserID(),
							OTelephone);
					orderInComeService.updateOrderinfo(orderCode);
					orderCartService.savetoOrderAcceptLog(orderCode, time,
							"已支付", "2");
				}
				return result;
			} else if (CarPayType.equals("hongbaoka")) {

				Double hongmoney = Double.parseDouble(hongMoney);

				// 查询订单信息
				List orderlist = orderService.findlistnew(user.getUserID(),
						WeixinOrderCode);
				// 如果订单金额<红包卡金额
				if (paymoneydou < hongmoney) {
					for (int i = 0; i < orderlist.size(); i++) {
						Map orderentity = (Map) orderlist.get(i);
						// 骑士卡足额循环订单使用骑士卡支付
						String orderjine = orderentity.get("total").toString();
						// 每个订单的总金额
						double subOrderjine = 0;
						if (StringUtil.isNotEmpty(orderjine)) {
							subOrderjine = Double.parseDouble(orderjine);
						}
						// 循环订单，单个订单中扣款
						OrderInCome orderInCome = new OrderInCome();
						orderInCome.setOrderCode(orderentity.get("OrderCode")
								.toString());
						orderInCome.setCardPayType(4);
						orderInCome.setMoney(subOrderjine);
						orderInCome.setCardNumber(hongCard);
						orderInCome.setOutOrInType(2);
						orderInCome.setRemark(orderentity.get("OrderCode")
								.toString()
								+ "从骑士卡"
								+ hongCard
								+ "中支出"
								+ subOrderjine + "元");
						orderInCome.setAddTime(new Date());
						orderInComeService.save(orderInCome);

						orderService.updatePayType(8,
								"'" + orderentity.get("OrderCode").toString()
										+ "'");
					}
				} else {
					Double bucha = 0.0;
					for (int i = 0; i < orderlist.size(); i++) {
						Map orderentity = (Map) orderlist.get(i);
						// 骑士卡足额循环订单使用骑士卡支付
						String orderjine = orderentity.get("total").toString();
						// 每个订单的总金额
						double subOrderjine = 0;
						if (StringUtil.isNotEmpty(orderjine)) {
							subOrderjine = Double.parseDouble(orderjine);
						}
						if (subOrderjine > hongmoney) {

							OrderInCome orderInCome = new OrderInCome();
							orderInCome.setOrderCode(orderentity.get(
									"OrderCode").toString());
							// 8 为补差类型 4 扣款类型
							orderInCome.setCardPayType(4);
							// 当前订单金额大于当前卡金额
							// 扣除卡金额
							orderInCome.setMoney(hongmoney);
							orderInCome.setCardNumber(hongCard);
							orderInCome.setOutOrInType(2);
							orderInCome.setRemark("菜卡支付但金额不足，当前卡可用余额："
									+ hongmoney + " ,订单金额：" + subOrderjine + ""
									+ "结算后卡剩余金额为0，部分或全部余额货到付款");
							orderInCome.setAddTime(new Date());
							if (hongmoney != 0.00) {
								orderInComeService.save(orderInCome);
							}
							subOrderjine = (subOrderjine - hongmoney);
							hongmoney = 0.00;

							if (subOrderjine != 0) {
								DecimalFormat df = new DecimalFormat("#.00");
								bucha = subOrderjine;
								bucha = Double.valueOf(df.format(bucha));
								OrderInCome orderInComebucha = new OrderInCome();
								orderInComebucha.setOrderCode(orderentity.get(
										"OrderCode").toString());
								// 8 为补差类型 4 扣款类型
								orderInComebucha.setCardPayType(8);
								orderInComebucha.setMoney(bucha);
								orderInComebucha.setCardNumber(null);
								orderInComebucha.setOutOrInType(2);
								orderInComebucha
										.setRemark("菜卡支付错误，卡金额不足，客户需要到付"
												+ bucha + "元");
								orderInComebucha.setAddTime(new Date());
								orderInComeService.save(orderInComebucha);

							}
							orderService.updatePayType(8, "'"
									+ orderentity.get("OrderCode").toString()
									+ "'");
						} else {
							// 循环订单，单个订单中扣款
							OrderInCome orderInCome = new OrderInCome();
							orderInCome.setOrderCode(orderentity.get(
									"OrderCode").toString());
							orderInCome.setCardPayType(4);
							orderInCome.setMoney(subOrderjine);
							orderInCome.setCardNumber(hongCard);
							orderInCome.setOutOrInType(2);
							orderInCome.setRemark(orderentity.get("OrderCode")
									.toString()
									+ "从骑士卡"
									+ hongCard
									+ "中支出"
									+ subOrderjine + "元");
							orderInCome.setAddTime(new Date());
							orderInComeService.save(orderInCome);

							orderService.updatePayType(8, "'"
									+ orderentity.get("OrderCode").toString()
									+ "'");
						}
					}
				}
			} else {
				return "/search/shopping_error";
			}
			// 绑定收货信息&&跟新订单状态
			for (int i = 0; i < weixinodercodes.size(); i++) {
				Map orderCodemap = (Map) weixinodercodes.get(i);
				String orderCode = orderCodemap.get("OrderCode").toString();
				orderInComeService.savepeisong(OConsignee, Address, Area,
						OMbile, riqi, shijian, orderCode, user.getUserID(),
						OTelephone);
				orderInComeService.updateOrderinfo(orderCode);
				orderCartService.savetoOrderAcceptLog(orderCode, time, "已支付",
						"2");
			}

			return "/search/shopping_success";
		} else {
			return "redirect:index";
		}
	}

	// 获取可以使用的骑士卡总信息（每个卡的卡号、金额与可以使用的卡的总金额）
	private Map getCardList(UserBasic user) {
		// 绑定的骑士卡总信息
		List<ShopCardOrderDetail> carlist = cardService.allListinfo(user
				.getUserID());
		String zongjine = "0";
		double Balancedou = 0;
		for (int k = 0; k < carlist.size(); k++) {
			Map map = (Map) carlist.get(k);
			if ("sum".equals(map.get("CardNo"))) {
				zongjine = String.valueOf(map.get("Balance"));
				if (StringUtil.isNotEmpty(zongjine)) {
					Balancedou = Double.parseDouble(zongjine);
				}
				carlist.remove(k);
				break;
			}

		}
		Map map = new HashMap();
		map.put("Balancedou", Balancedou);
		map.put("carlist", carlist);
		return map;
	}

	// 查询用户是否绑定骑士卡
	private boolean checkRedirect(UserBasic user) {
		// 绑定骑士卡总信息（包含已冻结的卡）
		List<ShopCardOrderDetail> carlistcheck = cardService
				.allListinfoall(user.getUserID());
		if (carlistcheck.size() == 1) {
			return true;
		}
		return false;
	}

	// 骑士卡足额使用骑士卡支付
	private void cardPayzue(Map orderentity, UserBasic user) {
		// 获取可以使用的骑士卡总信息（每个卡的卡号、金额与可以使用的卡的总金额）
		Map cardlistmap = getCardList(user);
		String orderjine = orderentity.get("total").toString();
		double orderjinedou = 0;
		if (StringUtil.isNotEmpty(orderjine)) {
			// 单个订单的总金额
			orderjinedou = Double.parseDouble(orderjine);
		}
		// 获取可以使用的骑士卡总信息
		List<ShopCardOrderDetail> carlist = (List<ShopCardOrderDetail>) cardlistmap
				.get("carlist");

		for (int j = 0; j < carlist.size(); j++) {
			Map maplist = (Map) carlist.get(j);
			// 循环获取单个骑士卡号
			String kahao = (String) maplist.get("CardNo");
			// 循环获取单个骑士卡余额
			String jine = String.valueOf(maplist.get("Balance"));

			double kaJine = 0.0;
			if (StringUtil.isNotEmpty(jine)) {
				// 单个骑士卡余额
				kaJine = Double.parseDouble(jine);
			}
			// 余额为零跳过
			if (kaJine == 0)
				continue;
			// 此张卡不够支付订单
			if (orderjinedou > kaJine) {
				OrderInCome orderInCome = new OrderInCome();
				orderInCome.setOrderCode(orderentity.get("OrderCode")
						.toString());
				orderInCome.setCardPayType(4);
				orderInCome.setMoney(kaJine);
				orderInCome.setCardNumber(kahao);
				orderInCome.setOutOrInType(2);
				orderInCome.setRemark(orderentity.get("OrderCode").toString()
						+ "从骑士卡" + kahao + "中支出" + kaJine + "元");
				orderInCome.setAddTime(new Date());
				orderInComeService.save(orderInCome);
				orderService.updatePayType(8, "'"
						+ orderentity.get("OrderCode").toString() + "'");
				orderjinedou = (orderjinedou - kaJine);
				// 清空卡列表中的余额
				maplist.put("Balance", "0");
				System.out
						.println("kahaokahaokahaokahaokahaokahaokahaokahaokahao"
								+ kahao);
			} else {
				OrderInCome orderInCome = new OrderInCome();
				orderInCome.setOrderCode(orderentity.get("OrderCode")
						.toString());
				orderInCome.setCardPayType(4);
				orderInCome.setMoney(orderjinedou);
				orderInCome.setCardNumber(kahao);
				orderInCome.setOutOrInType(2);
				orderInCome.setRemark(orderentity.get("OrderCode").toString()
						+ "从骑士卡" + kahao + "中支出" + orderjinedou + "元");
				orderInCome.setAddTime(new Date());
				orderInComeService.save(orderInCome);
				orderService.updatePayType(8, "'"
						+ orderentity.get("OrderCode").toString() + "'");
				// 更新卡列表中的余额
				maplist.put("Balance", Math.abs(orderjinedou - kaJine));
				break;
			}

		}
	}

	// 骑士卡不足额使用组合支付
	private void cardPayNotZue(Map orderentity, UserBasic user,
			double Balancedou) {
		// 获取可以使用的骑士卡总信息（每个卡的卡号、金额与可以使用的卡的总金额）
		Map cardlistmap = getCardList(user);
		// 获取每个订单的总金额
		String orderjine = orderentity.get("total").toString();
		// 每个订单的总金额
		double subOrderjine = 0;
		if (StringUtil.isNotEmpty(orderjine)) {
			subOrderjine = Double.parseDouble(orderjine);
		}
		// 获取可以使用的卡列表
		List<ShopCardOrderDetail> carlist = (List<ShopCardOrderDetail>) cardlistmap
				.get("carlist");
		Double bucha = 0.0;
		for (int j = 0; j < carlist.size(); j++) {
			// 获取每一个卡的金额和卡号
			Map maplist = (Map) carlist.get(j);
			// 当总金额不足时每一个卡的支付过程
			String a = onecardpay(maplist, orderentity, subOrderjine);

			if (a.equals("true")) {
				continue;
			}
			if (a.equals("false")) {
				subOrderjine = 0;
				break;
			}
			// 每循环一次跟新单个总金额订单
			subOrderjine = Double.valueOf(a);
		}
		if (subOrderjine != 0) {
			DecimalFormat df = new DecimalFormat("#.00");
			bucha = subOrderjine;
			bucha = Double.valueOf(df.format(bucha));
			OrderInCome orderInCome = new OrderInCome();
			orderInCome.setOrderCode(orderentity.get("OrderCode").toString());
			// 8 为补差类型 4 扣款类型
			orderInCome.setCardPayType(8);
			orderInCome.setMoney(bucha);
			orderInCome.setCardNumber(null);
			orderInCome.setOutOrInType(2);
			orderInCome.setRemark("菜卡支付错误，卡金额不足，客户需要到付" + bucha + "元");
			orderInCome.setAddTime(new Date());
			orderInComeService.save(orderInCome);
			Balancedou = 0;
		}
		orderService.updatePayType(8, "'"
				+ orderentity.get("OrderCode").toString() + "'");
	}

	// 总金额不足时每一个卡的支付过程
	private String onecardpay(Map maplist, Map orderentity, double subOrderjine) {
		// 每一个卡的金额与卡号
		String kahao = (String) maplist.get("CardNo");
		String jine = String.valueOf(maplist.get("Balance"));

		double kaJine = 0.0;
		if (StringUtil.isNotEmpty(jine)) {
			// 当前卡的金额
			kaJine = Double.parseDouble(jine);
		}
		if (kaJine == 0)
			return "true";
		OrderInCome orderInCome = new OrderInCome();
		orderInCome.setOrderCode(orderentity.get("OrderCode").toString());
		// 8 为补差类型 4 扣款类型
		orderInCome.setCardPayType(4);
		// 当前订单金额大于当前卡金额
		if (subOrderjine >= kaJine) {
			// 扣除卡金额
			orderInCome.setMoney(kaJine);
			orderInCome.setCardNumber(kahao);
			orderInCome.setOutOrInType(2);
			orderInCome.setRemark("菜卡支付但金额不足，当前卡可用余额：" + kaJine + " ,订单金额："
					+ subOrderjine + "" + "结算后卡剩余金额为0，部分或全部余额货到付款");
			orderInCome.setAddTime(new Date());
			subOrderjine = (subOrderjine - kaJine);
			// 清空卡列表中的余额
			maplist.put("Balance", "0");
		} else {
			orderInCome.setMoney(subOrderjine);
			orderInCome.setCardNumber(kahao);
			orderInCome.setOutOrInType(2);
			orderInCome.setRemark("菜卡支付成功，当前卡可用余额：" + kaJine + " ,订单金额："
					+ subOrderjine);
			orderInCome.setAddTime(new Date());
			// 更新卡列表中的余额
			maplist.put("Balance", kaJine - subOrderjine);
			orderInComeService.save(orderInCome);
			orderService.updatePayType(8, "'"
					+ orderentity.get("OrderCode").toString() + "'");
			return "false";
		}
		orderInComeService.save(orderInCome);
		orderService.updatePayType(8, "'"
				+ orderentity.get("OrderCode").toString() + "'");
		return String.valueOf(subOrderjine);
	}

	// 骑士卡支付方法
	private String cardPay(String WeixinOrderCode, UserBasic user,
			Double paymoneydou) {

		// 获取可以使用的骑士卡总信息（每个卡的卡号、金额与可以使用的卡的总金额）
		Map cardlistmap = getCardList(user);
		// 查询用户订单列表（获取每个小订单的总金额）
		List orderlist = orderService.findlistnew(user.getUserID(),
				WeixinOrderCode);
		Double Balancedou = (Double) cardlistmap.get("Balancedou");
		if (paymoneydou <= Balancedou) {
			for (int i = 0; i < orderlist.size(); i++) {
				Map orderentity = (Map) orderlist.get(i);
				// 骑士卡足额循环订单使用骑士卡支付
				cardPayzue(orderentity, user);
			}

		} else {
			for (int i = 0; i < orderlist.size(); i++) {
				Map orderentity = (Map) orderlist.get(i);
				// 骑士卡不足额循环订单使用骑士卡于货到付款组合支付
				cardPayNotZue(orderentity, user, Balancedou);
			}
		}

		return "/search/shopping_success";

	}

	// 微信支付方式
	@RequestMapping(value = "/weixinpaysubmit", method = { RequestMethod.GET })
	public String weixinpay(HttpServletRequest request) throws Exception {

		return "/index";

	}

	@RequestMapping(value = "/settle/success")
	@ResponseBody
	public String noticeUrl(String SkuFlag, String indexyingyang,
			HttpServletRequest request) throws Exception {
		Logger log = LoggerFactory.getLogger(MenuManager.class);
		String shijian = request.getParameter("shijian");
		String riqi = request.getParameter("riqi");
		String openidpay = request.getParameter("openidpay");
		log.info("shijian===" + shijian + "riqi=" + riqi + "openidpay="
				+ openidpay);

		System.out.println(request.getRequestURL());
		Map map = request.getParameterMap();
		Set key = map.keySet();
		Map<String, String> noticeinfo = new HashMap<String, String>();
		for (Object aaa : key.toArray()) {
			String parakey = aaa.toString();
			String paravalue = ((String[]) map.get(aaa))[0];

			noticeinfo.put(parakey, paravalue);
			log.info(parakey + "====" + paravalue);
		}
		HttpSession session = request.getSession();
		UserBasic user = (UserBasic) session.getAttribute("userinfo");
		System.out.println("trade_state-----" + noticeinfo.get("trade_state"));
		System.out
				.println("out_trade_no-----" + noticeinfo.get("out_trade_no"));
		if (noticeinfo.get("trade_state").equals("0")) {

			// 先保存订单信息
			savedingdaninfo(openidpay, noticeinfo, riqi, shijian);
			// 第三方用户唯一凭证
			String appId = ServerConfig.getAPP_ID();
			// 第三方用户唯一凭证密钥
			String appSecret = ServerConfig.getAPP_SECRET();

			// 调用接口获取access_token
			AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
			System.out.println(at.getToken());
			if (null != at) {
				// 调用接口通知发货
				OrderResult result = WeixinUtil
						.orderquery(OrderQueryMain.getorder(noticeinfo
								.get("out_trade_no")), at.getToken());
				System.out.println(result.getOrder_info().getOut_trade_no());
				if (0 == result.getErrcode()) {
					log.info("订单查询成功！");
					// String openid =user.getWX_OPENID();
					// String openid ="o6Lh5t7dGSFiAvHFTDzRZvhl1fuQ";
					String transid = result.getOrder_info().getTransaction_id();
					String out_trade_no = result.getOrder_info()
							.getOut_trade_no();
					int deliverresult = WeixinUtil.delivernotify(
							Delivernotifymain.getDeliver(openidpay, transid,
									out_trade_no), at.getToken());
					if (deliverresult == 0) {
						log.info("发货成功！");
						// 修改订单状态
						WeixinPayInfo weixinPayInfo = new WeixinPayInfo();
						weixinPayInfo.setWeixinpayId(UUID.randomUUID()
								.toString());
						weixinPayInfo.setTransportFee(Integer
								.parseInt(noticeinfo.get("transport_fee")));
						weixinPayInfo.setTradeState(Integer.parseInt(noticeinfo
								.get("trade_state")));
						weixinPayInfo.setTradeMode(Integer.parseInt(noticeinfo
								.get("trade_mode")));
						weixinPayInfo.setPartner(noticeinfo.get("partner"));
						weixinPayInfo.setBankType(noticeinfo.get("bank_type"));
						weixinPayInfo.setBankBillno(noticeinfo
								.get("bank_billno"));
						weixinPayInfo.setTotalFee(Integer.parseInt(noticeinfo
								.get("total_fee")));
						weixinPayInfo.setFeeType(Integer.parseInt(noticeinfo
								.get("fee_type")));
						weixinPayInfo.setNotifyId(noticeinfo.get("notify_id"));
						weixinPayInfo.setTransactionId(noticeinfo
								.get("transaction_id"));
						weixinPayInfo.setOutTradeNo(noticeinfo
								.get("out_trade_no"));
						weixinPayInfo.setTimeEnd(noticeinfo.get("time_end"));
						weixinPayInfo.setProductFee(Integer.parseInt(noticeinfo
								.get("product_fee")));
						weixinPayInfo.setDiscount(Integer.parseInt(noticeinfo
								.get("discount")));
						List list = weixinPayInfoService
								.findOutTradeNo(noticeinfo.get("out_trade_no"));
						if (list.size() == 0) {
							weixinPayInfoService.save(weixinPayInfo);
						}
					} else {
						log.info("发货失败！");
					}
				} else {
					log.info("订单查询失败，错误码：" + result);
					// 调用接口通知发货
					OrderResult result1 = WeixinUtil.orderquery(OrderQueryMain
							.getorder(noticeinfo.get("out_trade_no")), at
							.getToken());
					String openid = user.getWX_OPENID();
					String transid = result1.getOrder_info()
							.getTransaction_id();
					String out_trade_no = result1.getOrder_info()
							.getOut_trade_no();
					int deliverresult = WeixinUtil.delivernotify(
							Delivernotifymain.getDeliver(openid, transid,
									out_trade_no), at.getToken());
					if (deliverresult == 0) {
						log.info("发货成功！");

						WeixinPayInfo weixinPayInfo = new WeixinPayInfo();
						weixinPayInfo.setWeixinpayId(UUID.randomUUID()
								.toString());
						weixinPayInfo.setTransportFee(Integer
								.parseInt(noticeinfo.get("transport_fee")));
						weixinPayInfo.setTradeState(Integer.parseInt(noticeinfo
								.get("trade_state")));
						weixinPayInfo.setTradeMode(Integer.parseInt(noticeinfo
								.get("trade_mode")));
						weixinPayInfo.setPartner(noticeinfo.get("partner"));
						weixinPayInfo.setBankType(noticeinfo.get("bank_type"));
						weixinPayInfo.setBankBillno(noticeinfo
								.get("bank_billno"));
						weixinPayInfo.setTotalFee(Integer.parseInt(noticeinfo
								.get("total_fee")));
						weixinPayInfo.setFeeType(Integer.parseInt(noticeinfo
								.get("fee_type")));
						weixinPayInfo.setNotifyId(noticeinfo.get("notify_id"));
						weixinPayInfo.setTransactionId(noticeinfo
								.get("transaction_id"));
						weixinPayInfo.setOutTradeNo(noticeinfo
								.get("out_trade_no"));
						weixinPayInfo.setTimeEnd(noticeinfo.get("time_end"));
						weixinPayInfo.setProductFee(Integer.parseInt(noticeinfo
								.get("product_fee")));
						weixinPayInfo.setDiscount(Integer.parseInt(noticeinfo
								.get("discount")));
						List list = weixinPayInfoService
								.findOutTradeNo(noticeinfo.get("out_trade_no"));
						if (list.size() == 0) {
							weixinPayInfoService.save(weixinPayInfo);
						}

					} else {
						log.info("发货失败！");
					}
				}
			}

		}
		return "success";
	}

	/**
	 * 保存订单信息
	 * 
	 * @param openidpay
	 * @param noticeinfo
	 * @param riqi
	 * @param shijian
	 */

	public void savedingdaninfo(String openidpay,
			Map<String, String> noticeinfo, String riqi, String shijian) {
		// 根据openid查询user
		UserBasic userBasicopenid = new UserBasic();
		userBasicopenid.setWX_OPENID(openidpay);
		UserBasic userbasic_open = userBasicService.findUser(userBasicopenid);

		// 默认收货地址
		List orderaddresslist = addressService.getDefAddess(userbasic_open
				.getUserID().toString(), "1");
		Map deforderaddressmap = null;
		for (int i = 0; i < orderaddresslist.size(); i++) {
			deforderaddressmap = (Map) orderaddresslist.get(0);

		}
		String OConsignee = deforderaddressmap.get("OConsignee").toString();
		String Address = deforderaddressmap.get("Address").toString();
		String Area = deforderaddressmap.get("Area").toString();
		String OMbile = deforderaddressmap.get("OMbile").toString();
		String OTelephone = deforderaddressmap.get("OTelephone").toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		// 获取子订单
		List list = orderService.getorderCodes(noticeinfo.get("out_trade_no"));
		// 修改订单状态&&绑定收货信息&&保存订单信息
		for (int i = 0; i < list.size(); i++) {
			// orderbasic
			Map orderentity = (Map) list.get(i);
			orderService.updatePayType(12, "'"
					+ orderentity.get("OrderCode").toString() + "'");

			Map orderCodemap = (Map) list.get(i);
			String orderCode = orderCodemap.get("OrderCode").toString();

			List li = orderService.findlistnewWei(null, orderCode);
			// orderincome
			// 判断是否存在订单
			if (li.size() > 0) {
				Map mincheck = (Map) li.get(0);
				// 判断订单是否保存过
				if (checkorderincome((String) mincheck.get("OrderCode"))) {

					for (int j = 0; j < li.size(); j++) {
						OrderInCome od = new OrderInCome();
						Map min = (Map) li.get(j);
						od.setOrderCode((String) min.get("OrderCode"));
						od.setMoney((Double) min.get("total"));
						od.setCardPayType(12);
						od.setOutOrInType(2);
						od.setRemark("微信支付");
						od.setAddTime(new Date());
						orderInComeService.save(od);

					}

					// orderaddress
					orderInComeService.savepeisong(OConsignee, Address, Area,
							OMbile, riqi, shijian, orderCode,
							userbasic_open.getUserID(), OTelephone);
					// orderaccept
					orderInComeService.updateOrderinfo(orderCode);
					// orderacceptlog
					orderCartService.savetoOrderAcceptLog(orderCode, time,
							"已支付", "2");
				}
			}
		}

	}

	/**
	 * 验证是否在orderincome存在记录
	 * 
	 * @param ordercode
	 * @return
	 */
	private boolean checkorderincome(String ordercode) {
		// TODO Auto-generated method stub
		List list = orderInComeService.findincome(ordercode);
		if (list.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	@RequestMapping(value = "/settle/wxpay", method = { RequestMethod.GET })
	public String wxpaytest(String SkuFlag, String indexyingyang,
			HttpServletRequest request) throws Exception {
		return "/settle/wxpay";

	}

	// 会员招募活动支付成功后的界面
	@RequestMapping(value = "/settle/wxpayrediet")
	@ResponseBody
	public String testwxpay(HttpServletRequest request) throws Exception {
		System.out.println("cesh================");
		return "success";

	}

	// 会员招募活动支付
	@RequestMapping(value = "/settle/member", method = { RequestMethod.GET })
	public String member(HttpServletRequest request, String customerId,
			String orderId, String weixin) throws Exception {
		request.setAttribute("customerId", customerId);
		request.setAttribute("orderId", orderId);
		request.setAttribute("weixin", weixin);
		return "/settle/member";
	}

	// 会员招募活动支付成功后的界面
	@RequestMapping(value = "/settle/ok")
	@ResponseBody
	public String memberSuc(HttpServletRequest request) throws Exception {
		String customerId = request.getParameter("customerId");
		String orderId = request.getParameter("orderId");
		// String openidpay = request.getParameter("openidpay");
		System.out.println("------------------------------------" + customerId);
		Map map = request.getParameterMap();
		Set key = map.keySet();
		Map<String, String> noticeinfo = new HashMap<String, String>();
		for (Object aaa : key.toArray()) {
			String parakey = aaa.toString();
			String paravalue = ((String[]) map.get(aaa))[0];
			noticeinfo.put(parakey, paravalue);
		}

		if (noticeinfo.get("trade_state").equals("0")) {
			int trade_state = Integer.parseInt(noticeinfo.get("trade_state"));
			String bank_type = noticeinfo.get("bank_type");
			String bank_billo = noticeinfo.get("bank_billno");
			int total_fee = Integer.parseInt(noticeinfo.get("total_fee"));
			String transaction_id = noticeinfo.get("transaction_id");
			System.out.println("#########################" + total_fee);

			BankTransaction bt = new BankTransaction();
			bt.setOrderID(orderId);
			bt.setStatus(1);// 微信支付成功返回0 改为1.--
			bt.setTransactionID(transaction_id);
			bt.setBankType(bank_type);
			bt.setOrderMoney(600.0);
			userActivityService.updateCustomers(Integer.parseInt(customerId));
			userActivityService.updatePay(bt);
		}
		/*
		 * return "redirect:http://"+ServerConfig.getActivityIp()+
		 * "/Vitamin/settle/success?customerId="+customerId +"&trade_state="
		 * +trade_state+"&bank_type="+bank_type+"&bank_billo=" +bank_billo
		 * +"&total_fee="+total_fee+"&transaction_id="+transaction_id;
		 */
		return "success";
	}

	/**
	 * 查询用户有无骑士卡
	 * 
	 * @param request
	 * @param OrderCode
	 * @return
	 */
	@RequestMapping(value = "/yijiaka")
	@ResponseBody
	public boolean bindYiJiaKa(HttpServletRequest request, String OrderCode) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		List<ShopCardOrderDetail> carlistcheck = cardService
				.allListinfoall(Integer.valueOf(userBasic.getUserID()));
		if (carlistcheck.size() == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 众筹支付页面
	 * 
	 * @param request
	 * @param zccode
	 *            众筹单号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("settle/zcOrder")
	public String toZcPay(HttpServletRequest request, String zccode,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		zccode = request.getParameter("zccode");
		System.out.println(zccode+"---------zccode11");
		String zName = request.getParameter("wxname");
		if(zName!=null){
			zName = java.net.URLDecoder.decode(zName, "UTF-8");
		}
		System.out.println(zName);
		String type = request.getParameter("type");
	
		ZC_UserInfo uInfo = new ZC_UserInfo();
		if(zccode != null || "".equals(zccode)){
			System.out.println("if");
			uInfo.setZccode(zccode);
			uInfo.setzName(zName);
			uInfo.setType(type);
			session.setAttribute("uInfo", uInfo);
		}
		System.out.println("ifend11");
//		zccode = "1";
//		String openid = "o6Lh5t2oopCe9HxcnB9pWyBvAz6Y";
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		String openid;
		if (userBasic == null) {
			 openid = OAuthAPI_PAY.OAuthIfNesscary(request, response);
			if (openid.contains("https")) {
				return "redirect:" + openid;
			}
		}else{
			openid = userBasic.getWX_OPENID();
			System.out.println(openid+"openId");
		}
		System.out.println("---222");
		ZC_UserInfo u = (ZC_UserInfo) session.getAttribute("uInfo");
		System.out.println(u+"11");
		zccode = u.getZccode();
		System.out.println(zccode+"zccodeS");
		zName = u.getzName();
		System.out.println(zName+"zname");
		type = u.getType();
		//获取商品信息
		List<T_CrowdFunding_ProductInfo_two> list = zcPayOrderService.findProInfo(zccode);
		List<T_CrowdFunding_OrderBasic_two> order = zcPayOrderService.findOrderBasic(zccode);
		T_CrowdFunding_BackInfo_two backInfo = zcPayOrderService.findBackInfo(order.get(0).getACTIVITY_ID());
		if(order.get(0).getORDER_STATUS() == 1 || order.get(0).getORDER_STATUS() == 2){
			request.setAttribute("tuijian", list.get(0));
			request.setAttribute("zName", zName);
			return "/settle/zcFinish";//已完成或已取消的众筹订单
		}
		
		String orderPrice = order.get(0).getGOOD_PRICE()+"";
		/** 自己支付剩余金额*/
		if ("all".equals(type)) { 
			double money = zcPayOrderService.checkPrice(zccode);
			int moneyFee = (int) (money * 100);
			request.setAttribute("orderPrice", orderPrice);
			request.setAttribute("zccode", zccode);
			request.setAttribute("money", money);
			request.setAttribute("moneyFee", moneyFee);
			request.setAttribute("tuijian", list);
			request.setAttribute("openId", openid);
			request.setAttribute("zName", zName);
			request.setAttribute("state", 0);
			return "/settle/zcOrder";
		}
		
		
		int checkpayone  = zcPayOrderService.findpaybyopenid(openid,zccode);
		System.out.println(checkpayone+backInfo.getMAX_PAY_MORE()+"====");
		if(checkpayone>=backInfo.getMAX_PAY_MORE()){
			request.setAttribute("payone", "have");
		}

		double money = zcPayOrderService.checkProInfo(zccode);
		int moneyFee = (int) (money * 100);
		System.out.println(list.get(0).getGOOD_NAME());
		request.setAttribute("orderPrice", orderPrice);
		request.setAttribute("tuijian", list);
		request.setAttribute("openId", openid);
		request.setAttribute("zccode", zccode);
		request.setAttribute("money", moneyFee/100.0);
		request.setAttribute("moneyFee", moneyFee);
		request.setAttribute("zName", zName);
		request.setAttribute("state", 1);
		return "/settle/zcOrder";
	}

}
