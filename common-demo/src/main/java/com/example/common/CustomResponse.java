package com.example.common;

public class CustomResponse<T> extends BaseResponse {
    
    private T data; //数据
    
    protected CustomResponse(T t, Integer code, String message) {
        super(code, message);
        this.data = t;
    }
    
    protected CustomResponse(Integer code, String message) {
        this(null, code, message);
    }
    
    public static <T> CustomResponse<T> ok(T t) {
        ErrorCode errorCode = ErrorCode.OK;
        return new CustomResponse<>(t, errorCode.getCode(), errorCode.getMessage());
    }
    
    public static CustomResponse<Object> error(Integer code, String message) {
        return new CustomResponse<>(code, message);
    }
    
    public static CustomResponse<Object> error(ErrorCode errorCode) {
        if (errorCode == null) {
            errorCode = ErrorCode.UNKNOWN;
        }
        return new CustomResponse<>(errorCode.getCode(), errorCode.getMessage());
    }

    public T getData() {
        return data;
    }

}
