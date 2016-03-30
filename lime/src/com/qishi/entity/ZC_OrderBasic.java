package com.qishi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="T_ZC_OrderBasic")
public class ZC_OrderBasic implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String zccode;
	private Integer sku;
	private String Province;
	private String City;
	private String area;
	private String address;
	private Integer copies;
	private Integer isfinish;
	private String WX_OrderCode;
	private Date addTime;
	private Date EndTime;
	private String OpenId;
	private String Name;
	private Integer BuyNumber;
	private Integer iscancel;
	private String tel;
	private String ispayment;
	private String payurl;
	private String senddate;
	private Integer sendtimespan;
	private String skuname;
	private String goodprice;
	private Date paymenttime;
	public String getGoodprice() {
		return goodprice;
	}
	public void setGoodprice(String goodprice) {
		this.goodprice = goodprice;
	}
	@Id
	@Column(name = "zccode")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public String getZccode() {
		return zccode;
	}
	public void setZccode(String zccode) {
		this.zccode = zccode;
	}
	public Integer getSku() {
		return sku;
	}
	public void setSku(Integer sku) {
		this.sku = sku;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getCopies() {
		return copies;
	}
	public void setCopies(Integer copies) {
		this.copies = copies;
	}
	public Integer getIsfinish() {
		return isfinish;
	}
	public void setIsfinish(Integer isfinish) {
		this.isfinish = isfinish;
	}
	public String getWX_OrderCode() {
		return WX_OrderCode;
	}
	public void setWX_OrderCode(String wX_OrderCode) {
		WX_OrderCode = wX_OrderCode;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getEndTime() {
		return EndTime;
	}
	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}
	public String getOpenId() {
		return OpenId;
	}
	public void setOpenId(String openId) {
		OpenId = openId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getBuyNumber() {
		return BuyNumber;
	}
	public void setBuyNumber(Integer buyNumber) {
		BuyNumber = buyNumber;
	}
	public Integer getIscancel() {
		return iscancel;
	}
	public void setIscancel(Integer iscancel) {
		this.iscancel = iscancel;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIspayment() {
		return ispayment;
	}
	public void setIspayment(String ispayment) {
		this.ispayment = ispayment;
	}
	public String getPayurl() {
		return payurl;
	}
	public void setPayurl(String payurl) {
		this.payurl = payurl;
	}
	public String getSenddate() {
		return senddate;
	}
	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}
	public Integer getSendtimespan() {
		return sendtimespan;
	}
	public void setSendtimespan(Integer sendtimespan) {
		this.sendtimespan = sendtimespan;
	}
	public String getSkuname() {
		return skuname;
	}
	public void setSkuname(String skuname) {
		this.skuname = skuname;
	}
	public Date getPaymenttime() {
		return paymenttime;
	}
	public void setPaymenttime(Date paymenttime) {
		this.paymenttime = paymenttime;
	}

	
}
