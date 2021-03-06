package com.example.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/** 自定义分页响应 */
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

    protected CustomPageResponse(List<T> data, Integer code, String message, Integer page, Integer size,
            Long totalElements, Integer totalPages) {
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

    public static <T> CustomPageResponse<T> ok(PageResultObject<T> data) {
        ErrorCode errorCode = ErrorCode.OK;
        return new CustomPageResponse<>(data.getData(), errorCode.getCode(), errorCode.getMessage(),
                data.getPageNumber(), data.getPageSize(), data.getTotalElements(), data.getTotalPages());
    }

    public static <T> CustomPageResponse<T> error(Integer code, String message) {
        return new CustomPageResponse<>(code, message);
    }

    public static <T> CustomPageResponse<T> error(ErrorCode errorCode) {
        if (errorCode == null) {
            errorCode = ErrorCode.UNKNOWN;
        }
        return error(errorCode.getCode(), errorCode.getMessage());
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

    public boolean isSuccess() {
        return ErrorCode.OK.getCode().equals(this.getCode());
    }

    @Override
    public String toString() {
        return "CustomPageResponse [page=" + page + ", size=" + size + ", totalElements=" + totalElements
                + ", totalPages=" + totalPages + ", data=" + data + "]";
    }
}