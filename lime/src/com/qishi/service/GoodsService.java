package com.qishi.service;

import java.util.List;

import com.qishi.entity.ProductClass;

public interface GoodsService {

	
	
	List<ProductClass> findgoodclass();

	List findgoodclassid(String classid);
	
	List findgoods(String classid,String SkuFlag,String indexyingyang);
	
	List indexbanner();
	
	List findgoodsku(String proskuid,String param1,String param2);
	
	List findgoodInfo(String proskuid);

	String indexgoods(String ColumnId);
	
	List indexGetSku(String skuids);
	//查找营养套餐
	List findHealthGood();


}
