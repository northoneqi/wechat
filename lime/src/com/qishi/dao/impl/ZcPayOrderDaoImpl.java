package com.qishi.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.qishi.entity.T_CrowdFunding_BackInfo_two;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.qishi.dao.ZcPayOrderDao;
import com.qishi.entity.T_CrowdFunding_OrderBasic_two;
import com.qishi.entity.T_CrowdFunding_PayInfo_two;
import com.qishi.entity.T_CrowdFunding_ProductInfo_two;
import com.qishi.entity.ZC_OrderBasic;
import com.qishi.entity.ZC_PayInfo;
import com.qishi.entity.ZC_ProductInfo;

@Repository("ZcPayOrderDao")
public class ZcPayOrderDaoImpl extends BaseDaoImpl implements ZcPayOrderDao{

	@Override
	public List<T_CrowdFunding_OrderBasic_two> findOrderBasic(String zccode) {
		String sql = "select * from T_CrowdFunding_OrderBasic_two where CROWDFUNDING_CODE='"+zccode+"'";
		Query query = getSession().createSQLQuery(sql.toString());
		return getSession().createSQLQuery(sql.toString()).addEntity(T_CrowdFunding_OrderBasic_two.class).list();
	}
	
	@Override
	public List<T_CrowdFunding_OrderBasic_two> findOrderLock(String zccode) {
		String sql = "select * from T_CrowdFunding_OrderBasic_two(UPDLOCK) where CROWDFUNDING_CODE='"+zccode+"'";
		Query query = getSession().createSQLQuery(sql.toString());
		return getSession().createSQLQuery(sql.toString()).addEntity(T_CrowdFunding_OrderBasic_two.class).list();
	}

	@Override
	public List<T_CrowdFunding_PayInfo_two> findProInfo(String zccode) {
		String sql = "SELECT * from T_CrowdFunding_ProductInfo_two "
				+ " where sku = (select sku from T_CrowdFunding_OrderBasic_two "
				+ "where CROWDFUNDING_CODE = '"+zccode+"' )";
				return getSession().createSQLQuery(sql.toString()).addEntity(T_CrowdFunding_ProductInfo_two.class).list();
	}

	@Override
	public void updateIsPayMent(String state,String zccode,Long date) {
		//1可支付  0不可支付
		// TODO Auto-generated method stub
		String sql = " update T_CrowdFunding_OrderBasic_two set IS_PAY="+state+", PAYMENT_TIME='"+date+"' where CROWDFUNDING_CODE='"+zccode+"'";
		Query query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	
	
	@Override
	public List findOutTradeNo(String Ordercode) {
			String sql="select * from T_CrowdFunding_PayInfo_two where OUT_TRADE_NO='"+Ordercode+"'";
			Query query = getSession().createSQLQuery(sql.toString());
			List list = query.setResultTransformer(
					CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
			return list;
			
		}

	@Override
	public List<T_CrowdFunding_PayInfo_two> findPayInfo(String zccode) {
		String sql = "select * from T_CrowdFunding_PayInfo_two where CROWDFUNDING_CODE ='"+zccode+"'";
		return getSession().createSQLQuery(sql).addEntity(T_CrowdFunding_PayInfo_two.class).list();
	}

	@Override
	public void upOrderBasicSt(String zccode) {
		String sql = " update T_CrowdFunding_OrderBasic_two set ispayment=0 , isfinish = 1 where CROWDFUNDING_CODE='"+zccode+"'";
		Query query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void savaPayInfo(T_CrowdFunding_PayInfo_two payInfo) {
		getSession().save(payInfo);
	}

	@Override
	public void taskIsPayMent() {
//		String sql = "update T_CrowdFunding_OrderBasic_two set ispayment = 1 where isfinish = 0 and iscancel = 0";
//		Query query = getSession().createSQLQuery(sql);
//		query.executeUpdate();
	}

	@Override
	public int findpaybyopenid(String openid,String zccode) {
		String sql="select * from T_CrowdFunding_PayInfo_two where PAY_OPEN_ID='"+openid+"' and CROWDFUNDING_CODE= '"+zccode+"'";
		Query query = getSession().createSQLQuery(sql.toString());
		List list = query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list.size();
	}
	
	@Override
	public T_CrowdFunding_BackInfo_two findBackInfo(Long activity_ID) {
		String sql = "select * from T_CrowdFunding_BackInfo_two where ACTIVITY_ID="+activity_ID;
		T_CrowdFunding_BackInfo_two backInfo = null;
		Query query = getSession().createSQLQuery(sql).addEntity(T_CrowdFunding_BackInfo_two.class);
		List<T_CrowdFunding_BackInfo_two> orderList = query.list();
		Iterator it = orderList.iterator();
		if (it.hasNext()) {
			backInfo = (T_CrowdFunding_BackInfo_two) it.next();
		}
		return backInfo;
	}
	
}
