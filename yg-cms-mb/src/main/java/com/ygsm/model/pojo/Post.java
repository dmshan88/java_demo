package com.ygsm.model.pojo;

import lombok.Data;

/**文章*/
@Data
public class Post {
    
    private Long id;
    
    private String title;//标题
    
    private String content;//内容
    
    private Integer categoryId; //分类ID
    

}
