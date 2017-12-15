package com.sf.marathon.pentakill.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shipping_group")
public class Group {
	@Id
	@GeneratedValue
	@Column(name = "SHIPPING_GROUP_ID")
	private Long id;

	@Column(name = "MARKET_ID")
	private String marketId;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Column(name = "PERIOD_NUMBER")
	private String periodNum;

	@Column(name = "PICTURE")
	private String picture;

	@Column(name = "GROUP_TYPE")
	private String groupType;

	@Column(name = "MIN_BAG_NUMBER_PER_DAY")
	private Integer minBagNum;

	@Column(name = "ITEM_WEIGHT")
	private Double itemWeight;

	@Column(name = "PROMISE_USE_PERIOD")
	private Integer promisePeriod;

	@Column(name = "END_TIME")
	private Date endTime;

	@Column(name = "MIN_PRICE")
	private Double minPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(String periodNum) {
		this.periodNum = periodNum;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public Integer getMinBagNum() {
		return minBagNum;
	}

	public void setMinBagNum(Integer minBagNum) {
		this.minBagNum = minBagNum;
	}

	public Double getItemWeight() {
		return itemWeight;
	}

	public void setItemWeight(Double itemWeight) {
		this.itemWeight = itemWeight;
	}

	public Integer getPromisePeriod() {
		return promisePeriod;
	}

	public void setPromisePeriod(Integer promisePeriod) {
		this.promisePeriod = promisePeriod;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

}
