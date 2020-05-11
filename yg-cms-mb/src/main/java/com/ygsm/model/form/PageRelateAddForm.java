package com.ygsm.model.form;

import lombok.Data;

/**添加页面关联*/
@Data
public class PageRelateAddForm {
    
    private Long pageId;//页面ID
    
    private Long relateId;//关联ID
    
    private Integer relateType;//关联类型:1=分类,2=子分类
    
    private Integer priority;// 优先级
    
    private String description;//说明
    
}
