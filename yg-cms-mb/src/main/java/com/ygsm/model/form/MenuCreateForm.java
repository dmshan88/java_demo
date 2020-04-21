package com.ygsm.model.form;

import lombok.Data;

/**分类创建表单*/
@Data
public class MenuCreateForm {
    
    private String name; //名称

    private String url; //地址

    private Integer priority; //优先级

    private Integer parentId; //父节点
    
    private String summary; //简介
    
    private String image; //缩略图
    
}
