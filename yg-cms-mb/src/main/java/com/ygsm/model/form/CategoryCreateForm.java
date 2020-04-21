package com.ygsm.model.form;

import lombok.Data;

/**分类创建表单*/
@Data
public class CategoryCreateForm {
    
    private String name; //名称
    
    private Integer parentId; //父节点
    
    private String image; //缩略图
    
    private String summary; //摘要
}
