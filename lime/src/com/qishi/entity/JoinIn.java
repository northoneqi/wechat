package com.qishi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_MciroJoinIn")
public class JoinIn implements  Serializable {
	private static final long serialVersionUID = 1L; 
	//id
	private Integer LuckyId;
	//活动id
	private Integer ActivityId;
	//openid
	private String OpenId;
	//每个用户对应的url
	private String Url;
	//用户url的来源（openid）
	private String Source;
	//用户是否关注微信商城
	private Integer checklog;
	

	
	@Id
	@Column(name = "LuckyId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getLuckyId() {
		return LuckyId;
	}

	public void setLuckyId(Integer luckyId) {
		LuckyId = luckyId;
	}



	public Integer getChecklog() {
		return checklog;
	}

	public void setChecklog(Integer checklog) {
		this.checklog = checklog;
	}

	public String getOpenId() {
		return OpenId;
	}
	
	public Integer getActivityId() {
		return ActivityId;
	}

	public void setActivityId(Integer activityId) {
		ActivityId = activityId;
	}

	public void setOpenId(String openId) {
		OpenId = openId;
	}
	
	public String getUrl() {
		return Url;
	}
	
	public void setUrl(String url) {
		Url = url;
	}
	
	public String getSource() {
		return Source;
	}
	
	public void setSource(String source) {
		Source = source;
	}

}
