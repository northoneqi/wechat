package com.qishi.service;

import java.util.List;

import com.qishi.entity.OrderInCome;

public interface OrderService extends BaseService<OrderInCome> {
	// 订单列表
	public List findlist(Integer userId, String code, String orderCodes);

	// 订单详情
	List findOrderDetail(String code);

	// 修改订单
	public void updateAccept(String code);

	// 结算金额汇总
	String payOrderSum(String orderCode);

	// 修改订单的付款方式
	int updatePayType(int PayType, String orderCode);
	
	// 修改盒子订单的下单状态
	int updatePeisongStatus(int peisongStatus, String openId);

	// 查询骑士卡
	public List findallCart(String string);

	// 根据单号查询总价
	public List findlistnew(Integer userID, String orderCodes);
	
	// 根据单号查询总价
	public List findlistnewWei(Integer userID, String orderCodes);
	
	//根据订单号查询退款所需要的属性
	public List findReimburse(String orderCode);
	
	//根据总订单号查询子订单号
	public List getorderCodes(String WeixinOrderCode);
	//根据订单号查询订单状态
	public List getorderAccepttype(String orderCodde);
	//根据订单号查询已付金额，到付金额
	public List getMoney(String orderCodde);
	
	//盒子订单配送地址详细
	List findOrderAddress(String openId,String activityId);
	
	//盒子是否生成订单
	List isChou(String openId,String SKU);
	
	//众筹订单配送地址详细
	List findZCAddress(String zccode);
}
