package com.qishi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qishi.util.StringUtil;

@Entity
@Table(name = "T_UserBasic")
public class UserBasic implements  Serializable{
	private static final long serialVersionUID = 1L;
	//用户id
	@Id
	@Column(name = "UserID", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer UserID;
	//用户名
	private String Username;
	//用户密码
	private String UserPwd;
	//昵称
	private String NickName;
	//
	private Integer UIntegral;
	//手机号
	private String Phone;
	//邮箱
	private String email;
	//座机
	private String PhoneCode;
	//时间
	private Date CodeTime;
	//
	private Integer EValidation;
	//
	private Integer PValidation;
	//注册时间
	private Date Regtime;
	//用户类型
	private Integer UserType;
	//用户级别
	private Integer UserLevel;
	//用户头像
	private  String UserPic;
	//
	private Double UserMoney;
	//真实姓名
	private String TrueName;
	//用户状态
	private Integer UserState;
	//
	private String OtherCode;
	//
	private Date EmailOverTime;
	//
	private Integer HasSendTime;
	//
	private Date SendData;
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getUserPwd() {
		return UserPwd;
	}
	public void setUserPwd(String userPwd) {
		UserPwd = userPwd;
	}
	
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public Integer getUIntegral() {
		return UIntegral;
	}
	public void setUIntegral(Integer uIntegral) {
		UIntegral = uIntegral;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneCode() {
		return PhoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		PhoneCode = phoneCode;
	}
	public Date getCodeTime() {
		return CodeTime;
	}
	public void setCodeTime(Date codeTime) {
		CodeTime = codeTime;
	}
	public Integer getEValidation() {
		return EValidation;
	}
	public void setEValidation(Integer eValidation) {
		EValidation = eValidation;
	}
	public Integer getPValidation() {
		return PValidation;
	}
	public void setPValidation(Integer pValidation) {
		PValidation = pValidation;
	}
	public Date getRegtime() {
		return Regtime;
	}
	public void setRegtime(Date regtime) {
		Regtime = regtime;
	}
	public Integer getUserType() {
		return UserType;
	}
	public void setUserType(Integer userType) {
		UserType = userType;
	}
	public Integer getUserLevel() {
		return UserLevel;
	}
	public void setUserLevel(Integer userLevel) {
		UserLevel = userLevel;
	}
	public String getUserPic() {
		return UserPic;
	}
	public void setUserPic(String userPic) {
		UserPic = userPic;
	}
	
	
	public Double getUserMoney() {
		
		return UserMoney;
	}
	public void setUserMoney(Double userMoney) {
		UserMoney =userMoney;
		
	}
	public String getTrueName() {
		return TrueName;
	}
	public void setTrueName(String trueName) {
		TrueName = trueName;
	}
	public Integer getUserState() {
		return UserState;
	}
	public void setUserState(Integer userState) {
		UserState = userState;
	}
	public String getOtherCode() {
		return OtherCode;
	}
	public void setOtherCode(String otherCode) {
		OtherCode = otherCode;
	}
	public Date getEmailOverTime() {
		return EmailOverTime;
	}
	public void setEmailOverTime(Date emailOverTime) {
		EmailOverTime = emailOverTime;
	}
	public Integer getHasSendTime() {
		return HasSendTime;
	}
	public void setHasSendTime(Integer hasSendTime) {
		HasSendTime = hasSendTime;
	}
	public Date getSendData() {
		return SendData;
	}
	public void setSendData(Date sendData) {
		SendData = sendData;
	}
	public Integer getAccountType() {
		return AccountType;
	}
	public void setAccountType(Integer accountType) {
		AccountType = accountType;
	}
	public String getWX_OPENID() {
		return WX_OPENID;
	}
	public void setWX_OPENID(String wX_OPENID) {
		WX_OPENID = wX_OPENID;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Integer getProvince() {
		return Province;
	}
	public void setProvince(Integer province) {
		Province = province;
	}
	public Integer getCity() {
		return City;
	}
	public void setCity(Integer city) {
		City = city;
	}
	
	public Integer getArea() {
		return Area;
	}
	public void setArea(Integer area) {
		Area = area;
	}
	public Integer getRange() {
		return Range;
	}
	public void setRange(Integer range) {
		Range = range;
	}
	//
	private Integer AccountType;
	//
	private String WX_OPENID; 
	//地址
	private String Address;
	//省份
	private Integer Province;
	//城市
	private Integer City;
	//地区
	private Integer Area;
	//
	private Integer Range;
	
	
	
	
	

}
