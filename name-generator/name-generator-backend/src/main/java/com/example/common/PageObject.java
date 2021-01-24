package com.example.common;

import com.github.pagehelper.IPage;

/**分页对象*/
public class PageObject implements IPage {
    
    private final int startPageNum = 1; //1为第一页
    
    private final int defaultPageSize = 30;//默认页面大小
    
    private Integer pageNum;
    
    private Integer pageSize;
    
    private String orderBy;
    
    public static PageObject build(Integer pageNum, Integer pageSize) {
        return new PageObject(pageNum, pageSize, "");
    }
    
    private PageObject(Integer pageNum, Integer pageSize, String orderBy) {
        this.pageNum = (pageNum == null || pageNum < startPageNum) ? startPageNum : pageNum;
        this.pageSize = (pageSize == null || pageSize < 0 ) ? defaultPageSize : pageSize;
        this.orderBy = orderBy;
    }
    
    public Integer getOffset() {
        return (this.getPageNum() - startPageNum) * this.getPageSize();
    }
    
    @Override
    public Integer getPageNum() {
        return pageNum;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public String getOrderBy() {
        return orderBy;
    }

    @Override
    public String toString() {
        return "PageObject [pageNum=" + pageNum + ", pageSize=" + pageSize + ", offset=" + getOffset()
                + ", getOrderBy=" + orderBy + "]";
    }
}
