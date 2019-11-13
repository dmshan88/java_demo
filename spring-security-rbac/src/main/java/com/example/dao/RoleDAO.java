package com.example.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pojo.Role;

public interface RoleDAO extends JpaRepository<Role, Integer> {
    
    Collection<Role> findByIdIn(Collection<Integer> ids);
    
    Collection<Role> findByUsers_Id(Integer userId);

}
