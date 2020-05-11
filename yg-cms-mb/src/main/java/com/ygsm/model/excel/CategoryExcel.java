package com.ygsm.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

@Data
public class CategoryExcel {
    
    @ExcelProperty(value = "id")
    private Integer id;
    
    @ExcelProperty(value = "名称")
    private String name; //名称
    
    @ExcelProperty(value = "上级id")
    private Integer parentId; //父节点
    
    @ExcelProperty(value = "模板")
    private String template;//模板

}
