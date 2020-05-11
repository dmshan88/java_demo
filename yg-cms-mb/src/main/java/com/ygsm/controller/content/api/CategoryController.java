package com.ygsm.controller.content.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageInfo;
import com.ygsm.common.CustomPageRequestUtil;
import com.ygsm.common.CustomPageResponse;
import com.ygsm.common.CustomResponse;
import com.ygsm.common.ErrorCode;
import com.ygsm.model.dto.CategoryDTO;
import com.ygsm.model.dto.IdNameDTO;
import com.ygsm.model.form.CategoryAddForm;
import com.ygsm.model.form.CategoryQueryForm;
import com.ygsm.model.form.CategoryUpdateForm;
import com.ygsm.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "category")
@RestController
@RequestMapping(path = "/api/category", produces = { "application/json" })
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "分类详情")
    @GetMapping(value = "/detail")
    CustomResponse<CategoryDTO> categoryDetailGet(Integer id) {
        CategoryDTO object = categoryService.findDetail(id);
        if (object == null) {
            return CustomResponse.error(ErrorCode.NOT_FOUND);
        } else {
            return CustomResponse.ok(object);
        }
    }
    
    @ApiOperation(value = "分类ID名称列表")
    @GetMapping(value = "/name-list")
    CustomResponse<List<IdNameDTO>> categoryNameListGet() {
        List<IdNameDTO> list = categoryService.findNameList();
        return CustomResponse.ok(list);
    }
    
    @ApiOperation(value = "查询分类")
    @PostMapping(value = "/query")
    CustomPageResponse<CategoryDTO> categoryQueryPost(@RequestBody CategoryQueryForm object) {
        IPage pageable = CustomPageRequestUtil.build(object.getPageNumber(), object.getPageSize()).toIPage();
        PageInfo<CategoryDTO> pageInfo = categoryService.query(object, pageable);
        return CustomPageResponse.ok(pageInfo);
    }
    
    @ApiOperation(value = "添加分类")
    @PostMapping(value = "/add")
    CustomResponse<Object> categoryAddPost(CategoryAddForm object) {
        categoryService.add(object);
        return CustomResponse.ok(null);
    }
    
    @ApiOperation(value = "更新分类")
    @PostMapping(value = "/save")
    CustomResponse<Object> categorySavePost(CategoryUpdateForm object) {
        categoryService.update(object);
        return CustomResponse.ok(null);
    }
    
    @ApiOperation(value = "删除分类")
    @PostMapping(value = "/remove")
    CustomResponse<Object> categoryRemovePost(Integer id) {
        categoryService.delete(id);
        return CustomResponse.ok(null);
    }

}
