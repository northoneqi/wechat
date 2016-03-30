package com.qishi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_BankTransaction")
public class BankTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ID;
	private Integer CustomerID;
	private String OrderID;
	private Double OrderMoney;
	private String PayType;
	private String BankType;
	private String BankName;
	private String TransactionID;
	private Integer Status;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(Integer customerID) {
		CustomerID = customerID;
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public Double getOrderMoney() {
		return OrderMoney;
	}
	public void setOrderMoney(Double orderMoney) {
		OrderMoney = orderMoney;
	}
	public String getPayType() {
		return PayType;
	}
	public void setPayType(String payType) {
		PayType = payType;
	}
	public String getBankType() {
		return BankType;
	}
	public void setBankType(String bankType) {
		BankType = bankType;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public String getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}

}
