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

import com.qishi.entity.OrderAddress;
import com.qishi.entity.OrderAddressDetailed;
import com.qishi.entity.OrderCommonAddress;
import com.qishi.entity.UserBasic;
import com.qishi.service.OrderAddressService;

/**
 * 送货地址Controller
 * 
 * @author Administrator
 * 
 */
@Controller
public class OrderAddressController {
	protected final transient Log log = LogFactory.getLog(UserController.class);

	@Autowired
	@Qualifier("OrderAddressService")
	private OrderAddressService addressService;

	/** 显示地址列表 **//*
	@RequestMapping("/show_address")
	public String showAddress(HttpServletRequest request,ModelMap m) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		List<OrderCommonAddress> list = addressService
				.findAddress(userBasic.getUserID()); //user.getUserID;
		request.setAttribute("list", list);
		return "/address/address";
	}

	@RequestMapping("/to_Add_Page")
	public String to_Add_Page(ModelMap m) {
		return "/address/address_new";
	}

	*//** 保存地址 **//*
	@RequestMapping("/save_address")
	public String saveAddress(OrderAddress address, ModelMap m,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		try {
			request.setCharacterEncoding("UTF-8");
			address.setProvince(1);//省级 默认1..北京
			address.setUserId(userBasic.getUserID());
			addressService.save(address);
			m.put("userId", address.getUserId());
			request.setAttribute("sucmsg", "保存成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errmsg", "保存失败，请重新操作");
		}

		return ("redirect:show_address");
	}

	*//** 跳转至更新界面 **//*
	@RequestMapping("/to_Upd_Page")
	public String ToUpdPage(@RequestParam("id") int id, ModelMap m,HttpServletRequest request) {
		List<OrderAddress> list = addressService.findbyleiming("OAddressID", id
				+ "");
		m.put("order", list.get(0));
		return "/address/address_write";
	}

	*//**
	 * 保存更新
	 * 
	 * @throws Exception
	 **//*
	@RequestMapping(value = "/upd_Address", method = RequestMethod.POST)
	public String updAddress(OrderAddress address,String defAddress, ModelMap m,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		try {
			addressService.update(address);
			m.put("userId", address.getUserId());
			request.setAttribute("sucmsg", "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errmsg", "更新失败");
		}

		return ("redirect:show_address");
	}

	*//** 删除 **//*
	@RequestMapping(value="/del_Address",method = RequestMethod.GET)
	public String deleteAddress(@RequestParam("id") String id, ModelMap m,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBasic userBasic = (UserBasic) session.getAttribute("userinfo");
		OrderAddress order = new OrderAddress();
		order.setAddressId(Integer.parseInt(id));
		try {
			addressService.delete(order);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除失败");
		}
		//m.put("userId", userId);
		return ("redirect:show_address");
	}
	
	*//**更改默认收货地址**//*
	@RequestMapping("/defaultAddress")
	public String defaultAddress(@RequestParam("userId") int userId,@RequestParam("addressId") String addressId, ModelMap m){
		addressService.defaultAddress(userId,addressId);
		m.put("userId", userId);
		return ("redirect:settlement");
	}*/

}
