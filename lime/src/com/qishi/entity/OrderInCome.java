package com.qishi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="T_OrderInCome")
public class OrderInCome implements Serializable {
	private static final long serialVersionUID=1L;
	// 主键
	private int OrderPaymentId;
	// 订单号
	private String OrderCode;
	// 收支表收支类型 1.菜卡收入 2.菜卡汇总收入....7退款收入
	private int CardPayType;

	private Double money;
	// 卡号
	private String CardNumber;
	// 收支类型 1.收入 2.支出
	private int OutOrInType;
	// 备注
	private String Remark;
	// 取消订单时间
	private Date AddTime;

	@Id
	@Basic(optional = false)
	@Column(name = "OrderPaymentId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getOrderPaymentId() {
		return OrderPaymentId;
	}

	public void setOrderPaymentId(int orderPaymentId) {
		OrderPaymentId = orderPaymentId;
	}

	public String getOrderCode() {
		return OrderCode;
	}

	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}

	public int getCardPayType() {
		return CardPayType;
	}

	public void setCardPayType(int cardPayType) {
		CardPayType = cardPayType;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}

	public int getOutOrInType() {
		return OutOrInType;
	}

	public void setOutOrInType(int outOrInType) {
		OutOrInType = outOrInType;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public Date getAddTime() {
		return AddTime;
	}

	public void setAddTime(Date addTime) {
		AddTime = addTime;
	}

}
