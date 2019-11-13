package com.example.utils;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private Integer code = 0;
    
    private Integer status = 200;

    private String message = "";

    private T data = null;
    
    private Integer count = null;
}
