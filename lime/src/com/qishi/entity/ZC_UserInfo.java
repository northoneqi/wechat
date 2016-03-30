package com.qishi.entity;

import java.io.Serializable;

import javax.persistence.Entity;
public class ZC_UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String zccode;
	private String zName;
	private String type;
	public String getZccode() {
		return zccode;
	}
	public void setZccode(String zccode) {
		this.zccode = zccode;
	}
	public String getzName() {
		return zName;
	}
	public void setzName(String zName) {
		this.zName = zName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
