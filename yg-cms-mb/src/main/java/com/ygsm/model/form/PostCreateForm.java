package com.ygsm.model.form;

import lombok.Data;

/**文章创建表单*/
@Data
public class PostCreateForm {

    private String title; //标题
    
    private String content; //内容
    
    private String image; //缩略图
    
    private String summary; //摘要
    
    private Integer status; //状态
    
    private Integer categoryId; //分类Id
}
