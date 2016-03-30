package com.qishi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_MciroPrize")
public class Prize implements  Serializable{
	private static final long serialVersionUID = 1L; 
	
	//活动Id
	private Integer PrizeId;
	//活动编号
	private Integer ActivityId;
	//奖品内容	
	private String PrizeContent;
	//数量
	private Integer PrizeNumber;
	//概率
	private Integer Probably;
	//标记
	private String note;
	//卡号
	private String CardNo;
	//是否被绑定
	private Integer cardstate;
	//奖品分类
	private Integer prizeType;
	
	public Integer getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}

	@Id
	@Column(name = "PrizeId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getPrizeId() {
		return PrizeId;
	}

	public void setPrizeId(Integer prizeId) {
		PrizeId = prizeId;
	}

	public Integer getActivityId() {
		return ActivityId;
	}

	public void setActivityId(Integer activityId) {
		ActivityId = activityId;
	}

	public String getPrizeContent() {
		return PrizeContent;
	}

	public void setPrizeContent(String prizeContent) {
		PrizeContent = prizeContent;
	}

	public Integer getPrizeNumber() {
		return PrizeNumber;
	}

	public void setPrizeNumber(Integer prizeNumber) {
		PrizeNumber = prizeNumber;
	}

	public Integer getProbably() {
		return Probably;
	}

	public void setProbably(Integer probably) {
		Probably = probably;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public Integer getCardstate() {
		return cardstate;
	}

	public void setCardstate(Integer cardstate) {
		this.cardstate = cardstate;
	}

	
	

	
}
