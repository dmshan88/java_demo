package com.example.rbac.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbac.pojo.Permission;

public interface PermissionDAO extends JpaRepository<Permission, Integer> {
    
    Collection<Permission> findDistinctByRoles_Users_Id(Integer id);

}
