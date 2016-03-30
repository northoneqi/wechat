package com.qishi.dao;

import java.util.List;



import com.qishi.entity.WeixinPayInfo;

public interface WeixinPayInfoDao extends BaseDao<WeixinPayInfo> {
	/**
	 * 判断微信支付完成有无保存
	 */
	public List findOutTradeNo(String Ordercode);
}
