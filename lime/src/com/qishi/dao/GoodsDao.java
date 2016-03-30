package com.qishi.dao;

import java.util.List;

import com.qishi.entity.OrderCart;
import com.qishi.entity.ProductBasicInfo;
import com.qishi.entity.ProductClass;

public interface GoodsDao extends BaseDao<ProductBasicInfo> {
	
		List<ProductClass> findclass();
		List findgoods(String classid,String SkuFlag,String indexyingyang);
		List findgoodsku(String proskuid,String param1,String param2);
		List findgoodInfo(String proskuid);
		List indexbanner();
		List indexgoods(String ColumnId);
		List indexGetSku(String skuids);
		
		List LotteryGetSku(String skuid);
		
		List findclassid(String classid);
		
		List findHealthGood();
		List wxProSKUId(String proSKUId);

}
