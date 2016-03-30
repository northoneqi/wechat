package com.qishi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qishi.dao.BaseDao;
import com.qishi.dao.OrderInComeDao;
import com.qishi.entity.OrderInCome;
import com.qishi.service.OrderInComeService;
@Service("OrderInComeService")
public class OrderInComeServiceImpl extends BaseServiceImpl<OrderInCome> implements OrderInComeService{
    private OrderInComeDao orderInComeDao;
    @Autowired
    @Qualifier("OrderInComeDao")
	@Override
	public void setBaseDao(BaseDao<OrderInCome> orderInComeDao) {
		this.baseDao = orderInComeDao;
		this.orderInComeDao =(OrderInComeDao) orderInComeDao;
		
	}
	@Override
	public void savepeisong(String OConsignee, String Address, String Area,
			String OMbile, String riqi, String shijian, String orderCode,
			Integer userID,String OTelephone) {
		// TODO Auto-generated method stub
		orderInComeDao.savepeisong(OConsignee,Address,Area,OMbile,riqi,shijian,orderCode,userID,OTelephone);
		
	}
	@Override
	public void updateOrderinfo(String orderCode) {
		// TODO Auto-generated method stub
		orderInComeDao.updateOrderinfo(orderCode);
		
	}
	@Override
	public List findincome(String ordercode) {
		// TODO Auto-generated method stub
		return orderInComeDao.findincome(ordercode);
	}

}
