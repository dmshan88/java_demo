package com.example.rbac.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rbac.dao.PermissionDAO;
import com.example.rbac.dao.RoleDAO;
import com.example.rbac.dao.UserDAO;
import com.example.rbac.pojo.Permission;
import com.example.rbac.pojo.Role;
import com.example.rbac.pojo.User;

@RestController
public class TestController {
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private RoleDAO roleDAO;
    
    @Autowired
    private PermissionDAO permissionDAO;
    
    
    @GetMapping(path = "/test/{id}")
    void test(@PathVariable Integer id) {
        for (Permission permission : permissionDAO.findDistinctByRoles_Users_Id(id)) {
          System.out.println(permission.getUrl());
      }
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
