package com.ruhua.domain.common.base.response;


import com.ruhua.domain.common.base.constants.ServiceResponseCode;
import com.ruhua.domain.common.base.enums.ErrorCodeEnum;

import java.io.Serializable;

public class ServiceResponse
  implements Serializable
{
  private String code;
  private String msg;
  private String detail;

  public static ServiceResponse successResponse()
  {
    return new ServiceResponse(ServiceResponseCode.SUCCESS);
  }

  public static ServiceResponse failureResponse()
  {
    return new ServiceResponse(ServiceResponseCode.FAILURE);
  }

  public ServiceResponse() {
  }

  public ServiceResponse(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public ServiceResponse(String code, String msg, String detail) {
    this.code = code;
    this.msg = msg;
    this.detail = detail;
  }

  public ServiceResponse(ErrorCodeEnum errorCodeEnum) {
    this.code = errorCodeEnum.getCode();
    this.msg = errorCodeEnum.getMsg();
  }

  public ServiceResponse(ErrorCodeEnum errorCodeEnum, String detail) {
    this.code = errorCodeEnum.getCode();
    this.msg = errorCodeEnum.getMsg();
    this.detail = detail;
  }

  public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
    this.code = errorCodeEnum.getCode();
    this.msg = errorCodeEnum.getMsg();
  }

  public String getCode()
  {
    return this.code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public String getMsg()
  {
    return this.msg;
  }

  public void setMsg(String msg)
  {
    this.msg = msg;
  }

  public String getDetail() {
    return this.detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public <T extends ServiceResponse> T addDetail(String detail) {
    setDetail(detail);
    return (T)this;
  }
}