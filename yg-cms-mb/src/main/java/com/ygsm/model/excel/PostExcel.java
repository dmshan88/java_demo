package com.ygsm.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

@Data
public class PostExcel {
    
    @ExcelProperty(value = "标题")
    private String title; //标题
    
    @ExcelProperty(value = "内容")
    private String content; //内容
    
    @ExcelProperty(value = "分类Id")
    private Integer categoryId; //分类Id
    
    @ExcelProperty(value = "缩略图")
    private String thumbnail; //缩略图
    
    @ExcelProperty(value = "摘要")
    private String summary; //摘要
    
    @ExcelProperty(value = "类型")
    private Integer type; //类型:1=文章;2=资源
}
