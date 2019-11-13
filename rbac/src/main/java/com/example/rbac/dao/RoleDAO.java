package com.example.rbac.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbac.pojo.Role;

public interface RoleDAO extends JpaRepository<Role, Integer> {
    
    Collection<Role> findByIdIn(Collection<Integer> ids);

}
