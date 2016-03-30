package com.qishi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_OrderAccept")
public class OrderAccept {

	private int AcceptId;
	private String OrderCode;
	private int AcceptSite;
	private Boolean Accpeted;
	private int AcceptMen;
	private Date AcceptTime;
	private int AcceptType;

	@Id
	@Column(name = "AcceptId")
	@GeneratedValue
	public int getAcceptId() {
		return AcceptId;
	}

	public void setAcceptId(int acceptId) {
		AcceptId = acceptId;
	}

	public String getOrderCode() {
		return OrderCode;
	}

	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}

	public int getAcceptSite() {
		return AcceptSite;
	}

	public void setAcceptSite(int acceptSite) {
		AcceptSite = acceptSite;
	}

	public Boolean getAccpeted() {
		return Accpeted;
	}

	public void setAccpeted(Boolean accpeted) {
		Accpeted = accpeted;
	}

	public int getAcceptMen() {
		return AcceptMen;
	}

	public void setAcceptMen(int acceptMen) {
		AcceptMen = acceptMen;
	}

	public Date getAcceptTime() {
		return AcceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		AcceptTime = acceptTime;
	}

	public int getAcceptType() {
		return AcceptType;
	}

	public void setAcceptType(int acceptType) {
		AcceptType = acceptType;
	}

}
