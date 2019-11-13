package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.pojo.DemoData;

public interface DemoDataDAO extends JpaRepository<DemoData, Integer> {
    
    @Transactional
    void deleteById(Integer id);
//    @Query("select d from DemoData d where d.id = 1")
    @Transactional
    @Modifying
    @Query("update DemoData d set d.score = 11 where d.id = 1")
    int test();

}
