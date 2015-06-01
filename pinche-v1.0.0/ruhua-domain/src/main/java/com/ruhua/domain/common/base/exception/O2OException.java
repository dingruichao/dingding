package com.ruhua.domain.common.base.exception;


import com.ruhua.domain.common.base.constants.ServiceResponseCode;
import com.ruhua.domain.common.base.enums.ErrorCodeEnum;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class O2OException extends RuntimeException
{
    private String code;
    private List<String> detail;

    public O2OException(String code)
    {
        this.code = code;
    }

    public O2OException(String code, String message) {
        super(message);
        this.code = code;
    }

    public O2OException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public O2OException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public O2OException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
    }

    public O2OException(ErrorCodeEnum errorCodeEnum, Throwable cause) {
        super(errorCodeEnum.getMsg(), cause);
        this.code = errorCodeEnum.getCode();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return joinDetail();
    }

    public List<String> getDetailList() {
        return this.detail;
    }

    private void preAddDetail() {
        if (this.detail == null)
            this.detail = new ArrayList();
    }

    public void setDetail(String detail)
    {
        preAddDetail();
        this.detail.clear();
        this.detail.add(detail);
    }

    public void setDetail(String[] detail) {
        if (detail == null) {
            return;
        }
        preAddDetail();
        this.detail.addAll(Arrays.asList(detail));
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }

    public <T extends O2OException> T addDetail(String detail) {
        preAddDetail();
        this.detail.add(detail);
        return (T)this;
    }

    public <T extends O2OException> T addDetail(List<String> detail) {
        if (detail == null) {
            return (T)this;
        }
        preAddDetail();
        this.detail.addAll(detail);
        return (T)this;
    }

    public <T extends O2OException> T addDetail(String[] detail) {
        if (detail == null) {
            return (T)this;
        }
        preAddDetail();
        this.detail.addAll(Arrays.asList(detail));
        return (T)this;
    }

    private String joinDetail() {
        if ((this.detail == null) || (this.detail.size() == 0)) {
            return null;
        }
        if (this.detail.size() == 1) {
            return (String)this.detail.get(0);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : this.detail) {
            stringBuilder.append("，").append(item);
        }
        return stringBuilder.substring(1);
    }

    public String toString() {
        return new StringBuilder().append("code:").append(this.code).append(", msg:").append(getMessage()).append(",detail:[").append(joinDetail()).append("]").toString();
    }

    public static void main(String[] args) {
        O2OException exception = new O2OException(ServiceResponseCode.SESSION_EXPIRED);
        exception.setDetail(new String[] { "1", "2" });
        exception.addDetail("popErrorCode：12312");
        System.out.println(exception);
    }
}