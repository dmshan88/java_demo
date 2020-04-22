package com.ygsm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ygsm.model.pojo.PageRelate;
import com.ygsm.model.pojo.Post;

@Mapper
public interface PageRelateDAO {
    
    PageRelate findById(Long id);
    
    List<PageRelate> findByPageId(Long pageId);
    
    int insert(PageRelate entity);
    
    int batchInsert(List<PageRelate> entities);
    
    int update(Post entity);
    
    int delete(Long id);

}
