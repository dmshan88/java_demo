package com.ygsm.model.form;

import lombok.Data;

/**添加分类*/
@Data
public class CategoryAddForm {
    
    private String name;//名称
    
    private Integer parentId; //父节点
    
    private String template; //模板
    

}
