package com.example.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.common.CustomResponse;
import com.example.common.ErrorCode;


@ConditionalOnMissingBean(CustomErrorController.class)
@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = PATH)
    public CustomResponse<Object> error(HttpServletRequest request, HttpServletResponse response) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> errorMap = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        Integer status = (Integer) errorMap.get("status");
        ErrorCode errorcode = ErrorCode.UNKNOWN;
        if (status == HttpServletResponse.SC_NOT_FOUND) {
            errorcode = ErrorCode.NOT_FOUND;
        } else if (status == HttpServletResponse.SC_FORBIDDEN) {
            errorcode = ErrorCode.NO_PERMISSION;
        } else if (status == HttpServletResponse.SC_UNAUTHORIZED) {
            errorcode = ErrorCode.AUTH_ERROR;
        } else if (status == ErrorCode.TOKEN_EXPIRED.getCode()) {
            errorcode = ErrorCode.TOKEN_EXPIRED;
        }
        System.out.println(status);
        System.out.println(errorcode);
        response.setStatus(HttpServletResponse.SC_OK);
        return CustomResponse.error(errorcode);
    }

}
