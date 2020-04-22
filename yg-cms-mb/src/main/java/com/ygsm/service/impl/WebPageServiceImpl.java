package com.ygsm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ygsm.dao.PageRelateDAO;
import com.ygsm.dao.WebPageDAO;
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

}
