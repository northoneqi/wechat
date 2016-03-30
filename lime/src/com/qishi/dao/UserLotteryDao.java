package com.qishi.dao;

import java.util.List;

import com.qishi.entity.JoinIn;
import com.qishi.entity.LuckyPrize;
import com.qishi.entity.Prize;

public interface UserLotteryDao extends BaseDao<JoinIn> {
	// 获取奖品信息
	public List findPrizeInfo(String ActivityId);

	// 修改奖品数量
	public void setPrizeNote(int Id,int prizeId);
	
	//验证是否为商城用户
	public List checkuser(String openid);

	// 根据id查找奖品信息
	public List<Prize> findPrizeInfoById(int prizeId);

	public List<LuckyPrize> findLuckyPrize(String openId,String ActivityId);
	
	//查询用户有无电话信息
	public List SelectUser(String openId);
	//保存抽奖信息
	public void saveLucyPrice(String activityId, String openid, int isPriceze);
	//查询source数量
	public List findsouce(String source);
	//查询用户是否为第一次登陆
	public List finduser(String openid,String ActivityId);

	public void updatesource(String source);
		//查询获奖用户产生的URL
	public List selectUrl(String openId);
	
	//用户生成的链接被分享了几次
	public List urlShoreNum(String openId);
	
//	//用户已抽取奖项次数
//	public List prizeNumber(String openId);
//	//用户还没抽取奖项次数
//	public List prizeRemain(String openId);
	
	//更改奖品表中骑士卡的状态
	public void updatePrizeCardState(int prizeId);

	//查询未绑定的骑士卡
	public List findyijiaka(String openId);
	//更改用户状态为已关注
	public void updateLucyPrice(String openid);
	//查询骑士有无被绑定
	public List selectCard(String cardNo);
	//查询是否抽奖
	public List checkisprice(String openId);
	
	//查询正在进行的活动
	public List findActivity();
	
	//返回当前日期的抽奖数
	public int findPrizeSize();
	//
	public List findPrizeinfobuopenid(String openid,String ActivityNo);

	public List checkcardstate(String openid); 
	
	//查询openId和卡号对应信息
	public List selectopencard(String openid);		
	
	
}
