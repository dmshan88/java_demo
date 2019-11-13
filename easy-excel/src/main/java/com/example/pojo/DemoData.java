package com.example.pojo;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoData {
    
    
    @ExcelProperty(index = 4)
    String id;
    
    @ExcelProperty(index = 0)
    String name;
    
//    @ExcelProperty(index = 2)
//    String score;
    
    @ExcelProperty(index = 5)
    String duty;
    
    @ExcelProperty(index = 8)
    String late;
}
