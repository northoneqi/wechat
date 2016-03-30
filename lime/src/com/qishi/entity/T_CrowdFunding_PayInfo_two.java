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
@Table(name="T_CrowdFunding_PayInfo_two")
public class T_CrowdFunding_PayInfo_two implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id
	@Column(name="CROWDFUNDING_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private	Integer	CROWDFUNDING_ID	;
	
	/**
	 * 众筹订单号
	 */
	private String CROWDFUNDING_CODE;
	/**
	 * 支付人openid
	 */
	private String PAY_OPEN_ID;
	/**
	 * 支付人姓名
	 */
	private String PAY_NAME;
	/**
	 * 支付金额
	 */
	private Double PAY_MONEY;
	/**
	 * 支付时间
	 */
	private Long ADD_TIME;
	/**
	 * 交易出号
	 */
	private String OUT_TRADE_NO;
	/**
	 * 运输费
	 */
	private Long TRANSPORT_FEE;
	/**
	 * 交易状态
	 */
	private Long TRADE_STATE;
	/**
	 * 交易模式
	 */
	private Long TRADE_MODE;
	/**
	 * 合作人
	 */
	private String PARTNER;
	/**
	 * 银行类型
	 */
	private String BANK_TYPE;
	/**
	 * 银行编号
	 */
	private String BANK_BILLNO;
	/**
	 * 总价
	 */
	private Long TOTAL_FEE;
	/**
	 * 资金类型
	 */
	private Long FEE_TYPE;
	/**
	 * 公告id
	 */
	private String NOTIFY_ID;
	/**
	 * 事物id
	 */
	private String TRANSACTION_ID;
	/**
	 * 结束时间
	 */
	private Long TIME_END;
	/**
	 * 产品费用
	 */
	private Long PRODUCT_FEE;
	/**
	 * 折扣
	 */
	private Long DISCOUNT;
	/**
	 * 退款状态
	 */
	private Long REFUND_STATUS;
	/**
	 * 备注
	 */
	private String REMARK;
	public Integer getCROWDFUNDING_ID() {
		return CROWDFUNDING_ID;
	}
	public void setCROWDFUNDING_ID(Integer cROWDFUNDING_ID) {
		CROWDFUNDING_ID = cROWDFUNDING_ID;
	}
	public String getCROWDFUNDING_CODE() {
		return CROWDFUNDING_CODE;
	}
	public void setCROWDFUNDING_CODE(String cROWDFUNDING_CODE) {
		CROWDFUNDING_CODE = cROWDFUNDING_CODE;
	}
	public String getPAY_OPEN_ID() {
		return PAY_OPEN_ID;
	}
	public void setPAY_OPEN_ID(String pAY_OPEN_ID) {
		PAY_OPEN_ID = pAY_OPEN_ID;
	}
	public String getPAY_NAME() {
		return PAY_NAME;
	}
	public void setPAY_NAME(String pAY_NAME) {
		PAY_NAME = pAY_NAME;
	}
	public Double getPAY_MONEY() {
		return PAY_MONEY;
	}
	public void setPAY_MONEY(Double pAY_MONEY) {
		PAY_MONEY = pAY_MONEY;
	}
	public Long getADD_TIME() {
		return ADD_TIME;
	}
	public void setADD_TIME(Long aDD_TIME) {
		ADD_TIME = aDD_TIME;
	}
	public String getOUT_TRADE_NO() {
		return OUT_TRADE_NO;
	}
	public void setOUT_TRADE_NO(String oUT_TRADE_NO) {
		OUT_TRADE_NO = oUT_TRADE_NO;
	}
	public Long getTRANSPORT_FEE() {
		return TRANSPORT_FEE;
	}
	public void setTRANSPORT_FEE(Long tRANSPORT_FEE) {
		TRANSPORT_FEE = tRANSPORT_FEE;
	}
	public Long getTRADE_STATE() {
		return TRADE_STATE;
	}
	public void setTRADE_STATE(Long tRADE_STATE) {
		TRADE_STATE = tRADE_STATE;
	}
	public Long getTRADE_MODE() {
		return TRADE_MODE;
	}
	public void setTRADE_MODE(Long tRADE_MODE) {
		TRADE_MODE = tRADE_MODE;
	}
	public String getPARTNER() {
		return PARTNER;
	}
	public void setPARTNER(String pARTNER) {
		PARTNER = pARTNER;
	}
	public String getBANK_TYPE() {
		return BANK_TYPE;
	}
	public void setBANK_TYPE(String bANK_TYPE) {
		BANK_TYPE = bANK_TYPE;
	}
	public String getBANK_BILLNO() {
		return BANK_BILLNO;
	}
	public void setBANK_BILLNO(String bANK_BILLNO) {
		BANK_BILLNO = bANK_BILLNO;
	}
	public Long getTOTAL_FEE() {
		return TOTAL_FEE;
	}
	public void setTOTAL_FEE(Long tOTAL_FEE) {
		TOTAL_FEE = tOTAL_FEE;
	}
	public Long getFEE_TYPE() {
		return FEE_TYPE;
	}
	public void setFEE_TYPE(Long fEE_TYPE) {
		FEE_TYPE = fEE_TYPE;
	}
	public String getNOTIFY_ID() {
		return NOTIFY_ID;
	}
	public void setNOTIFY_ID(String nOTIFY_ID) {
		NOTIFY_ID = nOTIFY_ID;
	}
	public String getTRANSACTION_ID() {
		return TRANSACTION_ID;
	}
	public void setTRANSACTION_ID(String tRANSACTION_ID) {
		TRANSACTION_ID = tRANSACTION_ID;
	}
	public Long getTIME_END() {
		return TIME_END;
	}
	public void setTIME_END(Long tIME_END) {
		TIME_END = tIME_END;
	}
	public Long getPRODUCT_FEE() {
		return PRODUCT_FEE;
	}
	public void setPRODUCT_FEE(Long pRODUCT_FEE) {
		PRODUCT_FEE = pRODUCT_FEE;
	}
	public Long getDISCOUNT() {
		return DISCOUNT;
	}
	public void setDISCOUNT(Long dISCOUNT) {
		DISCOUNT = dISCOUNT;
	}
	public Long getREFUND_STATUS() {
		return REFUND_STATUS;
	}
	public void setREFUND_STATUS(Long rEFUND_STATUS) {
		REFUND_STATUS = rEFUND_STATUS;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

}
