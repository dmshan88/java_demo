package com.example.common.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.common.auth.shiro.CustomRealm;
import com.example.common.auth.shiro.JwtFilter;
import com.example.common.config.properties.AuthProperties;


@Configuration
public class ShiroConfig {
    
    @Bean
    SecurityManager securityManager(CustomRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        realm.setCredentialsMatcher(new AllowAllCredentialsMatcher());
        manager.setRealm(realm);
        return manager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, JwtFilter filter, AuthProperties authProperties) {
        String[] swaggerUrlArray = {"/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagge‌​r-ui.html"};
        String[] appUrlArray = {"/test/**","/error", "/login"};
        List<String> urlList = new ArrayList<String>();
        urlList.addAll(Arrays.asList(swaggerUrlArray));
        urlList.addAll(Arrays.asList(appUrlArray));
        urlList.addAll(Arrays.asList(authProperties.getPermitUrls()));
        
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", filter);
        factoryBean.setFilters(filterMap);
//        factoryBean.setUnauthorizedUrl("/unauthorized/noauth");
        Map<String, String> filterRuleMap = new HashMap<>();
        for (String url : urlList) {
            filterRuleMap.put(url, "anon");
        }
        filterRuleMap.put("/**", "authc");
        filterRuleMap.put("/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
//
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
}
