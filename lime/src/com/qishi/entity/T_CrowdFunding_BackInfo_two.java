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
@Table(name="T_CrowdFunding_BackInfo_two")
public class T_CrowdFunding_BackInfo_two implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@Column(name = "ACTIVITY_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ACTIVITY_ID;
	private String ACTIVITY_NUM;
	private Long ACTIVITY_STATUS;
	private String ACTIVITY_INFO;
	private String PLAY_NUM;
	private Integer IS_CANAL;
	private Integer IS_PAYALL;
	private Long MIN_PAY_NUMBER;
	private Long MAX_PLAY_MORE;
	private Long MAX_PAY_MORE;
	private Long ACTIVITY_BEGIN_TIME;
	private Long ACTIVITY_END_TIME;
	private String ACTIVITY_AUTO;
	private Long ACTIVITY_ADD_TIME;
	private String REMARK;
	public Long getACTIVITY_ID() {
		return ACTIVITY_ID;
	}
	public void setACTIVITY_ID(Long aCTIVITY_ID) {
		ACTIVITY_ID = aCTIVITY_ID;
	}
	public String getACTIVITY_NUM() {
		return ACTIVITY_NUM;
	}
	public void setACTIVITY_NUM(String aCTIVITY_NUM) {
		ACTIVITY_NUM = aCTIVITY_NUM;
	}
	public Long getACTIVITY_STATUS() {
		return ACTIVITY_STATUS;
	}
	public void setACTIVITY_STATUS(Long aCTIVITY_STATUS) {
		ACTIVITY_STATUS = aCTIVITY_STATUS;
	}
	public String getACTIVITY_INFO() {
		return ACTIVITY_INFO;
	}
	public void setACTIVITY_INFO(String aCTIVITY_INFO) {
		ACTIVITY_INFO = aCTIVITY_INFO;
	}
	public String getPLAY_NUM() {
		return PLAY_NUM;
	}
	public void setPLAY_NUM(String pLAY_NUM) {
		PLAY_NUM = pLAY_NUM;
	}
	public Integer getIS_CANAL() {
		return IS_CANAL;
	}
	public void setIS_CANAL(Integer iS_CANAL) {
		IS_CANAL = iS_CANAL;
	}
	public Integer getIS_PAYALL() {
		return IS_PAYALL;
	}
	public void setIS_PAYALL(Integer iS_PAYALL) {
		IS_PAYALL = iS_PAYALL;
	}
	public Long getMIN_PAY_NUMBER() {
		return MIN_PAY_NUMBER;
	}
	public void setMIN_PAY_NUMBER(Long mIN_PAY_NUMBER) {
		MIN_PAY_NUMBER = mIN_PAY_NUMBER;
	}
	public Long getMAX_PLAY_MORE() {
		return MAX_PLAY_MORE;
	}
	public void setMAX_PLAY_MORE(Long mAX_PLAY_MORE) {
		MAX_PLAY_MORE = mAX_PLAY_MORE;
	}
	public Long getMAX_PAY_MORE() {
		return MAX_PAY_MORE;
	}
	public void setMAX_PAY_MORE(Long mAX_PAY_MORE) {
		MAX_PAY_MORE = mAX_PAY_MORE;
	}
	public Long getACTIVITY_BEGIN_TIME() {
		return ACTIVITY_BEGIN_TIME;
	}
	public void setACTIVITY_BEGIN_TIME(Long aCTIVITY_BEGIN_TIME) {
		ACTIVITY_BEGIN_TIME = aCTIVITY_BEGIN_TIME;
	}
	public Long getACTIVITY_END_TIME() {
		return ACTIVITY_END_TIME;
	}
	public void setACTIVITY_END_TIME(Long aCTIVITY_END_TIME) {
		ACTIVITY_END_TIME = aCTIVITY_END_TIME;
	}
	public String getACTIVITY_AUTO() {
		return ACTIVITY_AUTO;
	}
	public void setACTIVITY_AUTO(String aCTIVITY_AUTO) {
		ACTIVITY_AUTO = aCTIVITY_AUTO;
	}
	public Long getACTIVITY_ADD_TIME() {
		return ACTIVITY_ADD_TIME;
	}
	public void setACTIVITY_ADD_TIME(Long aCTIVITY_ADD_TIME) {
		ACTIVITY_ADD_TIME = aCTIVITY_ADD_TIME;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}	 


	
	
}
