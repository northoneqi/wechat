package com.qishi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qishi.dao.SearchDao;
import com.qishi.service.SearchService;
@Service("searchService")
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SearchDao searchDao;
	@Override
	public List search(String goodName) {
		return searchDao.searchGoods(goodName);
	}
	@Override
	public int searchbysku(String sku) {
		// TODO Auto-generated method stub
		int isshow=1;
		List list=searchDao.searchbysku(sku);
		try{
			Map check = (Map) list.get(0);
			System.out.println(check.get("IsShow"));
			boolean show =  (boolean) check.get("IsShow");
			if(show){
				 isshow = 1;
			}else{
				 isshow = 0;
			}
		}catch(Exception e){
			isshow = 0;
			return isshow;
		}
		return isshow;
	}
}
