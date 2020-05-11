package com.ygsm.controller.content.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ygsm.common.CustomResponse;
import com.ygsm.common.ErrorCode;
import com.ygsm.model.dto.PageRelateDTO;
import com.ygsm.model.form.PageRelateAddForm;
import com.ygsm.model.form.PageRelateUpdateForm;
import com.ygsm.service.PageRelateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "page-relate")
@RestController
@RequestMapping(path = "/api/page-relate", produces = { "application/json" })
public class PageRelateController {

    @Autowired
    private PageRelateService pageRelateService;
    
    @ApiOperation(value = "页面关联详情")
    @GetMapping(value = "/detail")
    CustomResponse<PageRelateDTO> pageRelateDetailGet(Long id) {
        PageRelateDTO object = pageRelateService.findDetail(id);
        if (object == null) {
            return CustomResponse.error(ErrorCode.NOT_FOUND);
        } else {
            return CustomResponse.ok(object);
        }
    }
    
    @ApiOperation(value = "页面关联列表")
    @GetMapping(value = "/list")
    CustomResponse<List<PageRelateDTO>> pageRelateListGet(Long pageId) {
        List<PageRelateDTO> list = pageRelateService.findList();
        return CustomResponse.ok(list);
    }
    
    @ApiOperation(value = "添加页面关联")
    @PostMapping(value = "/add")
    CustomResponse<Object> pageRelateAddPost(PageRelateAddForm object) {
        pageRelateService.add(object);
        return CustomResponse.ok(null);
    }
    
    @ApiOperation(value = "更新页面关联")
    @PostMapping(value = "/save")
    CustomResponse<Object> pageRelateSavePost(PageRelateUpdateForm object) {
        pageRelateService.update(object);
        return CustomResponse.ok(null);
    }
    
    @ApiOperation(value = "删除页面关联")
    @PostMapping(value = "/remove")
    CustomResponse<Object> pageRelateRemovePost(Long id) {
        pageRelateService.delete(id);
        return CustomResponse.ok(null);
    }

}
