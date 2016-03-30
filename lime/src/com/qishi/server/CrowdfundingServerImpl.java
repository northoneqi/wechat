package com.qishi.server;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.qishi.entity.OrderInCome;
import com.qishi.entity.UserBasic;
import com.qishi.service.GoodsService;
import com.qishi.service.OrderCartService;
import com.qishi.service.OrderInComeService;
import com.qishi.service.OrderService;
import com.qishi.service.UserBasicService;
import com.qishi.util.AddressRule;
import com.qishi.util.BigorderRule;
import com.qishi.util.OrderRule;

@WebService(endpointInterface="com.qishi.server.CrowdfundingServer")
@Service("CrowdfundingServer")
public class CrowdfundingServerImpl implements CrowdfundingServer {

	@Autowired
	@Qualifier("OrderService")
	private OrderService orderService;
	
	@Autowired
	@Qualifier("userBasicService")
	private UserBasicService userBasicService;
	
	@Autowired
	@Qualifier("OrderCartService")
	private OrderCartService orderCartService;
	
	@Autowired  
	@Qualifier("goodsService")
	private GoodsService goodsservice;
	
	@Autowired
	@Qualifier("OrderInComeService")
	private OrderInComeService orderInComeService;
	
	@Override
	public String toOrder(String SKU, String openId,String SellPrice,String ProNum,String payType,String zccode) {
		/*openId="oLrN6uFpep7Syv5hliZreHAYKpec";
		SKU="1278";
		String SellPrice="888";
		String ProNum="1";
		String payType ="12";   //支付类型
		Integer zccode=1234;*/
		System.out.println("1.获得的参数"+SKU+openId+SellPrice+ProNum+payType+zccode);
		try{
			
			System.out.println("2.进入生成订单");
			UserBasic userBasic = new UserBasic();
			userBasic.setWX_OPENID(openId);
			userBasic = userBasicService.findUser(userBasic);
			String UserId = String.valueOf(userBasic.getUserID());
			/*if (UserId != null) {
				orderCartService.updateorder(UserId, openId);
			}*/
			OrderRule rule = new OrderRule();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfwx = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			String timewx = sdfwx.format(date);
			BigorderRule bigrule = new BigorderRule();
			// 生成微信订单号
			String big = bigrule.rule();
			// 生成订单号
			String orderCode = rule.GetOrderCode(5);
			//orderClasslist.add(orderCode);
			// 查找保存订单需要的全部信息
			List toorderlist = goodsservice.findgoodInfo(SKU);
			System.out.println("查到的商品"+toorderlist.size());
			for (int i = 0; i < toorderlist.size(); i++) {
				Map ordermap = (Map) toorderlist.get(i);
				String SKUName = String.valueOf(ordermap.get("ProName"));
				int num = Integer.parseInt(ProNum);
				System.out.println("3.进入循环取商品信息，获取参数数量"+num);
				
				Double price =Double.parseDouble(SellPrice);
				
				String OrderClass = String.valueOf(ordermap.get("OrderSplit"));
				
				SKU = String.valueOf(ordermap.get("SKU"));
				String Unit = String.valueOf(ordermap.get("UnitMent"));
				
				String sub = String.valueOf(num * price);
				System.out.println("4. 进入插入流程");
				// 订单详细
				orderCartService.savetoOrderDetail(orderCode, UserId, openId, SKU,
						ProNum, Unit, SellPrice, SKUName, sub);
				// 绑定微信订单信息
				orderCartService.saveweixinorder(big, orderCode, timewx);
				// 订单受理日志信息
				orderCartService.savetoOrderAcceptLog(orderCode, time, "此订单是众筹",
						"1");
				// 绑定受理表信息
				orderCartService.savetoOrderAccept(orderCode, time);
				// 订单总表
				orderCartService.savetoOrderBasicZC(UserId, openId, orderCode, timewx, OrderClass, payType);
				
				if(checkorderincome((String)orderCode)){
					System.out.println("5. 进入扣款插入流程");
					
					OrderInCome od = new OrderInCome();
					
					od.setOrderCode((String)orderCode);
					od.setMoney(Double.parseDouble(sub));
					od.setCardPayType(12);
					od.setOutOrInType(2);
					od.setRemark("众筹支付");
					od.setAddTime(new Date());
					orderInComeService.save(od);
					
				}
			}
				
			//绑定订单配送信息
			//int zccodes=Integer.parseInt(zccode);
			List orderAddress = orderService.findZCAddress(zccode);
			System.out.println(orderAddress.get(0).toString());
			for (int i = 0; i < orderAddress.size(); i++) {
				System.out.println("6. 进入配送插入流程");
				Map map = (Map) orderAddress.get(i);
				String OConsignee=map.get("NAME").toString();
				String Address=map.get("ADDRESS").toString();
				AddressRule addressrule = new AddressRule();
				String Area=addressrule.rule(map.get("AREA").toString());
				String OMbile=map.get("TEL").toString();
				long riqi=(long) map.get("SEND_DATE");
			    Date dat=new Date(riqi);  
			    GregorianCalendar gc = new GregorianCalendar();   
			    gc.setTime(dat);  
			    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
			    String riqis=format.format(gc.getTime());  
				String shijian=map.get("SEND_TIMES").toString();
				String OTelephone="";
			orderInComeService.savepeisong(OConsignee,Address,Area,OMbile,riqis,shijian,orderCode,userBasic.getUserID(),OTelephone);
			orderInComeService.updateOrderinfo(orderCode);
			orderCartService.savetoOrderAcceptLog(orderCode, time,"已支付","2");
			}
		
			return orderCode;
		
		}catch(Exception ex){
			
			return "";
		}
	}
	/**
	 * 验证是否在orderincome存在记录
	 * @param ordercode
	 * @return
	 */
		private boolean checkorderincome(String ordercode) {
			// TODO Auto-generated method stub
			 List list = orderInComeService.findincome(ordercode);
			if(list.size()>0){
			return false;
			}else{
			return true;	
			}
		}
	
}
