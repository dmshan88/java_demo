package com.ygsm.model.dto;

import lombok.Data;

/**分类*/
@Data
public class CategoryDTO {
    
    private Integer id;
    
    private String name;//名称
    
    private Integer parentId; //父节点
    
    private String parentName;//父节点名称
    
    private String template; //模板
    

}
