package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.RoleDAO;
import com.example.dao.UserDAO;
import com.example.pojo.Role;
import com.example.pojo.User;

@RestController
public class TestController {
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private RoleDAO roleDAO;
    
    @ModelAttribute
    void common() {
        String currentUser = "";
        Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principl instanceof UserDetails) {
            currentUser = ((UserDetails)principl).getUsername();
//            System.out.println( .size());
            
            for (GrantedAuthority grantedAuthority :((UserDetails)principl).getAuthorities()) {
                System.out.println(grantedAuthority);
                
            }
        } else {
            currentUser = principl.toString();
        }
        System.out.println(currentUser);
    }
    
    @GetMapping(path = "/user")
    String user() {
        System.out.println("user:");
        
        return "res";
    }
    @GetMapping(path = "/admin")
    String admin() {
        System.out.println("admin:");
        return "admin";
    }
    
    @PostMapping(path = "/add")
    void add(@RequestParam Integer id, @RequestParam Collection<Integer> roleIds) {
        for (Integer item : roleIds) {
            System.out.println("role" + item);
        }
        User user = userDAO.findOne(id);
        if (user != null) {
            if (user != null) {
                for (Role role : user.getRoles()) {
                    System.out.println("old role name: " + role.getName());
                }
            }
            
            Collection<Role> roles = roleDAO.findByIdIn(roleIds);
            for (Role role : roles) {
                System.out.println("role name: " + role.getName());
            }
            user.setRoles(roles);
            user = userDAO.save(user);
            
            if (user != null) {
                for (Role role : user.getRoles()) {
                    System.out.println("new role name: " + role.getName());
                }
            }
           

        }
    }
}
