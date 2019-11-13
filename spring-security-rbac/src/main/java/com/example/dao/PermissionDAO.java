package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pojo.Permission;

public interface PermissionDAO extends JpaRepository<Permission, Integer> {
    

}
