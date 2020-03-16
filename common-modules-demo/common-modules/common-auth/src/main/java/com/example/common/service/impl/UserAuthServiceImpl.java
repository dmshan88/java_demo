package com.example.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.common.service.UserAuthService;

@Component
@ConditionalOnMissingBean(value = UserAuthService.class, ignored = UserAuthServiceImpl.class)
public class UserAuthServiceImpl implements UserAuthService {
    
    private static final String AUTH_PROPERTY_USERNAME = "app.auth.username";
    private static final String AUTH_PROPERTY_PASSWORD = "app.auth.password";
    
    private final Environment environment;

    public UserAuthServiceImpl(Environment environment) {
        this.environment = environment;
        
    }
    @Override
    public boolean existByUsername(String username) {
        return this.getDefaultUsername().equals(username);
    }

    @Override
    public List<String> getPermissionListByUsername(String username) {
        List<String> permissionList = new ArrayList<>();
        if (existByUsername(username)) {
            permissionList.add("user");
        }
        return permissionList;
    }
    
    @Override
    public boolean authUserAndPassword(String username, String password) {
        return getDefaultUsername().equals(username) && getDefaultPassword().equals(password);
    }
    
    /**默认用户名*/
    private String getDefaultUsername() {
        return this.environment.getProperty(AUTH_PROPERTY_USERNAME, "user");
    }
    /**默认密码*/
    private String getDefaultPassword() {
        return this.environment.getProperty(AUTH_PROPERTY_PASSWORD, "12345678");
    }


}
