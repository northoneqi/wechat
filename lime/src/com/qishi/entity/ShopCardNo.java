package com.qishi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "T_ShopCardNo")
public class ShopCardNo {
	//卡号ID
	private int CardId ;
	//卡号名字
	private String CardNo ;
	//卡密码
	private String CardPwd;
	
	private int SaleState;
	
	private int UserState;
	
	private int CardType;
	
	private Date  Addtime;
	
	private Date BeginDate;
	
	private Date EndDate;
	
	private String Remark;
	
	@Id	
	@Column(name = "ID")
	@GeneratedValue
	public int getCardId() {
		return CardId;
	}
	public void setCardId(int cardId) {
		CardId = cardId;
	}
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getCardPwd() {
		return CardPwd;
	}
	public void setCardPwd(String cardPwd) {
		CardPwd = cardPwd;
	}
	public int getSaleState() {
		return SaleState;
	}
	public void setSaleState(int saleState) {
		SaleState = saleState;
	}
	public int getUserState() {
		return UserState;
	}
	public void setUserState(int userState) {
		UserState = userState;
	}
	public int getCardType() {
		return CardType;
	}
	public void setCardType(int cardType) {
		CardType = cardType;
	}
	public Date getAddtime() {
		return Addtime;
	}
	public void setAddtime(Date addtime) {
		Addtime = addtime;
	}
	public Date getBeginDate() {
		return BeginDate;
	}
	public void setBeginDate(Date beginDate) {
		BeginDate = beginDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public void save(String shopcardno) {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
