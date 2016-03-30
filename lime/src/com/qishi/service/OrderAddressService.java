package com.qishi.service;

import java.util.List;

import com.qishi.entity.OrderAddress;
import com.qishi.entity.OrderCommonAddress;
import com.qishi.entity.User;
import com.qishi.entity.UserBasic;
import com.qishi.util.Pager;

public interface OrderAddressService extends BaseService<OrderAddress>{
	public List<OrderAddress> findbyleiming(String ziduan, String liezhi);
	public void delete(OrderAddress order);
	public List findAddress(int userId);
	public void defaultAddress(int userId, String addressId);
	
	
	public List getDefAddess(String userId,String defAddess);
}
