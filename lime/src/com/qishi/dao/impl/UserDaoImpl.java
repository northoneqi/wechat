package com.qishi.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.qishi.dao.UserDao;
import com.qishi.entity.User;
import com.qishi.entity.UserBasic;

@Repository("userDao") 
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao  {

	
	/**
	 * 
	 */
	public UserBasic finduser(UserBasic u) {
		String hql = "from UserBasic";
		return (UserBasic) getSession().createQuery(hql);
	}

	public int finduserCount() {
		// TODO Auto-generated method stub
		int a = findAllcount();
		return a;
	}


	public void updateUser(UserBasic u) {
		getSession().beginTransaction();
		getSession().update(u);
		getSession().flush();
		getSession().getTransaction().commit();
	}

	
	
	

	 
	


	

	
	
}
