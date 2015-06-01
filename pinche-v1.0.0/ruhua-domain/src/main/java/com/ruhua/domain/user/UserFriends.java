package com.ruhua.domain.user;

import java.util.Date;

// UserFriends
public class UserFriends{
	// id
	private long id;
	// user_id
	private Integer userId;
	// Friend_user_id
	private Integer friendUserId;
	// create_time
	private Date createTime;

	// VERSION
	private int version;
	// 1,删除 0未删除
	private String delFlag;
	// update_time
	private Date updateTime;
	// remark
	private String remark;
  
	// getid
	public long getId() {
		return id;
	}

	// setid
	public void setId(long id) {
		this.id = id;
	}
	// getuser_id
	public Integer getUserId() {
		return userId;
	}

	// setuser_id
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	// getFriend_user_id
	public Integer getFriendUserId() {
		return friendUserId;
	}

	// setFriend_user_id
	public void setFriendUserId(Integer friendUserId) {
		this.friendUserId = friendUserId;
	}
	// getcreate_time
	public Date getCreateTime() {
		return createTime;
	}

	// setcreate_time
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	// getVERSION
	public int getVersion() {
		return version;
	}

	// setVERSION
	public void setVersion(int version) {
		this.version = version;
	}
	// get1,删除 0未删除
	public String getDelFlag() {
		return delFlag;
	}

	// set1,删除 0未删除
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	// getupdate_time
	public Date getUpdateTime() {
		return updateTime;
	}

	// setupdate_time
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	// getremark
	public String getRemark() {
		return remark;
	}

	// setremark
	public void setRemark(String remark) {
		this.remark = remark;
	}
}