package com.qishi.service;

import java.util.Date;
import java.util.List;

import com.qishi.entity.T_CrowdFunding_BackInfo_two;
import com.qishi.entity.T_CrowdFunding_PayInfo_two;
import com.qishi.entity.ZC_OrderBasic;
import com.qishi.entity.ZC_PayInfo;


public interface ZcPayOrderService extends BaseService<T_CrowdFunding_PayInfo_two>{

	/**
	 * 根据众筹号查找数据
	 * @param zccode
	 * @return 1可支付  0不可支付
	 */
	public String lockOrderBasicState(String zccode,String openId,String state);

	/**
	 * 根据众筹号查找商品，获取支付金额
	 * @param zccode
	 * @return
	 */
	public double checkProInfo(String zccode);

	/**
	 * 更新支付状态 ispayment 1可支付   0 不可支付
	 * @param zccode 众筹号
	 */
	public void updateIsPayMent(String state,String zccode,Long str);
	
	/**
	 * 判断微信支付完成有无保存
	 */
	public List findOutTradeNo(String Ordercode);

	/**
	 * 判断是否完成众筹
	 * @param zccode
	 * @return
	 */
	public int checkOrderBasic(String zccode);

	/**
	 * 完成众筹，更改状态
	 * @param zccode
	 */
	public void upOrderBasicSt(String zccode);

	/**
	 * 查找商品信息
	 * @param zccode
	 * @return
	 */
	public List findProInfo(String zccode);
	/**
	 * 查找订单信息
	 * @param zccode
	 * @return
	 */
	public List findOrderBasic(String zccode);
	
	/**
	 * 保存支付信息
	 */
	public void savaPayInfo(T_CrowdFunding_PayInfo_two payInfo);

	/**
	 * 完成剩余金额时应付金额
	 * @param zc
	 * @return
	 */
	public double checkPrice(String zc);
	/**
	 * 根据openid查询支付记录
	 * @param openid
	 * @return zc_payinfo记录条数
	 */
	public int findpaybyopenid(String openid,String zccode);

	/**
	 * 查询活动信息
	 * @param activity_ID
	 * @return
	 */
	public T_CrowdFunding_BackInfo_two findBackInfo(Long activity_ID);
	
	
}
