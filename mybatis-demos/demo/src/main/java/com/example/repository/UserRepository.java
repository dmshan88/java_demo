package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.entity.User;

@Mapper
public interface UserRepository {
    
    User findById(Integer id);
    List<User> findAll();
    int insert(User object);
    int update(User object);
    int delete(Integer id);

}
