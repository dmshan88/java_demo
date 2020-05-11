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
import com.ygsm.model.dto.IdNameDTO;
import com.ygsm.model.dto.WebPageDTO;
import com.ygsm.model.form.WebPageAddForm;
import com.ygsm.model.form.WebPageQueryForm;
import com.ygsm.model.form.WebPageUpdateForm;
import com.ygsm.service.WebPageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "page")
@RestController
@RequestMapping(path = "/api/page", produces = { "application/json" })
public class WebPageController {
    
    @Autowired
    private WebPageService webPageService;

    @ApiOperation(value = "页面详情")
    @GetMapping(value = "/detail")
    CustomResponse<WebPageDTO> pageDetailGet(Long id) {
        WebPageDTO object = webPageService.findDetail(id);
        if (object == null) {
            return CustomResponse.error(ErrorCode.NOT_FOUND);
        } else {
            return CustomResponse.ok(object);
        }
    }
    
    @ApiOperation(value = "页面ID名称列表")
    @GetMapping(value = "/name-list")
    CustomResponse<List<IdNameDTO>> pageNameListGet() {
        List<IdNameDTO> list = webPageService.findNameList();
        return CustomResponse.ok(list);
    }
    
    @ApiOperation(value = "查询页面")
    @PostMapping(value = "/query")
    CustomPageResponse<WebPageDTO> pageQueryPost(@RequestBody WebPageQueryForm object) {
        IPage pageable = CustomPageRequestUtil.build(object.getPageNumber(), object.getPageSize()).toIPage();
        PageInfo<WebPageDTO> pageInfo = webPageService.query(object, pageable);
        return CustomPageResponse.ok(pageInfo);
    }
    
    @ApiOperation(value = "添加页面")
    @PostMapping(value = "/add")
    CustomResponse<Object> pageAddPost(WebPageAddForm object) {
        webPageService.add(object);
        return CustomResponse.ok(null);
    }
    
    @ApiOperation(value = "更新页面")
    @PostMapping(value = "/save")
    CustomResponse<Object> pageSavePost(WebPageUpdateForm object) {
        webPageService.update(object);
        return CustomResponse.ok(null);
    }
    
    @ApiOperation(value = "删除页面")
    @PostMapping(value = "/remove")
    CustomResponse<Object> pageRemovePost(Long id) {
        webPageService.delete(id);
        return CustomResponse.ok(null);
    }

}
