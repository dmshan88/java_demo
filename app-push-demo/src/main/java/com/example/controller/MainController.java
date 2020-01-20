package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.AppPushUtil;
import com.example.service.AppPushUtil.Message;
import com.example.service.impl.JPushUtilImpl;

@RestController
public class MainController {
    
    @Value("${app.jpush.key}")
    private String appKey;
    
    @Value("${app.jpush.secret}")
    private String masterSecret;

    @GetMapping(path = "/test")
    void test(String message) {
        if (message == null || message.isEmpty()) {
            message = "default alert";
        }

        AppPushUtil push = new JPushUtilImpl(appKey, masterSecret);
        Message pubshMessage = new Message();
        pubshMessage.setContent(message);
        pubshMessage.setTitle("hahah");
        push.pushAliasOne("ssadfsce", pubshMessage);
    }
    
    @GetMapping(path = "/test1")
    void test1(String alsis) {
        if (alsis == null || alsis.isEmpty()) {
            alsis = "ssadfsce";
        }
        AppPushUtil push = new JPushUtilImpl(appKey, masterSecret);
        push.setAlias("13065ffa4e66a6568f8", "ssadfsce");
    }
    

}
