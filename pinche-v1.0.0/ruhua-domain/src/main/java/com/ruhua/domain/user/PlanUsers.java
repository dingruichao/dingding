package com.ruhua.domain.user;

import java.io.Serializable;
import java.util.Date;

// PlanUsers
public class PlanUsers implements Serializable {
	// id
	private long id;
	// user_id
	private Long userId;
	// say_samething
	private String saySamething;
	// 所在社区经纬度
	private Double lng;
	// lat
	private Double lat;
	// 参与拼车地址
	private String addAddress;
	// VERSION
	private int version;
	// create_time
	private Date createTime;
	// update_time
	private Date updateTime;

	private Long planId;

	private Integer state;

	// getid
	public long getId() {
		return id;
	}

	// setid
	public void setId(long id) {
		this.id = id;
	}
	// getuser_id
	public Long getUserId() {
		return userId;
	}

	// setuser_id
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	// getsay_samething
	public String getSaySamething() {
		return saySamething;
	}

	// setsay_samething
	public void setSaySamething(String saySamething) {
		this.saySamething = saySamething;
	}
	// get所在社区经纬度
	public Double getLng() {
		return lng;
	}

	// set所在社区经纬度
	public void setLng(Double lng) {
		this.lng = lng;
	}
	// getlat
	public Double getLat() {
		return lat;
	}

	// setlat
	public void setLat(Double lat) {
		this.lat = lat;
	}
	// get参与拼车地址
	public String getAddAddress() {
		return addAddress;
	}

	// set参与拼车地址
	public void setAddAddress(String addAddress) {
		this.addAddress = addAddress;
	}
	// getVERSION
	public int getVersion() {
		return version;
	}

	// setVERSION
	public void setVersion(int version) {
		this.version = version;
	}
	// getcreate_time
	public Date getCreateTime() {
		return createTime;
	}

	// setcreate_time
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	// getupdate_time
	public Date getUpdateTime() {
		return updateTime;
	}

	// setupdate_time
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}