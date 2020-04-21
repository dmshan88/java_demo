package com.ygsm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ygsm.model.pojo.Category;

@Mapper
public interface CategoryDAO {
    
    Category findById(Integer id);
    
    List<Category> findAll();
    
    List<Category> findByParentId(Integer parentId);
    
    int insert(Category entity);
    
    int batchInsert(List<Category> entities);
    
    int update(Category entity);

}
