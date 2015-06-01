package com.ruhua.domain.common.base.response;


import com.ruhua.domain.common.base.enums.ErrorCodeEnum;

import java.util.Map;

public class BaseMapResponse<K, V> extends ServiceResponse
{
  private Map<K, V> data;

  public BaseMapResponse()
  {
  }

  public BaseMapResponse(String code, String msg)
  {
    super(code, msg);
  }

  public BaseMapResponse(String code, String msg, String detail) {
    super(code, msg, detail);
  }

  public BaseMapResponse(ErrorCodeEnum errorCodeEnum) {
    super(errorCodeEnum);
  }

  public BaseMapResponse(ErrorCodeEnum errorCodeEnum, String detail) {
    super(errorCodeEnum);
    super.setDetail(detail);
  }

  public BaseMapResponse(ErrorCodeEnum errorCodeEnum, Map<K, V> data) {
    super(errorCodeEnum);
    this.data = data;
  }

  public Map<K, V> getData() {
    return this.data;
  }

  public void setData(Map<K, V> data) {
    this.data = data;
  }
}