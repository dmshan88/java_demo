package com.example.common.exception;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.common.core.CustomResponse;
import com.example.common.core.ErrorCode;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class AuthExceptionHandler {
    
    protected final Logger log = LoggerFactory.getLogger(AuthExceptionHandler.class);

    @ExceptionHandler(UnauthorizedException.class)
    public CustomResponse<Object> error(UnauthorizedException exception, HttpServletResponse response) {
        log.error("no permission:" + exception.getMessage());
        return CustomResponse.error(ErrorCode.NO_PERMISSION);
    }
}
