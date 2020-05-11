package com.ygsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ygsm.common.TreeBuilder;
import com.ygsm.dao.MenuDAO;
import com.ygsm.model.dto.MenuDTO;
import com.ygsm.model.dto.MenuTreeDTO;
import com.ygsm.model.form.MenuAddForm;
import com.ygsm.model.form.MenuUpdateForm;
import com.ygsm.model.pojo.Menu;
import com.ygsm.service.MenuService;
import com.ygsm.util.BeanUtil;

@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuDAO menuDAO;

    @Override
    public List<MenuTreeDTO> findMenuNestList() {
        List<Menu> menuList = menuDAO.findAll();
        List<MenuTreeDTO> menuVOList = new ArrayList<>();
        menuList.forEach(menu -> {
            MenuTreeDTO menuVO = new MenuTreeDTO();
            BeanUtil.copyProperties(menu, menuVO);
            menuVOList.add(menuVO);
        });
        TreeBuilder<MenuTreeDTO, Integer> builder = new TreeBuilder<MenuTreeDTO, Integer>(menuVOList);
        return new ArrayList<>(builder.buildTree());
    }

    @Override
    public MenuDTO findDetail(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MenuDTO> findList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(MenuAddForm object) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(MenuUpdateForm object) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        
    }

}
