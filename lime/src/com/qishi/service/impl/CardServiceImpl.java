package com.qishi.service.impl;



import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qishi.dao.BaseDao;
import com.qishi.dao.CardDao;
import com.qishi.entity.ShopCardNo;
import com.qishi.entity.ShopCardOrderDetail;
import com.qishi.service.CardService;
import com.qishi.util.EncryptUtil;

/**
 * 
 * @author 卡dao
 *
 */

@Service("CardService")
public class CardServiceImpl extends BaseServiceImpl<ShopCardOrderDetail> implements CardService {
	
	private CardDao carddao;
	
	@Autowired
	@Qualifier("CardDao")
	@Override
	
	public void setBaseDao(BaseDao<ShopCardOrderDetail> carddao) {
		// TODO Auto-generated method stub
		this.baseDao = carddao;
		this.carddao = (CardDao) carddao;
	}
	@Override
	public List<ShopCardOrderDetail> allList(int userId,String CardNo) {
	
		List<ShopCardOrderDetail> list=carddao.allList(userId,CardNo);
		return list ;
	}
	@SuppressWarnings("unused")
	@Override
	public int saveCard(String cardName, String password, int userId) {

		String cardNo=cardName.toUpperCase();
		EncryptUtil encryptUtil = new EncryptUtil(); // MD5加密
		String hashType = EncryptUtil.HashType_MD5;

		String pwdMd5 = null;
		try {

			pwdMd5 = EncryptUtil.getHashString(password, hashType);
		} catch (Exception e) {
			e.printStackTrace(); 
		}

		@SuppressWarnings("rawtypes")
		List list = carddao.selectCard(cardNo, pwdMd5);	//判断骑士卡状态			
		int noCard=0;
		if (list!= null && list.size()!= 0) {
			@SuppressWarnings("rawtypes")
			Map map = (Map) list.get(0);
			if (map.get("CardPwd").equals(pwdMd5)) {												
				List listIn = carddao.selectCardIn(cardNo);	//查询有没被绑定
				if (listIn == null || listIn.size() == 0) {
					List listMoney = carddao.selectCardMoney(cardNo);
					Map mapMoney = (Map) listMoney.get(0);
					double money = (double) mapMoney.get("Balance");
					if (money > 0.0) {						//判断金额是否大于0.0元
						noCard = 1;							//以上条件都满足为1
						carddao.saveCard(noCard, userId, cardNo);
					} else {
						noCard = 2;							//卡金额不足
					}
				}else{
					noCard =3;								//骑士卡已被绑定
				}
			} else {
				noCard = 0;									//卡号或密码错误
			}
		} 
		return noCard;

	}
	@Override
	public List<ShopCardOrderDetail> allListinfo(Integer userID) {
		// TODO Auto-generated method stub
		return carddao.allistinfo(userID);
	}

	@Override
	public List<ShopCardOrderDetail> allListinfoall(Integer userID) {
		// TODO Auto-generated method stub
		return carddao.allistinfoall(userID);
	}
	@Override
	public List<ShopCardOrderDetail> allCardList(int userId, String CardNo) {		//查询用户骑士卡列表
		return carddao.allCardList(userId,CardNo);
	}


	@Override
	public List allHongCardList(int userId) {
		// TODO Auto-generated method stub
		return carddao.allHongCardList(userId);
	}
	

	
}
