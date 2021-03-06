package com.ygsm.model.dto;

import lombok.Data;

/**目录*/
@Data
public class MenuDTO {
    
    private Integer id;
    
    private String name;//名称
    
    private String url;//地址
    
    private Integer parentId; //父节点
    
    private String parentName; //父节点名称
    
    private Integer priority; //优先级
    

}
