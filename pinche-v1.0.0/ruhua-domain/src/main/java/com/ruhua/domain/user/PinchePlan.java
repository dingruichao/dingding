package com.ruhua.domain.user;

import java.io.Serializable;
import java.util.Date;

// PinchePlan
public class PinchePlan implements Serializable {
	// id
	private long id;
	// 集合地址
	private String jiheAddress;
	// 地址类型 1,用户地址，2当前地址
	private String jiheAddressType;
	// 前往何处
	private String goAddress;
	// 费用
	private Long pcMoney;
	// remark
	private String remark;
	// go_time
	private Date goDate;
	// go_time
	private String goTime;
	// create_time
	private Date createTime;
	// create_user
	private Integer createUser;
	// 所在社区经纬度
	private Double lng;
	// lat
	private Double lat;
	// VERSION
	private int version;

	// update_time
	private Date updateTime;
	private String delFlag;

	// getid
	public long getId() {
		return id;
	}

	// setid
	public void setId(long id) {
		this.id = id;
	}
	// get集合地址
	public String getJiheAddress() {
		return jiheAddress;
	}

	// set集合地址
	public void setJiheAddress(String jiheAddress) {
		this.jiheAddress = jiheAddress;
	}
	// get地址类型 1,用户地址，2当前地址
	public String getJiheAddressType() {
		return jiheAddressType;
	}

	// set地址类型 1,用户地址，2当前地址
	public void setJiheAddressType(String jiheAddressType) {
		this.jiheAddressType = jiheAddressType;
	}
	// get前往何处
	public String getGoAddress() {
		return goAddress;
	}

	// set前往何处
	public void setGoAddress(String goAddress) {
		this.goAddress = goAddress;
	}
	// get费用
	public Long getPcMoney() {
		return pcMoney;
	}

	// set费用
	public void setPcMoney(Long pcMoney) {
		this.pcMoney = pcMoney;
	}
	// getremark
	public String getRemark() {
		return remark;
	}

	// setremark
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getGoDate() {
		return goDate;
	}

	public void setGoDate(Date goDate) {
		this.goDate = goDate;
	}

	public String getGoTime() {
		return goTime;
	}

	public void setGoTime(String goTime) {
		this.goTime = goTime;
	}

	// getcreate_time
	public Date getCreateTime() {
		return createTime;
	}

	// setcreate_time
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	// getcreate_user
	public Integer getCreateUser() {
		return createUser;
	}

	// setcreate_user
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
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
	// getVERSION
	public int getVersion() {
		return version;
	}

	// setVERSION
	public void setVersion(int version) {
		this.version = version;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;

	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}