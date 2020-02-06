package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pojo.User;

public interface UserDAO extends JpaRepository<User, Integer> {
    
}
