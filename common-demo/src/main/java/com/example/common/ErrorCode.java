package com.example.common;

/**错误码*/
public enum ErrorCode {
    UNKNOWN(-1,"初始化错误"),
    OK(0, "成功"),
    EXCEPTION(1, "未知异常"),
    ERROR(2, "未知错误"),
    DATABASE_ERROR(4, "数据库异常");
    
    final private Integer code;
    final private String message;
    
    public Integer getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
    private ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}