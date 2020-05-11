package com.ygsm.model.form;

import lombok.Data;

/**查询分类*/
@Data
public class CategoryQueryForm {
    
    private Integer pageNumber;//分页页码
    
    private Integer pageSize;//分页大小
    
    private String name;//名称
    
    private Integer parentId; //父节点
    
    private String template; //模板
    

}
