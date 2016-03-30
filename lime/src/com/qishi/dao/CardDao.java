package com.qishi.dao;

import java.util.List;







import com.qishi.entity.ShopCardNo;
import com.qishi.entity.ShopCardOrderDetail;


public interface CardDao extends BaseDao<ShopCardOrderDetail> {
	//用户列表
	public List<ShopCardOrderDetail> allList(int userId,String CardNo);
	//保存卡号和密码
	public List saveCard(int cardOk,int userId ,String cardName);
	//查询数据库有无卡号
	public List selectCard(String cardName,String password);
	//查询用户输入的卡号和密码有没有被绑定
	public List selectCardIn(String cardName);
	//查询骑士卡是否还剩余额
	public List selectCardMoney(String cardName);
	//查询骑士卡总信息
	public List<ShopCardOrderDetail> allistinfo(Integer userID);
	//查询骑士卡总信息（包含已冻结状态）
	public List<ShopCardOrderDetail> allistinfoall(Integer userID);
	//查询骑士卡
	public List<ShopCardOrderDetail> allCardList(int userId,String CardNo);		
	//查询红包卡
	public List allHongCardList(int userId);
}
