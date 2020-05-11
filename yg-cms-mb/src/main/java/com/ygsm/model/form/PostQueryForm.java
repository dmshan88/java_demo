package com.ygsm.model.form;

import lombok.Data;

/**更新文章*/
@Data
public class PostQueryForm {
    
    private Integer pageNumber;//分页页码
    
    private Integer pageSize;//分页大小
    
    private String title;//标题
    
    private Integer categoryId; //分类ID
    
    private Integer type; //类型:1=文章;2=资源
    

}
