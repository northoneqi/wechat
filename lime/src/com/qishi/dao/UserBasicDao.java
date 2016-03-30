package com.qishi.dao;

import java.util.List;


import com.qishi.entity.UserBasic;

public interface UserBasicDao extends BaseDao<UserBasic>{
		
	public List<UserBasic> findUserAndAddress(int userId);
	public UserBasic finduser(UserBasic u);
	int finduserCount();
	
	List<UserBasic> getUserByUsername(String Username);

	void updateOpenid(String openID,String UserID);
	
}
