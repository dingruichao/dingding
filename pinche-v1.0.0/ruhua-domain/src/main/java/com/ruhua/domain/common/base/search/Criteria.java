package com.ruhua.domain.common.base.search;

import java.util.HashMap;
import java.util.Map;

public class Criteria
{
  private Map<String, Object> extFields;

  public Criteria addExtField(String fieldName, Object filedValue)
  {
    if (this.extFields == null) {
      this.extFields = new HashMap();
    }
    this.extFields.put(fieldName, filedValue);
    return this;
  }

  public Map<String, Object> getExtFields() {
    return this.extFields;
  }

  public void setExtFields(Map<String, Object> extFields) {
    this.extFields = extFields;
  }
}