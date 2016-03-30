package com.qishi.service;

import java.util.List;

import com.qishi.entity.WeixinPayInfo;

public interface WeixinPayInfoService extends BaseService<WeixinPayInfo>{
	/**
	 * 判断微信支付完成有无保存
	 */
	public List findOutTradeNo(String Ordercode);
}
