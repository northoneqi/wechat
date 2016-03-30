package com.qishi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qishi.entity.UserBasic;
import com.qishi.service.UserBasicService;

/**
 * 用户信息Controller
 * @author 刘宁
 *
 */
@Controller
public class UserBasicController {
	@Autowired
	@Qualifier("userBasicService")
	private UserBasicService userBasicService;
	
	@RequestMapping("/showUserInf")
	public String showUserInf(ModelMap m,HttpServletRequest request){
		UserBasic userBasic = (UserBasic) request.getSession().getAttribute("userinfo");
		List<UserBasic> user = userBasicService.findbyleiming("UserID",userBasic.getUserID()+"");
		String url = request.getParameter("url");
		user.get(0).setCity(2); //设置城市2 北京。。防止数据库city为空时，js报错
		//System.out.println(user.get(0).getPhone());
		request.setAttribute("url", url);
		m.put("user", user.get(0));
		
		return "/personalmsg/personal_message_write";
	}
	/**
	 * 更新个人信息
	 * @param user
	 * @param m
	 * @return
	 */
	@RequestMapping(value="updUserInf",method=RequestMethod.POST)
	public String updUserInf(String UserID,ModelMap m,HttpServletRequest request){
		List<UserBasic> user = userBasicService.findbyleiming("UserID", UserID);
		String nickName = request.getParameter("NickName");
		String phone = request.getParameter("Phone");
		String phoneCode =request.getParameter("PhoneCode");
		System.out.println(phoneCode);
		String city = request.getParameter("City");
		String area = request.getParameter("Area");
		String address = request.getParameter("Address");
		user.get(0).setNickName(nickName);
		user.get(0).setPhone(phone);
		user.get(0).setPhoneCode(phoneCode);
		user.get(0).setCity(Integer.parseInt(city));
		user.get(0).setArea(Integer.parseInt(area));
		user.get(0).setAddress(address);
		userBasicService.updateUser(user.get(0));
		//回调
		String url = request.getParameter("url");
		if(!"".equals(url) && url != null){
			return "redirect:/index";
		}
		
		return "redirect:/memberCenter";
	}

} 
