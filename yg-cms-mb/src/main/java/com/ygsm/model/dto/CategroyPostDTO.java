package com.ygsm.model.dto;

import java.util.List;

import com.ygsm.model.pojo.Post;

import lombok.Data;

@Data
public class CategroyPostDTO {
    
    private Integer id; //分类id
    
    private String name; //名称
    
//    private String summary; //简介
    
//    private String image; //缩略图
    
    private List<Post> postList; //文章列表

}
