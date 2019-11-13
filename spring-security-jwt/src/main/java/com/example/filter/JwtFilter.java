package com.example.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.utils.CustomToken;
import com.example.utils.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("JwtFilter");
        String tokenStr = request.getParameter("token");
        if (tokenStr != null && !tokenStr.isEmpty()) {
            Map<String, Object> tokenMap = JwtHelper.verifyJwt(tokenStr);
            if (tokenMap != null) {
                CustomToken customToken = new ObjectMapper().convertValue(tokenMap, CustomToken.class);
                Collection<GrantedAuthority> authorities = new HashSet<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customToken.getUsername(), null, authorities);
                token.setDetails(customToken);
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request, response);
        
    }

}
