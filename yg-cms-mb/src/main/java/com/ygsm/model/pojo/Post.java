package com.ygsm.model.pojo;

import lombok.Data;

/**文章*/
@Data
public class Post {
    
    private Long id;
    
    private String title;//标题
    
    private String content;//内容
    
    private String summary;//简介
    
    private String thumbnail;//缩略图
    
    private Integer categoryId; //分类ID
    
    private Integer type; //类型:1=文章;2=资源
    

}
