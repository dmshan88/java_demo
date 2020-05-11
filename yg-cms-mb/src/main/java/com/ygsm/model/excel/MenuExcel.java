package com.ygsm.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

@Data
public class MenuExcel {
    
    @ExcelProperty(value = "id")
    private Integer id;
    
    @ExcelProperty(value = "名称")
    private String name; //名称
    
    @ExcelProperty(value = "地址")
    private String url; //地址
    
    @ExcelProperty(value = "上级id")
    private Integer parentId; //父节点
    
    @ExcelProperty(value = "优先级")
    private Integer priority; //优先级
    
//    @ExcelProperty(value = "缩略图")
//    private String image; //缩略图
//    
//    @ExcelProperty(value = "摘要")
//    private String summary; //摘要

}
