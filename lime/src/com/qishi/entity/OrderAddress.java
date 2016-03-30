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

@Entity
@Table(name="T_OrderAddress")
public class OrderAddress implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "AddressId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer addressId; 
	private String consignee;					//鏀惰揣浜�
	private String address;						//璇︾粏鍦板潃
	private Integer	province;
	private Integer	city;
	private Integer	area;	//鍖�
	private Integer	range;							//鍟嗕笟鍦�
	private String mobile;						//鎵嬫満
	private String telephone;					//鍥鸿瘽
	private String email;						//閭欢顔�
	private String postCode;					//閭紪
	private Integer	userId;							//鐢ㄦ埛id
	private Date sendDate;						//閫佽揣鏃堕棿
	private Integer	sendTimeSpan;					//閫佽揣鏃堕棿娈�
	private Integer isCall;						//鏄惁鐢佃瘽纭
	private Integer	distriType;						//閰嶉�鏂瑰紡
	private Double	distriMoney;			//杩愯垂
	private Integer	sendDateDetail;					//閫佽揣鏃ユ湡锛堝弬鏁拌〃锛氬彧鍛ㄥ叚鏃ラ�锛屽伐浣滄棩閫侊紝鍏ㄩ�锛�
	private Integer	tranStation;					//閰嶉�绔欑偣
	private String	orderCode;
	private Integer defAddress;
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
	public Integer getIsCall() {
		return isCall;
	}
	public void setIsCall(Integer isCall) {
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
	public Integer getDefAddress() {
		return defAddress;
	}
	public void setDefAddress(Integer defAddress) {
		this.defAddress = defAddress;
	}
	
}
