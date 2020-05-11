package com.ygsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ygsm.common.PageObject;
import com.ygsm.dao.PostDAO;
import com.ygsm.model.dto.CategroyPostDTO;
import com.ygsm.model.dto.PostDTO;
import com.ygsm.model.form.PostAddForm;
import com.ygsm.model.form.PostQueryForm;
import com.ygsm.model.form.PostUpdateForm;
import com.ygsm.model.pojo.Category;
import com.ygsm.model.pojo.Post;
import com.ygsm.service.CategoryService;
import com.ygsm.service.PostService;
import com.ygsm.util.BeanUtil;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;
    
    @Autowired
    private CategoryService categoryService;
    
    @Override
    public CategroyPostDTO findCategoryPostList(Integer categoryId, Integer postLimit, boolean findChildren) {

        Category category = categoryService.findOne(categoryId);
        if (category == null) {
            return null;
        }
        List<Post> postList;
        if (findChildren) {
            List<Category> categoryist = categoryService.findChildrenList(categoryId);
            List<Integer> idList = new ArrayList<>();
            idList.add(categoryId);
            categoryist.forEach(childCategory -> idList.add(childCategory.getId()));
            PageHelper.startPage(new PageObject(1, postLimit));
            postList = postDAO.findByCategoryIdIn(idList);
        } else {
            PageHelper.startPage(new PageObject(1, postLimit));
            postList = postDAO.findByCategoryId(categoryId);
        }
        CategroyPostDTO categroyPostDTO = new CategroyPostDTO();
        BeanUtil.copyProperties(category, categroyPostDTO);
        categroyPostDTO.setPostList(postList);
        return categroyPostDTO;
    }

    @Override
    public Post findOne(Long id) {
        return postDAO.findById(id);
    }

    @Override
    public PageInfo<Post> findCategoryPostPage(Integer categoryId, IPage pageable) {
        PageHelper.startPage(pageable);
        return new PageInfo<>(postDAO.findByCategoryId(categoryId));
    }

    @Override
    public PostDTO findDetail(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PageInfo<PostDTO> query(PostQueryForm object, IPage pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(PostAddForm object) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(PostUpdateForm object) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

}
