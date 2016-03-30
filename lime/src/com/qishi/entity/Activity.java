package com.qishi.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "T_MciroActivity")
public class Activity implements  Serializable{
	private static final long serialVersionUID = 1L; 
	
	private int ActivityId;
	//活动名称
	private String ActivityName;
	
	private Date BeginDate;
	
	private Date StopDate;
	
	private Integer PrizeCount;
	@Id
	@Column(name = "ActivityId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getActivityId() {
		return ActivityId;
	}

	public void setActivityId(int activityId) {
		ActivityId = activityId;
	}

	public String getActivityName() {
		return ActivityName;
	}

	public void setActivityName(String activityName) {
		ActivityName = activityName;
	}

	public Date getBeginDate() {
		return BeginDate;
	}

	public void setBeginDate(Date beginDate) {
		BeginDate = beginDate;
	}

	public Date getStopDate() {
		return StopDate;
	}

	public void setStopDate(Date stopDate) {
		StopDate = stopDate;
	}

	public Integer getPrizeCount() {
		return PrizeCount;
	}

	public void setPrizeCount(Integer prizeCount) {
		PrizeCount = prizeCount;
	}
	
	
	

}
