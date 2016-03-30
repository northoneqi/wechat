package com.qishi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qishi.entity.User;
import com.qishi.entity.UserBasic;
import com.qishi.service.UserService;

@Controller
public class UserController {

	protected final transient Log log = LogFactory.getLog(UserController.class);

	public UserBasic user;
	@Autowired
	@Qualifier("UserService")
	private UserService userservice;

	public UserController() {
	}

	@RequestMapping("/userlist")
	public String load(ModelMap modelMap) {
		// int a = userservice.test2();
		//List<User> list = userservice.test();
		//modelMap.put("list", list);
		//System.out.println("获取数据库查询到的用户名称" + list.get(0).getName());
		return "/a";

	}

	@RequestMapping(params = "method=save")
	public String save(HttpServletRequest request, ModelMap modelMap) {

		User user = new User();

		String name = request.getParameter("name");

		String psw = request.getParameter("psw");

		user.setName(name);

		user.setPsw(psw);

		try {

			userservice.save(user);

			modelMap.put("addstate", "添加成功");

		}

		catch (Exception e) {

			e.printStackTrace();

			log.error(e.getMessage());
			modelMap.put("addstate", "添加失败b");
		}

		return "user_add";

	}

	@RequestMapping(params = "method=del")
	public void del(@RequestParam("id") String id, HttpServletResponse response) {

		try {

			User st = new User();

			st.setId(id);

			userservice.delete(st);

			response.getWriter().print("{\"del\":\"true\"}");

		}

		catch (Exception e) {

			log.error(e.getMessage());

			e.printStackTrace();

		}
	}
}
