package com.ygsm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageInfo;
import com.ygsm.dao.PageRelateDAO;
import com.ygsm.dao.WebPageDAO;
import com.ygsm.model.dto.IdNameDTO;
import com.ygsm.model.dto.WebPageDTO;
import com.ygsm.model.form.WebPageAddForm;
import com.ygsm.model.form.WebPageQueryForm;
import com.ygsm.model.form.WebPageUpdateForm;
import com.ygsm.model.pojo.PageRelate;
import com.ygsm.model.pojo.WebPage;
import com.ygsm.service.WebPageService;

@Service
public class WebPageServiceImpl implements WebPageService {
    
  @Autowired
  private WebPageDAO webPageDAO;
  
  @Autowired
  private PageRelateDAO pageRelateDAO;

    @Override
    public List<PageRelate> findRelates(Long pageId) {
        return pageRelateDAO.findByPageId(pageId);
    }

    @Override
    public WebPage findOne(Long id) {
        return webPageDAO.findById(id);
    }

    @Override
    public WebPageDTO findDetail(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<IdNameDTO> findNameList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PageInfo<WebPageDTO> query(WebPageQueryForm object, IPage pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(WebPageAddForm object) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(WebPageUpdateForm object) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

}
