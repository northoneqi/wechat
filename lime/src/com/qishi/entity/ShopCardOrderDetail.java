package com.qishi.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ShopCardOrderDetail")
public class ShopCardOrderDetail implements Serializable{
	
	private  int CardOrderId ;
	
	private String OrderCode ;
	
	private String SunOrderCode ;
	
	private int OrderDetailId ;
	
	private String CarNo ;
	
	private double FaceValue ;
	
	private double GivenValue ;
	
	private boolean Type ;
	
	private boolean DateState ;
	
	private String Remark ;
	
	@Id
	@Column(name="CardOrderId")
	@GeneratedValue
	public int getCardOrderId() {
		return CardOrderId;
	}
	public void setCardOrderId(int cardOrderId) {
		CardOrderId = cardOrderId;
	}
	public String getOrderCode() {
		return OrderCode;
	}
	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}
	public String getSunOrderCode() {
		return SunOrderCode;
	}
	public void setSunOrderCode(String sunOrderCode) {
		SunOrderCode = sunOrderCode;
	}
	public int getOrderDetailId() {
		return OrderDetailId;
	}
	public void setOrderDetailId(int orderDetailId) {
		OrderDetailId = orderDetailId;
	}
	public String getCarNo() {
		return CarNo;
	}
	public void setCarNo(String carNo) {
		CarNo = carNo;
	}
	public double getFaceValue() {
		return FaceValue;
	}
	public void setFaceValue(double faceValue) {
		FaceValue = faceValue;
	}
	public double getGivenValue() {
		return GivenValue;
	}
	public void setGivenValue(double givenValue) {
		GivenValue = givenValue;
	}
	public boolean isType() {
		return Type;
	}
	public void setType(boolean type) {
		Type = type;
	}
	public boolean isDateState() {
		return DateState;
	}
	public void setDateState(boolean dateState) {
		DateState = dateState;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	
	
}
