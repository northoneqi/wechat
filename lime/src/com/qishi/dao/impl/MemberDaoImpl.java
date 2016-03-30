package com.qishi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.qishi.dao.MemberDao;
import com.qishi.entity.UserBasic;

@Repository("MemberDao")
public class MemberDaoImpl extends BaseDaoImpl<UserBasic> implements MemberDao {
	/**
	 * 会员个人详细信息
	 */
	@Override
	public List memberInfo(Integer userId) {
		// TODO Auto-generated method stub
		String sql="select b.ParaName+c.ParaName+u.Address Address,u.NickName,u.Phone,u.PhoneCode  from T_UserBasic u 	LEFT JOIN (select paravalue,ParaName from T_Param where onlygroupflag ='Area') a on u.Province = a.paravalue	LEFT JOIN (select paravalue,ParaName from T_Param where onlygroupflag ='Area') b on u.City = b.paravalue	LEFT JOIN (select paravalue,ParaName from T_Param where onlygroupflag ='Area') c on u.Area = c.paravalue WHERE u.UserID ='"+userId+"'";
		Query query=getSession().createSQLQuery(sql);
		List memberInfo=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return memberInfo;
	}

}
