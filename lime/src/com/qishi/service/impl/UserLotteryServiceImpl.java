package com.qishi.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qishi.dao.BaseDao;
import com.qishi.dao.CardDao;
import com.qishi.dao.GoodsDao;
import com.qishi.dao.UserLotteryDao;
import com.qishi.entity.JoinIn;
import com.qishi.entity.LuckyPrize;
import com.qishi.entity.Prize;
import com.qishi.entity.ProductBasicSkuInfo;
import com.qishi.entity.UserBasic;
import com.qishi.service.UserLotteryService;

@Service("UserLotteryService")
public class UserLotteryServiceImpl extends BaseServiceImpl<JoinIn> implements
		UserLotteryService {
	private static final Object Url = null;

	@Autowired
	private GoodsDao gooddao;

	@Autowired
	@Qualifier("CardDao")
	private CardDao cardDao;

	public UserLotteryDao userLotteryDao;

	@Autowired
	@Qualifier("UserLotteryDao")
	@Override
	public void setBaseDao(BaseDao<JoinIn> userLotteryDao) {
		// TODO Auto-generated method stub
		this.baseDao = userLotteryDao;
		this.userLotteryDao = (UserLotteryDao) userLotteryDao;

	}

	@Override
	public List checkuser(String openid) {			//验证是否为商城用户
		// TODO Auto-generated method stub
		return userLotteryDao.checkuser(openid);
	}

	@Transactional
	@Override
	public String sweepstaks(String openId, String ActivityId, int prizeCount) {
		// 判断每天抽奖的次数 2--每天抽奖次数已满
		int count = userLotteryDao.findPrizeSize();
		if (count >= prizeCount) {
			return "a";
		}
		// System.out.println("-----");
		// 获取所有未被抽走的奖品
		List<Prize> list = userLotteryDao.findPrizeInfo(ActivityId);
		// System.out.println(list.get(0).getPrizeId());
		System.out.println("sizesizesizesizesizesize" + list.size());
		if (list.size() == 0) {
			return "0";
			// 抽奖逻辑
		} else {
			List prize = new ArrayList<Prize>();
			for (Prize l : list) {
				prize.add(l.getPrizeId());
			}
			// 打乱顺序
			Collections.shuffle(prize);
			int randomStep = new Random().nextInt(prize.size());
			int prizeId = (int) prize.get(randomStep);
			List list1 = userLotteryDao.findLuckyPrize(openId, ActivityId);
			// 抽过奖或者数据库中没找到openid
			if (list1.size() == 0)
				return "-1";
			// 更改为抽过奖，和奖品状态
			int userid = ((LuckyPrize) list1.get(0)).getId();
			userLotteryDao.setPrizeNote(userid, prizeId);
			// 返回奖品id
			return String.valueOf(prizeId);
		}
	}

	@Override
	public List<Prize> findPrizeInfoById(int prizeId) {
		// TODO Auto-generated method stub
		return userLotteryDao.findPrizeInfoById(prizeId);
	}

	@Override
	public int findLuckyPrize(String openId, String ActivityId) {
		List<LuckyPrize> list = userLotteryDao.findLuckyPrize(openId,
				ActivityId);
		// System.out.println(list.get(0).getOpenId());
		int size = list.size();
		if (size == 0) {
			return -1;
		} else {
			return list.get(0).getIsPrize();
		}
	}

	@Override
	public int SelectUserPhone(String openId) {
		// TODO Auto-generated method stub
		List<UserBasic> listIn = userLotteryDao.SelectUser(openId);
		// there为有无电话等信息状态
		int there = 0;
		if (listIn != null && listIn.size() != 0) {
			String phone = listIn.get(0).getPhone();
			String address = listIn.get(0).getAddress();
			System.out.println(phone);
			if ("".equals(phone) || phone == null && "".equals(address)
					|| address == null) {
				there = 0;
			} else {
				there = 1;
			}
		}
		return there;
	}

	@Override
	public void saveLucyPrice(String activityId, String openid, int isPriceze) {
		// TODO Auto-generated method stub

		userLotteryDao.saveLucyPrice(activityId, openid, isPriceze);

	}

	@Override
	public void savaPrizeCardNo(String openId) {
		
		List<UserBasic> list = userLotteryDao.SelectUser(openId);  	//由openId查用户ID
		List li = userLotteryDao.selectopencard(openId);			//查询用户
		if (list != null && list.size() != 0) {
			Map map = (Map) li.get(0);
			List listIn = userLotteryDao.selectCard((String) map.get("CardNo")); // 去shopCarduserNo中查修重复绑定
			if (listIn == null || listIn.size() == 0) {
				cardDao.saveCard(1, ((UserBasic) list.get(0)).getUserID(),
						(String) map.get("CardNo"));
				//userLotteryDao.updatePrizeCardState((String) map.get("CardNo"));// 更新奖品已使用
			}
		}

	}

	@Override
	public List finsouce(String source) {
		// TODO Auto-generated method stub
		List list = userLotteryDao.findsouce(source);
		return list;
	}

	@Override
	public List finduser(String openid, String ActivityId) {
		// TODO Auto-generated method stub
		List list = userLotteryDao.finduser(openid, ActivityId);
		return list;

	}

	@Override
	public void updatesource(String source) {
		// TODO Auto-generated method stub
		userLotteryDao.updatesource(source);

	}

	@Override
	public String selectUrl(String openId) { // 查询用户URL
		// TODO Auto-generated method stub

		List listUrl = userLotteryDao.selectUrl(openId);
		String userURL = null;
		if (listUrl.size() != 0 && listUrl != null) {
			userURL = (String) listUrl.get(0);
		}

		return userURL;
	}

	@Override
	public int urlShore(String openId) { // 用户生成的链接被分享了几次
		// TODO Auto-generated method stub
		List urlList = userLotteryDao.urlShoreNum(openId); //查询分享数量
		int urlNum = 0;
		if (urlList.size() != 0 && urlList != null) {		//统计数量
			urlNum = (int) urlList.get(0);

		}
		return urlNum;
	}

	// @Override
	// public int prizeNum(String openId) { // 用户已抽取奖项次数
	// // TODO Auto-generated method stub
	// UserLotteryServiceImpl ulsi = new UserLotteryServiceImpl();
	// int prize = ulsi.urlShore(openId);
	// prize /= 10; // 10为定数
	// return prize;
	// }

	@Override
	public int noPrizeNum(String openId) { // 用户离下次送奖品需要的分享次数
		// TODO Auto-generated method stub
		UserLotteryServiceImpl ulsi = new UserLotteryServiceImpl();
		int addPrize = ulsi.urlShore(openId);

		addPrize = 10 - addPrize % 10; // 再加几次分享
		return addPrize;
	}
	
	@Override
	public List findyijika(String openId) {
		// TODO Auto-generated method stub
		List list = userLotteryDao.findyijiaka(openId);		//查询用户未绑定的骑士卡

		return list;
	}

	@Override
	public void updateLucyPrice(String openid) {
		// TODO Auto-generated method stub
		userLotteryDao.updateLucyPrice(openid);
	}

	@Override
	public List checkisprice(String openId) {
		// TODO Auto-generated method stub
		List list = userLotteryDao.checkisprice(openId);
		return list;
	}

	@Override
	public List tuijianXml(String fileName) {
		List xmlList = new ArrayList();
		try {
			// 创建解析器
			SAXReader reader = new SAXReader();
			// 读成取文档
			Document doc = reader.read(new File(fileName));
			// 读取根元素
			Node root = doc.selectSingleNode("/menus");
			// xpath 查询
			List<Element> list = root.selectNodes("menu");
			int ij = 0;
			// 取得name
			for (Element e : list) {
				ij++;
				Map node = new HashMap();
				node.put("name", e.elementText("name"));
				List<Element> products = e.selectNodes("productList/product");
				List proList = new ArrayList();
				for (Element p : products) {
					String id = p.elementText("id");
					System.out.println(id + "1111");
					List result = gooddao.LotteryGetSku(id);
					if (result != null && result.size() > 0) {
						proList.add(result.get(0));
					}
				}
				System.out.println(ij + "-----");
				node.put("list", proList);
				xmlList.add(node);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < xmlList.size(); i++) {
			System.out.println(i);
			Map map = new HashMap<>();
			List<ProductBasicSkuInfo> list = new ArrayList<>();
			map = (Map) xmlList.get(i);
			list = (List) map.get("list");
			for (int j = 0; j < list.size(); j++) {
				System.out.println(j + "---");
				System.out.println(list.get(j));
			}
		}
		return xmlList;
	}

	// 查询正在进行的活动
	public List findActivity() {
		return userLotteryDao.findActivity();
	}

	@Override
	public List findPrizeInfoByopenId(String openid,String ActivityNo) {
		// TODO Auto-generated method stub
		return userLotteryDao.findPrizeinfobuopenid(openid,ActivityNo);
	}
	
	public void updatePrizeCardState(int prizeId){
		userLotteryDao.updatePrizeCardState(prizeId);
	}

	@Override
	public List checkcardstate(String openid) {
		// TODO Auto-generated method stub
		return userLotteryDao.checkcardstate(openid);
	}
}
