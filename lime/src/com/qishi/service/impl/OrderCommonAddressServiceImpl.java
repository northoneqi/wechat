package com.qishi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qishi.dao.BaseDao;
import com.qishi.dao.OrderAddressDao;
import com.qishi.dao.OrderCommonAddressDao;
import com.qishi.entity.OrderAddress;
import com.qishi.entity.OrderCommonAddress;
import com.qishi.service.OrderAddressService;
import com.qishi.service.OrderCommonAddressService;

@Service("OrderCommonAddressService")
public class OrderCommonAddressServiceImpl extends BaseServiceImpl<OrderCommonAddress> implements OrderCommonAddressService{

	
	private OrderCommonAddressDao addressDao;
	@Autowired
	@Qualifier("OrderCommonAddressDao")
	@Override
	public void setBaseDao(BaseDao<OrderCommonAddress> addressDao) {
		this.baseDao = addressDao;
		this.addressDao = (OrderCommonAddressDao) addressDao;
		
	}
	
	public void delete(OrderCommonAddress order){
		addressDao.delete(order);
	}

	@Override
	public List<OrderCommonAddress> findbyleiming(String ziduan, String liezhi) {
		return addressDao.findbyleiming(ziduan, liezhi);
	}

	@Override
	public List findAddress(int userId) {
		return addressDao.findAddress(userId);
	}

	@Override
	public void defaultAddress(int userId, String addressId) {
		addressDao.defaultAddress(userId,addressId);
	}

	@Override
	public List getDefAddess(String userId, String defAddess) {
		// TODO Auto-generated method stub
		return addressDao.getDefAddess(userId, defAddess);
	}
	
	public  void defSetAddress(int userId){
		addressDao.defSetAddress(userId);
	}
	//查找用户点击后的地址
	public List findSetAddress(String oAddressId){
		return addressDao.findSetAddress(oAddressId);
	}
}
