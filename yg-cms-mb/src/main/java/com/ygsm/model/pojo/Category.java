package com.ygsm.model.pojo;

import lombok.Data;

/**分类*/
@Data
public class Category {
    
    private Integer id;
    
    private String name;//名称
    
    private Integer parentId; //父节点
    

}
