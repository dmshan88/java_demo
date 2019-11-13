package com.example.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.PermissionDAO;
import com.example.dao.RoleDAO;
import com.example.dao.UserDAO;
import com.example.pojo.Permission;
import com.example.pojo.Role;
import com.example.pojo.User;
import com.example.pojo.form.UserCreateForm;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    static final String DEFAULT_PASSWORD = "111111";
    
    @Autowired
    UserDAO userDAO;
    
    @Autowired
    RoleDAO roleDao;
    
    @Autowired
    PermissionDAO permissionDAO;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User findOne(Integer id) {
        return userDAO.findOne(id);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null || user.getRoles() == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in db");
        }
        Collection<GrantedAuthority> grantedAuthorities = convertAuthority(findPermissionsByUserId(user.getId()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
    

//    private Set<Role> findRolesByUserId(Integer userId) {
//      return roleDao.findByUsers_Id(userId);
//    }
    private Set<Permission> findPermissionsByUserId(Integer userId) {
        return permissionDAO.findByRoles_Users_Id(userId);
      }

//    private Collection<GrantedAuthority> convertAuthority(Collection<Role> roles) {
//        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//        for (Role role : roles) {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
//            grantedAuthorities.add(grantedAuthority);
//        }
//        return grantedAuthorities;
//    }
    
    private Collection<GrantedAuthority> convertAuthority(Collection<Permission> permissions) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (Permission permission : permissions) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
            grantedAuthorities.add(grantedAuthority);
        }
        return grantedAuthorities;
    }

    @Override
    public User createUser(UserCreateForm userform) {
        User user = new User();
        user.setUsername(userform.getUsername());
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        return userDAO.save(user);
    }

    @Override
    public boolean updatePassowrd(Integer userId, String password) {
        User user = userDAO.findOne(userId);
        if (user != null) {
            if (password == null) {
                password = DEFAULT_PASSWORD;
            }
            user.setPassword(passwordEncoder.encode(password));
            if (userDAO.save(user) != null) {
                return true;
            }
        }
        return false;
    }
    

}
