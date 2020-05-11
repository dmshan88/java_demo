package com.ygsm.service;

import java.util.List;

import com.ygsm.model.dto.PageRelateDTO;
import com.ygsm.model.form.PageRelateAddForm;
import com.ygsm.model.form.PageRelateUpdateForm;

public interface PageRelateService {

    PageRelateDTO findDetail(Long id);

    List<PageRelateDTO> findList();

    void add(PageRelateAddForm object);

    void update(PageRelateUpdateForm object);

    void delete(Long id);

}
