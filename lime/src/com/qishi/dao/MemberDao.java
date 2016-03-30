package com.qishi.dao;

import java.util.List;

import com.qishi.entity.UserBasic;

public interface MemberDao extends BaseDao<UserBasic> {
	public List memberInfo(Integer userId);
}
