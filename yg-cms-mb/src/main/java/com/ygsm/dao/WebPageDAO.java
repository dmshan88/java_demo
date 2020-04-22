package com.ygsm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ygsm.model.pojo.Post;
import com.ygsm.model.pojo.WebPage;

@Mapper
public interface WebPageDAO {
    
    WebPage findById(Long id);
    
    int insert(Post entity);
    
    int batchInsert(List<WebPage> entities);
    
    int update(Post entity);

}
