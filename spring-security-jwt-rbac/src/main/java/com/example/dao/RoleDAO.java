package com.example.dao;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pojo.Role;

public interface RoleDAO extends JpaRepository<Role, Integer> {
    
    Set<Role> findByIdIn(Collection<Integer> ids);
    
    Set<Role> findByUsers_Id(Integer userId);

}
