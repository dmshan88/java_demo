package com.example.controller;

import java.util.Collection;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.PermissionDAO;
import com.example.pojo.Permission;
import com.example.pojo.Role;
import com.example.pojo.User;
import com.example.pojo.form.Param;
import com.example.pojo.form.UserCreateForm;
import com.example.service.AuthService;
import com.example.service.UserService;
import com.example.utils.TreeBuilder;

@RestController
public class TestController {
    
    private User user;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PermissionDAO permissionDAO;
    
    @Autowired
    private AuthService authService;
    
    @ModelAttribute
    void common() {
        System.out.print("common:");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        if (user != null) {
            this.user = user;
        }
    }
    
    @Secured({"ROLE_PERMISSION_REVIEW"})
    @GetMapping(path = "/review")
    String review(String token) {
        return "review";
    }
    
    @GetMapping(path = "/expection")
    String expection(String token) throws Exception {
        throw new Exception("aaa");
    }
    
    @GetMapping(path = "/test")
    Collection<Permission> test(String token) {
        return new TreeBuilder<Permission, Integer>(permissionDAO.findAll()).buildTree();
    }
    
    @PostMapping(path = "/test")
    String testPost(String token, Param param) {
        System.out.println(token);
        System.out.println(param.getA());
        System.out.println(param.getB());
        
        return "test post";
    }
    
    @PostMapping(path = "/createUser") 
    void createuser(UserCreateForm userForm, String token){
        if (userService.createUser(userForm) != null) {
            System.out.println("save ok");
        }
    }
    
    @PostMapping(path = "/changePassword") 
    void changePassword(String oldPwd, String newPwd, String token){
        if (authService.verifyPassword(user.getUsername(), oldPwd)) {
            if(userService.updatePassowrd(user.getId(), newPwd)) {
                System.out.println("change password ok");
            }
        }
    }
    
    @PostMapping(path = "/resetPassword") 
    void resetPassword(Integer userId, String token){
        if(userService.updatePassowrd(userId, null)) {
            System.out.println("reset password ok");
        }
    }
}
