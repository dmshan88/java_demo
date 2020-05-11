package com.ygsm.controller.content.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ygsm.common.CustomResponse;
import com.ygsm.common.ErrorCode;
import com.ygsm.model.dto.MenuDTO;
import com.ygsm.model.form.MenuAddForm;
import com.ygsm.model.form.MenuUpdateForm;
import com.ygsm.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "menu")
@RestController
@RequestMapping(path = "/api/menu", produces = { "application/json" })
public class MenuController {
    
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "目录详情")
    @GetMapping(value = "/detail")
    CustomResponse<MenuDTO> menuDetailGet(Integer id) {
        MenuDTO object = menuService.findDetail(id);
        if (object == null) {
            return CustomResponse.error(ErrorCode.NOT_FOUND);
        } else {
            return CustomResponse.ok(object);
        }
    }
    
    @ApiOperation(value = "目录列表")
    @GetMapping(value = "/list")
    CustomResponse<List<MenuDTO>> menuListGet() {
        List<MenuDTO> list = menuService.findList();
        return CustomResponse.ok(list);
    }
    
    @ApiOperation(value = "添加目录")
    @PostMapping(value = "/add")
    CustomResponse<Object> menuAddPost(MenuAddForm object) {
        menuService.add(object);
        return CustomResponse.ok(null);
    }
    
    @ApiOperation(value = "更新目录")
    @PostMapping(value = "/save")
    CustomResponse<Object> menuSavePost(MenuUpdateForm object) {
        menuService.update(object);
        return CustomResponse.ok(null);
    }
    
    @ApiOperation(value = "删除分类")
    @PostMapping(value = "/remove")
    CustomResponse<Object> menuRemovePost(Integer id) {
        menuService.delete(id);
        return CustomResponse.ok(null);
    }

}
