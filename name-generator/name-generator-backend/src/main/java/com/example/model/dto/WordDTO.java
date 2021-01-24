package com.example.model.dto;

import lombok.Data;

@Data
public class WordDTO {

    private Integer id;
    
    private String name;
    
    private Integer tone;//音调1,2,3,4
    
    private Integer level; //等级-1=未评价,0=不喜欢,1=一般,2=喜欢
}
