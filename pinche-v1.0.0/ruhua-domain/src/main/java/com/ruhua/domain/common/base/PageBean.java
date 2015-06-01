package com.ruhua.domain.common.base;

import java.io.Serializable;
import java.util.List;

public class PageBean<T>
  implements Serializable
{
  public static final int DEFAULT_PAGE_SIZE = 10;
  private int page;
  private int pageSize = 10;
  private int totalCount;
  private List<T> resultList;

  public PageBean(int totalCount, List<T> resultList)
  {
    this.totalCount = totalCount;
    this.resultList = resultList;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public List<T> getResultList() {
    return this.resultList;
  }

  public int getTotalCount() {
    return this.totalCount;
  }

  public int getTotalPage()
  {
    int totalPage = 0;
    if (this.totalCount > 0) {
      totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
    }

    return totalPage;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPage() {
    return this.page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public void setResultList(List<T> resultList) {
    this.resultList = resultList;
  }
}