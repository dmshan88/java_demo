package com.example.compoment;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.common.CustomException;
import com.example.common.CustomResponse;
import com.example.common.ErrorCode;

import lombok.extern.log4j.Log4j;

@Log4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CustomResponse<Object> error(CustomException exception, HttpServletResponse response) {
        log.error("CustomException", exception);
        return CustomResponse.error(exception.getCode(), exception.getMessage());
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public CustomResponse<Object> error(NoHandlerFoundException exception, HttpServletResponse response) {
        log.error("404:" + exception.getMessage());
        return CustomResponse.error(ErrorCode.ERROR.getCode(), "404");
    }
    
    @ExceptionHandler(UnauthorizedException.class)
    public CustomResponse<Object> error(UnauthorizedException exception, HttpServletResponse response) {
        log.error("404:" + exception.getMessage());
        return CustomResponse.error(ErrorCode.NO_PERMISSION);
    }
    
    @ExceptionHandler(ServletRequestBindingException.class)
    public CustomResponse<Object> error(ServletRequestBindingException exception, HttpServletResponse response) {
        log.error("404:" + exception.getMessage());
        return CustomResponse.error(ErrorCode.ERROR.getCode(), "请求参数错误");
    }
    
//    @ExceptionHandler(AuthenticationException.class)
//    public CustomResponse<Object> error(AuthenticationException exception, HttpServletResponse response) {
//        log.error("404:" + exception.getMessage());
//        return CustomResponse.error(ErrorCode.ERROR.getCode(), "auth");
//    }
    
    @ExceptionHandler(Exception.class)
    public CustomResponse<Object> error(Exception exception, HttpServletResponse response) {
        log.error("Exception", exception);
        return CustomResponse.error(ErrorCode.EXCEPTION);
    }
}
