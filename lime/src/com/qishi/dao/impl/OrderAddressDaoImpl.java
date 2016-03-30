package com.qishi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.qishi.dao.OrderAddressDao;
import com.qishi.entity.OrderAddress;
import com.qishi.entity.OrderCommonAddress;
import com.qishi.util.StringUtil;

/***
 * 送货地址DAO
 * 
 * @author Administrator
 * 
 */
@Repository("orderAddressDao")
public class OrderAddressDaoImpl extends BaseDaoImpl<OrderAddress> implements
		OrderAddressDao {

	@Override
	public List<OrderAddress> findAddress(int userId) {
		String sql = " select b.ParaName+c.ParaName+u.OAddress OAddress,"
				+ " u.OAddressID,u.OConsignee ,u.OAddress ,u.OProvince,u.OCity,u.OArea,u.ORange,u.OMbile,u.OTelephone,u.UserId,u.OEmail,u.OPostCode,u.TranStation,u.DefAddress"
				+ " from T_OrderCommonAddress u "
				+ "	LEFT JOIN (select paravalue,ParaName from T_Param where onlygroupflag ='Area') a on u.OProvince = a.paravalue "
				+ "	LEFT JOIN (select paravalue,ParaName from T_Param where onlygroupflag ='Area') b on u.OCity = b.paravalue "
				+ " LEFT JOIN (select paravalue,ParaName from T_Param where onlygroupflag ='Area') c on u.OArea = c.paravalue "
				+ " WHERE u.UserId =" + userId;
		return getSession().createSQLQuery(sql).addEntity(OrderCommonAddress.class).list();
	}

	@Override
	public void defaultAddress(int userId, String addressId) {
		System.out.println(userId + addressId);
		String sql = " update T_OrderCommonAddress set defAddress=0 where userId = "
				+ userId
				+ " ;  update T_OrderCommonAddress set defAddress=1 where OAddressID= "
				+ addressId;// 防止报错
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List getDefAddess(String userId, String defAddess) {
		StringBuffer sql= new StringBuffer(" select a.ParaName Province,b.ParaName City,c.ParaName Area,u.OAddress Address,u.OConsignee,u.OMbile,u.OTelephone from T_OrderCommonAddress u  " );
		sql.append(" LEFT JOIN (select paravalue,ParaName from T_Param where onlygroupflag ='Area') a on u.OProvince = a.paravalue " );
		sql.append(" LEFT JOIN (select paravalue,ParaName from T_Param where onlygroupflag ='Area') b on u.OCity = b.paravalue " );
		sql.append(" LEFT JOIN (select paravalue,ParaName from T_Param where onlygroupflag ='Area') c on u.OArea = c.paravalue where 1=1 " );
		if (StringUtil.isNotEmpty(userId)) {
			sql.append(" and  u.UserID =").append(userId).append(" ");
		}
		
		if (StringUtil.isNotEmpty(defAddess)) {
			sql.append(" and  u.DefAddress ='").append(defAddess).append("' ");
		}
		Query query = getSession().createSQLQuery(sql.toString());
		List list =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
}
