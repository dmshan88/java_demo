package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pojo.Blog;

public interface BlogDAO extends JpaRepository<Blog, Long> {
    
    Blog findByUser_Id(Integer id);
    
    List<Blog> findByUser_Name(String name);
}
