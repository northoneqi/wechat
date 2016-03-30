package com.qishi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qishi.dao.BaseDao;
import com.qishi.dao.OrderAddressDao;
import com.qishi.entity.OrderAddress;
import com.qishi.entity.OrderCommonAddress;
import com.qishi.service.OrderAddressService;

@Service("OrderAddressService")
public class OrderAddressServiceImpl extends BaseServiceImpl<OrderAddress> implements OrderAddressService{

	
	private OrderAddressDao orderAddressDao;
	@Autowired
	@Qualifier("orderAddressDao")
	@Override
	public void setBaseDao(BaseDao<OrderAddress> orderAddressDao) {
		this.baseDao = orderAddressDao;
		this.orderAddressDao = (OrderAddressDao) orderAddressDao;
		
	}
	
	public void delete(OrderAddress order){
		orderAddressDao.delete(order);
	}

	@Override
	public List<OrderAddress> findbyleiming(String ziduan, String liezhi) {
		return orderAddressDao.findbyleiming(ziduan, liezhi);
	}


	@Override
	public List findAddress(int userId) {
		return orderAddressDao.findAddress(userId);
	}

	@Override
	public void defaultAddress(int userId, String addressId) {
		orderAddressDao.defaultAddress(userId,addressId);
	}

	@Override
	public List getDefAddess(String userId, String defAddess) {
		// TODO Auto-generated method stub
		return orderAddressDao.getDefAddess(userId, defAddess);
	}
}
