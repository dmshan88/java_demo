package com.example.common;

import java.util.List;

import org.springframework.data.domain.Page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomPageResponse<T> extends BaseResponse {

    private final Integer page;
    
    private final Integer size;
    
    @JsonProperty(value = "count")
    private final Long totalElements;
    
    private final Integer totalPages;

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
    
    public static <T> CustomPageResponse<T> ok(List<T> data, Integer page, Integer size, 
            Long totalElements, Integer totalPages) {
        ErrorCode errorCode = ErrorCode.OK;
        return new CustomPageResponse<>(data, errorCode.getCode(), errorCode.getMessage(),
                page, size, totalElements, totalPages);
    }
    
    public static <T> CustomPageResponse<T> ok(Page<T> data) {
        return CustomPageResponse.ok(data.getContent(), data.getNumber() + 1, data.getSize(),
                data.getTotalElements(), data.getTotalPages());
    }
    
    public static <T> CustomPageResponse<T> ok(IPage<T> data) {
        return CustomPageResponse.ok(data.getRecords(), (int)data.getCurrent(), (int)data.getSize(),
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
