package com.qishi.service;

import java.util.List;

import com.qishi.entity.UserBasic;

public interface MemberService extends BaseService<UserBasic> {
	public List memberInfo(Integer userId);
}
