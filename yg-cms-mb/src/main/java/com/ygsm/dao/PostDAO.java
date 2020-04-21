package com.ygsm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ygsm.model.pojo.Post;

@Mapper
public interface PostDAO {
    
    Post findById(Long id);
    
//    List<Post> findAll();
    
    List<Post> findByCategoryId(Integer categoryId);
    
    List<Post> findByCategoryIdIn(List<Integer> categoryIds);
    
    int insert(Post entity);
    
    int batchInsert(List<Post> entities);
    
    int update(Post entity);

}
