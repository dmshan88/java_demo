package com.example.common.auth.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

import com.example.common.core.ErrorCode;
import com.example.common.jwt.JwtUtil;
import com.example.common.service.UserAuthService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private Integer status = HttpServletResponse.SC_UNAUTHORIZED;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("jwtfilter" + request.getRequestURI());
        boolean ok = false;
        String token = request.getParameter("token");;
        if (token == null || token.isEmpty()) {
            ok = true;
        } else {
            try {
                String username = jwtUtil.parseJwt(token);
                if (userAuthService.existByUsername(username)) {
                    List<String> permissionList = userAuthService.getPermissionListByUsername(username);
                    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
                    for (String permission : permissionList) {
                        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + permission);
                        grantedAuthorities.add(grantedAuthority);
                    }
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
