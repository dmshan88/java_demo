package com.ygsm.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;

/**自定义分页响应*/
public class CustomPageResponse<T> extends BaseResponse {

    @JsonProperty(value = "pageNumber")
    private final Integer page;
    
    @JsonProperty(value = "pageSize")
    private final Integer size;
    
    @JsonProperty(value = "total")
    private final Long totalElements;
    
    private final Integer totalPages;

    @JsonProperty(value = "rows")
    private final List<T> data;
    
    protected CustomPageResponse(List<T> data, Integer code, String message, 
            Integer page, Integer size, Long totalElements, Integer totalPages) {
        super(code, message);
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
    
    protected CustomPageResponse(Integer code, String message) {
        this(null, code, message, 0, 0, 0L, 0);
    }
    
    public static <T> CustomPageResponse<T> ok(List<T> totalData, Integer page, Integer size) {
        ErrorCode errorCode = ErrorCode.OK;
        List<List<T>> partitionList = ListUtils.partition(totalData, size);
        Integer totalPages = 0;
        List<T> data = new ArrayList<>();
        if (partitionList.size() > 0) {
            totalPages = partitionList.size();
            if (page >= 1 && page <= totalPages) {
                data = partitionList.get(page - 1);
            }
        } else {
            page = 1;
            size = 0;
        }
        return new CustomPageResponse<>(data, errorCode.getCode(), errorCode.getMessage(),
                page, size, totalData.size() + 0L, totalPages);
    }
    
    public static <T> CustomPageResponse<T> ok(List<T> data, Integer page, Integer size, 
            Long totalElements, Integer totalPages) {
        ErrorCode errorCode = ErrorCode.OK;
        return new CustomPageResponse<>(data, errorCode.getCode(), errorCode.getMessage(),
                page, size, totalElements, totalPages);
    }
    
    public static <T> CustomPageResponse<T> ok(PageInfo<T> data) {
        return CustomPageResponse.ok(data.getList(), (int)data.getPageNum(), (int)data.getSize(),
                data.getTotal(), (int)data.getPages());
    }

    public static <T> CustomPageResponse<T> error(Integer code, String message) {
        return new CustomPageResponse<>(code, message);
    }
    
    public static <T> CustomPageResponse<T> error(ErrorCode errorCode) {
        if (errorCode == null) {
            errorCode = ErrorCode.UNKNOWN;
        }
        return new CustomPageResponse<>(errorCode.getCode(), errorCode.getMessage());
    }
    
    public boolean getLast() {
        return this.page >= this.totalPages;
    }
    
    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
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
        return "CustomPageResponse [page=" + page + ", size=" + size + ", totalElements=" + totalElements
                + ", totalPages=" + totalPages + ", data=" + data + "]";
    }
}
