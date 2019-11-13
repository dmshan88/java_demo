package com.example.service;

//import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.pojo.User;
import com.example.pojo.form.UserCreateForm;

public interface UserService extends UserDetailsService {
    
    User findByUsername(String username);
    
    User findOne(Integer id);
    
    User createUser(UserCreateForm userform);
    
    boolean updatePassowrd(Integer userId, String password);
    
//    Collection<GrantedAuthority> convertAuthority(Collection<Role> roles);
    
//    Collection<Role> findRolesByUserId(Integer userId);
    
    

}
