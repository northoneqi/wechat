package com.qishi.dao;

import java.util.List;

import com.qishi.entity.OrderAddress;
import com.qishi.entity.OrderCommonAddress;

public interface OrderCommonAddressDao extends BaseDao<OrderCommonAddress>{

	/**
	 * 根据用户Id查找地址
	 * @param userId
	 * @return
	 */
	public List findAddress(int userId);
	
	/**
	 * 新增地址时清空默认地址
	 * @param userId
	 */
	public void defSetAddress(int userId);
	/**
	 * 默认收货地址
	 * @param userId
	 * @param addressId
	 */
	public void defaultAddress(int userId, String addressId);
	/**
	 * 获取默认地址
	 * @param userId
	 * @param defAddess
	 * @return
	 */
	public List getDefAddess(String userId,String defAddess);

	/**
	 * 表联合查找地址名
	 * @param oAddressId
	 * @return
	 */
	public List findSetAddress(String oAddressId);
}
