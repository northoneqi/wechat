package com.qishi.service;

import java.util.List;

import com.qishi.entity.User;
import com.qishi.entity.UserBasic;

public  interface UserService {


	public void updateUser(UserBasic st);
	public UserBasic findUser(UserBasic st);
	

	public void delete(User st);
	public void save(User st);
	public  List<User> findAll();
	public int test2();

}
