package com.qishi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_OrderCart")
public class OrderCart implements Serializable{
	// 订单ID
	private int CarId;
	// 用户ID
	private int UserId;
	// SKU
	private int SKU;
	// 购买数量
	private int BuyNum;
	// 订单分类
	private int OrderClass;
	// 组Id
	private String GroupId;
	// 判断
	private Boolean IsMain;
	//openid
	private String openId;
	

	@Id
	@Column(name = "CartId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getCarId() {
		return CarId;
	}

	public void setCarId(int carId) {
		CarId = carId;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getSKU() {
		return SKU;
	}

	public void setSKU(int sKU) {
		SKU = sKU;
	}

	public int getBuyNum() {
		return BuyNum;
	}

	public void setBuyNum(int buyNum) {
		BuyNum = buyNum;
	}

	public int getOrderClass() {
		return OrderClass;
	}

	public void setOrderClass(int orderClass) {
		OrderClass = orderClass;
	}

	public String getGroupId() {
		return GroupId;
	}

	public void setGroupId(String groupId) {
		GroupId = groupId;
	}

	public Boolean getIsMain() {
		return IsMain;
	}

	public void setIsMain(Boolean isMain) {
		IsMain = isMain;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	

}
