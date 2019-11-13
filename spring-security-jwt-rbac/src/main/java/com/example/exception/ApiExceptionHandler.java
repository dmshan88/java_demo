package com.example.exception;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.utils.BaseResponse;

/**统一异常处理*/
@RestControllerAdvice(basePackages = "com.example.api")
public class ApiExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler
    public BaseResponse<Object> error(Exception exception, HttpServletResponse response) {
        logger.error("got error: " + exception.getMessage());
        BaseResponse<Object> responseObject = new BaseResponse<Object>();
        responseObject.setMessage(exception.getMessage());
        responseObject.setCode(-1);
        return responseObject;
    }
}
