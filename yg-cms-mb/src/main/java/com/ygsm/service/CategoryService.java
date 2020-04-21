package com.ygsm.service;

import java.util.List;

import com.ygsm.model.pojo.Category;

public interface CategoryService {
    
    /**根据ID查找*/
    Category findOne(Integer Id);
    
    /**获取所有分类列表*/ 
    List<Category> findAll();
    
    /**获取上级分类列表*/ 
    List<Category> findTopList();
    
    /**获取子分类列表*/ 
    List<Category> findChildrenList(Integer categoryId);

}
