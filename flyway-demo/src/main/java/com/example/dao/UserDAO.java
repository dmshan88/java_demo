package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.User;

@Mapper
public interface UserDAO {
    
    List<User> findAll();
    
    int insert(User user);

}
