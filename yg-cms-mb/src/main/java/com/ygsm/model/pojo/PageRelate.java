package com.ygsm.model.pojo;

import lombok.Data;

/**页面关联*/
@Data
public class PageRelate {
    
    private Long id;
    
    private Long pageId;//页面ID
    
    private Long relateId;//关联ID
    
    private Integer relateType;//关联类型:1=分类,2=子分类
    
    private Integer priority;// 优先级
    
    private String description;//说明
    
}
