package com.ygsm.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**页面*/
@Data
public class WebPageExcel {
    
    @ExcelProperty(value = "名称")
    private String name;//名称
    
    @ExcelProperty(value = "模板")
    private String template;//模板
    
}
