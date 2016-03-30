package com.qishi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qishi.dao.BaseDao;
import com.qishi.dao.MemberDao;
import com.qishi.entity.UserBasic;
import com.qishi.service.MemberService;

@Service("MemberService")
public class MemberServiceImpl extends BaseServiceImpl<UserBasic> implements
		MemberService {
	private MemberDao memberDao;

	@Autowired
	@Qualifier("MemberDao")
	@Override
	public void setBaseDao(BaseDao<UserBasic> memberDao) {
		// TODO Auto-generated method stub
		this.baseDao = memberDao;
		this.memberDao = (MemberDao) memberDao;
	}
	//会员个人详细信息
	@Override
	public List memberInfo(Integer userId) {
		// TODO Auto-generated method stub
		return memberDao.memberInfo(userId);
	}

}
