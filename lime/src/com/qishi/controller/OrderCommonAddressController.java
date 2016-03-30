package com.qishi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qishi.entity.OrderAddressDetailed;
import com.qishi.entity.OrderCommonAddress;
import com.qishi.entity.UserBasic;
import com.qishi.service.OrderAddressService;
import com.qishi.service.OrderCommonAddressService;

/**
 * 送货地址Controller
 * 
 * @author 刘宁
 * 
 */
@Controller
public class OrderCommonAddressController {
	//protected final transient Log log = LogFactory.getLog(UserController.class);

	@Autowired
	@Qualifier("OrderCommonAddressService")
	private OrderCommonAddressService addressService;

	/** 显示地址列表 **/
	@RequestMapping("/show_address")
	public String showAddress(HttpServletRequest request,ModelMap m,String url,String orderCodes) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		List<OrderCommonAddress> list = addressService
				.findAddress(userBasic.getUserID()); //user.getUserID;
		request.setAttribute("list", list);
		request.setAttribute("reurl", url);
		request.setAttribute("orderCodes", orderCodes);
		return "/address/address";
	}

	/**跳转至地址增加页面**/
	@RequestMapping("/to_Add_Page")
	public String to_Add_Page(ModelMap m,HttpServletRequest request,String url,String orderCodes) {
		
		System.out.println(url);
		request.setAttribute("reurl", url);
		request.setAttribute("orderCodes", orderCodes);
		
		return "/address/address_new";
	}

	/** 保存地址 **/
	@RequestMapping("/save_address")
	public String saveAddress(OrderCommonAddress address, ModelMap m,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		
		try {
			request.setCharacterEncoding("UTF-8");
			address.setOProvince(1);//省级 默认1..北京
			address.setUserId(userBasic.getUserID());
			addressService.defSetAddress(userBasic.getUserID());
			address.setDefAddress(1);
			addressService.save(address);
			m.put("userId", address.getUserId());
			request.setAttribute("sucmsg", "保存成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errmsg", "保存失败，请重新操作");
		}
		//回调url地址
		String repay_url = request.getParameter("reurl");
		//回调订单号
		String repay = request.getParameter("orderCodes");
		//如果存在url地址和订单号。回调
		if(repay_url!=null&&!"".equals(repay_url)){
			System.out.println(repay_url);
			return "redirect:"+repay_url+"?dingdanhao="+repay;
		}
		return ("redirect:show_address");
	}

	/** 跳转至更新界面 **/
	@RequestMapping("/to_Upd_Page")
	public String ToUpdPage(@RequestParam("id") int id, ModelMap m,
			HttpServletRequest request,String url,String orderCodes) {
		List<OrderCommonAddress> list = addressService.findbyleiming("OAddressID", id
				+ "");
		request.setAttribute("reurl",url);
		request.setAttribute("orderCodes",orderCodes);
		m.put("order", list.get(0));
		return "/address/address_write";
	}

	/**
	 * 保存更新
	 * 
	 * @throws Exception
	 **/
	@RequestMapping(value = "/upd_Address", method = RequestMethod.POST)
	public String updAddress(String OAddressID,String defAddress, ModelMap m,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		try {
			List<OrderCommonAddress> address = addressService.findbyleiming("OAddressID",OAddressID);
			address.get(0).setOConsignee(request.getParameter("OConsignee"));
			address.get(0).setOMbile(request.getParameter("OMbile"));
			address.get(0).setOTelephone(request.getParameter("OTelephone"));
			address.get(0).setOCity(Integer.parseInt(request.getParameter("OCity")));
			address.get(0).setOArea(Integer.parseInt(request.getParameter("OArea")));
			address.get(0).setOAddress(request.getParameter("OAddress"));
			addressService.update(address.get(0));
			m.put("userId", address.get(0).getUserId());
			request.setAttribute("sucmsg", "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errmsg", "更新失败");
		}
		//回调url
		String repay_url = request.getParameter("reurl");
		String repay = request.getParameter("orderCodes");
		if(repay_url!=null&&!"".equals(repay_url)){
			return "redirect:"+repay_url+"?dingdanhao="+repay+"&oAddressId="+OAddressID;
		}

		return ("redirect:show_address");
	}

	/** 删除 **/
	@RequestMapping(value="/del_Address",method = RequestMethod.GET)
	public String deleteAddress(@RequestParam("id") String id, ModelMap m,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		OrderCommonAddress order = new OrderCommonAddress();
		order.setOAddressID(Integer.parseInt(id));
		try {
			addressService.delete(order);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除失败");
		}
		//m.put("userId", userId);
		return ("redirect:show_address");
	}
	
	/**更改默认收货地址**/
	@RequestMapping("/defaultAddress")
	public String defaultAddress(@RequestParam("userId") int userId,@RequestParam("addressId") String addressId,
			ModelMap m,HttpServletRequest request, String url,String orderCodes){
		System.out.println(url);
		if(url!=null&&!"".equals(url)){
			System.out.println("-----");
			addressService.defaultAddress(userId,addressId);
			return "redirect:"+url+"?dingdanhao="+orderCodes+"&oAddressId="+addressId;
		}
		System.out.println("morenshoih");
		addressService.defaultAddress(userId,addressId);
		m.put("userId", userId);
		return ("redirect:show_address");
	}

}
