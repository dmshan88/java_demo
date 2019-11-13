package com.example.common;

import lombok.Getter;

/**错误码*/
@Getter 
public enum ErrorCode {
    UNKNOWN(-1,"初始化错误"),
    OK(0, "成功"),
    EXCEPTION(1, "未知异常"),
    ERROR(2, "未知错误"),
    EMPTY_ERROR(3, "空错误"),
    REQUEST_ERROR(4, "请求失败"),
    PARAM_ERROR(5, "参数错误");
    
    private Integer code;

    private String message;
    
    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    /**根据code返回ErrorCode*/
    static public ErrorCode getByCode(Integer code) {
        ErrorCode[] array = ErrorCode.values();
        for (int i = 0; i < array.length; i++) {
            if (array[i].getCode().equals(code)) {
                return array[i];
            }
        }
        return ERROR;
    }
}