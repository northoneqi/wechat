package com.qishi.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qishi.service.UserLotteryService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qishi.entity.OrderCart;
import com.qishi.entity.UserBasic;
import com.qishi.service.CardService;
import com.qishi.service.OrderCartService;
import com.qishi.service.SearchService;
import com.qishi.util.BigorderRule;
import com.qishi.util.OrderRule;
/**
 * 
 * @author chenghao
 *
 */
@Controller
public class OrderCartColltroller {
	
	private static Log logger = LogFactory.getLog(OrderCartColltroller.class);
	//抽奖服务类
	@Autowired
	@Qualifier("UserLotteryService")
	private UserLotteryService userlotteryservice;
	
	//商品购物车服务类
	@Autowired
	@Qualifier("OrderCartService")
	private OrderCartService orderCartService;
	//骑士卡服务类
	@Autowired
	@Qualifier("CardService")
	private CardService cardService;
	//商品搜索服务类
	@Autowired
	private SearchService searchservice;
	/**
	 * 加入购物车
	 * @param UserId 用户Id
	 * @param sku 商品sku
	 * @param openId 用户openId
	 * @param num 购买数量
	 * @param orderClass 商品分单信息
	 * @param param2 预留参数（预留）
	 * @param request
	 * @return ajax方法
	 * @author chenghao
	 */
	@RequestMapping(value = "/ordercartadd", method = RequestMethod.POST)
	@ResponseBody
	public Map toOrdercart(String UserId, String sku, String openId,String num, String orderClass, String param2,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		//从session中获取用户基本信息
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		//用户Id
		UserId = String.valueOf(userBasic.getUserID());
		//用户openId（微信唯一标识）
		openId = userBasic.getWX_OPENID();
		//获取购物车内商品
		List<OrderCart> goods = orderCartService.findCartgoodby(UserId, sku,openId);
		//获取商品分单信息
		List orderclass = orderCartService.findorderclass(sku);
		List<OrderCart> allgoods = new ArrayList();
		//获取商品分单Id
		Map maporderclass = (Map) orderclass.get(0);
		orderClass = String.valueOf(maporderclass.get("OrderSplit"));
		//如果商品在购物车存在进入方法
		if (goods.size() != 0) {
			OrderCart orderCart = goods.get(0);
			
			
			//num不为空说明用户在购物车中增加数量了
			if (num != null) {
				//商品增加数量
				orderCart.setBuyNum(Integer.parseInt(num));
				orderCartService.saveOrUpdate(orderCart);
				//查询当前购物车中的商品及购买数量
				List goodslist = orderCartService.findcartgoodinfo(UserId,
						openId);
				Double zongjia = 0.00;
				String zongjia1 = null;
				Double zongjia2 = 0.00;
				String zongjiamarket = null;
				for (int i = 0; i < goodslist.size(); i++) {
					Map allgoodsmap = (Map) goodslist.get(i);
					int buynum = (int) allgoodsmap.get("BuyNum");
					Double pricesell = (Double) allgoodsmap.get("sellPrice");
					Double pricemarket = (Double) allgoodsmap.get("marketprice");
					zongjia = (Double) (zongjia + (buynum * pricesell));
					zongjia2 = (Double) (zongjia2 + (buynum * (pricemarket - pricesell)));
					DecimalFormat df = new DecimalFormat("###.00");
					//商品实际总价
					zongjia1 = df.format(zongjia).toString();
					//商品标价总价
					zongjiamarket = df.format(zongjia2).toString();
				}
				List list = new ArrayList();
				Map map = new HashMap();
				//商品实际总价
				map.put("zhongjia", zongjia1);
				//商品标价总价
				map.put("zhongjiam", zongjiamarket);
				return map;
			} else {
				//若num为空，说明用户再次点击加入购物车，商品数量加一
				orderCart.setBuyNum(orderCart.getBuyNum() + 1);
			}
			//查询当前购物车中的商品及购买数量
			allgoods = orderCartService.findCartgoodby(UserId, null, openId);
			//保存商品购物车
			orderCartService.saveOrUpdate(orderCart);
			
		} else {
			//若购物车为空的情况向购物车中添加商品
			OrderCart addcar = new OrderCart();
			//判断用户是否登陆若以登陆则插入用户Id
			if (userBasic.getUserID() != null) {
				addcar.setUserId(Integer.parseInt(UserId));
			}
			//若num为空默认数量为1
			if (num == null && num.equals("null")) {
				num = "1";
			}
			addcar.setBuyNum(Integer.parseInt(num));
			addcar.setGroupId("1");
			addcar.setIsMain(true);
			addcar.setOpenId(openId);
			addcar.setOrderClass(Integer.parseInt(orderClass));
			addcar.setSKU(Integer.parseInt(sku));
			//保存购物车信息
			orderCartService.save(addcar);
			//获取购物车中商品数量并增加1
			int cartnum = (int) session.getAttribute("cartnum");
			session.setAttribute("cartnum", cartnum + 1);
			//查询当前购物车中的商品及购买数量
			allgoods = orderCartService.findCartgoodby(UserId, null, openId);
		}
		System.out.println("USerId" + UserId + "SKU" + sku);
		String size = String.valueOf(allgoods.size());
		List list = new ArrayList();
		Map map = new HashMap();
		//购物车中商品数量
		map.put("size", size);
		return map;
	}
	/**
	 * 显示购物车列表
	 * @param UserId 用户Id
	 * @param openId 用户openId
	 * @param param1 预留参数（未用）
	 * @param param2 预留参数（未用）
	 * @param request
	 * @return 购物车列表页
	 * @author chenghao
	 */
	@RequestMapping(value = "/ordercartlist", method = RequestMethod.GET)
	public String findcartlist(String UserId, String openId, String param1,String param2, HttpServletRequest request) {
		HttpSession session = request.getSession();
		//从session中获取用户基本信息
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		UserId = String.valueOf(userBasic.getUserID());
		openId = userBasic.getWX_OPENID() + "";
		//查询购物车内商品信息
		List goods = orderCartService.findcartgoodinfo(UserId, openId);
		//若没有商品返回空购物车页面
		if (goods.size() == 0) {
			return "redirect:ordercartkong";
		}
		Double zongjia = 0.00;
		String zongjia1 = null;
		Double zongjia2 = 0.00;
		String zongjiamarket = null;
		for (int i = 0; i < goods.size(); i++) {
			Map allgoodsmap = (Map) goods.get(i);
			int buynum = (int) allgoodsmap.get("BuyNum");
			Double pricesell = (Double) allgoodsmap.get("sellPrice");
			Double pricemarket = (Double) allgoodsmap.get("marketprice");
			zongjia = (Double) (zongjia + (buynum * pricesell));
			zongjia2 = (Double) (zongjia2 + (buynum * (pricemarket - pricesell)));
			DecimalFormat df = new DecimalFormat("##0.00");
			zongjia1 = df.format(zongjia).toString();
			zongjiamarket = df.format(zongjia2).toString();
		}
		//商品总价
		request.setAttribute("zongjia", zongjia1);
		//商品节省的钱
		request.setAttribute("jiesheng", zongjiamarket);
		//购物车商品信息
		request.setAttribute("goodCart", goods);
		String goodCart = request.getParameter("downCartGoods");
		List goodName=orderCartService.finddowncartgoodinfo(UserId, openId);
		String goodCartName="";
		if(goodName!=null){
		for (int i = 0; i < goodName.size(); i++) {
			Map cartName=(Map) goodName.get(i);
			goodCartName+=cartName.get("ProName").toString();
		}
		//下架商品信息
		request.setAttribute("downGoodName", goodCartName);
		}	
		return "/ordercart/shopping";
		
	}
	/**
	 * 清空购物车
	 * @param UserId 用户Id
	 * @param openId 用户openId
	 * @param sku 商品sku
	 * @param param1 预留参数（未用）
	 * @param param2 预留参数（未用）
	 * @param response
	 * @param request
	 * @return 购物车列表页
	 * @author chenghao
	 */

	@RequestMapping(value = "/ordercartdelete", method = RequestMethod.GET)
	public String deletecartlist(String UserId, String openid, String sku,String param1, String param2
			, HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		UserId = String.valueOf(userBasic.getUserID());
		openid = userBasic.getWX_OPENID();
		//根据商品sku和用户信息删除用户购物车内商品
		orderCartService.deletecat(UserId, openid, sku);
		//session中购物车商品数量减一
		int cartnum = (int) session.getAttribute("cartnum");
		session.setAttribute("cartnum", cartnum - 1);
		return "redirect:ordercartlist";
		
	}
	/**
	 * ajax验证购物车中是否有商品
	 * @param UserId 用户Id
	 * @param openId 用户openId
	 * @param sku 商品sku
	 * @param param1 预留参数（未用）
	 * @param param2 预留参数（未用）
	 * @param response
	 * @param request
	 * @return 购物车列表页
	 * @author chenghao
	 */
	@RequestMapping(value = "/ordercartcheck", method = RequestMethod.GET)
	@ResponseBody
	public String deletecartcheck(String UserId, String openid, String sku,String param1, String param2, 
			HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		UserId = String.valueOf(userBasic.getUserID());
		openid = userBasic.getWX_OPENID();
		//根据用户信息查询购物车中的商品
		List goods = orderCartService.findcartgoodinfo(UserId, openid);
		if (goods.size() == 0) {
			//购物车无商品
			return "1";
		}else{
			//购物车中没有商品
			return "0";
		}
	}
	/**
	 * 显示空购物车页面
	 * @param UserId 用户Id
	 * @param openId 用户openId
	 * @param sku 商品sku
	 * @param response
	 * @param request
	 * @return 空购物车页面
	 * @author chenghao
	 */
	@RequestMapping(value = "/ordercartkong", method = RequestMethod.GET)
	public String cartlistkong(String UserId, String openid, String sku,
			HttpServletResponse response,HttpServletRequest request) {
		//查询推荐商品
		List goodslist = searchservice.search("");
		request.setAttribute("goodcha", goodslist);
		return "/ordercart/shopping_none";
	}
	/**
	 * 下订单
	 * @param UserId 用户Id
	 * @param openId 用户openId
	 * @param sku 商品sku
	 * @param param1 预留参数（未用）
	 * @param param2 预留参数（未用）
	 * @param response
	 * @param request
	 * @return 支付settlement colltroller
	 */
	@RequestMapping(value = "/toOrder", method = RequestMethod.GET)
	public String toOrder(String UserId, String openId, String sku,
			String param1, String param2, HttpServletResponse response,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		UserId = String.valueOf(userBasic.getUserID());
		openId = userBasic.getWX_OPENID();
		//判断用户是否为空若不为空更新购物车商品userId属性
		if(userBasic.getUserID() != null){
		orderCartService.updateorder(UserId, openId);
		}
		//查询购物车中的商品
		List orderClassList = orderCartService.findAllOrderClass(UserId,
				openId);
		//若商品为空跳转到购物车列表页面（在购物车列表页面会自动跳转到购物车空页面）
		if(orderClassList.size()==0){
			return "redirect:ordercartlist";
		}
		//判断用户是否登陆若未登陆跳转到登陆页面，登陆会回调到当前colltroller
		if (userBasic.getUserID() == null) {
			String redirct_url = request.getRequestURI();
			redirct_url = redirct_url.replace("/lime/", "");
			try {
				redirct_url = URLDecoder.decode(redirct_url, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//设置回调url
			request.setAttribute("redirct_url", "toOrder");
			//跳转到登陆页面
			return "/login";
		} else {
			//查询购物车中的商品
			List goods=orderCartService.finddowncartgoodinfo(UserId, openId);
			if(goods.size()==0){
			OrderRule rule = new OrderRule();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfwx = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			String timewx = sdfwx.format(date);
			List<String> orderClasslist = new ArrayList();
			StringBuffer zhifu = new StringBuffer();
			orderClassList = orderCartService.findAllOrderClass(UserId,
					openId);
			BigorderRule bigrule = new BigorderRule();
			//生成微信订单号
			String big = bigrule.rule();
			for (int j = 0; j < orderClassList.size(); j++) {
				System.out.println(orderClassList.toString());
				Map allorderclass = (Map) orderClassList.get(j);
				String orderClasszong = String.valueOf(allorderclass
						.get("OrderClass"));
				// 生成订单号
				String orderCode = rule.GetOrderCode(5);
				orderClasslist.add(orderCode);
				// 找到保存订单需要的全部信息
				List toorderlist = orderCartService.findAllInfo(UserId, openId,
						orderClasszong);
				for (int i = 0; i < toorderlist.size(); i++) {
					Map ordermap = (Map) toorderlist.get(i);
					String SKUName = String.valueOf(ordermap.get("WXProName"));
					String ProNum = String.valueOf(ordermap.get("BuyNum"));
					int num = (int) ordermap.get("BuyNum");
					String SellPrice = String.valueOf(ordermap.get("SellPrice"));
					Double price = (Double) ordermap.get("SellPrice");
					String OrderClass = String.valueOf(ordermap.get("OrderClass"));
					String SKU = String.valueOf(ordermap.get("SKU"));
					String Unit = String.valueOf(ordermap.get("UnitMent"));
					String sub = String.valueOf(num * price);
					// 订单详细
					orderCartService.savetoOrderDetail(orderCode, UserId,
							openId, SKU, ProNum, Unit, SellPrice, SKUName, sub);
					
				}
				//绑定微信订单信息
				orderCartService.saveweixinorder(big,orderCode,timewx);
				// 订单受理日志信息
				orderCartService.savetoOrderAcceptLog(orderCode, time,"此订单通过线上下单","1");
				// 绑定受理表信息
				orderCartService.savetoOrderAccept(orderCode, time);
				// 订单总表
				orderCartService.savetoOrderBasic(UserId, openId, orderCode,
						time, orderClasszong);
				session.setAttribute("cartnum", 0);
				System.out.println(toorderlist.toString());

			}
			orderCartService.deleteAll(UserId, openId);
			return "redirect:settle/settlement?dingdanhao=" +big ;
		}else{
			return "redirect:ordercartlist";
		}
		
	}
	}

	/**
	 * 立即支付
	 * @param UserId
	 * @param openId
	 * @param sku
	 * @param ProNum 
	 * @param SellPrice
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/goPay", method = RequestMethod.GET)
	public String goPay(String UserId, String openId, Integer sku,String ProNum, String SellPrice,String split
			,HttpServletResponse response,HttpServletRequest request) {
		if(ProNum == null || "".equals(ProNum)){
			ProNum = "1";
		}
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		UserId = String.valueOf(userBasic.getUserID());
		openId = userBasic.getWX_OPENID();	
		if(sku==null){
			sku = Integer.parseInt((String) session.getAttribute("sku2"));
		}
		if (userBasic.getUserID() == null) {
			String redirct_url = request.getRequestURI();
			redirct_url = redirct_url.replace("/lime/", "");
			try {
				redirct_url = URLDecoder.decode(redirct_url, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("redirct_url", redirct_url);
			return "/login";
		} else {
			orderCartService.updateorder(UserId, openId);
			OrderRule rule = new OrderRule();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfwx = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			String timewx = sdfwx.format(date);
			List<String> orderClasslist = new ArrayList();
			StringBuffer zhifu = new StringBuffer();
			//生成订单号
			String orderCode=rule.GetOrderCode(5);
			BigorderRule bigrule = new BigorderRule();
			//生成微信订单号
			String big = bigrule.rule();
			List findOrderDetail=orderCartService.goPay(sku);
			Map map=(Map) findOrderDetail.get(0);
			String SKUName=String.valueOf(map.get("WXProName"));
			String OrderClasszong=String.valueOf(map.get("OrderSplit"));
			SellPrice=String.valueOf(map.get("SellPrice"));
			double dSellPrice = Double.valueOf(SellPrice);
			String UnitMent=String.valueOf(map.get("UnitMent"));
			double dProNum = Double.valueOf(ProNum);
			String sub= dSellPrice * dProNum + "";
			String SKU=sku+"";
			
			orderCartService.savetoOrderDetail(orderCode, UserId,
					openId, SKU, ProNum, UnitMent, SellPrice, SKUName, sub);
			//绑定微信订单信息
			orderCartService.saveweixinorder(big,orderCode,timewx);
			// 订单受理日志信息
			orderCartService.savetoOrderAcceptLog(orderCode, time,"此订单通过线上下单","1");
			// 绑定受理表信息
			orderCartService.savetoOrderAccept(orderCode, time);
			// 订单总表
			orderCartService.savetoOrderBasic(UserId, openId, orderCode,
					time, OrderClasszong);
			
			session.setAttribute("cartnum", 0);
			return "redirect:settle/settlement?dingdanhao=" + big+"&split="+split;
		}
	}
	
	@RequestMapping(value = "/hongbaoPay", method = RequestMethod.GET)
	public String hongbaoPay(String ActivityNo
			,HttpServletResponse response,HttpServletRequest request) {
			//获取用户基本信息
			HttpSession session = request.getSession();
			UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
			String UserId = String.valueOf(userBasic.getUserID());
			String openId = userBasic.getWX_OPENID();	
			Integer sku = Integer.parseInt((String) session.getAttribute("sku2"));
			ActivityNo = (String) session.getAttribute("activityNo");
			logger.debug("hongbaoPayRukou---ActivityNo="+ActivityNo+"--sku"+sku+"--openId"+openId);
			OrderRule rule = new OrderRule();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfwx = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			String timewx = sdfwx.format(date);
			List<String> orderClasslist = new ArrayList();
			StringBuffer zhifu = new StringBuffer();
			//生成订单号
			String orderCode=rule.GetOrderCode(5);
			BigorderRule bigrule = new BigorderRule();
			//生成微信订单号
			String big = bigrule.rule();
			logger.debug("hongbaoPay dingdan---orderCode="+orderCode+"--orderCodebig"+big);
			List findOrderDetail=orderCartService.goPay(sku);
			Map map=(Map) findOrderDetail.get(0);
			String SKUName=String.valueOf(map.get("WXProName"));
			String OrderClasszong=String.valueOf(map.get("OrderSplit"));
			double oldprize = (double) map.get("SellPrice");
			String SellPrice=String.valueOf(map.get("SellPrice"));//????oldprice?buyprice?
			logger.debug("hongbaoPay ---SellPrice="+SellPrice);
			//查询用户中奖金额
			List list = userlotteryservice.findPrizeInfoByopenId(openId,ActivityNo);
			Map prize = new HashMap();
			prize = (Map) list.get(0);
			double prizePic =	Double.parseDouble((String)prize.get("PrizeContent"));
			Integer prizeId = (Integer) prize.get("PrizeId");
			double dSellPrice = oldprize-prizePic;
			String UnitMent=String.valueOf(map.get("UnitMent"));
			String sub = dSellPrice+"";
			String SKU=sku+"";
			logger.debug("hongbaoPaysave ---prizePic="+prizePic+"oldprize"+oldprize+"dSellPrice"+dSellPrice+"sub"+sub);
			String remark="商品原价为："+oldprize+"红包金额为："+prizePic+"应收金额为："+sub;
			try{
				orderCartService.savetoOrderDetail(orderCode, UserId,
						openId, SKU, "1", UnitMent, SellPrice, SKUName, sub);
				//绑定微信订单信息
				orderCartService.saveweixinorder(big,orderCode,timewx);
				// 订单受理日志信息
				orderCartService.savetoOrderAcceptLog(orderCode, time,"此订单通过线上下单","1");
				// 绑定受理表信息
				orderCartService.savetoOrderAccept(orderCode, time);
				// 订单总表(红包)
				orderCartService.savetoOrderBasichongbao(UserId, openId, orderCode,
						time, OrderClasszong,remark);
			}catch(Exception e){
				logger.debug("hongbaoPay ---exception="+e.toString());
			}
			
			session.setAttribute("cartnum", 0);
			userlotteryservice.updatePrizeCardState(prizeId);
			//------
			
			return "redirect:settle/settlement?dingdanhao=" + big+"&hongbao=hongbao";
		}
	/**
	 * 根据sku查询商品状态
	 * @param sku
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkxiajia", method = RequestMethod.POST)
	@ResponseBody
	public int checkxiajia( String sku,HttpServletResponse response,HttpServletRequest request) {
		
		//查询 商品是否下架
		int states = searchservice.searchbysku(sku);

		return states;
	}
	
}
