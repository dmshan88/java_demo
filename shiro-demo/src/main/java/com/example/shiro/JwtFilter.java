package com.example.shiro;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.common.ErrorCode;

@Scope(value = "prototype")
@Component
public class JwtFilter extends AccessControlFilter {

    private Integer status = HttpServletResponse.SC_UNAUTHORIZED;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        Subject subject = getSubject(request, response);
//        String token = request.getParameter("token");
        String token = httpServletRequest.getHeader("auth");
        System.out.println(token);
        if (httpServletRequest.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }
        if (token == null || token.isEmpty()) {
            return false;
        }
        try {
            subject.login(new JwtAuthToken(token));
            return true;
        } catch (UnknownAccountException e) {
            System.out.println("UnknownAccountException" + e.getMessage());
        } catch (ExpiredCredentialsException e) {
            status = ErrorCode.TOKEN_EXPIRED.getCode();
            System.out.println("ExpiredCredentialsException" + e.getMessage());
        } catch (CredentialsException e) {
            System.out.println("CredentialsException" + e.getMessage());
        } catch (AuthenticationException e) {
            System.out.println("AuthenticationException" + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendError(status);
        return false;
    }

}
