package com.ygsm.model.dto;

import com.ygsm.model.enums.RelateTypeEnum;

import lombok.Data;

/**页面关联*/
@Data
public class PageRelateDTO {
    
    private Long id;
    
    private Long pageId;//页面ID
    
    private String pageName;//页面名称
    
    private Long relateId;//关联ID
    
    private Integer relateType;//关联类型:1=分类,2=子分类
    
    private Integer priority;// 优先级
    
    private String description;//说明
    
    /**关联类型名称*/
    public String getRelateTypeName() {
        return RelateTypeEnum.getNameByIndex(this.getRelateType());
    }
    
}
