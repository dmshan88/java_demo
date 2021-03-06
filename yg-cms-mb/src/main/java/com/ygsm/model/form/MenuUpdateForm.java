package com.ygsm.model.form;

import lombok.Data;

/**更新目录*/
@Data
public class MenuUpdateForm {
    
    private Integer id;
    
    private String name;//名称
    
    private String url;//地址
    
    private Integer parentId; //父节点
    
    private Integer priority; //优先级
    

}
