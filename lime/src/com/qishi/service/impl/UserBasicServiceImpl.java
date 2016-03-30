package com.qishi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qishi.dao.BaseDao;
import com.qishi.dao.UserBasicDao;
import com.qishi.entity.UserBasic;
import com.qishi.service.UserBasicService;
@Service("userBasicService")
public class UserBasicServiceImpl extends BaseServiceImpl<UserBasic> implements UserBasicService {
	
	public UserBasicDao userBasicDao; 
	@Autowired
	@Qualifier("userBasicDao")
	@Override
	public void setBaseDao(BaseDao<UserBasic> userBasicDao) {
		this.baseDao = userBasicDao;
		this.userBasicDao =(UserBasicDao) userBasicDao;
		
	}
  

	
	public void updateUser(UserBasic st) {
		// TODO Auto-generated method stub
		userBasicDao.saveOrUpdate(st);
	}

	
	public void delete(UserBasic st) {
		
		userBasicDao.delete(st);
		
	}
  
//	public void save(UserBasic st) {
//		
//		userBasicDao.save(st);
//	}
    
	public List<UserBasic> findAll() {
		List <UserBasic> list  = userBasicDao.findAll();
		return list;
	}
	
	
	public int test2() {
		int a = userBasicDao.findAllcount();
		//List <User> list  = baseDao.findbyleiming("name", "userhhhh");
		//List <User> list  = baseDao.findbypage(2, 5);
		return a;
	}



	public UserBasic findUser(UserBasic user) {
		return userBasicDao.finduser(user);
	}


	@Override
	public List<UserBasic> getUserByUsername(String Username) {
		// TODO Auto-generated method stub
		return userBasicDao.getUserByUsername(Username);
	}


	//根据用户表内的市区id查找市区
	@Override
	public List<UserBasic> findUserAndAddress(int id) {
		// TODO Auto-generated method stub
		return userBasicDao.findUserAndAddress(id);
	}


	@Override
	public List<UserBasic> findbyleiming(String ziduan,String liezhi) {
		// TODO Auto-generated method stub
		return userBasicDao.findbyleiming(ziduan, liezhi);
	}


	@Override
	public void updateOpenid(String openID, String UserID) {
		//���û�
		userBasicDao.updateOpenid(openID, UserID);
		
	}


	

}
