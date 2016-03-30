package com.qishi.service;

import java.util.List;

import com.qishi.entity.UserBasic;

public  interface UserBasicService extends BaseService<UserBasic> {

	public void delete(UserBasic st);

	//public void save(UserBasic st);

	public void updateUser(UserBasic st);
	/**
	 * 查找全部
	 * @return
	 */
	public  List<UserBasic> findAll();
	
	/**
	 * 查找用户
	 * @param st
	 * @return
	 */
	public UserBasic findUser(UserBasic st);
	
	public int test2();
	
	List<UserBasic> getUserByUsername(String Username);

	/**
	 * 根据用户表内的市区id查找市区
	 * @param id
	 * @return
	 */
	public List<UserBasic> findUserAndAddress(int id);

	public List<UserBasic> findbyleiming(String ziduan,String liezhi);
	
	void updateOpenid(String openID,String UserID);

}
