package com.ruhua.domain.common;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class Criteria
  implements Serializable
{
  public static final String SORT_DIRECTION_ASC = "ASC";
  public static final String SORT_DIRECTION_DESC = "DESC";
  private LinkedHashMap<String, String> sortItemMap;



  private int rowEnd;
  private int rowStart;
  private int pageSize;
  private int pageNo;

  public int getRowEnd() {
    return rowEnd;
  }

  public void setRowEnd(int rowEnd) {
    this.rowEnd = rowEnd;
  }

  public int getRowStart() {
    return rowStart;
  }

  public void setRowStart(int rowStart) {
    this.rowStart = rowStart;
  }

  public LinkedHashMap<String, String> getSortItemMap()
  {
    return this.sortItemMap;
  }

  public void setSortItemMap(LinkedHashMap<String, String> sortItemMap) {
    this.sortItemMap = sortItemMap;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }
}