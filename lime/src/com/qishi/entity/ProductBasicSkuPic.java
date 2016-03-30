package com.qishi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 

@Table(name="T_ProductBasicSkuPic") 

public class ProductBasicSkuPic {
	
	//产品SKU图片Id
	private int SKUPicId;
	//产品SKU
	private int SKU;
	//产品Id
	private int ProId;
	//单品页小图
	private String SmallPic;
	//单品页中图
	private String MidPic;
	//单品页大图
	private String BigPic;

	@Id 
	@Column(name="SKUPicId") 

	@GeneratedValue() 
	public int getSKUPicId() {
		return SKUPicId;
	}
	public void setSKUPicId(int sKUPicId) {
		SKUPicId = sKUPicId;
	}
	public int getSKU() {
		return SKU;
	}
	public void setSKU(int sKU) {
		SKU = sKU;
	}
	public int getProId() {
		return ProId;
	}
	public void setProId(int proId) {
		ProId = proId;
	}
	public String getSmallPic() {
		return SmallPic;
	}
	public void setSmallPic(String smallPic) {
		SmallPic = smallPic;
	}
	public String getMidPic() {
		return MidPic;
	}
	public void setMidPic(String midPic) {
		MidPic = midPic;
	}
	public String getBigPic() {
		return BigPic;
	}
	public void setBigPic(String bigPic) {
		BigPic = bigPic;
	}

}
