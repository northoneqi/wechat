package com.qishi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.qishi.dao.UserBasicDao;
import com.qishi.dao.UserDao;
import com.qishi.entity.OrderAddressDetailed;
import com.qishi.entity.User;
import com.qishi.entity.UserBasic;

@Repository("userBasicDao")
public class UserBasicDaoImpl extends BaseDaoImpl<UserBasic> implements
		UserBasicDao {

	public UserBasic finduser(UserBasic u) {
		String hql = "from UserBasic where wx_openid='"+ u.getWX_OPENID() +"'" ;
		Query query = getSession().createQuery(hql);
		List list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		return (UserBasic)list.get(0); 
	}

	public int finduserCount() {
		// TODO Auto-generated method stub
		int a = findAllcount();
		return a;
	}

	@Override
	public List<UserBasic> getUserByUsername(String Username) {
		String hql = "from UserBasic where Username='" + Username
				+ "' and WX_OPENID is null ";
		Query query = getSession().createQuery(hql);
		List<UserBasic> list = query.setResultTransformer(
				CriteriaSpecification.ROOT_ENTITY).list();
		return list;
	}

	/**
	 * 根据用户表内的市区id查找市区
	 */
	@Override
	public List<UserBasic> findUserAndAddress(int userId) {
		//"SELECT *,paraname from T_UserBasic  o1 "
				//+ " LEFT JOIN  (select paravalue,paraname from  T_Param p1  where p1.OnlyGroupFlag = 'Area' ) "
				//+ " o2 on o1.Area=o2.ParaValue  where UserId =" 
		String sql = "select * from T_UserBasic where userID ="+ userId;
		return getSession().createSQLQuery(sql).addEntity(
				UserBasic.class).list();
	}

	@Override
	public void updateOpenid(String openID,String UserID) {
		String sql = "update T_UserBasic set WX_OPENID='"+openID+"' where UserID="+UserID;
		getSession().createSQLQuery(sql);
		
	}

}
