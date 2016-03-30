package com.qishi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.qishi.dao.UserLotteryDao;
import com.qishi.entity.Activity;
import com.qishi.entity.JoinIn;
import com.qishi.entity.LuckyPrize;
import com.qishi.entity.Prize;
import com.qishi.entity.UserBasic;

@Repository("UserLotteryDao")
public class UserLotteryDaoImpl extends BaseDaoImpl<JoinIn> implements UserLotteryDao{
	
	@Override
	public List checkuser(String openid) {
		// TODO Auto-generated method stub
		String sql = "select * from T_UserBasic where WX_OPENID='"+openid+"'";
		List check =  getSession().createSQLQuery(sql).list();
		return check;
	}

	@Override
	public List<Prize> findPrizeInfo(String ActivityId) {
		String sql = "select * from T_MciroPrize(xlock) where note = 0 and ActivityId ="+ActivityId;
		return getSession().createSQLQuery(sql).addEntity(Prize.class).list();
	}

	@Override
	public void setPrizeNote(int Id,int prizeId) {
		String sql = " update T_MciroPrize set note = 1 where prizeId ="+prizeId+
				"; update T_MciroLuckyPrize set prizeId="+prizeId+", isPrize = 1 ,PrizeDate = GetDate() where id ="+Id;
		Query query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Prize> findPrizeInfoById(int prizeId) {
		String sql = " select * from T_MciroPrize where prizeId="+prizeId;
		return  getSession().createSQLQuery(sql).addEntity(Prize.class).list();
	}

	@Override
	public List<LuckyPrize> findLuckyPrize(String openId,String ActivityId) {
		String sql = "select * from T_MciroLuckyPrize where openid='"+openId+"' and ActivityId ="+ActivityId;
		return getSession().createSQLQuery(sql).addEntity(LuckyPrize.class).list();
	}

	@Override
	public List SelectUser(String openId) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM T_UserBasic WHERE WX_OPENID='"+openId+"'";
		return getSession().createSQLQuery(sql).addEntity(UserBasic.class).list();
	}

	@Override
	public void saveLucyPrice(String activityId, String openid, int isPriceze ){
		// TODO Auto-generated method stub
		String sql = "insert into T_MciroLuckyPrize(ActivityId,openid,isPrize) values('"+activityId+"','"+openid+"','"+isPriceze+"')";
		Query query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List findsouce(String source) {
		// TODO Auto-generated method stub
		String sql = "select * from T_MciroJoinIn where and source='"+source+"'";
		List list = getSession().createSQLQuery(sql).list();
		return list;
		
	}

	@Override
	public List finduser(String openid,String ActivityId) {
		// TODO Auto-generated method stub
		String sql = "select * from T_MciroJoinIn where openid='"+openid+"' and ActivityId = "+ActivityId;
		Query query = getSession().createSQLQuery(sql);
		List list =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}

	@Override
	public void updatesource(String source) {
		// TODO Auto-generated method stub
		
	}

@Override
	public List selectUrl(String openId) {
		// TODO Auto-generated method stub
		String sql="SELECT TOP 1 Url FROM T_MciroJoinIn WHERE openid='"+openId+"' and checklog=1;";
		List listUrl = getSession().createSQLQuery(sql).list();
		
		return listUrl;
	}

	@Override
	public List urlShoreNum(String openId) {
		// TODO Auto-generated method stub
		String sql="select count(*)from T_MciroJoinIn where source='"+openId+"' and checklog=1";
		List listNum = getSession().createSQLQuery(sql).list();
		return listNum;
	}
//
//	@Override
//	public List prizeNumber(String openId) {
//		// TODO Auto-generated method stub
//		String sql="SELECT COUNT(*) FROM T_MciroJoinIn WHERE Source='"+openId+"' AND SourceNum='1'";
//		List list= getSession().createSQLQuery(sql).list();
//		return list;
//	}
//
//	@Override
//	public List prizeRemain(String openId) {
//		// TODO Auto-generated method stub
//		String sql="SELECT COUNT(*) FROM T_MciroJoinIn WHERE Source='"+openId+"' AND SourceNum='0';";
//		List list= getSession().createSQLQuery(sql).list();
//		return list;	
//	}

	@Override
	public void updatePrizeCardState(int prizeId) {
		String sql = "update T_MciroPrize set CardState = 1 where PrizeId ="+prizeId+"";
		Query query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List findyijiaka(String openId) {
		// TODO Auto-generated method stub
		String sql = "select tlp.id, tlp.openid, tp.cardno from T_MciroLuckyPrize tlp left join T_MciroPrize tp on  tlp.PrizeId = tp.PrizeId where tp.cardstate=0 and tlp.openid='"+openId+"'";
		Query query = getSession().createSQLQuery(sql);
		List a =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return a;
	}

	@Override
	//更改用户状态为已登陆
	public void updateLucyPrice(String openid) {
		// TODO Auto-generated method stub
		String sql = "update T_MciroJoinIn set checklog=1 where openid='"+openid+"'";
		Query query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	//查询卡号和密码是否被别人或已绑定
	public List selectCard(String cardNo) {
		// TODO Auto-generated method stub
		String sql = "SELECT CardNo FROM T_ShopUserCardNo WHERE CardNo='"+cardNo+"';";
		Query query = getSession().createSQLQuery(sql);
		List listIn=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		
		return listIn;
	}

	@Override
	public List checkisprice(String openId) {
		// TODO Auto-generated method stub
		String sql = "select * from T_MciroLuckyPrize where openid = '"+openId+"' and isprize=1";
		Query query = getSession().createSQLQuery(sql);
		List list=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}

	@Override
	public List findActivity() {
		String sql = "select * from T_MciroActivity where GETDATE() between BeginDate and StopDate";
		return  getSession().createSQLQuery(sql).addEntity(Activity.class).list();
	}

	@Override
	public int findPrizeSize() {
		int countSize = 0;
		String sql = "select count(id) from T_MciroLuckyPrize(xlock) where convert(varchar(10),PrizeDate,112) = convert(varchar(10),GetDate(),112)";
		countSize = Integer.parseInt(getSession().createSQLQuery(sql).uniqueResult().toString());
		return countSize;
	}

	@Override
	public List findPrizeinfobuopenid(String openid,String ActivityNo) {
		String sql="select p.PrizeContent,p.PrizeId from T_MciroLuckyPrize b "+
				" inner join T_MciroPrize p on b.prizeId=p.PrizeId "+
				" where OpenId='"+openid+"' and p.ActivityId="+ActivityNo;
		Query query = getSession().createSQLQuery(sql);
		List list=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}

	@Override
	public List checkcardstate(String openid) {
		// TODO Auto-generated method stub
		String sql = " SELECT MP.CardState FROM T_MciroLuckyPrize TP LEFT JOIN T_MciroPrize MP ON TP.prizeId=MP.PrizeId WHERE OpenId='"+openid+"' and MP.ActivityId=2";
		List list = getSession().createSQLQuery(sql).list();
		return list;
	}

	@Override
	public List selectopencard(String openid) {
		String sql=" SELECT b.CardNo FROM T_MciroLuckyPrize a "
				+ "	LEFT JOIN T_MciroPrize b ON a.prizeId=b.PrizeId "
				+ "	WHERE OpenId='"+openid+"' AND a.ActivityId='2' ";

		Query query = getSession().createSQLQuery(sql);
		List list=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		
		System.out.println("88888888888888888"+list+openid);
		return list;
	}

	

}
