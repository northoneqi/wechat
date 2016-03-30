package org.qishi.micromark.pojo;

public class OrderResult {
	
	private int errcode;
	private String errmsg;
	private OrderInfo order_info;
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public OrderInfo getOrder_info() {
		return order_info;
	}
	public void setOrder_info(OrderInfo order_info) {
		this.order_info = order_info;
	}
	

}
