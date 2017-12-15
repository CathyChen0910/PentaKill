package com.sf.marathon.pentakill.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pro_market_base")
public class Market {
	
	@Id
	@Column(name = "MKT_ID")
	private String id;
	
	@Column(name = "MKT_NAME_SHOW")
	private String marketName;
	
	@Column(name = "DAILY_MIN_PACKAGES")
	private int dailyMinPacks;
	
	@Column(name = "WEIGHT_MIN")
	private double minWeight;
	
	@Column(name = "WEIGHT_MAX")
	private double maxWeight;
	
	@Column(name = "BASE_PRICE")
	private double basePrice;
	
	@Column(name = "BASE_WEIGHT")
	private String baseWeight;
	
	@Column(name = "GROUP_LIMIT")
	private int limitNum;
	
	@Column(name = "GROUP_DURATION")
	private int groupPeriod;
	
	@Column(name = "USE REQUIRE")
	private String userRequire;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public int getDailyMinPacks() {
		return dailyMinPacks;
	}

	public void setDailyMinPacks(int dailyMinPacks) {
		this.dailyMinPacks = dailyMinPacks;
	}

	public double getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(double minWeight) {
		this.minWeight = minWeight;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public String getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(String baseWeight) {
		this.baseWeight = baseWeight;
	}

	public int getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}

	public int getGroupPeriod() {
		return groupPeriod;
	}

	public void setGroupPeriod(int groupPeriod) {
		this.groupPeriod = groupPeriod;
	}

	public String getUserRequire() {
		return userRequire;
	}

	public void setUserRequire(String userRequire) {
		this.userRequire = userRequire;
	}
}
