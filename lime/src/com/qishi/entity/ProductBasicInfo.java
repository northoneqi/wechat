package com.qishi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ProductBasicInfo")
public class ProductBasicInfo implements Serializable{

	// 产品id
	private int ProId;
	// 分类id
	private int ClassId;
	// 品牌id
	private int BrandId;
	// 产品名称
	private String ProName;
	// 产品描述
	private String ProDescri;
	// 产品包装清单
	private String ProPackInfo;
	// 产品添加时间
	private String ProAddtime;
	// 产品序列号
	private String ProSericeNum;
	// 计量单位
	private int UnitMent;
	//
	private int ProIdOld;
	
	//微信商品名称
	private String WXPRONAME;
	//是否为微信商品
	private int ISWX;

	public String getWXPRONAME() {
		return WXPRONAME;
	}

	public void setWXPRONAME(String wXPRONAME) {
		WXPRONAME = wXPRONAME;
	}

	public int getISWX() {
		return ISWX;
	}

	public void setISWX(int iSWX) {
		ISWX = iSWX;
	}

	@Id
	@Column(name = "ProId")
	@GeneratedValue()
	public int getProId() {
		return ProId;
	}

	public void setProId(int proId) {
		ProId = proId;
	}

	public int getClassId() {
		return ClassId;
	}

	public void setClassId(int classId) {
		ClassId = classId;
	}

	public int getBrandId() {
		return BrandId;
	}

	public void setBrandId(int brandId) {
		BrandId = brandId;
	}

	public String getProName() {
		return ProName;
	}

	public void setProName(String proName) {
		ProName = proName;
	}

	public String getProDescri() {
		return ProDescri;
	}

	public void setProDescri(String proDescri) {
		ProDescri = proDescri;
	}

	public String getProPackInfo() {
		return ProPackInfo;
	}

	public void setProPackInfo(String proPackInfo) {
		ProPackInfo = proPackInfo;
	}

	public String getProAddtime() {
		return ProAddtime;
	}

	public void setProAddtime(String proAddtime) {
		ProAddtime = proAddtime;
	}

	public String getProSericeNum() {
		return ProSericeNum;
	}

	public void setProSericeNum(String proSericeNum) {
		ProSericeNum = proSericeNum;
	}

	public int getUnitMent() {
		return UnitMent;
	}

	public void setUnitMent(int unitMent) {
		UnitMent = unitMent;
	}

	public int getProIdOld() {
		return ProIdOld;
	}

	public void setProIdOld(int proIdOld) {
		ProIdOld = proIdOld;
	}

}
