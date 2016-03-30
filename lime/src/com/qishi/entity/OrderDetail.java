package com.qishi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_OrderDetail")
public class OrderDetail {
	// 订单详情ID
	private Integer OrderDetailId;
	// 订单号
	private String OrderCode;
	// 用户ID
	private Integer UserId;
	// 商品SKU
	private Integer SKU;
	// 数量
	private Integer ProNum;
	// 单位
	private Integer Unit;
	// 是否称重
	private Boolean IsCountByWeight;
	// 单价
	private Double BuyPrice;
	// 原来价格
	private Double OldPrice;
	// 促销Id
	private Integer PromotionId;
	// 标重
	private Double Biaozhong;
	// 市重
	private Double Shizhong;
	// 总
	private Double Subtotal;
	// SKU名字
	private String SKUName;

	public Double getBuyPrice() {
		return BuyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		BuyPrice = buyPrice;
	}

	public Double getOldPrice() {
		return OldPrice;
	}

	public void setOldPrice(Double oldPrice) {
		OldPrice = oldPrice;
	}

	public Double getBiaozhong() {
		return Biaozhong;
	}

	public void setBiaozhong(Double biaozhong) {
		Biaozhong = biaozhong;
	}

	public Double getShizhong() {
		return Shizhong;
	}

	public void setShizhong(Double shizhong) {
		Shizhong = shizhong;
	}

	public Double getSubtotal() {
		return Subtotal;
	}

	public void setSubtotal(Double subtotal) {
		Subtotal = subtotal;
	}

	@Id
	@Column(name = "OrderDetailId")
	@GeneratedValue
	public Integer getOrderDetailId() {
		return OrderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		OrderDetailId = orderDetailId;
	}

	public String getOrderCode() {
		return OrderCode;
	}

	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public Integer getSKU() {
		return SKU;
	}

	public void setSKU(Integer sKU) {
		SKU = sKU;
	}

	public Integer getProNum() {
		return ProNum;
	}

	public void setProNum(Integer proNum) {
		ProNum = proNum;
	}

	public Integer getUnit() {
		return Unit;
	}

	public void setUnit(Integer unit) {
		Unit = unit;
	}

	public Boolean getIsCountByWeight() {
		return IsCountByWeight;
	}

	public void setIsCountByWeight(Boolean isCountByWeight) {
		IsCountByWeight = isCountByWeight;
	}

	public Integer getPromotionId() {
		return PromotionId;
	}

	public void setPromotionId(Integer promotionId) {
		PromotionId = promotionId;
	}

	public String getSKUName() {
		return SKUName;
	}

	public void setSKUName(String sKUName) {
		SKUName = sKUName;
	}

}
