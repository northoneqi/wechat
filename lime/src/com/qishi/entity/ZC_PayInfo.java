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
@Table(name = "T_ZC_PayInfo")
public class ZC_PayInfo implements Serializable{

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	private String zccode;
	private Double Paymoney;
	private String Payname;
	private String PayopenId;
	private Date addtime;
	private String out_trade_no;
	private Integer transport_fee;
	private Integer trade_state;
	private Integer trade_mode;
	private String partner;
	private String bank_type;
	private String bank_billno;
	private Integer total_fee;
	private Integer fee_type;
	private String notify_id;
	private String transaction_id;
	private Date time_end;
	private Integer product_fee;
	private Integer discount;
	private Integer refundStatus;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getZccode() {
		return zccode;
	}
	public void setZccode(String zccode) {
		this.zccode = zccode;
	}
	public Double getPaymoney() {
		return Paymoney;
	}
	public void setPaymoney(Double paymoney) {
		Paymoney = paymoney;
	}
	public String getPayname() {
		return Payname;
	}
	public void setPayname(String payname) {
		Payname = payname;
	}
	public String getPayopenId() {
		return PayopenId;
	}
	public void setPayopenId(String payopenId) {
		PayopenId = payopenId;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public Integer getTransport_fee() {
		return transport_fee;
	}
	public void setTransport_fee(Integer transport_fee) {
		this.transport_fee = transport_fee;
	}
	public Integer getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(Integer trade_state) {
		this.trade_state = trade_state;
	}
	public Integer getTrade_mode() {
		return trade_mode;
	}
	public void setTrade_mode(Integer trade_mode) {
		this.trade_mode = trade_mode;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getBank_billno() {
		return bank_billno;
	}
	public void setBank_billno(String bank_billno) {
		this.bank_billno = bank_billno;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public Integer getFee_type() {
		return fee_type;
	}
	public void setFee_type(Integer fee_type) {
		this.fee_type = fee_type;
	}
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public Date getTime_end() {
		return time_end;
	}
	public void setTime_end(Date time_end) {
		this.time_end = time_end;
	}
	public Integer getProduct_fee() {
		return product_fee;
	}
	public void setProduct_fee(Integer product_fee) {
		this.product_fee = product_fee;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Integer getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}
	

}
