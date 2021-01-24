package com.example.common;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;

/**分页结果对象*/
public class PageResultObject<T> {
    
    private final Integer pageNumber;
    
    private final Integer pageSize;
    
    private final Long totalElements;
    
    private final Integer totalPages;

    private final List<T> data;
    
    private PageResultObject(List<T> data, int page, int size, long totalElements, Integer totalPages) {
        this.data = data;
        this.pageNumber = page;
        this.pageSize = size;
        this.totalElements = (totalElements < 0) ? 0 : totalElements;
        if (totalPages == null) {
            this.totalPages = this.calcPages(totalElements, pageSize);
        } else {
            this.totalPages = totalPages;
        }
    }
    
    public static <T> PageResultObject<T> build(PageInfo<T> pageinfo) {
        return new PageResultObject<T>(pageinfo.getList(), pageinfo.getPageNum(), 
                pageinfo.getSize(), pageinfo.getTotal(), pageinfo.getPages());
    }
    
    /**手动分页*/
    public static <T> PageResultObject<T> build(List<T> list, PageObject pageable) {
        List<T> partList = new ArrayList<>();
        int start = pageable.getOffset();
        int end = Math.min(pageable.getOffset() + pageable.getPageSize(), list.size());
        for (int i = start; i < end; i++) {
            T item = list.get(i);
            partList.add(item);
        }
        return new PageResultObject<T>(partList, pageable.getPageNum(), pageable.getPageSize(),
                list.size() + 0L, null);
    }
    
    public boolean getLast() {
        return this.pageNumber >= this.totalPages;
    }
    
    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "PageResultObject [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", totalElements=" + totalElements
                + ", totalPages=" + totalPages + ", data=" + data + "]";
    }
    
    /**计算总页数*/
    private int calcPages(long total, int size) {
        if (total <= 0) 
            return 0; 
        return (int) (total - 1) / size + 1;
    }

}
