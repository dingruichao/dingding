//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.domain.common.base;


import com.ruhua.domain.common.ErrorConstant;

public class JDResult<T> extends BaseBean {
    private static final long serialVersionUID = -1627120670767191981L;
    private boolean isRet;
    private String retCode;
    private String retMsg;
    private T data;

    public JDResult() {
    }

    public JDResult(ErrorConstant codeMsgEnum, T data) {
        if(ErrorConstant.PROCESS_SUCCESS == codeMsgEnum) {
            this.isRet = Boolean.TRUE.booleanValue();
        } else {
            this.isRet = Boolean.FALSE.booleanValue();
        }

        this.retCode = codeMsgEnum.getErrorCode();
        this.retMsg = codeMsgEnum.getErrorMsg();
        this.data = data;
    }

    public JDResult(ErrorConstant codeMsgEnum, Exception e) {
        if(ErrorConstant.PROCESS_SUCCESS == codeMsgEnum) {
            this.isRet = Boolean.TRUE.booleanValue();
        } else {
            this.isRet = Boolean.FALSE.booleanValue();
        }

        this.retCode = codeMsgEnum.getErrorCode();
        this.retMsg = codeMsgEnum.getErrorMsg() + ":" + e.getMessage();
    }

    public JDResult(boolean isRet, String retCode, String retMsg) {
        this.isRet = isRet;
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public JDResult(boolean isRet, String retCode, String retMsg, T data) {
        this.isRet = isRet;
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.data = data;
    }

    public boolean isRet() {
        return this.isRet;
    }

    public void setRet(boolean isRet) {
        this.isRet = isRet;
    }

    public String getRetCode() {
        return this.retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return this.retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
