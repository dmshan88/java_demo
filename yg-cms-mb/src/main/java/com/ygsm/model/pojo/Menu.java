package com.ygsm.model.pojo;

import lombok.Data;

/**目录*/
@Data
public class Menu {
    
    private Integer id;
    
    private String name;//名称
    
    private String url;//地址
    
    private Integer parentId; //父节点
    
    private Integer priority; //优先级
    

}
