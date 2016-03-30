package com.qishi.dao;

import java.util.Date;
import java.util.List;

import com.qishi.entity.T_CrowdFunding_BackInfo_two;

import com.qishi.entity.T_CrowdFunding_OrderBasic_two;
import com.qishi.entity.T_CrowdFunding_PayInfo_two;
import com.qishi.entity.ZC_OrderBasic;
import com.qishi.entity.ZC_PayInfo;

public interface ZcPayOrderDao extends BaseDao{

	/**
	 * 查找订单
	 * @param zccode
	 * @return
	 */
	List findOrderBasic(String zccode);

	/**
	 * 查找订单信息，枷锁
	 * @param zccode
	 * @return
	 */
	public List<T_CrowdFunding_OrderBasic_two> findOrderLock(String zccode);
	
	
	/**
	 * 根据众筹单号查找商品信息
	 * @param zccode
	 * @return
	 */
	List findProInfo(String zccode);

	/**
	 * 更新支付状态 ispayment 1 可支付 0不可支付
	 * @param zccode 众筹号
	 */
	void updateIsPayMent(String state,String zccode,Long str);
	
	
	/**
	 * 判断微信支付完成有无保存
	 */
	public List findOutTradeNo(String Ordercode);

	/**
	 * 根据众筹单号查找订单信息
	 * @param zccode
	 * @return
	 */
	List findPayInfo(String zccode);

	/**
	 * 更新订单状态
	 * @param zccode
	 */
	void upOrderBasicSt(String zccode);

	/**
	 * 保存支付信息
	 * @param payInfo
	 */
	void savaPayInfo(T_CrowdFunding_PayInfo_two payInfo);

	public void taskIsPayMent();
	/**
	 * 根据openid查找支付信息
	 * @param payInfo
	 */
	int findpaybyopenid(String openid,String zccode);
	
	/**
	 * 查找活动信息
	 * @param activity_ID
	 * @return
	 */
	public T_CrowdFunding_BackInfo_two findBackInfo(Long activity_ID);
}
