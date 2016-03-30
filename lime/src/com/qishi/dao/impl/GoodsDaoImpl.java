package com.qishi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.qishi.dao.GoodsDao;
import com.qishi.entity.ProductBasicInfo;
import com.qishi.entity.ProductBasicSkuInfo;
import com.qishi.entity.ProductClass;
import com.qishi.util.StringUtil;

@Repository("goodsDao") 
public class GoodsDaoImpl extends BaseDaoImpl<ProductBasicInfo> implements GoodsDao  {

	public List<ProductClass> findclass() {
		String hql = "from ProductClass where ISWXSHOW='1'";
		List<ProductClass> a= getSession().createQuery(hql).list();
		List<ProductClass> classlist =new ArrayList();
		for(int i=0;i<a.size();i++){
		String classid = a.get(i).getClassId();
		List check = findgoods(classid,null,null);
		if(check.size()!=0){
			classlist.add(a.get(i));
		}
		}
		return classlist;
	}
	public List findclassid(String classid) {
		String sql = "select c.ClassId FROM T_ProductClass c where c.ParentId in( "+
					" select b.ClassId FROM T_ProductClass b where b.ParentId in ( "+
	                " select ClassId from T_ProductClass  a  where a.ClassId='"+classid+"' "+
	              "  ))"+
				" UNION "+
				    " select b.ClassId FROM T_ProductClass b where b.ParentId in ( "+
				    " select ClassId from T_ProductClass  a  where a.ClassId='"+classid+"' "+
	               " ) "  +             
			   " UNION "+
			    	 "select ClassId from T_ProductClass  a  where a.ClassId='"+classid+"' ";
		Query query =getSession().createSQLQuery(sql.toString());
		List a =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return a;
	}
	public List findgoods(String classid,String SkuFlag,String indexyingyang) {
		// TODO Auto-generated method stub
		List b = findclassid(classid);
		System.out.println(b.toString());
		List goodlist = new ArrayList();
		for(int j=0;j<b.size();j++){
			Map map = (Map) b.get(j);
			classid =  map.get("ClassId").toString();
			StringBuffer sql =new StringBuffer("select pb.ProName ProName,pbs.ListPagePic ListPagePic,pbs.SellPrice,pbs.ProSKUId  ProSKUId,pbs.WXPRONAME WXPRONAME ,pbs.SKU SKU ,pc.OrderSplit OrderSplit from T_ProductBasicInfo pb ").
					append(" left join T_ProductBasicSkuInfo pbs on pb.Proid = pbs.Proid left join T_ProductClass pc on pc.ClassId = pb.ClassId where 1=1 and pb.ISWX=1 and pbs.ISWX=1 and pbs.IsShow=1 ");
			if (StringUtil.isNotEmpty(classid)) {
				sql.append("and pb.ClassId = '").append(classid).append("'");
			}
			if (StringUtil.isNotEmpty(SkuFlag)) {
				sql.append("and pbs.SkuFlag='").append(SkuFlag).append("'");
			}
			if (StringUtil.isNotEmpty(indexyingyang)) {
				sql.append("and  pb.ClassId in (SELECT ClassId from T_ProductClass where ParentId in ('452','477','473')) ");
			}
					
			//String sql = "select ProName from  T_ProductBasicInfo ";
		Query query =getSession().createSQLQuery(sql.toString());
		List a =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
			goodlist.addAll(a);
		}
		
		
	

	return goodlist;
	}

	
//ColumnID	101-��һ�Ž���ͼ(����ҳ)  77-�ڼ��ؼ�    79-��Ʒ�ϼ�
	@Override
	public List indexbanner() {
		String sql ="select Title,Href,HrefImg from T_Column_Consult where ConsultID in (select ConsutID from T_Column_ColumnConsut where ColumnID in( select ColumnID from T_Column_ColumnInfo where ColumnID = '101'))";
		Query query = getSession().createSQLQuery(sql);
		List list = query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		
		return list;
	}

	
	//ColumnID  77-�ڼ��ؼ�    79-��Ʒ�ϼ�     80-��ʱ����   
	@Override
	public List indexgoods(String ColumnId) {
		
		String sql ="select Href from T_Column_Consult where ConsultID in (select ConsutID from T_Column_ColumnConsut where ColumnID in( select ColumnID from T_Column_ColumnInfo where ColumnID = "+ColumnId+"))";
		Query query = getSession().createSQLQuery(sql);
		List list = query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		
		return list;
	}

	@Override
	public List indexGetSku(String skuids) {
		System.out.println(skuids+"------------------!!!!!");
		// TODO Auto-generated method stub
				StringBuffer sql =new StringBuffer("select pb.ProName ProName,pbs.ListPagePic ListPagePic,pbs.SellPrice,pbs.ProSKUId  ProSKUId from T_ProductBasicInfo pb ").
						append(" left join T_ProductBasicSkuInfo pbs on pb.Proid = pbs.Proid where 1=1 and pbs.IsShow=1  and pbs.ISWX=1 ");
				
				
				if (StringUtil.isNotEmpty(skuids)) {
					sql.append("and  pbs.ProSKUId in (").append(skuids).append(") ");
				}
						
				
				//String sql = "select ProName from  T_ProductBasicInfo ";
			Query query =getSession().createSQLQuery(sql.toString());
			List list =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
			

			return list;
	}

	public List LotteryGetSku(String skuid){
		String sql = "select pb.ProName ProName,pbs.ListPagePic ListPagePic,pbs.SellPrice,pbs.ProSKUId  ProSKUId from T_ProductBasicInfo pb "
				+ " left join T_ProductBasicSkuInfo pbs on pb.Proid = pbs.Proid where 1=1  and "
				+ " pbs.ProSKUId = '"+skuid+"'";
		
		Query query =getSession().createSQLQuery(sql);
		List list =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		
		return list;
	}
	
	public List findgoodsku(String proskuid, String param1, String param2) {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer("select pb.ProName ProName,pbs.ListPagePic ListPagePic,pbs.SellPrice,pbs.volume volume,pb.ProDescri ProDescri,pbs.MarketPrice MarketPrice,pbs.ProSKUId,pc.OrderSplit from T_ProductBasicInfo pb ").
				append(" left join T_ProductBasicSkuInfo pbs on pb.Proid = pbs.Proid left join T_ProductClass pc on pc.ClassId = pb.ClassId where 1=1 ");
		sql.append(" and pbs.ProSKUId ='").append(proskuid).append("' ");
		
		Query query =getSession().createSQLQuery(sql.toString());
		List list =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	//查找商品详细详细
	public List findgoodInfo(String proskuid) {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer("select pbs.ISWX, pbs.SKU,pb.UnitMent,pbs.SellPrice,pb.ProName,pc.OrderSplit from T_ProductBasicInfo pb ").
				append(" left join T_ProductBasicSkuInfo pbs on pb.Proid = pbs.Proid left join  T_ProductClass pc on pc.ClassId = pb.ClassId where 1=1 ");
				sql.append(" and pbs.ProSKUId ='").append(proskuid).append("' ");
		Query query =getSession().createSQLQuery(sql.toString());
		List list =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	
	//健康套餐
	@Override
	public List findHealthGood() {
		String sql = 	"SELECT a.ClassId,a.[Level] from T_ProductClass  a JOIN( "+
						" select ClassId,[Level] "+
						" from T_ProductClass "+
						" where ParentId=452 and ClassId not in (453,454,463,477)) b ON a.ParentId = b.ClassId ";
		Query query = getSession().createSQLQuery(sql.toString());
		List list =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		String classid = null;
		System.out.println(list.toString());
		List goodlist = new ArrayList();
		for(int j=0;j<list.size();j++){
			Map map = (Map) list.get(j);
			classid =  map.get("ClassId").toString();
			StringBuffer sql2 =new StringBuffer("select pb.ProName ProName,pbs.ListPagePic ListPagePic,pbs.SellPrice,pbs.ProSKUId  ProSKUId,pbs.WXPRONAME WXPRONAME ,pbs.SKU SKU from T_ProductBasicInfo pb ").
					append(" left join T_ProductBasicSkuInfo pbs on pb.Proid = pbs.Proid where 1=1 and pbs.ListPagePic is not null  and IsShow=1 ");
			if (StringUtil.isNotEmpty(classid)) {
				sql2.append("and pb.ClassId = '").append(classid).append("'");
			}
			//String sql = "select ProName from  T_ProductBasicInfo ";
		Query query2 =getSession().createSQLQuery(sql2.toString());
		List a =query2.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		goodlist.addAll(a);
		System.out.println("!!!!!!!!!!!!!!!"+a.toString());
		}
		System.out.println("!!!!!!!!!!!!!!!"+goodlist.toString());
		return goodlist;
	}
	/**
	 * 过滤是否是微信的ProSKUId
	 */
	@Override
	public List wxProSKUId(String proSKUId) {
		String sql="select ProSKUId from T_ProductBasicSkuInfo where ProSKUId="+proSKUId+" and ISWX=1 and IsShow=1";
		Query query=getSession().createSQLQuery(sql.toString());
		List getwxProSKUId=query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return getwxProSKUId;
	}

}
