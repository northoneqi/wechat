package com.qishi.entity;

import java.io.Serializable;
import java.lang.Long;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_CrowdFunding_Orderbasic_two")
public class T_CrowdFunding_OrderBasic_two implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 众筹订单
	 */
	@Id
	@Column(name="CROWDFUNDING_CODE")
	private String CROWDFUNDING_CODE;
	/**
	 * 活动ID
	 */
	private Long ACTIVITY_ID;
	/**
	 * 是否在支付,0-未在支付，1-正在支付
	 */
	private Long IS_PAY;
	/**
	 * 众筹订单添加时间
	 */
	private Long ADD_TIME;
	/**
	 * 众筹订单结束时间
	 */
	private Long END_TIME;
	/**
	 * 众筹订单状态：0-进行中，1-已完成，2-已取消
	 */
	private Long ORDER_STATUS;
	/**
	 * 众筹订单支付url
	 */
	private String PAYURL;
	/**
	 * 订单发送日期
	 */
	private Long SEND_DATE;
	/**
	 * 订单发送时间段
	 */
	private Long SEND_TIMES;
	/**
	 * 订单支付时间
	 */
	private Long PAYMENT_TIME;
	/**
	 * 众筹人份数
	 */
	private Long PLAY_NUM;
	/**
	 * 微信订单编号
	 */
	private String WX_ORDERCODE;
	/**
	 * 微信Openid
	 */
	private String OPEN_ID;
	/**
	 * 发起人姓名
	 */
	private String NAME;
	/**
	 * 电话
	 */
	private String TEL;
	/**
	 * 省
	 */
	private String PROVINCE;
	private String CITY;
	private String AREA;
	/**
	 * 地址
	 */
	private String ADDRESS;
	/**
	 * 订单商品sku
	 */
	private Long SKU;
	/**
	 * 购买数量
	 */
	private Long BUY_NUMBER;
	/**
	 * 商品名称
	 */
	private String SKU_NAME;
	/**
	 * 商品价格
	 */
	private Double GOOD_PRICE;
	/**
	 * 备注
	 */
	private String REMARK;
	public String getCROWDFUNDING_CODE() {
		return CROWDFUNDING_CODE;
	}
	public void setCROWDFUNDING_CODE(String cROWDFUNDING_CODE) {
		CROWDFUNDING_CODE = cROWDFUNDING_CODE;
	}
	public Long getACTIVITY_ID() {
		return ACTIVITY_ID;
	}
	public void setACTIVITY_ID(Long aCTIVITY_ID) {
		ACTIVITY_ID = aCTIVITY_ID;
	}
	public Long getIS_PAY() {
		return IS_PAY;
	}
	public void setIS_PAY(Long iS_PAY) {
		IS_PAY = iS_PAY;
	}
	public Long getADD_TIME() {
		return ADD_TIME;
	}
	public void setADD_TIME(Long aDD_TIME) {
		ADD_TIME = aDD_TIME;
	}
	public Long getEND_TIME() {
		return END_TIME;
	}
	public void setEND_TIME(Long eND_TIME) {
		END_TIME = eND_TIME;
	}
	public Long getORDER_STATUS() {
		return ORDER_STATUS;
	}
	public void setORDER_STATUS(Long oRDER_STATUS) {
		ORDER_STATUS = oRDER_STATUS;
	}
	public String getPAYURL() {
		return PAYURL;
	}
	public void setPAYURL(String pAYURL) {
		PAYURL = pAYURL;
	}
	public Long getSEND_DATE() {
		return SEND_DATE;
	}
	public void setSEND_DATE(Long sEND_DATE) {
		SEND_DATE = sEND_DATE;
	}
	public Long getSEND_TIMES() {
		return SEND_TIMES;
	}
	public void setSEND_TIMES(Long sEND_TIMES) {
		SEND_TIMES = sEND_TIMES;
	}
	public Long getPAYMENT_TIME() {
		return PAYMENT_TIME;
	}
	public void setPAYMENT_TIME(Long pAYMENT_TIME) {
		PAYMENT_TIME = pAYMENT_TIME;
	}
	public Long getPLAY_NUM() {
		return PLAY_NUM;
	}
	public void setPLAY_NUM(Long pLAY_NUM) {
		PLAY_NUM = pLAY_NUM;
	}
	public String getWX_ORDERCODE() {
		return WX_ORDERCODE;
	}
	public void setWX_ORDERCODE(String wX_ORDERCODE) {
		WX_ORDERCODE = wX_ORDERCODE;
	}
	public String getOPEN_ID() {
		return OPEN_ID;
	}
	public void setOPEN_ID(String oPEN_ID) {
		OPEN_ID = oPEN_ID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getPROVINCE() {
		return PROVINCE;
	}
	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getAREA() {
		return AREA;
	}
	public void setAREA(String aREA) {
		AREA = aREA;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public Long getSKU() {
		return SKU;
	}
	public void setSKU(Long sKU) {
		SKU = sKU;
	}
	public Long getBUY_NUMBER() {
		return BUY_NUMBER;
	}
	public void setBUY_NUMBER(Long bUY_NUMBER) {
		BUY_NUMBER = bUY_NUMBER;
	}
	public String getSKU_NAME() {
		return SKU_NAME;
	}
	public void setSKU_NAME(String sKU_NAME) {
		SKU_NAME = sKU_NAME;
	}
	public Double getGOOD_PRICE() {
		return GOOD_PRICE;
	}
	public void setGOOD_PRICE(Double gOOD_PRICE) {
		GOOD_PRICE = gOOD_PRICE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

}
