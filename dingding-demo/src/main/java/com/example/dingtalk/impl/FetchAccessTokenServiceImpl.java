package com.example.dingtalk.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.example.common.CustomException;
import com.example.common.ErrorCode;
import com.example.dingtalk.FetchAccessTokenService;

import com.taobao.api.ApiException;

@Service
public class FetchAccessTokenServiceImpl implements FetchAccessTokenService {

    @Value("${app.dingding.key}")
    private String appKey;
    
    @Value("${app.dingding.secret}")
    private String appSecret;
    
//    @Getter
    private String accessToken = "1bf42de4cd773c83b1c25ab0aa771220";
    
    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void fetchAccessToken() throws CustomException {
        accessToken = null;
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appKey);
        request.setAppsecret(appSecret);
        request.setHttpMethod("GET");
        OapiGettokenResponse response;
        try {
            response = client.execute(request);
        } catch (ApiException e) {
            throw new CustomException(ErrorCode.REQUEST_ERROR, e.getErrMsg());
        }
        if (!response.isSuccess()) {
            throw new CustomException(ErrorCode.REQUEST_ERROR, response.getErrmsg());
        }
        accessToken = response.getAccessToken();
        System.out.println(accessToken);
    }
    
}
