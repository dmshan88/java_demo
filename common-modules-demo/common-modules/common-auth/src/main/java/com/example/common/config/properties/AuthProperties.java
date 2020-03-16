package com.example.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.auth.properties")
public class AuthProperties {
    
    private String[] permitUrls = {};

    public String[] getPermitUrls() {
        return permitUrls;
    }

    public void setPermitUrls(String[] permitUrls) {
        this.permitUrls = permitUrls;
    }

    @Override
    public String toString() {
        return "AuthProperties [permitUrls=" + permitUrls + "]";
    }


}
