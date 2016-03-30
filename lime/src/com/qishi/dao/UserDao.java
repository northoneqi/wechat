package com.qishi.dao;

import java.util.List;

import com.qishi.entity.User;
import com.qishi.entity.UserBasic;

public interface UserDao extends BaseDao<User>{
		
	public void updateUser(UserBasic u);
	public UserBasic finduser(UserBasic u);
	int finduserCount();
	
}
