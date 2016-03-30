package com.qishi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_OrderCommonAddress")
public class OrderCommonAddress implements Serializable{
	
	@Id
	@Column(name = "OAddressID")
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer OAddressID;
	private String OConsignee;
	private String OAddress;
	private Integer OProvince;
	private Integer OCity;
	private Integer OArea;
	private Integer ORange;
	private String OMbile;
	private String OTelephone;
	private Integer UserId;
	private String OEmail;
	private String OPostCode;	//邮编
	private Integer TranStation; //配送站点
	private Integer DefAddress;
	
	public Integer getOAddressID() {
		return OAddressID;
	}
	public void setOAddressID(Integer oAddressID) {
		OAddressID = oAddressID;
	}
	public String getOConsignee() {
		return OConsignee;
	}
	public void setOConsignee(String oConsignee) {
		OConsignee = oConsignee;
	}
	public String getOAddress() {
		return OAddress;
	}
	public void setOAddress(String oAddress) {
		OAddress = oAddress;
	}
	public Integer getOProvince() {
		return OProvince;
	}
	public void setOProvince(Integer oProvince) {
		OProvince = oProvince;
	}
	public Integer getOCity() {
		return OCity;
	}
	public void setOCity(Integer oCity) {
		OCity = oCity;
	}
	public Integer getOArea() {
		return OArea;
	}
	public void setOArea(Integer oArea) {
		OArea = oArea;
	}
	public Integer getORange() {
		return ORange;
	}
	public void setORange(Integer oRange) {
		ORange = oRange;
	}
	public String getOMbile() {
		return OMbile;
	}
	public void setOMbile(String oMbile) {
		OMbile = oMbile;
	}
	public String getOTelephone() {
		return OTelephone;
	}
	public void setOTelephone(String oTelephone) {
		OTelephone = oTelephone;
	}
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public String getOEmail() {
		return OEmail;
	}
	public void setOEmail(String oEmail) {
		OEmail = oEmail;
	}
	public String getOPostCode() {
		return OPostCode;
	}
	public void setOPostCode(String oPostCode) {
		OPostCode = oPostCode;
	}
	public Integer getTranStation() {
		return TranStation;
	}
	public void setTranStation(Integer tranStation) {
		TranStation = tranStation;
	}
	public Integer getDefAddress() {
		return DefAddress;
	}
	public void setDefAddress(Integer defAddress) {
		DefAddress = defAddress;
	}
	
}
