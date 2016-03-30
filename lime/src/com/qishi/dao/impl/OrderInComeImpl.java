package com.qishi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qishi.dao.OrderInComeDao;
import com.qishi.entity.OrderInCome;
import com.qishi.util.AddressRule;
@Repository("OrderInComeDao")
public class OrderInComeImpl extends BaseDaoImpl<OrderInCome> implements OrderInComeDao{

	@Override
	public void savepeisong(String OConsignee, String Address, String Area,
			String OMbile, String riqi, String shijian, String OrderCode,
			Integer userID,String OTelephone) {
		// TODO Auto-generated method stub
		AddressRule rule =  new AddressRule();
		//转成地区代号
		Area=rule.rule(Area);
		String sql = "insert into T_OrderAddress(Consignee,Address,Province,City,Area,Mobile,UserId,SendDate,SendTimeSpan,SendDateDetail,isCall,DistriMoney,DistriType,OrderCode,Telephone) "
				+ "VALUES('"+OConsignee+"','"+Address+"','1','1','"+Area+"','"+OMbile+"','"+userID+"','"+riqi+"','"+shijian+"','0',0,'0','0','"+OrderCode+"','"+OTelephone+"')";
		getSession().createSQLQuery(sql.toString()).executeUpdate();
		
	}

	@Override
	public void updateOrderinfo(String orderCode) {
		// TODO Auto-generated method stub  accepttype= 10
		String sql = "update  T_OrderAccept SET accepttype = '2' where OrderCode='"+orderCode+"'";
		getSession().createSQLQuery(sql.toString()).executeUpdate();
		
	}

	@Override
	public List findincome(String orderCode) {
		// TODO Auto-generated method stub
		 String sql = "SELECT * from T_OrderInCome where ordercode = '"+orderCode+"'";
		 List list =getSession().createSQLQuery(sql.toString()).list();
		 return list;
	}

}
