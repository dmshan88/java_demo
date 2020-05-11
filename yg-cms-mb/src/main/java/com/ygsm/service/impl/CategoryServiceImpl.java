package com.ygsm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageInfo;
import com.ygsm.dao.CategoryDAO;
import com.ygsm.model.dto.CategoryDTO;
import com.ygsm.model.dto.IdNameDTO;
import com.ygsm.model.form.CategoryAddForm;
import com.ygsm.model.form.CategoryQueryForm;
import com.ygsm.model.form.CategoryUpdateForm;
import com.ygsm.model.pojo.Category;
import com.ygsm.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;
    
    @Override
    public Category findOne(Integer categoryId) {
        return categoryDAO.findById(categoryId);
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }
    
    @Override
    public List<Category> findTopList() {
        return categoryDAO.findByParentId(0);
    }
    
    @Override
    public List<Category> findChildrenList(Integer categoryId) {
        return categoryDAO.findByParentId(categoryId);
    }

    @Override
    public List<Category> findParentList(Integer parentId) {
        List<Category> parentCategoryList = new ArrayList<>();
        if (parentId != null) {
            Integer tempId = parentId;
            while(tempId != null && tempId != 0) {
                Category parentCategory = this.findOne(tempId);
                if (parentCategory != null) {
                    parentCategoryList.add(parentCategory);
                    tempId = parentCategory.getParentId();
                } else {
                    tempId = 0;
                }
            }
            Collections.reverse(parentCategoryList);
        }
        return parentCategoryList;
    }

    @Override
    public CategoryDTO findDetail(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<IdNameDTO> findNameList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PageInfo<CategoryDTO> query(CategoryQueryForm object, IPage pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(CategoryAddForm object) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(CategoryUpdateForm object) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        
    }

}
