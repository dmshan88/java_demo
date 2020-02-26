package com.example.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthToken extends AbstractAuthenticationToken {
    
    private static final long serialVersionUID = 1L;

    //    private String credentials;
    
    private String principal;

    public JwtAuthToken(String principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
    }
    
    public JwtAuthToken(String principal) {
        this(principal, null);
    }
    
    @Override
    public boolean isAuthenticated() {
        return true;
    }
    
    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}
