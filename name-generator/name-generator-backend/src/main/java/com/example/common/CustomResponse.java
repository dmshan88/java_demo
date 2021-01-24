package com.example.common;

import java.util.Collection;

public class CustomResponse<T> extends BaseResponse {

    private T data; // 数据

    protected CustomResponse(T t, Integer code, String message) {
        super(code, message);
        this.data = t;
    }

    protected CustomResponse(Integer code, String message) {
        this(null, code, message);
    }

    public static <T> CustomResponse<T> ok(T t, String message) {
        return new CustomResponse<>(t, ErrorCode.OK.getCode(), message);
    }

    public static <T> CustomResponse<T> ok(T t) {
        return CustomResponse.ok(t, ErrorCode.OK.getMessage());
    }

    public static <T> CustomResponse<T> ok(String message) {
        return CustomResponse.ok(null, message);
    }

    public static <T> CustomResponse<T> error(Integer code, String message) {
        return new CustomResponse<>(code, message);
    }

    public static <T> CustomResponse<T> error(ErrorCode errorCode) {
        if (errorCode == null) {
            errorCode = ErrorCode.UNKNOWN;
        }
        return CustomResponse.error(errorCode.getCode(), errorCode.getMessage());
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return ErrorCode.OK.getCode().equals(this.getCode());
    }

    public Object getRows() {
        if (data instanceof Collection) {
            return data;
        }
        return null;
    }

    public Integer getTotal() {
        if (data instanceof Collection) {
            return ((Collection) data).size();
        }
        return null;
    }
}
