package com.ygsm.model.form;

import lombok.Data;

/**更新文章*/
@Data
public class PostUpdateForm {
    
    private Long id;
    
    private String title;//标题
    
    private String content;//内容
    
    private String summary;//简介
    
    private String thumbnail;//缩略图
    
    private Integer categoryId; //分类ID
    
    private Integer type; //类型:1=文章;2=资源
    

}
