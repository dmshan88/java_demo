package com.ygsm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ygsm.model.pojo.Menu;

@Mapper
public interface MenuDAO {
    
    Menu findById(Integer id);
    
    List<Menu> findAll();
    
    int insert(Menu entity);
    
    int batchInsert(List<Menu> entities);
    
    int update(Menu entity);

}
