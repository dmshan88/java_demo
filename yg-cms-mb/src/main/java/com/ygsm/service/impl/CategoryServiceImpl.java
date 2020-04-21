package com.ygsm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ygsm.dao.CategoryDAO;
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

}
