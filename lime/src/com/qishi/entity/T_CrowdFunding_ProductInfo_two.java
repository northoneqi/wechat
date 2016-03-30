package com.qishi.entity;

import java.io.Serializable;
import java.lang.Long;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_CrowdFunding_ProductInfo_two")
public class T_CrowdFunding_ProductInfo_two implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="CROWDFUNDING_PRODUCT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long CROWDFUNDING_PRODUCT_ID;
	/**
	 * 活动id
	 */
	private Long ACTIVITY_ID;
	/**
	 * 商品sku
	 */
	private Long SKU;
	/**
	 * 商品名称
	 */
	private String GOOD_NAME;
	/**
	 * 商品图片
	 */
	private String GOOD_PNG;
	/**
	 * 商品原价
	 */
	private Double OLD_PRICE;
	/**
	 * 众筹价格
	 */
	private Double PRICE;
	/**
	 * 众筹商品数量
	 */
	private Long GOOD_NUMBER;
	/**
	 * 备注
	 */
	private String REMARK;
	public Long getCROWDFUNDING_PRODUCT_ID() {
		return CROWDFUNDING_PRODUCT_ID;
	}
	public void setCROWDFUNDING_PRODUCT_ID(Long cROWDFUNDING_PRODUCT_ID) {
		CROWDFUNDING_PRODUCT_ID = cROWDFUNDING_PRODUCT_ID;
	}
	public Long getACTIVITY_ID() {
		return ACTIVITY_ID;
	}
	public void setACTIVITY_ID(Long aCTIVITY_ID) {
		ACTIVITY_ID = aCTIVITY_ID;
	}
	public Long getSKU() {
		return SKU;
	}
	public void setSKU(Long sKU) {
		SKU = sKU;
	}
	public String getGOOD_NAME() {
		return GOOD_NAME;
	}
	public void setGOOD_NAME(String gOOD_NAME) {
		GOOD_NAME = gOOD_NAME;
	}
	public String getGOOD_PNG() {
		return GOOD_PNG;
	}
	public void setGOOD_PNG(String gOOD_PNG) {
		GOOD_PNG = gOOD_PNG;
	}
	public Double getOLD_PRICE() {
		return OLD_PRICE;
	}
	public void setOLD_PRICE(Double oLD_PRICE) {
		OLD_PRICE = oLD_PRICE;
	}
	public Double getPRICE() {
		return PRICE;
	}
	public void setPRICE(Double pRICE) {
		PRICE = pRICE;
	}
	public Long getGOOD_NUMBER() {
		return GOOD_NUMBER;
	}
	public void setGOOD_NUMBER(Long gOOD_NUMBER) {
		GOOD_NUMBER = gOOD_NUMBER;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
	

}
