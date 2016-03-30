package com.qishi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qishi.dao.BaseDao;
import com.qishi.dao.UserActivityDao;
import com.qishi.entity.BankTransaction;
import com.qishi.service.UserActivityService;

@Service("UserActivityService")
public class UserActivityServiceImpl extends BaseServiceImpl<BankTransaction> implements UserActivityService{

	@Autowired
	@Qualifier("UserActivityDao")
	private UserActivityDao userAcitvityDao;
	
	
	
	@Override
	public void setBaseDao(BaseDao<BankTransaction> userAcitvityDao) {
		this.baseDao = userAcitvityDao;
		this.userAcitvityDao = (UserActivityDao) userAcitvityDao;
	}
	
	@Override
	public void updateCustomers(int id) {
		// TODO Auto-generated method stub
		userAcitvityDao.updateCustomers(id);
	}

	@Override
	public void updatePay(BankTransaction bt) {
		userAcitvityDao.updatePay(bt);
	}



	
}
