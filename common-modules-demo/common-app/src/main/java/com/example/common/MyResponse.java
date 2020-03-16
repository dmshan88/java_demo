package com.example.common;

import com.example.common.core.CustomResponse;
import com.example.common.core.ErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyResponse<T> extends CustomResponse<T>{

    protected MyResponse(T t, Integer code, String message) {
        super(t, code, message);

    }
    
    protected MyResponse(Integer code, String message) {
        super(null, code, message);

    }
    
    @JsonProperty("content")
    public T getData() {
        return super.getData();
    }
    
    public static <T> MyResponse<T> ok(T t) {
        ErrorCode errorCode = ErrorCode.OK;
        return new MyResponse<>(t, errorCode.getCode(), errorCode.getMessage());
    }


}
