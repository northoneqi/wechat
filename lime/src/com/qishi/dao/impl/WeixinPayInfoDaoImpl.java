package com.qishi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.qishi.dao.WeixinPayInfoDao;
import com.qishi.entity.WeixinPayInfo;
@Repository("WeixinPayInfoDao")
public class WeixinPayInfoDaoImpl extends BaseDaoImpl<WeixinPayInfo> implements WeixinPayInfoDao{

	@Override
	public List findOutTradeNo(String Ordercode) {
			String sql="select * from T_WeixinPayInfo where OutTradeNo='"+Ordercode+"'";
			Query query = getSession().createSQLQuery(sql.toString());
			List list = query.setResultTransformer(
					CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
			return list;
			
		}

}
