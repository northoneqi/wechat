package com.qishi.dao;

import java.util.List;

import com.qishi.entity.OrderAddress;

public interface OrderAddressDao extends BaseDao<OrderAddress>{

	public List findAddress(int userId);

	//默认收货地址
	public void defaultAddress(int userId, String addressId);
	public List getDefAddess(String userId,String defAddess);
}
