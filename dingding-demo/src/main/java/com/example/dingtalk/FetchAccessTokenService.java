package com.example.dingtalk;

import com.example.common.CustomException;

public interface FetchAccessTokenService {
    
    /**获取access_token*/
    void fetchAccessToken() throws CustomException;
    
    /**返回accesstoken*/
    String getAccessToken();
    
}
