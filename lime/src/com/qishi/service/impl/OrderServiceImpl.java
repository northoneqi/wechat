package com.qishi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qishi.dao.BaseDao;
import com.qishi.dao.OrderDao;
import com.qishi.entity.OrderInCome;
import com.qishi.service.OrderService;

@Service("OrderService")
public class OrderServiceImpl extends BaseServiceImpl<OrderInCome> implements OrderService {

	
	private OrderDao orderDao;
	
	@Autowired
	@Qualifier("orderDao")
	@Override
	public void setBaseDao(BaseDao<OrderInCome> orderDao) {
		// TODO Auto-generated method stub
		this.baseDao=orderDao;
		this.orderDao=(OrderDao) orderDao;
		
	}

	

	
	@Override
	public List findlist(Integer userId, String code,String orderCodes) {
		// TODO Auto-generated method stub
		return orderDao.findlist(userId, code,orderCodes);
	}

	
	public List findOrderDetail(String code) {
		// TODO Auto-generated method stub
		return orderDao.findOrderDetail(code);
	}
	
	public List findOrderAddress(String openId,String activityId) {
		// TODO Auto-generated method stub
		return orderDao.findOrderAddress(openId,activityId);
	}
	
	@Override
	public void updateAccept(String code) {
		// TODO Auto-generated method stub
		//System.out.println("执锟叫碉拷12");
		orderDao.updateAccept(code);
	}


	
	@Override
	public String payOrderSum(String orderCode) {
		// TODO Auto-generated method stub
		return orderDao.payOrderSum(orderCode);
	}



	@Override
	public int updatePayType(int PayType, String orderCode) {
		// TODO Auto-generated method stub
		return orderDao.updatePayType(PayType, orderCode);
	}
	
	@Override
	public int updatePeisongStatus(int peisongStatus, String openId) {
		// TODO Auto-generated method stub
		return orderDao.updatePeisongStatus(peisongStatus, openId);
	}



	@Override
	public List findallCart(String UserId) {
		// TODO Auto-generated method stub
		
		
		return orderDao.findallCart(UserId);
	}



	@Override
	public List findlistnew(Integer userID, String orderCodes) {
		// TODO Auto-generated method stub
		return orderDao.findlistnew(userID,orderCodes);
	}
	
	@Override
	public List findlistnewWei(Integer userID, String orderCodes) {
		// TODO Auto-generated method stub
		return orderDao.findlistnewWei(userID,orderCodes);
	}

	
	@Override
	public List findReimburse(String orderCode) {
		// TODO Auto-generated method stub
		return orderDao.findReimburse(orderCode);
	}



	@Override
	public List getorderCodes(String WeixinOrderCode) {
		// TODO Auto-generated method stub
		return orderDao.getorderCodes(WeixinOrderCode);
	}



	@Override
	public List getorderAccepttype(String orderCodde) {
		// TODO Auto-generated method stub
		return orderDao.getorderAccepttype(orderCodde);
	}




	@Override
	public List getMoney(String orderCodde) {
		// TODO Auto-generated method stub
		return orderDao.getMoney(orderCodde);
	}




	@Override
	public List isChou(String openId, String SKU) {
		// TODO Auto-generated method stub
		return orderDao.isChou(openId, SKU);
	}




	@Override
	public List findZCAddress(String zccode) {
		// TODO Auto-generated method stub
		return orderDao.findZCAddress(zccode);
	}

	

	

}
