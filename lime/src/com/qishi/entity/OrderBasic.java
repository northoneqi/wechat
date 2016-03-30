package com.qishi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_OrderBasic")
public class OrderBasic implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 订单Id
	private Integer OrderId;
	// 订单号
	private String OrderCode;
	// 子订单号
	private String SonOrderCcde;
	// 用户Id
	private Integer UserId;
	// 下单时间
	private Date AddTime;
	// 备注
	private String Remark;
	// 单类型(线-1，呼-2，分-3)
	private Integer OrderType;
	// 订单分类(报-1，水-2，卡类订单-3，蔬菜-4等)
	private Integer OrderClass;
	// 已优惠的钱(总)
	private Double PreferPrice;
	// 销售人
	private Integer Saler;
	// 销售站点
	private Integer SaleSite;
	// 支付类型
	private Integer PayType;
	// 促销ID
	private Integer PromotionId;

	private Integer Writer;

	@Id
	@Column(name = "OrderId")
	@GeneratedValue
	public Integer getOrderId() {
		return OrderId;
	}

	public void setOrderId(Integer orderId) {
		OrderId = orderId;
	}

	public String getOrderCode() {
		return OrderCode;
	}

	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}

	public String getSonOrderCcde() {
		return SonOrderCcde;
	}

	public void setSonOrderCcde(String sonOrderCcde) {
		SonOrderCcde = sonOrderCcde;
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public Date getAddTime() {
		return AddTime;
	}

	public void setAddTime(Date addTime) {
		AddTime = addTime;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public Integer getOrderType() {
		return OrderType;
	}

	public void setOrderType(Integer orderType) {
		OrderType = orderType;
	}

	public Integer getOrderClass() {
		return OrderClass;
	}

	public void setOrderClass(Integer orderClass) {
		OrderClass = orderClass;
	}

	public Double getPreferPrice() {
		return PreferPrice;
	}

	public void setPreferPrice(Double preferPrice) {
		PreferPrice = preferPrice;
	}

	public Integer getSaler() {
		return Saler;
	}

	public void setSaler(Integer saler) {
		Saler = saler;
	}

	public Integer getSaleSite() {
		return SaleSite;
	}

	public void setSaleSite(Integer saleSite) {
		SaleSite = saleSite;
	}

	public Integer getPayType() {
		return PayType;
	}

	public void setPayType(Integer payType) {
		PayType = payType;
	}

	public Integer getPromotionId() {
		return PromotionId;
	}

	public void setPromotionId(Integer promotionId) {
		PromotionId = promotionId;
	}

	public Integer getWriter() {
		return Writer;
	}

	public void setWriter(Integer writer) {
		Writer = writer;
	}

}
