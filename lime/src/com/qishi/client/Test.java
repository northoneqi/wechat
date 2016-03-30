package com.qishi.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qishi.server.CrowdfundingServer;
import com.qishi.server.IHelloWorld;



public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-client.xml");
		/*IHelloWorld client = (IHelloWorld) ctx.getBean("client");
		
		String result = client.sayHello(say);
		return result;*/
		
		CrowdfundingServer cfs=(CrowdfundingServer)ctx.getBean("orderclient");
		//String result= cfs.toOrder("1278","oLrN6uFpep7Syv5hliZreHAYKpec","888","1","12",1234);
		//System.out.println("返回次订单的单号："+result);
	
		//System.out.println("返回次订单的单号："+say("1278","oLrN6uFpep7Syv5hliZreHAYKpec","888","1","12",1234));
	}
}