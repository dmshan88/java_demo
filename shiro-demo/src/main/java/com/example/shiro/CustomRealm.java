package com.example.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.compoment.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println((String)principals.getPrimaryPrincipal());
        
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        info.addStringPermission("user");
        return info;
    }

    
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(new AllowAllCredentialsMatcher());
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
      JwtAuthToken usertoken = (JwtAuthToken) token;
      try {
          String username = jwtUtil.parseJwt(usertoken.getToken());
          if (!username.equals("aa")) {
              throw new UnknownAccountException();
          }
          return new SimpleAuthenticationInfo(username, "", getName());
      } catch (ExpiredJwtException e) {
          throw new ExpiredCredentialsException();
      }  catch (JwtException e) {
          throw new CredentialsException();
      } catch (Exception e) {
          e.printStackTrace();
      }
      throw new AuthenticationException();
    }
    
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtAuthToken;
    }


}
