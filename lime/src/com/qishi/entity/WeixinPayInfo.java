package com.qishi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_WeixinPayInfo")
public class WeixinPayInfo implements Serializable{
	private String WeixinpayId;
	private int TradeMode;
	private int TradeState;
	private String Partner;
	private String BankType;
	private String BankBillno;
	private int TotalFee;
	private int FeeType;
	private String NotifyId;
	private String TransactionId;
	private String OutTradeNo;
	private String TimeEnd;
	private int TransportFee;
	private int ProductFee;
	private int Discount;
	
	@Id
	@Column(name = "WeixinpayId")
	public String getWeixinpayId() {
		return WeixinpayId;
	}
	public void setWeixinpayId(String weixinpayId) {
		WeixinpayId = weixinpayId;
	}
	@Column(name = "TradeMode")
	public int getTradeMode() {
		return TradeMode;
	}
	public void setTradeMode(int tradeMode) {
		TradeMode = tradeMode;
	}
	@Column(name = "TradeState")
	public int getTradeState() {
		return TradeState;
	}
	public void setTradeState(int tradeState) {
		TradeState = tradeState;
	}
	@Column(name = "Partner")
	public String getPartner() {
		return Partner;
	}
	public void setPartner(String partner) {
		Partner = partner;
	}
	@Column(name = "BankType")
	public String getBankType() {
		return BankType;
	}
	public void setBankType(String bankType) {
		BankType = bankType;
	}
	@Column(name = "BankBillno")
	public String getBankBillno() {
		return BankBillno;
	}
	public void setBankBillno(String bankBillno) {
		BankBillno = bankBillno;
	}
	@Column(name = "TotalFee")
	public int getTotalFee() {
		return TotalFee;
	}
	public void setTotalFee(int totalFee) {
		TotalFee = totalFee;
	}
	@Column(name = "FeeType")
	public int getFeeType() {
		return FeeType;
	}
	public void setFeeType(int feeType) {
		FeeType = feeType;
	}
	@Column(name = "NotifyId")
	public String getNotifyId() {
		return NotifyId;
	}
	public void setNotifyId(String notifyId) {
		NotifyId = notifyId;
	}
	@Column(name = "TransactionId")
	public String getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}
	@Column(name = "OutTradeNo")
	public String getOutTradeNo() {
		return OutTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		OutTradeNo = outTradeNo;
	}
	@Column(name = "TimeEnd")
	public String getTimeEnd() {
		return TimeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		TimeEnd = timeEnd;
	}
	@Column(name = "TransportFee")
	public int getTransportFee() {
		return TransportFee;
	}
	public void setTransportFee(int transportFee) {
		TransportFee = transportFee;
	}
	@Column(name = "ProductFee")
	public int getProductFee() {
		return ProductFee;
	}
	public void setProductFee(int productFee) {
		ProductFee = productFee;
	}
	@Column(name = "Discount")
	public int getDiscount() {
		return Discount;
	}
	public void setDiscount(int discount) {
		Discount = discount;
	}
	
	
	

}
