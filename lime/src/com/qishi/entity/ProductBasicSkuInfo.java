package com.qishi.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ProductBasicSkuInfo")
public class ProductBasicSkuInfo {

	// 产品skuid
	private int ProSKUId;
	// 产品sku
	private int SKU;
	// 产品Id
	private int ProId;
	// 产品首页展示图片
	private String SKUName;
	// 市场价
	private Double MarketPrice;
	// 本站销售价
	private Double SellPrice;
	// 产品列表图片
	private String ListPagePic;
	// 产品页颜色图片
	private String ColorPic;
	// 产品sku添加时间
	private Date SKUAddtime;
	//
	private int Volume;
	// 销量
	private int SaleVolume;
	// 评论
	private int Comment;
	// 人气
	private int Popularity;
	// 是否上架
	private boolean IsShow;
	// 产品标记特价1促销2hot3
	private int SkuFlag;
	// 是否网站
	private boolean IsWeb;
	// 是否呼叫中心
	private boolean IsCallCenter;
	// 是否分站
	private boolean IsSite;
	// 税率
	private boolean TaxRate;
	// SKU属性集合。使用分割字符拼接。
	private String AttList;
	// 是否使用百分比计算销售价格
	private boolean IsUseCal;
	// 计算百分比
	private Double PercentOf;
	// 采购平均价格
	private Double AveragePrice;
	//
	private int ProSKUIdOld;

	public Double getMarketPrice() {
		return MarketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		MarketPrice = marketPrice;
	}

	public Double getSellPrice() {
		return SellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		SellPrice = sellPrice;
	}

	public Double getPercentOf() {
		return PercentOf;
	}

	public void setPercentOf(Double percentOf) {
		PercentOf = percentOf;
	}

	public Double getAveragePrice() {
		return AveragePrice;
	}

	public void setAveragePrice(Double averagePrice) {
		AveragePrice = averagePrice;
	}

	@Id
	@Column(name = "ProSKUId")
	@GeneratedValue()
	public int getProSKUId() {
		return ProSKUId;
	}

	public void setProSKUId(int proSKUId) {
		ProSKUId = proSKUId;
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

	public String getListPagePic() {
		return ListPagePic;
	}

	public void setListPagePic(String listPagePic) {
		ListPagePic = listPagePic;
	}

	public String getColorPic() {
		return ColorPic;
	}

	public void setColorPic(String colorPic) {
		ColorPic = colorPic;
	}

	public Date getSKUAddtime() {
		return SKUAddtime;
	}

	public void setSKUAddtime(Date sKUAddtime) {
		SKUAddtime = sKUAddtime;
	}

	public int getSaleVolume() {
		return SaleVolume;
	}

	public void setSaleVolume(int saleVolume) {
		SaleVolume = saleVolume;
	}

	public int getComment() {
		return Comment;
	}

	public void setComment(int comment) {
		Comment = comment;
	}

	public int getPopularity() {
		return Popularity;
	}

	public void setPopularity(int popularity) {
		Popularity = popularity;
	}

	public boolean isIsShow() {
		return IsShow;
	}

	public void setIsShow(boolean isShow) {
		IsShow = isShow;
	}

	public int getSkuFlag() {
		return SkuFlag;
	}

	public void setSkuFlag(int skuFlag) {
		SkuFlag = skuFlag;
	}

	public boolean isIsWeb() {
		return IsWeb;
	}

	public void setIsWeb(boolean isWeb) {
		IsWeb = isWeb;
	}

	public boolean isIsCallCenter() {
		return IsCallCenter;
	}

	public void setIsCallCenter(boolean isCallCenter) {
		IsCallCenter = isCallCenter;
	}

	public boolean isIsSite() {
		return IsSite;
	}

	public void setIsSite(boolean isSite) {
		IsSite = isSite;
	}

	public boolean isTaxRate() {
		return TaxRate;
	}

	public void setTaxRate(boolean taxRate) {
		TaxRate = taxRate;
	}

	public String getAttList() {
		return AttList;
	}

	public void setAttList(String attList) {
		AttList = attList;
	}

	public boolean isIsUseCal() {
		return IsUseCal;
	}

	public void setIsUseCal(boolean isUseCal) {
		IsUseCal = isUseCal;
	}

	public String getSKUName() {
		return SKUName;
	}

	public void setSKUName(String sKUName) {
		SKUName = sKUName;
	}

	public int getVolume() {
		return Volume;
	}

	public void setVolume(int volume) {
		Volume = volume;
	}
	@Column(name = "ProSKUIdOld")
	public int getProSKUIdOld() {
		return ProSKUIdOld;
	}

	public void setProSKUIdOld(int proSKUIdOld) {
		ProSKUIdOld = proSKUIdOld;
	}

}
