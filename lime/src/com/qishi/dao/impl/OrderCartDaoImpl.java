package com.qishi.dao.impl;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.qishi.dao.OrderCartDao;
import com.qishi.entity.OrderCart;
@Repository("OrderCartDao") 
public class OrderCartDaoImpl extends BaseDaoImpl<OrderCart> implements OrderCartDao{
	@Override
	public List<OrderCart> findCartgoodbysku(String userId, String sku, String openid) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("from OrderCart where 1=1");
		if(sku!=null){
			sql.append(" and SKU = "+sku);
		}
		if(userId!=null && !"null".equals(userId)){
			sql.append(" and UserId="+userId);
			
		}else if(openid!=null){
			
			sql.append(" and openId='"+openid+"'");
			
		}else{
			sql.append("错误");
		}
		List<OrderCart> cartgoods =getSession().createQuery(sql.toString()).list();
		
		
		return cartgoods;
	}

	@Override
	public List findcartgoodinfo(String userId, String openId) {
		// TODO Auto-generated method stub
		StringBuffer sql  = new StringBuffer("select oc.UserId,oc.openId,oc.sku,pbs.marketprice,pbs.sellPrice, pbs.ListPagePic ,pbf.ProName,oc.BuyNum from  T_ProductBasicSkuInfo"
				+ " pbs left join T_ProductBasicInfo pbf on pbs.ProId = pbf.ProId left join T_OrderCart oc on oc.SKU = pbs.ProSKUId "
				+ " where 1=1  ");
		
		if(userId!=null&& !"null".equals(userId)){
			sql.append(" and oc.UserId="+userId);
			
		}else if(openId!=null){
			
			sql.append(" and oc.openId='"+openId+"'");
			
		}else{
			
			sql.append("错误");
		}
		Query query =getSession().createSQLQuery(sql.toString());
		List goodsinfo =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return goodsinfo;
	}

	@Override
	public void deletecar(String userId, String openId, String sku) {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer("delete "); 
		System.out.println("dao"+userId+" "+openId+" "+sku);
		if(userId!=null&& !"null".equals(userId)){
			sql.append(" T_OrderCart where sku="+sku+" and UserId = "+userId);
			
		}else{
			sql.append(" T_OrderCart where sku="+sku+" and openId = '"+openId+"'");
		}
		
		System.out.println("执行到了dao"+sql.toString());
		getSession().createSQLQuery(sql.toString()).executeUpdate();
		
	}
	
	@Override
	public List findAllInfo(String UserId,String openId,String orderClass) {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer( "SELECT oc.SKU,oc.BuyNum,oc.OrderClass ,pbi.UnitMent,pbs.SellPrice,"
				+ "case when (pbs.WXProName IS NULL or pbs.WXProName='') then pbi.ProName else pbs.WXProName end WXProName "
				+ "from T_OrderCart oc LEFT JOIN T_ProductBasicSkuInfo pbs on oc.SKU = pbs.SKU"+
				" LEFT JOIN T_ProductBasicInfo pbi ON pbi.ProId = pbs.ProId WHERE 1=1 ");
		if(UserId!=null&& !"null".equals(UserId)){
			sql.append(" and oc.UserId = "+UserId);
			
		}else{
			sql.append(" and oc.openId =' "+openId+"'");
		}
		sql.append(" and oc.orderclass=" + orderClass);
		
		Query query =getSession().createSQLQuery(sql.toString());
		List tocatinfo =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		return tocatinfo;
	}
	
	@Override
	public void savetoOrderDetail(String orderCode,String UserId,String openId,String SKU,String ProNum,String Unit,String SellPrice,String SKUName,String sub) {
		// TODO Auto-generated method stub
		String sql = "insert into T_OrderDetail(OrderCode,UserId,SKU,ProNum,Unit,IsCountByWeight,BuyPrice,OldPrice,PromotionId,SKUName,Subtotal)"
				+ "values('"+orderCode+"','"+UserId+"','"+SKU+"','"+ProNum+"','"+Unit+"','0','"+SellPrice+"','"+SellPrice+"','0','"+SKUName+"','"+sub+"')";
		getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	@Override
	public void savetoOrderAcceptLog(String orderCode,String time,String zhuangtai,String LogType) {
		// TODO Auto-generated method stub
		String sql = "insert into T_OrderAcceptLog(LogTime ,LogContent ,Logger ,OrderCode ,LogSite ,IsShow, LogType )"
			+ "values('"+time+"','"+zhuangtai+"','0','"+orderCode+"','0','false','"+LogType+"')";
		getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	@Override
	public void savetoOrderBasic(String UserId,String openId,String orderCode,String time,String orderClasszong) {
		// TODO Auto-generated method stub
		String sql = "insert into T_OrderBasic(OrderCode,UserId ,AddTime ,Remark ,OrderType ,OrderClass ,PreferPrice,SaleSite ,SonOrderCcde ,Saler,PayType  ,PromotionId  )"
				+ " values('"+orderCode+"','"+UserId+"','"+time+"','此订单为网站线上订单','5','"+orderClasszong+"','0','52',' ','0','12','0')";
		getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	@Override
	public void savetoOrderInvoice() {
		// TODO Auto-generated method stub
		String sql = "insert into T_OrderInvoice(UserId,HeadType ,InvoiceType ,InvoiceInfo ,OrderCode ,IsOpen )"
				+ "values('userId','invoiceHead','invoiceType','invoiceInfo','orderCode','isOpen')";
		getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	@Override
	public void deleteAll(String UserId,String openId) {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer( "delete from T_OrderCart where 1=1");
		if(UserId!=null&& !"null".equals(UserId)){
			sql.append(" and UserId="+UserId);
		}else{
			sql.append(" and openId='"+openId+"'");
		}
		getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	@Override
	public void savetoOrderAccept(String orderCode,String time) {
		// TODO Auto-generated method stub
		String sql = "insert into T_OrderAccept(OrderCode,AcceptSite,Accpeted,AcceptMen,AcceptTime,AcceptType) values('"+orderCode+"','0','false','0','"+time+"','1')";
		getSession().createSQLQuery(sql.toString()).executeUpdate();
		
	}

	@Override
	public List findorderclass(String sku) {
		String sql = "select a.sku,c.OrderSplit from t_productbasicSKUinfo a LEFT JOIN T_ProductBasicInfo b on a.proid = b.ProId"+
				" LEFT JOIN T_ProductClass c on b.ClassId=c.ClassId"+
				" where sku ="+sku ;
		Query query =getSession().createSQLQuery(sql.toString());
		List orderclass =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		
		return orderclass;
	}

	@Override
	public List findAllOrderClass(String userId,String openId) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select DISTINCT OrderClass from T_OrderCart  where 1=1");
		if(userId!=null&& !"null".equals(userId)){
			sql.append(" and UserId = "+userId);
			
		}else{
			sql.append(" and openId = '"+openId+"'");
		}
		Query query =getSession().createSQLQuery(sql.toString());
		List allorderclass =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
		
		return allorderclass;
	}

	@Override
	public void updatorder(String userId, String openId) {
		// TODO Auto-generated method stub
		String sql="UPDATE T_OrderCart set userId="+userId+" where openId='"+openId+"'";
		getSession().createSQLQuery(sql.toString()).executeUpdate();
		
	}

	//立即支付
		@Override
		public List goPay(Integer sku) {
			// TODO Auto-generated method stub
			String sql = "select case when a.WXProName is null then b.ProName else a.WXProName end WXProName,c.OrderSplit,b.UnitMent,a.SellPrice "
	+"from T_ProductBasicSkuInfo a left join T_ProductBasicInfo b on a.ProId=b.ProId left join T_ProductClass c "
	+"on b.ClassId = c.ClassId "
	+"where a.SKU=?";
			Query query=getSession().createSQLQuery(sql).setParameter(0, sku);
			List orderDetail=query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
			return orderDetail;
		}

		@Override
		public void saveweixinorder(String big ,String dandanhao,String time) {
			// TODO Auto-generated method stub
			String rule = UUID.randomUUID().toString();
			
			String sql = "insert into T_WeixinOrder(WeixinOrderId,WeixinOrderCode,OrderCode,OrderTime) values('"+rule+"','"+big+"','"+dandanhao+"','"+time+"')";
			getSession().createSQLQuery(sql.toString()).executeUpdate();
		}

		@Override
		public List finddowncartgoodinfo(String userId, String openId) {
			// TODO Auto-generated method stub
			StringBuffer sql  = new StringBuffer("select oc.UserId,oc.openId,oc.sku,pbs.marketprice,pbs.sellPrice, pbs.ListPagePic ,pbf.ProName,oc.BuyNum from  T_ProductBasicSkuInfo"
					+ " pbs left join T_ProductBasicInfo pbf on pbs.ProId = pbf.ProId left join T_OrderCart oc on oc.SKU = pbs.ProSKUId "
					+ " where 1=1 and (pbs.IsShow=0 or pbs.ISWX=0 )");
			
			if(userId!=null&& !"null".equals(userId)){
				sql.append(" and oc.UserId="+userId);
				
			}else if(openId!=null){
				
				sql.append(" and oc.openId='"+openId+"'");
				
			}else{
				
				sql.append("错误");
			}
			Query query =getSession().createSQLQuery(sql.toString());
			List goodsinfo =query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
			return goodsinfo;
		}

		@Override
		public void savetoOrderBasichongbao(String userId, String openId,
				String orderCode, String time, String orderClasszong,String remark) {
			String sql = "insert into T_OrderBasic(OrderCode,UserId ,AddTime ,Remark ,OrderType ,OrderClass ,PreferPrice,SaleSite ,SonOrderCcde ,Saler,PayType  ,PromotionId  )"
					+ " values('"+orderCode+"','"+userId+"','"+time+"','"+remark+"','5','"+orderClasszong+"','0','52',' ','0','12','0')";
			getSession().createSQLQuery(sql.toString()).executeUpdate();
			
		}
		
		/**
		 * 订单总表OrderType=8 众筹订单
		 */
		@Override
		public void savetoOrderBasicZC(String UserId,String openId,String orderCode,String time,String orderClasszong,String payType) {
			// TODO Auto-generated method stub
			String sql = "insert into T_OrderBasic(OrderCode,UserId ,AddTime ,Remark ,OrderType ,OrderClass ,PreferPrice,SaleSite ,SonOrderCcde ,Saler,PayType  ,PromotionId  )"
					+ " values('"+orderCode+"','"+UserId+"','"+time+"','此订单为网站线上订单','8','"+orderClasszong+"','0','52',' ','0','"+payType+"','0')";
			getSession().createSQLQuery(sql.toString()).executeUpdate();
		}

		@Override
		public void saveweixinorderbox(String WeixinOrderId, String big,
				String dandanhao, String time) {
			// TODO Auto-generated method stub
					
			String sql = "insert into T_WeixinOrder(WeixinOrderId,WeixinOrderCode,OrderCode,OrderTime) values('"+WeixinOrderId+"','"+big+"','"+dandanhao+"','"+time+"')";
			getSession().createSQLQuery(sql.toString()).executeUpdate();
		
		}
	
}
