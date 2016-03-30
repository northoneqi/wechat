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
@Table(name = "T_MciroLuckyPrize")
public class LuckyPrize implements  Serializable {
	private static final long serialVersionUID = 1L; 
	
	private int Id;
	//活动编号
	private String ActivityId;
	//微信用户Id
	private String OpenId;
	//奖品Id
	private String PrizeId;
	//是否抽奖（0,1）
	private int isPrize;
	
	private Date PrizeDate; 
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	public String getOpenId() {
		return OpenId;
	}
	public void setOpenId(String openId) {
		OpenId = openId;
	}
	public String getPrizeId() {
		return PrizeId;
	}
	public void setPrizeId(String prizeId) {
		PrizeId = prizeId;
	}
	public int getIsPrize() {
		return isPrize;
	}
	public void setIsPrize(int isPrize) {
		this.isPrize = isPrize;
	}
	public String getActivityId() {
		return ActivityId;
	}
	public void setActivityId(String activityId) {
		ActivityId = activityId;
	}
	
	

}
