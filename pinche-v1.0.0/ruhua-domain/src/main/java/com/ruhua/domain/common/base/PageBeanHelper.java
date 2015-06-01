package com.ruhua.domain.common.base;


import com.ruhua.domain.common.base.search.SearchCriteria;

public class PageBeanHelper
{
  public static <T> PageBean<T> buildPageBean(PageList<T> pageList, SearchCriteria criteria)
  {
    PageBean pageBean = new PageBean(pageList.getTotalCount().intValue(), pageList);
    pageBean.setPage(criteria.getPageNo());
    pageBean.setPageSize(criteria.getPageSize());
    return pageBean;
  }
}