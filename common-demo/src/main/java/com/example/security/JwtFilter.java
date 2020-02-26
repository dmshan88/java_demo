package com.example.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.common.ErrorCode;
import com.example.compoment.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private Integer status = HttpServletResponse.SC_UNAUTHORIZED;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("jwtfilter");
        boolean ok = false;
        String token = request.getParameter("token");;
        if (token == null || token.isEmpty()) {
            ok = true;
        } else {
            try {
                String username = jwtUtil.parseJwt(token);
                if (username.equals("user1")) {
                    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_user");
                    grantedAuthorities.add(grantedAuthority);
                    SecurityContextHolder.getContext().setAuthentication(new JwtAuthToken(username,grantedAuthorities));
                    ok = true;
                }
            } catch (ExpiredJwtException e) {
                status = ErrorCode.TOKEN_EXPIRED.getCode();
                e.printStackTrace();
            } catch (JwtException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ok) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(status);
        }
    }

}
