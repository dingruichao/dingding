package com.ruhua.domain.common.base.search;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class SearchCriteria extends Criteria
  implements Serializable
{
  private static final int DEFAULT_PAGE_NO = 1;
  private static final int DEFAULT_PAGE_SIZE = 20;
  protected int pageNo = 1;

  protected int pageSize = 20;
  private LinkedHashMap<String, String> sortItemMap;
  protected boolean autoCount = false;

  protected boolean doPage = false;

  public SearchCriteria()
  {
  }

  public SearchCriteria(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageNo()
  {
    return this.pageNo;
  }

  public void setPageNo(int pageNo)
  {
    this.pageNo = pageNo;

    if (pageNo < 1)
      this.pageNo = 1;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }

  public boolean isDoPage()
  {
    return this.doPage;
  }

  public void setDoPage(boolean doPage) {
    this.doPage = doPage;
  }

  public LinkedHashMap<String, String> getSortItemMap() {
    return this.sortItemMap;
  }

  public void setSortItemMap(LinkedHashMap<String, String> sortItemMap) {
    this.sortItemMap = sortItemMap;
  }

  public boolean isAutoCount()
  {
    return this.autoCount;
  }

  public void setAutoCount(boolean autoCount)
  {
    this.autoCount = autoCount;
  }
}