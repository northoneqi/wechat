package com.qishi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qishi.dao.BaseDao;
import com.qishi.dao.OrderCartDao;
import com.qishi.entity.OrderCart;
import com.qishi.service.OrderCartService;
@Service("OrderCartService")
public class OrderCartServiceImpl extends BaseServiceImpl<OrderCart> implements OrderCartService {
	
	private OrderCartDao orderCartDao;
	@Autowired
	@Qualifier("OrderCartDao")
	@Override
	public void setBaseDao(BaseDao<OrderCart> orderCartDao) {
		this.baseDao = orderCartDao;
		this.orderCartDao = (OrderCartDao) orderCartDao;
		
	}
	@Override
	public List<OrderCart> findCartgoodby(String userId, String sku, String openid) {
		// TODO Auto-generated method stub
		List<OrderCart> ordercartlist =orderCartDao.findCartgoodbysku(userId, sku, openid);
		
		return ordercartlist;
	}
	@Override
	public List findcartgoodinfo(String userId, String openId) {
		// TODO Auto-generated method stub
		List goodinfolist = orderCartDao.findcartgoodinfo( userId, openId);
		return goodinfolist;
	}
	@Override
	public void deletecat(String userId, String openId, String sku) {
		// TODO Auto-generated method stub
		System.out.println("service"+userId+" "+openId+" "+sku);
		orderCartDao.deletecar(userId, openId, sku);
		
		
	}
	@Override
	public List findAllInfo(String UserId,String openId,String sku) {
		// TODO Auto-generated method stub
		List allinfolist = orderCartDao.findAllInfo(UserId,openId,sku);
		return allinfolist;
	}
	@Override
	public void savetoOrderDetail(String orderCode,String UserId,String openId,String SKU,String ProNum,String Unit,String SellPrice,String SKUName,String sub) {
		// TODO Auto-generated method stub
		orderCartDao.savetoOrderDetail(orderCode, UserId,openId,SKU, ProNum,Unit,SellPrice, SKUName,sub);
	}
	@Override
	public void savetoOrderAcceptLog(String orderCode,String time,String zhuangtai,String LogType) {
		// TODO Auto-generated method stub
		orderCartDao.savetoOrderAcceptLog(orderCode,time,zhuangtai,LogType);
	}
	@Override
	public void savetoOrderBasic(String UserId,String openId,String orderCode,String time,String orderClasszong) {
		// TODO Auto-generated method stub
		orderCartDao.savetoOrderBasic(UserId,openId,orderCode,time,orderClasszong);
	}
	@Override
	public void savetoOrderInvoice() {
		// TODO Auto-generated method stub
		orderCartDao.savetoOrderInvoice();
	}
	@Override
	public void deleteAll(String UserId,String openId) {
		// TODO Auto-generated method stub
		orderCartDao.deleteAll(UserId,openId);
	}
	@Override
	public void savetoOrderAccept(String orderCode,String time) {
		// TODO Auto-generated method stub
		orderCartDao.savetoOrderAccept(orderCode,time);
	}
	@Override
	public List findorderclass(String sku) {
		// TODO Auto-generated method stub
		
		List list = orderCartDao.findorderclass(sku);
		
		return list;
	}
	@Override
	public List findAllOrderClass(String UserId,String openId) {
		// TODO Auto-generated method stub
		List list = orderCartDao.findAllOrderClass( UserId,openId);
		return list;
	}
	@Override
	public void updateorder(String userId, String openId) {
		// TODO Auto-generated method stub
		orderCartDao.updatorder(userId,openId);
	}
	//立即支付
	@Override
	public List goPay(Integer sku) {
		// TODO Auto-generated method stub
		return orderCartDao.goPay(sku);
	}
	@Override
	public void saveweixinorder(String big, String danhaoto,String time) {
		// TODO Auto-generated method stub
		orderCartDao.saveweixinorder(big,danhaoto,time);
		
	}
	@Override
	public List finddowncartgoodinfo(String userId, String openId) {
		// TODO Auto-generated method stub
		return orderCartDao.finddowncartgoodinfo(userId, openId);
	}
	@Override
	public void savetoOrderBasichongbao(String userId, String openId,
			String orderCode, String time, String orderClasszong,String remark) {
		// TODO Auto-generated method stub
		orderCartDao.savetoOrderBasichongbao(userId,openId,orderCode,time,orderClasszong,remark);
	}
	@Override
	public void savetoOrderBasicZC(String UserId, String openId,
			String orderCode, String time, String orderClasszong, String payType) {
		// TODO Auto-generated method stub
		orderCartDao.savetoOrderBasicZC(UserId, openId, orderCode, time, orderClasszong, payType);
	}
	@Override
	public void saveweixinorderbox(String WeixinOrderId, String big,
			String dandanhao, String time) {
		orderCartDao.saveweixinorderbox(WeixinOrderId, big, dandanhao, time);
		
	}
	

	

	

	
}
