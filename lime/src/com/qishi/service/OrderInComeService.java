package com.qishi.service;

import java.util.List;

import com.qishi.entity.OrderInCome;

public interface OrderInComeService extends BaseService<OrderInCome>{
	//保存配送信息
	void savepeisong(String oConsignee, String address, String area,
			String oMbile, String riqi, String shijian, String orderCodes,
			Integer userID,String OTelephone);
	void updateOrderinfo(String orderCode);
	
	List findincome(String ordercode);



}
