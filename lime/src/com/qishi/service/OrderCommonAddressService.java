package com.qishi.service;

import java.util.List;

import com.qishi.entity.OrderCommonAddress;
import com.qishi.entity.User;
import com.qishi.entity.UserBasic;
import com.qishi.util.Pager;

public interface OrderCommonAddressService extends BaseService<OrderCommonAddress>{
	
	/**
	 * 根据列名查数据
	 * @param ziduan
	 * @param liezhi
	 * @return
	 */
	public List<OrderCommonAddress> findbyleiming(String ziduan, String liezhi);

	/**
	 * 删除
	 * @param ziduan
	 * @param liezhi
	 * @return
	 */
	public void delete(OrderCommonAddress order);

	/**
	 * 根据userid查找地址
	 * @param userId
	 * @return
	 */
	public List findAddress(int userId);
	/**
	 * 设置默认收货地址
	 * @param userId
	 * @param addressId
	 */
	public void defaultAddress(int userId, String addressId);
	/**
	 * 新增收货地址时，取消当前默认。
	 * @param userId
	 */
	public void defSetAddress(int userId);

	/**
	 * 获取默认收货地址
	 * @param userId
	 * @param defAddess
	 * @return
	 */
	public List getDefAddess(String userId, String defAddess);

	/**
	 * 联合查询地址
	 * @param oAddressId
	 * @return
	 */
	public List findSetAddress(String oAddressId);
}
