package com.qishi.service;




import com.qishi.entity.BankTransaction;



public interface UserActivityService extends BaseService<BankTransaction>{
	
	public void updateCustomers(int id);
	
	public void updatePay(BankTransaction bt);
	
}
