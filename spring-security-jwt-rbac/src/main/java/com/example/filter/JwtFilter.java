package com.example.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.utils.CustomToken;
import com.example.utils.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final static String TOKEN_PARAM = "token";

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenStr = request.getParameter(TOKEN_PARAM);
        if (tokenStr != null && !tokenStr.isEmpty()) {
            Map<String, Object> tokenMap = JwtHelper.verifyJwt(tokenStr);
            if (tokenMap != null) {
                CustomToken customToken = new ObjectMapper().convertValue(tokenMap, CustomToken.class);
                if (customToken != null) {
                    UsernamePasswordAuthenticationToken token =  new UsernamePasswordAuthenticationToken(
                        customToken.getUsername(), null, userDetailsService.loadUserByUsername(customToken.getUsername()).getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
