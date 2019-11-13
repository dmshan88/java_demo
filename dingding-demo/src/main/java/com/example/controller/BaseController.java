package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.common.Constant;
import com.example.common.CustomException;
import com.example.common.ErrorCode;

public class BaseController {
    
    @Value("${app.accesstoken}")
    protected String accessToken;

    @ModelAttribute
    protected void common(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        if (!accessToken.equals(request.getHeader(Constant.REQUEST_PARAM_TOKEN))) {
            throw new CustomException(ErrorCode.PARAM_ERROR, "no auth");
        }
    }
}
