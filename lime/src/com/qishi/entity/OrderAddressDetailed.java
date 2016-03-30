package com.qishi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 送货地址
 * @author Administrator
 *
 */
@Entity
public class OrderAddressDetailed implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer addressId; 
	private String consignee;					//收货人
	private String address;						//详细地址
	private Integer	province;
	private Integer	city;
	private Integer	area;	//区
	private Integer	range;							//商业圈
	private String mobile;						//手机
	private String telephone;					//固话
	private String email;						//邮件
	private String postCode;					//邮编
	private Integer	userId;							//用户id
	private Date sendDate;						//送货时间
	private Integer	sendTimeSpan;					//送货时间段
	private Integer isCall;						//是否电话确认
	private Integer	distriType;						//配送方式
	private Double	distriMoney;			//运费
	private Integer	sendDateDetail;					//送货日期（参数表：只周六日送，工作日送，全送）
	private Integer	tranStation;					//配送站点
	private String	orderCode;
	private int defAddress;
	
	//private String cityDetailed;
	private String paraName;
	private String paraValue;
	
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getRange() {
		return range;
	}
	public void setRange(Integer range) {
		this.range = range;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Integer getSendTimeSpan() {
		return sendTimeSpan;
	}
	public void setSendTimeSpan(Integer sendTimeSpan) {
		this.sendTimeSpan = sendTimeSpan;
	}
	public Integer isCall() {
		return isCall;
	}
	public void setCall(Integer isCall) {
		this.isCall = isCall;
	}
	public Integer getDistriType() {
		return distriType;
	}
	public void setDistriType(Integer distriType) {
		this.distriType = distriType;
	}
	public Double getDistriMoney() {
		return distriMoney;
	}
	public void setDistriMoney(Double distriMoney) {
		this.distriMoney = distriMoney;
	}
	public Integer getSendDateDetail() {
		return sendDateDetail;
	}
	public void setSendDateDetail(Integer sendDateDetail) {
		this.sendDateDetail = sendDateDetail;
	}
	public Integer getTranStation() {
		return tranStation;
	}
	public void setTranStation(Integer tranStation) {
		this.tranStation = tranStation;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
/*	public String getDefAddressId() {
		return defAddressId;
	}
	public void setDefAddressId(String defAddressId) {
		this.defAddressId = defAddressId;
	}*/
	public int getDefAddress() {
		return defAddress;
	}
	public void setDefAddress(int defAddress) {
		this.defAddress = defAddress;
	}
	public String getParaValue() {
		return paraValue;
	}
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	

}
