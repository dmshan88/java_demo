package com.ygsm.service;

import java.util.List;

import com.ygsm.model.pojo.PageRelate;
import com.ygsm.model.pojo.WebPage;

public interface WebPageService {
    
    /**查找关联*/
    List<PageRelate> findRelates(Long pageId);

    /**根据ID查找*/
    WebPage findOne(Long id);
    
}
