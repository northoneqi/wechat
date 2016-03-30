package com.qishi.dao;

import java.io.Serializable;
import java.util.List; 

  




import org.hibernate.SessionFactory;  
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository; 

import com.qishi.entity.ShopCardOrderDetail;
import com.qishi.entity.User;



public interface BaseDao<M extends java.io.Serializable> {
	
	//查询全部内容
	public List<M> findAll();
	//跟新
	public void update(M model);
	//删除
	public void delete(M model);
	//保存
	public void save(M model);
	//保存或跟新
	public void saveOrUpdate(M model);
	//查询并根据列名排序
	public List<M> findAllbyorder(String lieming ,String shunxi);
	//分页查询
	public List<M> findbypage(int page,int pagesize);
	//根据列名查询某一个数据
	public List<M> findbyleiming(String ziduan,String liezhi);
	//查询全部内容求总数量
	public int findAllcount();
	
	 public void merge(M model);
	 
	 public void flush();
	    
	    public void clear();


	


}

  

