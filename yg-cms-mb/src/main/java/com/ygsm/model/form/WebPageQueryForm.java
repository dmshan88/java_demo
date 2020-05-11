package com.ygsm.model.form;

import lombok.Data;

/**查询页面*/
@Data
public class WebPageQueryForm {
    
    private Integer pageNumber;//分页页码
    
    private Integer pageSize;//分页大小

    private String name;//名称
    
    private String template;//模板
    
}
