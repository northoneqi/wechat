package com.qishi.service;

import java.util.List;

import com.qishi.entity.JoinIn;
import com.qishi.entity.LuckyPrize;
import com.qishi.entity.Prize;
import com.qishi.entity.UserBasic;


public interface UserLotteryService extends BaseService<JoinIn>{
	public String sweepstaks(String OpenId,String ActivityId,int prizeCount);


	// 根据id查找奖品信息
	public List<Prize> findPrizeInfoById(int prizeId);
	//验证是否为商城用户
	List checkuser(String openid);
	
	public int findLuckyPrize(String openId,String ActivityId);

	//新增抽奖信息表
	public void saveLucyPrice(String activityId, String openid,int isPriceze );
	
	//查询用户信息
	public int SelectUserPhone(String openId);

	//查询source数量
	public List finsouce(String source);
	
	//保存抽中的骑士卡id
	public void savaPrizeCardNo(String openId);

	//查询用户是否第一次登陆
	public List finduser(String openid,String ActivityId);

	//更新主表source信息系
	public void updatesource(String source);
	
	//查询用户URL被转发
	public String selectUrl(String openId);
	
	//用户生成的链接被分享了几次
	public int urlShore(String openId);
	
	//用户已抽取奖项次数

	
	//用户离下次送奖品需要的分享次数
	public int noPrizeNum(String openId);

	//查询用户未绑定的骑士卡
	public List findyijika(String openId);

	//更改用户状态为已关注
	public void updateLucyPrice(String openid);

	//查询是否抽奖
	public List checkisprice(String openId);

	
	public List tuijianXml(String fileName);
	
	//查询正在进行的活动
	public List findActivity();

	
	public void updatePrizeCardState(int prizeId);
	
	//根据用户查询奖品
	public List findPrizeInfoByopenId(String openid,String ActivityNo);

	//
	public List checkcardstate(String openid);
	}


