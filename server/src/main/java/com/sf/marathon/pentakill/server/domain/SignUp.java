package com.sf.marathon.pentakill.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sign_up")
public class SignUp {
	@Id
	@GeneratedValue
	@Column(name = "SIGN_UP_ID")
	private Long id;

	@Column(name = "SHIPPING_GROUP_ID")
	private Long groupId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_CELLPHONE")
	private String userCellphone;

	@Column(name = "USER_ADDRESS")
	private String userAddress;

	@Column(name = "ITEM_NUMBER_PER_DAY")
	private Integer numPerDay;

	@Column(name = "ITEM_WEIGHT_PER_DAY")
	private Double weightPerDay;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getNumPerDay() {
		return numPerDay;
	}

	public void setNumPerDay(Integer numPerDay) {
		this.numPerDay = numPerDay;
	}

	public Double getWeightPerDay() {
		return weightPerDay;
	}

	public void setWeightPerDay(Double weightPerDay) {
		this.weightPerDay = weightPerDay;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

}
