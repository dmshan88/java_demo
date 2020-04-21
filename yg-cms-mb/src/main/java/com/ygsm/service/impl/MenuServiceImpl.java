package com.ygsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ygsm.common.TreeBuilder;
import com.ygsm.dao.MenuDAO;
import com.ygsm.model.dto.MenuDTO;
import com.ygsm.model.pojo.Menu;
import com.ygsm.service.MenuService;
import com.ygsm.util.BeanUtil;

@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuDAO menuDAO;

    @Override
    public List<MenuDTO> findMenuNestList() {
        List<Menu> menuList = menuDAO.findAll();
        List<MenuDTO> menuVOList = new ArrayList<>();
        menuList.forEach(menu -> {
            MenuDTO menuVO = new MenuDTO();
            BeanUtil.copyProperties(menu, menuVO);
            menuVOList.add(menuVO);
        });
        TreeBuilder<MenuDTO, Integer> builder = new TreeBuilder<MenuDTO, Integer>(menuVOList);
        return new ArrayList<>(builder.buildTree());
    }

}
