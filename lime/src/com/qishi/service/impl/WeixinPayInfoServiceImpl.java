package com.qishi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qishi.dao.BaseDao;
import com.qishi.dao.WeixinPayInfoDao;
import com.qishi.entity.WeixinPayInfo;
import com.qishi.service.WeixinPayInfoService;
@Service("WeixinPayInfoService")
public class WeixinPayInfoServiceImpl extends BaseServiceImpl<WeixinPayInfo> implements WeixinPayInfoService {
	private WeixinPayInfoDao weixinPayInfoDao;
	@Autowired
	@Qualifier("WeixinPayInfoDao")
	@Override
	public void setBaseDao(BaseDao<WeixinPayInfo> weixinPayInfoDao) {
		this.baseDao = weixinPayInfoDao;
		this.weixinPayInfoDao = (WeixinPayInfoDao) weixinPayInfoDao;
		
	}
	@Override
	public List findOutTradeNo(String Ordercode) {
		// TODO Auto-generated method stub
		return weixinPayInfoDao.findOutTradeNo(Ordercode);
	}

}
