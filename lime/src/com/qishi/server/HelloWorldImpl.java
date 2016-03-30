package com.qishi.server;

import javax.jws.WebService;

@WebService(endpointInterface="com.qishi.server.IHelloWorld")
public class HelloWorldImpl implements IHelloWorld {
	public String sayHello(String text) {	
		return "Hello" + text ;
	}
}