package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.common.service.UserAuthService;

//@Service()
public class UserServiceImpl implements UserAuthService {


    
    @Override
    public boolean existByUsername(String username) {
    
        return false;
    }

    @Override
    public List<String> getPermissionListByUsername(String username) {
        List<String> permissionList = new ArrayList<>();
        permissionList.add("user");
        return permissionList;
    }

    @Override
    public boolean authUserAndPassword(String username, String password) {
        return "aa".equals(username) && password.equals("bb");
    }

}
