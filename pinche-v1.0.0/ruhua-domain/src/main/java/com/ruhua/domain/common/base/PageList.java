package com.ruhua.domain.common.base;

import java.util.ArrayList;
import java.util.List;

public class PageList<T> extends ArrayList
  implements List
{
  private Integer totalCount;

  public PageList()
  {
  }

  public PageList(int totalCount, List<T> list)
  {
    this.totalCount = Integer.valueOf(totalCount);
    addAll(list);
  }

  public Integer getTotalCount() {
    if (this.totalCount == null) {
      return Integer.valueOf(size());
    }
    return this.totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }
}