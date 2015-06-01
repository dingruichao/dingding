package com.ruhua.domain.common;

import java.util.Date;

/**
 * Created by dingrc on 2015/4/21.
 */
public class PlanUsersCriteria extends  Criteria{

    // user_id
    private Long userId;
    // say_samething
    private String saySamething;

    // 参与拼车地址
    private String addAddress;


    private Long planId;

    private Integer state;



    private String beginTime;

    private String endTime;

    private Double maxLat;
    private Double minLat;
    private Double maxLng;
    private Double minLng;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSaySamething() {
        return saySamething;
    }

    public void setSaySamething(String saySamething) {
        this.saySamething = saySamething;
    }

    public String getAddAddress() {
        return addAddress;
    }

    public void setAddAddress(String addAddress) {
        this.addAddress = addAddress;
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

    public Double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(Double maxLat) {
        this.maxLat = maxLat;
    }

    public Double getMinLat() {
        return minLat;
    }

    public void setMinLat(Double minLat) {
        this.minLat = minLat;
    }

    public Double getMaxLng() {
        return maxLng;
    }

    public void setMaxLng(Double maxLng) {
        this.maxLng = maxLng;
    }

    public Double getMinLng() {
        return minLng;
    }

    public void setMinLng(Double minLng) {
        this.minLng = minLng;
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
