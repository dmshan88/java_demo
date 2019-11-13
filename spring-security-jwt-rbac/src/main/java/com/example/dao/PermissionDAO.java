package com.example.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pojo.Permission;

public interface PermissionDAO extends JpaRepository<Permission, Integer> {
    
    Set<Permission> findByRoles_Users_Id(Integer id);

}
