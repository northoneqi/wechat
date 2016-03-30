package com.qishi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.qishi.dao.BaseDao;
import com.qishi.dao.ZcPayOrderDao;
import com.qishi.entity.T_CrowdFunding_BackInfo_two;
import com.qishi.entity.T_CrowdFunding_OrderBasic_two;
import com.qishi.entity.T_CrowdFunding_PayInfo_two;
import com.qishi.entity.ZC_OrderBasic;
import com.qishi.entity.ZC_PayInfo;
import com.qishi.entity.ZC_ProductInfo;
import com.qishi.service.ZcPayOrderService;

@Service("zcPayOrderService")
public class ZcPayOrderServiceImpl extends BaseServiceImpl<T_CrowdFunding_PayInfo_two> implements
		ZcPayOrderService {

	
	
	@Autowired
	@Qualifier("ZcPayOrderDao")
	private ZcPayOrderDao zcPayOrderDao;

	@Override
	public void setBaseDao(BaseDao<T_CrowdFunding_PayInfo_two> ZcPayOrderDao) {
		// TODO Auto-generated method stub
		this.baseDao = ZcPayOrderDao;
		this.zcPayOrderDao = (ZcPayOrderDao) zcPayOrderDao;

	}

	/**
	 *  1可支付 0不可支付 
	 */
	@Override
	public String lockOrderBasicState(String zccode,String openId,String state) {
		System.out.println(state);
		List<T_CrowdFunding_OrderBasic_two> list = zcPayOrderDao.findOrderLock(zccode);
		
		
		if(list.size() == 0){
			System.out.println("list siZe");
			return "0";
		}
		Long isPayMent = list.get(0).getIS_PAY();//1可支付。 0不支付
		System.out.println(isPayMent+"100");
		Long orderStatus = list.get(0).getORDER_STATUS(); //状态 0进行中 1已完成 2已取消
		System.out.println(orderStatus+"--");
		if(1==orderStatus){ //已完成不可支付
			return "0";
		}else if(2 == orderStatus){ //已取消不可支付
			return "0";
		}else if(0 == orderStatus){//进行中 isPayMent 0不可支付    1可支付
			System.out.println("status 0");
			if("0".equals(state)){
				if(openId.equals(list.get(0).getOPEN_ID())){
					return "3";
				}
			}else if(1 == isPayMent){
				System.out.println("ispayMent1");
				Date date = new Date();
				long time = date.getTime()+1000*60*4;
//				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
//				date = new Date(time);
//				String str = sdf.format(date);
//				System.out.println(str);
				zcPayOrderDao.updateIsPayMent("0",zccode,time);
				return "1";
			}else if(0 == isPayMent){
				System.out.println("ispayMent0");
				return "0";
			}
		}
		System.out.println("---");
		return "0";
}

	@Override
	public double checkProInfo(String zccode) {
		List<T_CrowdFunding_PayInfo_two> list = zcPayOrderDao.findProInfo(zccode);
		List<T_CrowdFunding_OrderBasic_two> order = zcPayOrderDao.findOrderBasic(zccode);
		if(order.size()==0){
			
		}
		double goodp = order.get(0).getGOOD_PRICE();
		double money = goodp/ order.get(0).getPLAY_NUM();
		System.out.println(money);
		return money;
	}

	@Override
	public void updateIsPayMent(String state,String zccode,Long str) {
		zcPayOrderDao.updateIsPayMent(state, zccode,str);
	}
	
	
	@Override
	public List findOutTradeNo(String Ordercode) {
		return zcPayOrderDao.findOutTradeNo(Ordercode);
	}

	@Override
	public int checkOrderBasic(String zccode) {
		List<T_CrowdFunding_OrderBasic_two> list = zcPayOrderDao.findOrderBasic(zccode);
		List<T_CrowdFunding_PayInfo_two> payOrder =  zcPayOrderDao.findPayInfo(zccode);
		//System.out.println(payOrder.size()+"size"+list.get(0).getCopies());
		if(list == null || list.size() == 0 ) {
			System.out.println("-1");
			return -1;
		}
		//支付份数以够完成众筹
		if(list.get(0).getPLAY_NUM() <= payOrder.size()){
			System.out.println("1");
			return 1;
		}
		
		//份数不够，钱够完成众筹
		double goodprice = list.get(0).getGOOD_PRICE();
		int payPr=0;
		for(T_CrowdFunding_PayInfo_two payInfo:payOrder){
			payPr += payInfo.getTOTAL_FEE();
		}
		double payPrice = payPr/100.00;
		System.out.println(payPrice+"service");
		if(Double.doubleToLongBits(goodprice) == Double.doubleToLongBits(payPrice)){
			System.out.println("11");
			return 1;
		}
		return -1;
	}

	@Override
	public void upOrderBasicSt(String zccode) {
		zcPayOrderDao.upOrderBasicSt(zccode);
	}

	@Override
	public List findProInfo(String zccode) {

		return zcPayOrderDao.findProInfo(zccode);
	}

	@Override
	public List findOrderBasic(String zccode) {

		return zcPayOrderDao.findOrderBasic(zccode);
	}

	@Override
	public void savaPayInfo(T_CrowdFunding_PayInfo_two payInfo) {
		zcPayOrderDao.savaPayInfo(payInfo);
	}

	@Override
	public double checkPrice(String zc) {
		List<T_CrowdFunding_PayInfo_two> list = zcPayOrderDao.findPayInfo(zc);
		double payPrice  = 0.;
		for(T_CrowdFunding_PayInfo_two payInfo:list){
			payPrice += payInfo.getPAY_MONEY();
		}
		List<T_CrowdFunding_OrderBasic_two> order = zcPayOrderDao.findOrderBasic(zc);
		double price =  order.get(0).getGOOD_PRICE()-payPrice;
		return price;
	}

	@Override
	public int findpaybyopenid(String openid,String zccode) {
		
		int size = zcPayOrderDao.findpaybyopenid(openid,zccode);
		return size;
	}

	@Override
	public T_CrowdFunding_BackInfo_two findBackInfo(Long activity_ID) {
		return zcPayOrderDao.findBackInfo(activity_ID);
	}


}
