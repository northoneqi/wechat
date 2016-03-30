package com.qishi.service;

import java.util.List;

import com.qishi.entity.OrderCart;
/**
 * 
 * @author chenghao
 *
 */

public interface OrderCartService extends BaseService<OrderCart>{
	//根据openId和sku查询用户购物车内的商品
	List<OrderCart> findCartgoodby(String userId,String sku,String openid);
	//根据openId或userId查询用户购物车内的商品
	List findcartgoodinfo(String userId,String openId);
	//清空购物车
	void deletecat(String userId,String openId,String sku);
	//保存订单详细T_orderDetail
	void savetoOrderDetail(String orderCode,String UserId,String openId,String SKU,String ProNum,String Unit,String SellPrice,String SKUName,String sub);
	//保存订单OrderAcceptLog
	void savetoOrderAcceptLog(String orderCode,String time,String zhuangtai,String LogType);
	//保存订单总信息
	void savetoOrderBasic(String UserId,String openId,String orderCode,String time,String orderClasszong);
	//保存订单OrderInvoice
	void savetoOrderInvoice();
	//清空购物车所有信息
	void deleteAll(String UserId,String openId);
	//保存订单OrderAccept
	void savetoOrderAccept(String orderCode,String time);
	//查询购物车所有信息
	List findAllInfo(String userId,String openId,String sku);
	//查询订单分单信息
	List findorderclass(String sku);
	//查询用户分单信息
	List findAllOrderClass(String UserId,String openId);
	//更新订单信息（将userId添加到对应的openId对应的记录中）
	void updateorder(String userId, String openId);
	//立即支付
	List goPay(Integer sku);
	//微信订单号
	void saveweixinorder(String big, String danhaoto,String time);
	
	//保存微信活动（盒子）订单号
	void saveweixinorderbox(String WeixinOrderId,String big,String dandanhao,String time);
	//查询下架商品
	List finddowncartgoodinfo(String userId, String openId);
	//保存订单总信息(红包)
	void savetoOrderBasichongbao(String userId, String openId,
	String orderCode, String time, String orderClasszong,String remark);
	//保存众筹订单总表信息
	void savetoOrderBasicZC(String UserId,String openId,String orderCode,String time,String orderClasszong,String payType);
}
