package com.qishi.dao;

import java.util.List;

import com.qishi.entity.OrderInCome;

public interface OrderInComeDao extends BaseDao<OrderInCome>{

	void savepeisong(String oConsignee, String address, String area,
			String oMbile, String riqi, String shijian, String orderCode,
			Integer userID,String OTelephone);

	void updateOrderinfo(String orderCode);

	List findincome(String ordercode);

}
