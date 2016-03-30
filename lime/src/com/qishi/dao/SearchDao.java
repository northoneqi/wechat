package com.qishi.dao;

import java.util.List;

import com.qishi.entity.ProductBasicInfo;

public interface SearchDao {
	/**
	 * 查找商品
	 * @param goodName
	 * @return
	 */
	public List searchGoods(String goodName);
	/**
	 * 
	 * @param sku
	 */
	public List searchbysku(String sku);
}
