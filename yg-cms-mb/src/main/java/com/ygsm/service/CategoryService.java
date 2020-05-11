package com.ygsm.service;

import java.util.List;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageInfo;
import com.ygsm.model.dto.CategoryDTO;
import com.ygsm.model.dto.IdNameDTO;
import com.ygsm.model.form.CategoryAddForm;
import com.ygsm.model.form.CategoryQueryForm;
import com.ygsm.model.form.CategoryUpdateForm;
import com.ygsm.model.pojo.Category;

public interface CategoryService {
    
    /**根据ID查找*/
    Category findOne(Integer Id);
    
    /**获取所有分类列表*/ 
    List<Category> findAll();
    
    /**获取顶级分类列表*/ 
    List<Category> findTopList();
    
    /**获取各上级分类列表*/ 
    List<Category> findParentList(Integer parentId);
    
    /**获取子分类列表*/ 
    List<Category> findChildrenList(Integer categoryId);

    CategoryDTO findDetail(Integer id);

    List<IdNameDTO> findNameList();

    PageInfo<CategoryDTO> query(CategoryQueryForm object, IPage pageable);

    void add(CategoryAddForm object);

    void update(CategoryUpdateForm object);

    void delete(Integer id);

}
