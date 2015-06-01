//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.ruhua.domain.common.base.BaseEntity;
import com.ruhua.domain.common.base.PageList;
import com.ruhua.domain.common.base.search.SearchCriteria;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public abstract class BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());

    public BaseController() {
    }

    protected <T> Map<String, Object> buildPageBeanModel(PageList<T> pageList) {
        HashMap model = new HashMap();
        model.put("rows", pageList);
        model.put("total", pageList.getTotalCount());
        return model;
    }

    protected void setterSearchBaseParams(SearchCriteria criteria, HttpServletRequest request) {
        if(criteria == null) {
            criteria = new SearchCriteria();
        }

        if(!StringUtils.isEmpty(request.getParameter("page"))) {
            criteria.setDoPage(true);
            criteria.setAutoCount(true);
            criteria.setPageNo(Integer.parseInt(request.getParameter("page")));
        }

        if(!StringUtils.isEmpty(request.getParameter("rows"))) {
            criteria.setDoPage(true);
            criteria.setAutoCount(true);
            criteria.setPageSize(Integer.parseInt(request.getParameter("rows")));
        }

        if(!StringUtils.isEmpty(request.getParameter("sort"))) {
            String[] sortFields = request.getParameter("sort").split(",");
            String[] orders = StringUtils.isEmpty(request.getParameter("order"))?null:request.getParameter("order").split(",");
            LinkedHashMap sortItemMap = new LinkedHashMap();
            int index = 0;
            String[] arr$ = sortFields;
            int len$ = sortFields.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String sortField = arr$[i$];
                sortItemMap.put(sortField, orders != null && orders.length > index?orders[index]:"ASC");
                ++index;
            }

            criteria.setSortItemMap(sortItemMap);
        }

    }

    protected abstract String getCurrentUserPin();

    protected void paddingData4Add(BaseEntity entity) {
        entity.setCreatePin(this.getCurrentUserPin());
        entity.setCreateTime(new Date());
    }

    protected void paddingData4Update(BaseEntity entity) {
        entity.setUpdatePin(this.getCurrentUserPin());
        entity.setUpdateTime(new Date());
    }
}
