package com.qishi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.qishi.dao.SearchDao;
import com.qishi.entity.ProductBasicInfo;

@Repository("searchDao")
public class SearchDaoImpl extends BaseDaoImpl implements SearchDao {

	@Override
	public List searchGoods(String goodName) {
		String sql = "select T1.ProId ProId,T1.ProName ProName,t3.ListPagePic ListPagePic,"
				+ " t3.SellPrice SellPrice,t3.ProSKUId ProSKUId,T3.WXPRONAME WXPRONAME,t3.SKU SKU "
				+ " from T_ProductBasicInfo T1  JOIN( "
				+ " select c.ClassId from T_ProductClass c JOIN ( "
				+ " SELECT a.ClassId,a.[Level] from T_ProductClass  a JOIN("
				+ " select ClassId,[Level]"
				+ " from T_ProductClass "
				+ " where ISWXSHOW=1 "
				+ " and level=1) b ON a.ParentId = b.ClassId) d "
				+ " ON c.ParentId = d.ClassId "
				+ " UNION SELECT a1.ClassId  from T_ProductClass  a1 JOIN( "
				+ " select ClassId,[Level] from T_ProductClass "
				+ " where ISWXSHOW=1 "
				+ " and level=2) b1 ON a1.ParentId = b1.ClassId "
				+ " UNION  SELECT a2.ClassId  from T_ProductClass a2 "
				+ " where ISWXSHOW=1 "
				+ " and [Level]=3  "
				+ " ) t2 on T1.ClassId=t2.ClassId join T_ProductBasicSkuInfo t3 on T1.ProId=t3.ProId "
				+ " where T1.ISWX=1 and t3.iswx=1 AND T1.ProName like '%"+goodName+"%' ";
		
		Query query = getSession().createSQLQuery(sql.toString());
		List list =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}

	@Override
	public List searchbysku(String sku) {
		// TODO Auto-generated method stub
		String sql = "select IsShow from T_ProductBasicSkuInfo where SKU='"+sku+"' and ISWX=1;";
		
		Query query = getSession().createSQLQuery(sql.toString());
		List list =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
		
		
		
	}
}
