package com.example.common.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.common.auth.security.JwtFilter;
import com.example.common.config.properties.AuthProperties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private AuthProperties authProperties;
    
    private JwtFilter jwtFilter;
    
    WebSecurityConfig(JwtFilter jwtFilter, AuthProperties authProperties) {
        this.jwtFilter = jwtFilter;
        this.authProperties = authProperties;
    }
    
    @SuppressWarnings("deprecation")
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] swaggerUrlArray = {"/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagge‌​r-ui.html"};
        String[] appUrlArray = {"/test/**","/error", "/login"};
        List<String> urlList = new ArrayList<String>();
        urlList.addAll(Arrays.asList(swaggerUrlArray));
        urlList.addAll(Arrays.asList(appUrlArray));
        urlList.addAll(Arrays.asList(authProperties.getPermitUrls()));

        http.csrf().disable().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                    AuthenticationException authException) throws IOException, ServletException {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                
            }
        }).accessDeniedHandler(new AccessDeniedHandler() {
            
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response,
                    AccessDeniedException accessDeniedException) throws IOException, ServletException {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                
            }
        })
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().cors()
        
        .and().authorizeRequests()
            .antMatchers(urlList.toArray(new String[0])).permitAll()
             .anyRequest().authenticated()
        .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }
    
}
