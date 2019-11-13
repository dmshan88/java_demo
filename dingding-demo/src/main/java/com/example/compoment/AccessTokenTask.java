package com.example.compoment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.BaseTask;
import com.example.common.CustomException;
import com.example.dingtalk.FetchAccessTokenService;

@Component
public class AccessTokenTask extends BaseTask {

    @Autowired
    private FetchAccessTokenService fetchAccessTokenService;
    
    @Override
    public void work() throws CustomException {
        fetchAccessTokenService.fetchAccessToken();
    }

}
