package com.qishi.server;


import javax.jws.WebService;

@WebService
public interface CrowdfundingServer {
	public String toOrder(String SKU, String openId,String SellPrice,String ProNum,String payType,String zccode);
	
}
