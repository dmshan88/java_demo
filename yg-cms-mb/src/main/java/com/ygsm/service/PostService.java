package com.ygsm.service;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageInfo;
import com.ygsm.model.dto.CategroyPostDTO;
import com.ygsm.model.dto.PostDTO;
import com.ygsm.model.form.PostAddForm;
import com.ygsm.model.form.PostQueryForm;
import com.ygsm.model.form.PostUpdateForm;
import com.ygsm.model.pojo.Post;

public interface PostService {
    
    /**查找分类文章列表*/
    CategroyPostDTO findCategoryPostList(Integer categoryId, Integer postLimit, boolean findChildren);
    
    /**查找分类文章分页列表*/
    PageInfo<Post> findCategoryPostPage(Integer categoryId, IPage pageable);

    /**根据ID查找*/
    Post findOne(Long id);

    PostDTO findDetail(Long id);

    PageInfo<PostDTO> query(PostQueryForm object, IPage pageable);

    void add(PostAddForm object);

    void update(PostUpdateForm object);

    void delete(Long id);
    
}
