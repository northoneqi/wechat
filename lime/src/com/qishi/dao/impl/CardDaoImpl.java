package com.qishi.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.qishi.dao.CardDao;
import com.qishi.entity.ShopCardOrderDetail;
import com.qishi.util.StringUtil;


@Repository("CardDao")
public class CardDaoImpl extends BaseDaoImpl<ShopCardOrderDetail>implements CardDao {

	@Override
	public List<ShopCardOrderDetail> allList(int userId,String CardNo) {
		String sql ="SELECT u.CardNo,(d.FaceValue+d.GivenValue-ISNULL((select SUM(oic.[money]) "
				+ " FROM T_OrderInCome oic  WHERE oic.CardNumber=u.CardNo),0)+ISNULL((select SUM(cmc.ConvertMoney)"
				+ " FROM T_CardMoneyConvert cmc  WHERE cmc.CardNumber=u.CardNo),0))Balance "
				+ "  FROM T_ShopUserCardNo u  INNER JOIN T_ShopCardOrderDetail d   ON u.CardNo=d.CardNo"
				+ " INNER JOIN T_ShopCardNo cn ON u.CardNo=cn.CardNo  WHERE 1=1 and  u.UserID='"+userId+"' ";
				if (StringUtil.isNotEmpty(CardNo)) {
					sql+=" and u.CardNo='"+CardNo+"' ";
				}
						sql+= " order by Balance desc;";
		Query query = getSession().createSQLQuery(sql);

		@SuppressWarnings("unchecked")
		List<ShopCardOrderDetail> list =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
		
	}
	@SuppressWarnings("unused")

	@Override
	//查询列表存不存在
	public List selectCard(String cardName, String password) {
		// TODO Auto-generated method stub
		String sql = "SELECT a.CardPwd FROM T_ShopCardNo a INNER JOIN T_ShopCardOrderDetail b ON a.CardNo=b.CardNo WHERE a.CardNo='"+cardName+"';";
		Query query = getSession().createSQLQuery(sql);
		
		List list =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	


	@Override
	//插入绑定的卡号和密码
	public List saveCard(int cardOk, int userId ,String cardName) {
		// TODO Auto-generated method stub
		if(cardOk==1){
			String sql="INSERT INTO T_ShopUserCardNo (UserID,CardNo,Remark)VALUES('"+userId+"','"+cardName+"','微信绑定的');";
			getSession().createSQLQuery(sql.toString()).executeUpdate();
		}
		return null;
		
	}
	@Override
	//查询卡号和密码是否被别人或已绑定
	public List selectCardIn(String cardName) {
		// TODO Auto-generated method stub
		String sql = "SELECT CardNo FROM T_ShopUserCardNo WHERE CardNo='"+cardName+"';";
		Query query = getSession().createSQLQuery(sql);
		List listIn=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		
		return listIn;
	}
	@Override
	public List<ShopCardOrderDetail> allistinfo(Integer userID) {
		// TODO Auto-generated method stub
		//检查卡有效期
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(" SELECT 'sum' CardNo,SUM(s.Balance) Balance									 ");
		sqlBuf.append(" from														 ");
		sqlBuf.append(" (SELECT														 ");
		sqlBuf.append(" 	b.Balance												 ");
		sqlBuf.append(" FROM														 ");
		sqlBuf.append(" 	T_ShopUserCardNo a											 ");
		sqlBuf.append(" LEFT JOIN (													 ");
		sqlBuf.append(" 	SELECT													 ");
		sqlBuf.append(" 		u.CardNo,											 ");
		sqlBuf.append(" 		(												 ");
		sqlBuf.append(" 			d.FaceValue + d.GivenValue - ISNULL(							 ");
		sqlBuf.append(" 				(										 ");
		sqlBuf.append(" 					SELECT									 ");
		sqlBuf.append(" 						SUM (oic.[money])						 ");
		sqlBuf.append(" 					FROM									 ");
		sqlBuf.append(" 						T_OrderInCome oic						 ");
		sqlBuf.append(" 					WHERE									 ");
		sqlBuf.append(" 						oic.CardNumber = u.CardNo					 ");
		sqlBuf.append(" 				),										 ");
		sqlBuf.append(" 				0										 ");
		sqlBuf.append(" 			) + ISNULL(										 ");
		sqlBuf.append(" 				(										 ");
		sqlBuf.append(" 					SELECT									 ");
		sqlBuf.append(" 						SUM (cmc.ConvertMoney)						 ");
		sqlBuf.append(" 					FROM									 ");
		sqlBuf.append(" 						T_CardMoneyConvert cmc						 ");
		sqlBuf.append(" 					WHERE									 ");
		sqlBuf.append(" 						cmc.CardNumber = u.CardNo					 ");
		sqlBuf.append(" 				),										 ");
		sqlBuf.append(" 				0										 ");
		sqlBuf.append(" 			)											 ");
		sqlBuf.append(" 		) Balance       										 ");
		sqlBuf.append(" 	FROM													 ");
		sqlBuf.append(" 		T_ShopUserCardNo u										 ");
		sqlBuf.append(" 	INNER JOIN T_ShopCardOrderDetail d ON u.CardNo = d.CardNo						 ");
		sqlBuf.append(" 	INNER JOIN T_ShopCardNo cn ON u.CardNo = cn.CardNo							 ");
		sqlBuf.append(" ) b ON a.CardNo = b.CardNo											 ");
		sqlBuf.append(" INNER JOIN (													 ");
		sqlBuf.append(" 	select 													 ");
		sqlBuf.append(" cardno,														 ");
		sqlBuf.append(" CASE SaleState													 ");
		sqlBuf.append(" 	WHEN '4' THEN '4.已锁定'											 ");
		sqlBuf.append(" ELSE														 ");
		sqlBuf.append(" 	CASE WHEN convert(varchar(50),EndDate,121) < convert(varchar(50),GetDate(),121) THEN '5.已过期' ");
		sqlBuf.append(" 	ELSE													 ");
		sqlBuf.append(" 		CASE UserState											 ");
		sqlBuf.append(" 			WHEN '1' THEN '1.正常'									 ");
		sqlBuf.append(" 			WHEN '2' THEN '2.注销'									 ");
		sqlBuf.append(" 			WHEN '3' THEN '3.退卡'									 ");
		sqlBuf.append(" 		END												 ");
		sqlBuf.append(" 	END													 ");
		sqlBuf.append(" END cardstate													 ");
		sqlBuf.append("  from T_ShopCardNo												 ");
		sqlBuf.append("  where 1=1												 ");
		sqlBuf.append("  and EnableBackCard=1												 ");
		sqlBuf.append("  and EnableCombineCard=1											 ");
		sqlBuf.append(" )	c on a.CardNo=c.cardno											 ");
		sqlBuf.append(" WHERE														 ");
		sqlBuf.append(" 	a.UserID = 	"+userID											);
		sqlBuf.append(" AND c.cardstate = '1.正常'											 ");
		sqlBuf.append("  and b.Balance > 0												 ");
		sqlBuf.append(" ) s														 ");
		sqlBuf.append(" union all													 ");
		sqlBuf.append(" SELECT														 ");
		sqlBuf.append(" 	a2.CardNo,b2.Balance											 ");
		sqlBuf.append(" FROM														 ");
		sqlBuf.append(" 	T_ShopUserCardNo a2											 ");
		sqlBuf.append(" LEFT JOIN (													 ");
		sqlBuf.append(" 	SELECT													 ");
		sqlBuf.append(" 		u.CardNo,											 ");
		sqlBuf.append(" 		(												 ");
		sqlBuf.append(" 			d.FaceValue + d.GivenValue - ISNULL(							 ");
		sqlBuf.append(" 				(										 ");
		sqlBuf.append(" 					SELECT									 ");
		sqlBuf.append(" 						SUM (oic.[money])						 ");
		sqlBuf.append(" 					FROM									 ");
		sqlBuf.append(" 						T_OrderInCome oic						 ");
		sqlBuf.append(" 					WHERE									 ");
		sqlBuf.append(" 						oic.CardNumber = u.CardNo					 ");
		sqlBuf.append(" 				),										 ");
		sqlBuf.append(" 				0										 ");
		sqlBuf.append(" 			) + ISNULL(										 ");
		sqlBuf.append(" 				(										 ");
		sqlBuf.append(" 					SELECT									 ");
		sqlBuf.append(" 						SUM (cmc.ConvertMoney)						 ");
		sqlBuf.append(" 					FROM									 ");
		sqlBuf.append(" 						T_CardMoneyConvert cmc						 ");
		sqlBuf.append(" 					WHERE									 ");
		sqlBuf.append(" 						cmc.CardNumber = u.CardNo					 ");
		sqlBuf.append(" 				),										 ");
		sqlBuf.append(" 				0										 ");
		sqlBuf.append(" 			)											 ");
		sqlBuf.append(" 		) Balance           										 ");
		sqlBuf.append(" 	FROM													 ");
		sqlBuf.append(" 		T_ShopUserCardNo u										 ");
		sqlBuf.append(" 	INNER JOIN T_ShopCardOrderDetail d ON u.CardNo = d.CardNo						 ");
		sqlBuf.append(" 	INNER JOIN T_ShopCardNo cn ON u.CardNo = cn.CardNo 							 ");
		sqlBuf.append(" ) b2 ON a2.CardNo = b2.CardNo											 ");
		sqlBuf.append(" INNER JOIN (													 ");
		sqlBuf.append(" 	select 													 ");
		sqlBuf.append(" cardno,														 ");
		sqlBuf.append(" CASE SaleState													 ");
		sqlBuf.append(" 	WHEN '4' THEN '4.已锁定'											 ");
		sqlBuf.append(" ELSE														 ");
		sqlBuf.append(" 	CASE WHEN convert(varchar(50),EndDate,121) < convert(varchar(50),GetDate(),121) THEN '5.已过期'		 ");
		sqlBuf.append(" 	ELSE													 ");
		sqlBuf.append(" 		CASE UserState											 ");
		sqlBuf.append(" 			WHEN '1' THEN '1.正常'									 ");
		sqlBuf.append(" 			WHEN '2' THEN '2.注销'									 ");
		sqlBuf.append(" 			WHEN '3' THEN '3.退卡'									 ");
		sqlBuf.append(" 		END												 ");
		sqlBuf.append(" 	END													 ");
		sqlBuf.append(" END cardstate													 ");
		sqlBuf.append("  from T_ShopCardNo												 ");
		sqlBuf.append("  where 1=1												 ");
		sqlBuf.append("  and EnableBackCard=1												 ");
		sqlBuf.append("  and EnableCombineCard=1											 ");
		sqlBuf.append(" )	c2 on a2.CardNo=c2.cardno										 ");
		sqlBuf.append(" WHERE														 ");
		sqlBuf.append(" 	a2.UserID = "	+userID											 );
		sqlBuf.append(" AND c2.cardstate = '1.正常'											 ");
		sqlBuf.append("  and b2.Balance > 0												 ");
		sqlBuf.append(" ORDER BY   Balance ASC												 ");
		Query query = getSession().createSQLQuery(sqlBuf.toString());
		List list=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	@Override
	//查询骑士卡是否还剩余额
	public List selectCardMoney(String cardName) {
		// TODO Auto-generated method stub
		String sqlMoney ="SELECT d.CardNo,(d.FaceValue+d.GivenValue-ISNULL((select SUM(oic.[money])"
				+ "	FROM T_OrderInCome oic WHERE oic.CardNumber=d.CardNo),0)+ISNULL((select SUM(cmc.ConvertMoney)	"
				+ "FROM T_CardMoneyConvert cmc WHERE cmc.CardNumber=d.CardNo),0))Balance "
				+ "FROM  T_ShopCardOrderDetail d INNER JOIN T_ShopCardNo cn ON d.CardNo=cn.CardNo "
				+ "WHERE 1=1 and d.CardNo='"+cardName+"';";
		Query query = getSession().createSQLQuery(sqlMoney);
		List listMoney=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return listMoney;
	}
	
//	//检查卡有效期
//	public boolean changecardtype(Integer userID) {
//		try{
//		String sql = "SELECT tsc.cardno cardno,FROM T_ShopCardNo tsc LEFT JOIN T_ShopUserCardNo tsuc on tsc.cardno = tsuc.cardno " 
//					+"WHERE tsuc.UserID="+userID;
//		Query query = getSession().createSQLQuery(sql);
//		List listcardtime=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
//		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		for(int i=0;i<listcardtime.size();i++){
//			Map map = (Map) listcardtime.get(i);
//			String time =  map.get("cardno").toString();
//			Date enddate = sdf.parse(time);
//			Date now = new Date();
//			if(enddate.getTime()>=now.getTime()){
//			}else{
//				String cardno =  map.get("cardno").toString();
//				String sql2 = "update T_ShopCardNo set cardType=2 where cardno='"+cardno+"'";
//				getSession().createSQLQuery(sql2).executeUpdate();
//			}
//		}
//		return true;
//		}catch (Exception e){
//		return false;
//		}
//	}
	@Override
	public List<ShopCardOrderDetail> allistinfoall(Integer userID) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("SELECT 'sum' CardNo,SUM(s.Balance) Balance					");
		sqlBuf.append("from										");
		sqlBuf.append("(SELECT										");
		sqlBuf.append("	b.Balance									");
		sqlBuf.append("FROM										");
		sqlBuf.append("	T_ShopUserCardNo a								");
		sqlBuf.append(" JOIN (									");
		sqlBuf.append("	SELECT										");
		sqlBuf.append("		u.CardNo,								");
		sqlBuf.append("		(									");
		sqlBuf.append("			d.FaceValue + d.GivenValue - ISNULL(				");
		sqlBuf.append("				(							");
		sqlBuf.append("					SELECT						");
		sqlBuf.append("						SUM (oic.[money])			");
		sqlBuf.append("					FROM						");
		sqlBuf.append("						T_OrderInCome oic			");
		sqlBuf.append("					WHERE						");
		sqlBuf.append("						oic.CardNumber = u.CardNo		");
		sqlBuf.append("				),							");
		sqlBuf.append("				0							");
		sqlBuf.append("			) + ISNULL(							");
		sqlBuf.append("				(							");
		sqlBuf.append("					SELECT						");
		sqlBuf.append("						SUM (cmc.ConvertMoney)			");
		sqlBuf.append("					FROM						");
		sqlBuf.append("						T_CardMoneyConvert cmc			");
		sqlBuf.append("					WHERE						");
		sqlBuf.append("						cmc.CardNumber = u.CardNo		");
		sqlBuf.append("				),							");
		sqlBuf.append("				0							");
		sqlBuf.append("			)								");
		sqlBuf.append("		) Balance       							");
		sqlBuf.append("	FROM										");
		sqlBuf.append("		T_ShopUserCardNo u							");
		sqlBuf.append("	INNER JOIN T_ShopCardOrderDetail d ON u.CardNo = d.CardNo			");
		sqlBuf.append("	INNER JOIN T_ShopCardNo cn ON u.CardNo = cn.CardNo				");
		sqlBuf.append("  where 1=1												 ");
		sqlBuf.append("  and cn.EnableBackCard=1												 ");
		sqlBuf.append("  and cn.EnableCombineCard=1											 ");
		sqlBuf.append(") b ON a.CardNo = b.CardNo							");
		sqlBuf.append("WHERE										");
		sqlBuf.append("	a.UserID = "+userID);
		sqlBuf.append(" and b.Balance > 0								");
		sqlBuf.append(") s										");
		sqlBuf.append("union all										");
		sqlBuf.append("SELECT										");
		sqlBuf.append("	a2.CardNo,b2.Balance								");
		sqlBuf.append("FROM										");
		sqlBuf.append("	T_ShopUserCardNo a2								");
		sqlBuf.append(" JOIN (									");
		sqlBuf.append("	SELECT										");
		sqlBuf.append("		u.CardNo,								");
		sqlBuf.append("		(									");
		sqlBuf.append("			d.FaceValue + d.GivenValue - ISNULL(				");
		sqlBuf.append("				(							");
		sqlBuf.append("					SELECT						");
		sqlBuf.append("						SUM (oic.[money])			");
		sqlBuf.append("					FROM						");
		sqlBuf.append("						T_OrderInCome oic			");
		sqlBuf.append("					WHERE						");
		sqlBuf.append("						oic.CardNumber = u.CardNo		");
		sqlBuf.append("				),							");
		sqlBuf.append("				0							");
		sqlBuf.append("			) + ISNULL(							");
		sqlBuf.append("				(							");
		sqlBuf.append("					SELECT						");
		sqlBuf.append("						SUM (cmc.ConvertMoney)			");
		sqlBuf.append("					FROM						");
		sqlBuf.append("						T_CardMoneyConvert cmc			");
		sqlBuf.append("					WHERE						");
		sqlBuf.append("						cmc.CardNumber = u.CardNo		");
		sqlBuf.append("				),							");
		sqlBuf.append("				0							");
		sqlBuf.append("			)								");
		sqlBuf.append("		) Balance           							");
		sqlBuf.append("	FROM										");
		sqlBuf.append("		T_ShopUserCardNo u							");
		sqlBuf.append("	INNER JOIN T_ShopCardOrderDetail d ON u.CardNo = d.CardNo			");
		sqlBuf.append("	INNER JOIN T_ShopCardNo cn ON u.CardNo = cn.CardNo			");
		sqlBuf.append("  where 1=1												 ");
		sqlBuf.append("  and cn.EnableBackCard=1												 ");
		sqlBuf.append("  and cn.EnableCombineCard=1											 ");
		sqlBuf.append(") b2 ON a2.CardNo = b2.CardNo							");
		sqlBuf.append("WHERE										");
		sqlBuf.append("	a2.UserID = "+userID);
		sqlBuf.append(" and b2.Balance > 0		ORDER BY   Balance ASC						");
		Query query = getSession().createSQLQuery(sqlBuf.toString());
		List list=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}	


	@Override
	public List<ShopCardOrderDetail> allCardList(int userId, String CardNo) {	//查询骑士卡列表升序
		String sql ="SELECT u.CardNo,CASE SaleState	WHEN '4' THEN '4'ELSE"
				+" CASE WHEN convert(varchar(50),EndDate,121) < convert(varchar(50),GetDate(),121) THEN '5'"
				+" ELSE	CASE UserState	WHEN '1' THEN '1'	WHEN '2' THEN '2'	WHEN '3' THEN '3'	END"
				+" END END cardstate ,(d.FaceValue+d.GivenValue-ISNULL((select SUM(oic.[money])" 
				+" FROM T_OrderInCome oic  WHERE oic.CardNumber=u.CardNo),0)+ISNULL((select SUM(cmc.ConvertMoney)"
				+" FROM T_CardMoneyConvert cmc  WHERE cmc.CardNumber=u.CardNo),0))Balance "
				+" FROM T_ShopUserCardNo u  INNER JOIN T_ShopCardOrderDetail d   ON u.CardNo=d.CardNo"
				+" INNER JOIN T_ShopCardNo cn ON u.CardNo=cn.CardNo  WHERE 1=1 and  u.UserID='"+userId+"' ORDER BY cardstate";
		Query query = getSession().createSQLQuery(sql);

		List list = query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;	
	}
	//查询红包卡
	@Override
	public List allHongCardList(int userId) {
		String sql ="SELECT "
				+" u.CardNo CardNo"
				+" ,( "
				+" d.FaceValue "
				+" +d.GivenValue "
				+" -ISNULL((select SUM(oic.[money]) FROM T_OrderInCome oic WHERE oic.CardNumber=u.CardNo),0) "
				+" +ISNULL((select SUM(cmc.ConvertMoney) "
				+" FROM T_CardMoneyConvert cmc "
				+" WHERE cmc.CardNumber=u.CardNo),0) "
				+" )Balance  "
				+" FROM T_ShopUserCardNo u  "
				+" JOIN T_ShopCardOrderDetail d  ON u.CardNo=d.CardNo " 
				+" JOIN T_ShopCardNo cn ON u.CardNo=cn.CardNo "
				+" WHERE "
				+" (cn.EnableBackCard=0) "
				+" and (cn.EnableCombineCard=0) "
				+" and convert(varchar(50),EndDate,121) > convert(varchar(50),GetDate(),121) "
				+" and cn.UserState=1 "
				+" and (cn.SaleState=3 or cn.SaleState=2) "
				+" and cn.CardType=1 "
				+" and  u.UserID='"+userId+"'";
		Query query = getSession().createSQLQuery(sql);

		List list = query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;	
	}

}
	
