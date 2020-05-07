package com.ygsm.model.pojo;

import lombok.Data;
import lombok.Getter;

/**页面关联*/
@Data
public class PageRelate {
    
    private Long id;
    
    private Long pageId;//页面ID
    
    private Long relateId;//关联ID
    
    public enum RelateType {
        CATEGORY(1), SUB_CATEGORY(2);
        @Getter
        private Integer type;
        RelateType(Integer type) {
            this.type = type;
        }
        
    }
    private Integer relateType;//关联类型:1=分类
    
    private Integer priority;// 优先级
    
    private String description;//说明
    
}
