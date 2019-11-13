package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Profile("aaa")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) //方法级别权限控制
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/test","/upload/**").permitAll() // /test路径允许所有请求
            .antMatchers("/user/**").hasRole("USER") // /user路径需要用户权限
            .antMatchers("/admin/**").hasRole("ADMIN") // /admin路径需要管理员权限
            .anyRequest().authenticated() //请求需要认证信息
            .and().formLogin()//表单登录
            ;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() //认证信息保存在内存
          // 管理员，同时具有 ADMIN,USER权限，可以访问所有资源
            .withUser("admin1").password("1").roles("ADMIN", "USER") 
          // 普通用户， 仅具有USER权限
            .and().withUser("user1").password("1").roles("USER"); 
    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/static/");
//    }
}
