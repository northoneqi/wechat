package com.qishi.dao.impl;


import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.qishi.dao.OrderDao;
import com.qishi.entity.OrderInCome;
import com.qishi.util.StringUtil;


@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<OrderInCome> implements OrderDao {

	/**
	 * 订单列表 根据用户Id 订单状态： 待付款 =1  已完成 =10   已取消 =12 
	 * 		   OrderType=5  订单为微信上订单
	 * 		   OrderType=8 众筹订单
	 */
	public List findlist(Integer userId, String code,String orderCodes) {
		//userId=941;
		StringBuffer sql = new StringBuffer(
				"select b.ParaName,a.AcceptType,wxo.WeixinOrderCode,wxo.OrderTime OrderTime,h.ParaName+i.ParaName+d.Address Address,c.PayType,sum(e.Subtotal) total from T_OrderAccept a "
+"left join (select ParaValue,ParaName from T_Param  where OnlyGroupName='订单受理状态') b "
+"on a.AcceptType=b.ParaValue "
+"left join T_OrderBasic c "
+"on a.OrderCode=c.OrderCode "
+"left join T_OrderAddress d "
+"on d.OrderCode=c.OrderCode "
+"left join T_OrderDetail e "
+"on e.OrderCode=c.OrderCode "
+"left join (select ParaName,ParaValue from T_Param   where OnlyGroupFlag='Area') g "
+"on d.Province=g.ParaValue "
+"left join (select ParaName,ParaValue from T_Param  where OnlyGroupFlag='Area') h "
+"on d.City=h.ParaValue "
+"left join (select ParaName,ParaValue from T_Param  where OnlyGroupFlag='Area') i "
+"on d.Area=i.ParaValue " 
+" LEFT JOIN T_WeixinOrder wxo ON wxo.OrderCode = c.OrderCode "
+"where 1=1 and (c.OrderType=5 or c.OrderType=8)");

		if (userId != null) {
			sql.append(" and c.UserId='").append(userId).append("'");
		}

//		if (StringUtil.isNotEmpty(code)) {
//			sql.append("and c.OrderCode='").append(code).append("'");
//		}
		
		if (StringUtil.isNotEmpty(orderCodes)) {
			sql.append("and wxo.WeixinOrderCode  ='").append(orderCodes).append("'");
		}
		sql.append(" GROUP BY h.ParaName,i.ParaName,b.ParaName,a.AcceptType,wxo.WeixinOrderCode,wxo.OrderTime,Address,c.PayType order by a.AcceptType asc,wxo.OrderTime desc");

		// System.out.println(sql.toString());
		Query query = getSession().createSQLQuery(sql.toString());

		// System.out.println(query);
		List orderList = query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return orderList;
	}

	/**
	 * 订单详细 根据订单号  (s.ProSKUId作为查看商品详细的参数)
	 * 已取消 =12
	 */
	
	public List findOrderDetail(String code) {
		
		String sql = "select case when d.SKUName is null then s.WXProName else d.SKUName end ProName,a.SendTimeSpan,a.SendDate,ob.OrderClass,d.OrderCode,s.ProSKUId,t.AcceptType,d.ProNum,d.Subtotal,wxo.WeixinOrderCode,wxo.OrderTime,a.Address,a.PostCode,a.Consignee,a.Telephone,a.Mobile,s.ListPagePic from  T_OrderDetail d  left join T_OrderAccept t on t.OrderCode =d.OrderCode left join T_OrderAddress a on a.OrderCode=d.OrderCode left join T_ProductBasicSkuInfo s on s.SKU=d.SKU LEFT JOIN T_WeixinOrder wxo ON wxo.OrderCode = d.OrderCode LEFT JOIN T_OrderBasic ob on d.OrderCode=ob.OrderCode  where d.OrderCode in (select OrderCode from T_WeixinOrder where WeixinOrderCode='"+code+"')";
		Query query = getSession().createSQLQuery(sql.toString());
		List findOrderDetail = query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return findOrderDetail;
	}
	
	/**
	 * 根据openid查询盒子的配送地址信息
	 * 
	 */
	
	public List findOrderAddress(String openId,String activityId) {
		if(openId!=null){
			if(!openId.startsWith("'")){
				openId = "'"+openId+"'";
			}
		}
		if(activityId!=null){
			if(!activityId.startsWith("'")){
				activityId = "'"+activityId+"'";
			}
		}
		String sql = " select Area,Name,Address,Telephone,PeisongMoment,OpenId,PeisongTime  from T_MciroUserInfo where OpenId="+openId+" and ActivityId="+activityId;
		Query query = getSession().createSQLQuery(sql.toString());
		List findOrderDetail = query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return findOrderDetail;
	}

	/**
	 * 修改订单状态 根据订单号
	 */
	@Override
	public void updateAccept(String code) {
		if(code!=null){
			if(!code.startsWith("'")){
				code = "'"+code+"'";
			}
		}
		String sql = "update T_OrderAccept  set AcceptType=12 where OrderCode="
				+ code + "";
		getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public String  payOrderSum(String orderCode) {
		StringBuffer sql = new StringBuffer("select SUM(tod.Subtotal) Subtotal from T_OrderBasic tob  LEFT JOIN T_OrderDetail tod on tod.OrderCode = tob.OrderCode LEFT JOIN T_WeixinOrder wxo ON wxo.OrderCode = tob.OrderCode where 1=1 ");
		if (StringUtil.isNotEmpty(orderCode)) {
			sql.append(" and wxo.WeixinOrderCode='").append(orderCode).append("'");
		}
		Query query = getSession().createSQLQuery(sql.toString());
		List list = query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		System.out.println(list.toString());
		String result = "";
		if (list!=null) {
			Map map = (Map) list.get(0);
			if ( map.get("Subtotal")!=null) {
				result =map.get("Subtotal").toString();
				
			}
			
		}
		
		return result;
			
		
		
		
	}

	@Override
	public int updatePayType(int PayType,String orderCode) {
		if(orderCode!=null){
			if(!orderCode.startsWith("'")){
				orderCode = "'"+orderCode+"'";
			}
		}
		String sql = "update T_OrderBasic  set PayType="+PayType+" where OrderCode="+ orderCode + "";
		return getSession().createSQLQuery(sql).executeUpdate();
		
	}
	
	@Override
	public int updatePeisongStatus(int peisongStatus, String openId) {
		if(openId!=null){
			if(!openId.startsWith("'")){
				openId = "'"+openId+"'";
			}
		}
		String sql = "update T_MciroGrabBox  set PeisongStatus="+peisongStatus+" where OpenId="+ openId + "";
		return getSession().createSQLQuery(sql).executeUpdate();
		
	}

	@Override
	public List findallCart(String userId) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("SELECT                                                                     ");     
		sqlBuf.append("	a.CardNo,b.Balance									  ");
		sqlBuf.append("FROM									  ");
		sqlBuf.append("	T_ShopUserCardNo a							  ");
		sqlBuf.append("LEFT JOIN (								  ");
		sqlBuf.append("	SELECT									  ");
		sqlBuf.append("		u.CardNo,							  ");
		sqlBuf.append("		(								  ");
		sqlBuf.append("			d.FaceValue + d.GivenValue - ISNULL(			  ");
		sqlBuf.append("				(						  ");
		sqlBuf.append("					SELECT					  ");
		sqlBuf.append("						SUM (oic.[money])		  ");
		sqlBuf.append("					FROM					  ");
		sqlBuf.append("						T_OrderInCome oic		  ");
		sqlBuf.append("					WHERE					  ");
		sqlBuf.append("						oic.CardNumber = u.CardNo	  ");
		sqlBuf.append("				),						  ");
		sqlBuf.append("				0						  ");
		sqlBuf.append("			) + ISNULL(						  ");
		sqlBuf.append("				(						  ");
		sqlBuf.append("					SELECT					  ");
		sqlBuf.append("						SUM (cmc.ConvertMoney)		  ");
		sqlBuf.append("					FROM					  ");
		sqlBuf.append("						T_CardMoneyConvert cmc		  ");
		sqlBuf.append("					WHERE					  ");
		sqlBuf.append("						cmc.CardNumber = u.CardNo	  ");
		sqlBuf.append("				),						  ");
		sqlBuf.append("				0						  ");
		sqlBuf.append("			)							  ");
		sqlBuf.append("		) Balance 				  ");
		sqlBuf.append("	FROM									  ");
		sqlBuf.append("		T_ShopUserCardNo u						  ");
		sqlBuf.append("	INNER JOIN T_ShopCardOrderDetail d ON u.CardNo = d.CardNo		  ");
		sqlBuf.append("	INNER JOIN T_ShopCardNo cn ON u.CardNo = cn.CardNo			  ");
		sqlBuf.append(") b ON a.CardNo = b.CardNo						  ");
		sqlBuf.append("WHERE									  ");
		sqlBuf.append("	a.UserID = "+userId);
		sqlBuf.append("ORDER BY b.Balance DESC							  ");

		Query query = getSession().createSQLQuery(sqlBuf.toString());
		List cartlist = query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return cartlist;
	}

	@Override
	public List findlistnew(Integer userID, String orderCodes) {
		// TODO Auto-generated method stub
		
		String sqlBuf = "select a.OrderCode,sum(b.subtotal) total "+
				" from T_OrderBasic a LEFT JOIN T_OrderDetail b on a.OrderCode=b.OrderCode LEFT JOIN T_WeixinOrder wxo ON wxo.OrderCode=a.OrderCode "+
				 " where 1=1"+
				 " and wxo.WeixinOrderCode='"+ orderCodes +"' "+
                 " GROUP BY a.OrderCode";
		Query query = getSession().createSQLQuery(sqlBuf.toString());
		List cartlist = query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return cartlist;
	}
	
	@Override
	public List findlistnewWei(Integer userID, String orderCodes) {
		// TODO Auto-generated method stub
		
		String sqlBuf = "select a.OrderCode,sum(b.subtotal) total "+
				" from T_OrderBasic a LEFT JOIN T_OrderDetail b on a.OrderCode=b.OrderCode LEFT JOIN T_WeixinOrder wxo ON wxo.OrderCode=a.OrderCode "+
				 " where 1=1"+
				 " and wxo.OrderCode='"+ orderCodes +"' "+
                 " GROUP BY a.OrderCode";
		Query query = getSession().createSQLQuery(sqlBuf.toString());
		List cartlist = query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return cartlist;
	}

	@Override
	public List findReimburse(String orderCode) {
		// TODO Auto-generated method stub
		String sql = "select c.OrderCode,c.CardNumber,c.money from "
				 +"T_OrderInCome c "
				+"left join T_WeixinOrder b "
				 +"on  b.OrderCode=c.OrderCode "
				 +"where WeixinOrderCode='"+orderCode+"' and c.CardPayType=4";
		Query query=getSession().createSQLQuery(sql.toString());
		List reimburse=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return reimburse;
	}

	@Override
	public List getorderCodes(String WeixinOrderCode) {
		String sql = "SELECT WeixinOrderId,WeixinOrderCode,OrderCode from T_WeixinOrder where WeixinOrderCode='"+WeixinOrderCode+"'";
		Query query = getSession().createSQLQuery(sql);
		
		List weixinOrderCode = query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		
		return weixinOrderCode;
	}

	@Override
	public List getorderAccepttype(String orderCodde) {
		String sql="select * from T_OrderAccept where OrderCode='"+orderCodde+"'";
		Query query=getSession().createSQLQuery(sql);
		List accepttype=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return accepttype;
	}

	@Override
	public List getMoney(String orderCodde) {
		String sql="select b.CardPayType,b.money from T_OrderInCome b where OrderCode='"+orderCodde+"'";
		Query query=getSession().createSQLQuery(sql);
		List money=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return money;
	}

	@Override
	public List isChou(String openId, String SKU) {
		String sql="SELECT c.WX_OPENID from T_OrderBasic a JOIN T_OrderDetail b on a.UserId=b.UserId and a.OrderCode=b.OrderCode join T_UserBasic c  on a.UserId=c.UserID   WHERE c.WX_OPENID='"+openId+"' and b.SKU='"+SKU+"' AND  CONVERT(VARCHAR(10),a.AddTime,120)=CONVERT(VARCHAR(10),GETDATE(),120)";
		Query query=getSession().createSQLQuery(sql);
		List isChou=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return isChou;
	}

	@Override
	public List findZCAddress(String zccode) {
		// TODO Auto-generated method stub
		String sql="  select *  from T_CrowdFunding_OrderBasic_two where CROWDFUNDING_CODE='"+zccode+"'";
		Query query=getSession().createSQLQuery(sql);
		List ZCAddress=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return ZCAddress;
	}
}
