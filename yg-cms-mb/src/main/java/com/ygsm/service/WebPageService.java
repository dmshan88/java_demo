package com.ygsm.service;

import java.util.List;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageInfo;
import com.ygsm.model.dto.IdNameDTO;
import com.ygsm.model.dto.WebPageDTO;
import com.ygsm.model.form.WebPageAddForm;
import com.ygsm.model.form.WebPageQueryForm;
import com.ygsm.model.form.WebPageUpdateForm;
import com.ygsm.model.pojo.PageRelate;
import com.ygsm.model.pojo.WebPage;

public interface WebPageService {
    
    /**查找关联*/
    List<PageRelate> findRelates(Long pageId);

    /**根据ID查找*/
    WebPage findOne(Long id);

    WebPageDTO findDetail(Long id);

    List<IdNameDTO> findNameList();

    PageInfo<WebPageDTO> query(WebPageQueryForm object, IPage pageable);

    void add(WebPageAddForm object);

    void update(WebPageUpdateForm object);

    void delete(Long id);
    
}
