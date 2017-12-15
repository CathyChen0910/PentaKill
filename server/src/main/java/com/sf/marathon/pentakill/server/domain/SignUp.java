package com.sf.marathon.pentakill.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shipping_group")
public class SignUp {
	@Id
	@GeneratedValue
	@Column(name = "SIGN_UP_ID")
	private Long id;
	
	@Column(name = "SHIPPING_GROUP_ID")
	private Long marketId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "USER_CELLPHONE")
	private String userCellphone;
	
	@Column(name = "USER_ADDRESS")
	private String userAddress;
	
	@Column(name = "ITEM_NUMBER_PER_DAY")
	private int numPerDay;
	
	@Column(name = "ITEM_WEIGHT_PER_DAY")
	private double weightPerDay;
	
	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMarketId() {
		return marketId;
	}

	public void setMarketId(Long marketId) {
		this.marketId = marketId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCellphone() {
		return userCellphone;
	}

	public void setUserCellphone(String userCellphone) {
		this.userCellphone = userCellphone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public int getNumPerDay() {
		return numPerDay;
	}

	public void setNumPerDay(int numPerDay) {
		this.numPerDay = numPerDay;
	}

	public double getWeightPerDay() {
		return weightPerDay;
	}

	public void setWeightPerDay(double weightPerDay) {
		this.weightPerDay = weightPerDay;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
