package com.qishi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qishi.dao.BaseDao;
import com.qishi.dao.UserDao;
import com.qishi.entity.User;
import com.qishi.entity.UserBasic;
import com.qishi.service.BaseService;
import com.qishi.service.UserService;
@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	public UserDao userDao; 
	
  

	/***
	 * 更新用户信息
	 */
	public void updateUser(UserBasic st) {
		// TODO Auto-generated method stub
		userDao.updateUser(st);
	}

	
	public void delete(User st) {
		
    	userDao.delete(st);
		
	}
  
	public void save(User st) {
		
    	userDao.save(st);
	}
    
	public List<User> findAll() {
		List <User> list  = userDao.findAll();
		return list;
	}
	
	
	public int test2() {
		int a = userDao.findAllcount();
		//List <User> list  = baseDao.findbyleiming("name", "userhhhh");
		//List <User> list  = baseDao.findbypage(2, 5);
		return a;
	}



	public UserBasic findUser(UserBasic user) {
		return userDao.finduser(user);
	}

}
