package com.qishi.dao;

import java.util.List;

import com.qishi.entity.OrderBasic;
import com.qishi.entity.OrderInCome;


public interface OrderDao extends BaseDao<OrderInCome> {
	//待付款
	//public List<OrderInCome> findlist(String userId,String code);
	public List findlist(Integer userId,String code,String orderCodes);
	//订单的详情
	List findOrderDetail(String code);
	
	//修改订单状态
	public void updateAccept(String code);
	//结算金额汇总
	String payOrderSum(String orderCode);
	//修改订单的付款方式
	int updatePayType(int PayType,String orderCode);
	
	// 修改盒子订单的下单状态
	int updatePeisongStatus(int peisongStatus, String openId);
	//查询卡号
	public List findallCart(String userId);
	//根据大单号查询总价
	public List findlistnew(Integer userID, String orderCodes);
	//根据小单号查询总价
	public List findlistnewWei(Integer userID, String orderCodes);
	//根据订单号查询退款所需要的属性
	public List findReimburse(String orderCode);
	
	//根据总订单号查询子订单号
	public List getorderCodes(String WeixinOrderCode);
	//根据订单号查询订单状态
	public List getorderAccepttype(String orderCodde);
	
	public List getMoney(String orderCodde);
	
	//盒子订单配送地址详细
	List findOrderAddress(String openId,String activityId);
	
	//盒子是否生成订单
	List isChou(String openId,String SKU);
	//众筹订单配送地址详细
	List findZCAddress(String zccode);
}
