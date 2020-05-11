package com.ygsm.model.form;

import lombok.Data;

/**更新分类*/
@Data
public class CategoryUpdateForm {
    
    private Integer id;
    
    private String name;//名称
    
    private Integer parentId; //父节点
    
    private String template; //模板
    

}
