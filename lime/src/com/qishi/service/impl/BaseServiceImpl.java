package com.qishi.service.impl;

import com.qishi.dao.BaseDao;
import com.qishi.service.BaseService;

public abstract class BaseServiceImpl<M  extends java.io.Serializable> implements BaseService<M> {

	  	protected BaseDao<M> baseDao;
	    
	    public abstract void setBaseDao(BaseDao<M> baseDao);
	

	@Override
	public M save(M model) {
		baseDao.save(model);
		return model;
	}

	@Override
	 public void saveOrUpdate(M model) {
        baseDao.saveOrUpdate(model);
    }

	@Override
	public void update(M model) {
        baseDao.update(model);
    }

	@Override
	public void merge(M model) {
        baseDao.merge(model);
    }

	
	@Override
	public void flush() {
		baseDao.flush();
		
	}

	@Override
	public void clear() {
		baseDao.clear();
		
	}
	
}
