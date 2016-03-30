package com.qishi.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.qishi.dao.UserActivityDao;
import com.qishi.entity.BankTransaction;

@Repository("UserActivityDao")
public class UserActivityDaoImpl  extends BaseDaoImpl implements UserActivityDao{

	@Override
	public void updateCustomers(int id) {
		String sql = "update T_Customers set IsPay = 1 where CustomerID ="+id;
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updatePay(BankTransaction bt) {
		String sql = "update T_BankTransaction set OrderMoney ="+bt.getOrderMoney()
		+ ", BankType ="+bt.getBankType()
		+ ", TransactionID ="+bt.getTransactionID()
		+ ", Status = 1 "
		+ " where OrderID = '"+bt.getOrderID()+"'" ;
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

}
