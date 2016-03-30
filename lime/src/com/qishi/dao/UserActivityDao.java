package com.qishi.dao;

import com.qishi.entity.BankTransaction;


public interface UserActivityDao extends BaseDao{
	//
		
		public void updateCustomers(int id);
		
		public void updatePay(BankTransaction bt);
		
}
