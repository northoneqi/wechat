package com.qishi.service;

import java.util.List;

import com.qishi.entity.ProductBasicInfo;

public interface SearchService {
	/**
	 * 查找商品
	 * @param goodName 搜索字段
	 * @return
	 */
	public List search(String goodName);
	/**
	 * 查询商品是否下架
	 * @param sku
	 * @return
	 */
	public int searchbysku(String sku);
}
