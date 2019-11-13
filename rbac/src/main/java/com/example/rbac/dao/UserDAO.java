package com.example.rbac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbac.pojo.User;

public interface UserDAO extends JpaRepository<User, Integer> {

}
