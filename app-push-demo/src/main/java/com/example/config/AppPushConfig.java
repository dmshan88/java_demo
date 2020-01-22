package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.util.AppPushUtil;
import com.example.util.impl.JPushUtilImpl;

/**app推送设置*/
@Configuration
public class AppPushConfig {

    @Value("${app.jpush.key}")
    private String appKey;
    
    @Value("${app.jpush.secret}")
    private String masterSecret;
    
    @Bean
    public AppPushUtil appPushUtil() {
        return new JPushUtilImpl(appKey, masterSecret);	
    }

}
