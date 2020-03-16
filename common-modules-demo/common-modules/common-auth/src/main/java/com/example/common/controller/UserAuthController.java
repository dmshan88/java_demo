package com.example.common.controller;

import com.example.common.core.CustomResponse;

public interface UserAuthController {

    /**登录*/
    public CustomResponse<String> login(String username, String password);
}
