package com.ygsm.service;

import java.util.List;

import com.ygsm.model.dto.MenuDTO;
import com.ygsm.model.dto.MenuTreeDTO;
import com.ygsm.model.form.MenuAddForm;
import com.ygsm.model.form.MenuUpdateForm;

public interface MenuService {

    /**获取嵌套菜单*/
    List<MenuTreeDTO> findMenuNestList();

    MenuDTO findDetail(Integer id);

    List<MenuDTO> findList();

    void add(MenuAddForm object);

    void update(MenuUpdateForm object);

    void delete(Integer id);
}
