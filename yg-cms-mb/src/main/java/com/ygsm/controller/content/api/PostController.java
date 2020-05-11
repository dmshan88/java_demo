package com.ygsm.controller.content.api;

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
import com.ygsm.model.dto.PostDTO;
import com.ygsm.model.form.PostAddForm;
import com.ygsm.model.form.PostQueryForm;
import com.ygsm.model.form.PostUpdateForm;
import com.ygsm.service.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "post")
@RestController
@RequestMapping(path = "/api/post", produces = { "application/json" })
public class PostController {

    @Autowired
    private PostService postService;
    
    @ApiOperation(value = "文章详情")
    @GetMapping(value = "/detail")
    CustomResponse<PostDTO> postDetailGet(Long id) {
        PostDTO object = postService.findDetail(id);
        if (object == null) {
            return CustomResponse.error(ErrorCode.NOT_FOUND);
        } else {
            return CustomResponse.ok(object);
        }
    }
    
    @ApiOperation(value = "查询文章")
    @PostMapping(value = "/query")
    CustomPageResponse<PostDTO> postQueryPost(@RequestBody PostQueryForm object) {
        IPage pageable = CustomPageRequestUtil.build(object.getPageNumber(), object.getPageSize()).toIPage();
        PageInfo<PostDTO> pageInfo = postService.query(object, pageable);
        return CustomPageResponse.ok(pageInfo);
    }
    
    @ApiOperation(value = "添加文章")
    @PostMapping(value = "/add")
    CustomResponse<Object> postAddPost(PostAddForm object) {
        postService.add(object);
        return CustomResponse.ok(null);
    }
    
    @ApiOperation(value = "更新文章")
    @PostMapping(value = "/save")
    CustomResponse<Object> postSavePost(PostUpdateForm object) {
        postService.update(object);
        return CustomResponse.ok(null);
    }
    
    @ApiOperation(value = "删除文章")
    @PostMapping(value = "/remove")
    CustomResponse<Object> postRemovePost(Long id) {
        postService.delete(id);
        return CustomResponse.ok(null);
    }

}
