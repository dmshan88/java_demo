package com.ygsm.service;

import java.util.List;

import com.ygsm.model.dto.MenuDTO;

public interface MenuService {

    /**获取嵌套菜单*/
    List<MenuDTO> findMenuNestList();
}
