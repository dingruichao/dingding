package com.ruhua.domain.common;

/**
 * Created by dingrc on 2015/4/21.
 */
public class UserFriendsCriteria extends  Criteria {

    // user_id
    private Integer userId;
    private String delFlag;

    private String beginTime;

    private String endTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
