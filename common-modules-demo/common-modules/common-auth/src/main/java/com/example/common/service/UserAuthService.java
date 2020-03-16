package com.example.common.service;

import java.util.List;

public interface UserAuthService {
    
    /**用户名存在*/
    boolean existByUsername(String username);
    
    /**根据用户名查找权限*/
    List<String> getPermissionListByUsername(String username);
    
    /**验证用户名,密码*/
    boolean authUserAndPassword(String username, String password);

}
