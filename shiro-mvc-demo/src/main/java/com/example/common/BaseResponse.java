package com.example.common;

public abstract class BaseResponse {
    
    private final Integer code; //错误码

    private final String message; //错误信息
    
    protected BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}