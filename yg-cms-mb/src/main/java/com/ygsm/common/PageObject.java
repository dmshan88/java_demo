package com.ygsm.common;

import com.github.pagehelper.IPage;

/**分页对象*/
public class PageObject implements IPage {
    
    private Integer pageNum;
    
    private Integer pageSize;
    
    private String orderBy;
    
    public PageObject(Integer pageNum, Integer pageSize) {
        this(pageNum, pageSize, "");
    }
    
    public PageObject(Integer pageNum, Integer pageSize, String orderBy) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
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

}
