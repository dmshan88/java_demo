package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pojo.DemoData;

public interface DemoDataDAO extends JpaRepository<DemoData, Integer>, DemoDataDAOCustom {
    

}
