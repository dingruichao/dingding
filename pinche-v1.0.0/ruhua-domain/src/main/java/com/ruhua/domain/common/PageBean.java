package com.ruhua.domain.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dingruichao
 * Date: 15-4-20
 * Time: 下午4:03
 */
public class PageBean<T> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    //默认每页条数
    public static final int DEFAULT_PAGESIZE = 20;

	//当前页码
    private int currentPage;

    private int rowEnd;
    private int rowStart;



    //每页的条数
    private int pageSize;
    //此次查询的本页记录数
    private int currentCount;
    //此次查询的总记录数（并不一定是当前页的记录条数）
    private long totalCount;
    //存放查询的返回结果
    private List<T> resultList;


    public PageBean() {
    }
    /**
     * 作为查询参数的PageBean的构造方法
     * @param currentPage 当前要查询的页数（第一页编号为1）
     * @param pageSize
     */
    public PageBean(int currentPage, int pageSize) {
        if (currentPage <= 0)
            currentPage = 1;
        if (pageSize <= 0)
            pageSize = DEFAULT_PAGESIZE;

        this.currentPage = currentPage;
        this.pageSize = pageSize;

    }

    /**
     * 作为查询结果的PageBean的构造方法
     * @param totalCount
     * @param list
     */
    public PageBean(int totalCount, List<T> list) {
        this.totalCount = totalCount;
        addAll(list);
    }
    /**
     * 作为查询参数的PageBean的构造方法
     * @param currentPage 当前要查询的页数（第一页编号为1）
     * @param pageSize
     * @param totalCount
     * @param list
     */
    public PageBean(int currentPage, int pageSize, long totalCount, List<T> list) {
        if (currentPage <= 0)
            currentPage = 1;
        if (pageSize <= 0)
            pageSize = DEFAULT_PAGESIZE;

        this.currentPage = currentPage;
        this.pageSize = pageSize;


        this.totalCount = totalCount;
        addAll(list);
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void addAll(List<T> result) {
        resultList = new ArrayList<T>();
        resultList.addAll(result);
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

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


}
