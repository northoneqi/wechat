package com.qishi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 

@Table(name="T_ProductClass") 
public class ProductClass {
	
	//分类Id
	private String ClassId;
	//分类名称
	private String ClassName;
	//分类父Id
	private int ParentId;
	//类别级别
	private int Level;
	//分单（拆单）标识：此列标识本类别根据什么类型来分单。（例如：1、报刊类型分单。2、水类型分单。3、蔬菜类型分单，等等）
	private int OrderSplit;
	//微信类别名称
	private String WXCLASSNAME;
	//
	private int ISWXSHOW;


	public String getWXCLASSNAME() {
		return WXCLASSNAME;
	}
	public void setWXCLASSNAME(String wXCLASSNAME) {
		WXCLASSNAME = wXCLASSNAME;
	}
	public int getISWXSHOW() {
		return ISWXSHOW;
	}
	public void setISWXSHOW(int iSWXSHOW) {
		ISWXSHOW = iSWXSHOW;
	}
	@Column(length=200,columnDefinition="varchar(200)")
	public String getClassName() {
		return ClassName;
	}
	@Id 

	@Column(name="ClassId") 

	@GeneratedValue() 
	public String getClassId() {
		return ClassId;
	}
	public void setClassId(String classId) {
		ClassId = classId;
	}
	public void setClassName(String className) {
		ClassName = className;
	}
	public int getParentId() {
		return ParentId;
	}
	public void setParentId(int parentId) {
		ParentId = parentId;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}
	public int getOrderSplit() {
		return OrderSplit;
	}
	public void setOrderSplit(int orderSplit) {
		OrderSplit = orderSplit;
	}

}
