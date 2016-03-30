package com.qishi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_ZC_ProductInfo")
public class ZC_ProductInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	private Integer ActivityId;
	private Integer sku;
	private Double SellPrice;
	private String goodpng;
	private String goodname;
	
	public String getGoodpng() {
		return goodpng;
	}
	public void setGoodpng(String goodpng) {
		this.goodpng = goodpng;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getActivityId() {
		return ActivityId;
	}
	public void setActivityId(Integer activityId) {
		ActivityId = activityId;
	}
	public Integer getSku() {
		return sku;
	}
	public void setSku(Integer sku) {
		this.sku = sku;
	}
	public Double getSellPrice() {
		return SellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		SellPrice = sellPrice;
	}
	public String getGoodname() {
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	

}
