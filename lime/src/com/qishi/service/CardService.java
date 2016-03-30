package com.qishi.service;

import java.util.List;










//import com.qishi.entity.ShopCardNo;
import com.qishi.entity.ShopCardOrderDetail;
/**
 * 
 * @author 骑士卡service
 *
 */

public interface CardService extends BaseService<ShopCardOrderDetail> {
	public List<ShopCardOrderDetail> allList(int userId,String CardNo);
	
	public List<ShopCardOrderDetail> allCardList(int userId,String CardNo);		//查询骑士卡列表

	public int saveCard(String cardName, String password ,int userId);			//保存用户骑士卡

	public List<ShopCardOrderDetail> allListinfo(Integer userID);

	public List<ShopCardOrderDetail> allListinfoall(Integer userID);
	//查询红包卡
	public List allHongCardList(int userId);
}
