package com.example.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.dao.RoleDAO;
import com.example.dao.UserDAO;
import com.example.pojo.Role;
import com.example.pojo.User;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserDAO userDAO;
    
    @Autowired
    RoleDAO roleDao;
    
    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User findOne(Integer id) {
        return userDAO.findOne(id);
    }

    @Override
    public UserDetails getUserDetail(User user) {
        if (user == null || user.getRoles() == null) {
            return null;
        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (Role role : roleDao.findByUsers_Id(user.getId())) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
    
}
