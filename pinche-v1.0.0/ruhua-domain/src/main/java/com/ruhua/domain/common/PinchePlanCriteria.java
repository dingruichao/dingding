package com.ruhua.domain.common;

import java.util.Date;

/**
 * Created by dingrc on 2015/4/21.
 */
public class PinchePlanCriteria extends  Criteria{

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
    private Date goTime;
    // create_time
    private Date createTime;
    // create_user
    private Integer createUser;

    private String delFlag;

    private String beginTime;

    private String endTime;

    private Double maxLat;
    private Double minLat;
    private Double maxLng;
    private Double minLng;


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
    // getgo_time
    public Date getGoTime() {
        return goTime;
    }

    // setgo_time
    public void setGoTime(Date goTime) {
        this.goTime = goTime;
    }
    // getcreate_time
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
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
