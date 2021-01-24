package com.example.model.pojo;

import lombok.Data;

@Data
public class Word {
    
    private Integer id;
    
    private String name;
    
    private Integer tone;//音调1,2,3,4
    
    private Integer level; //等级-1=未评价,0=不喜欢,1=一般,2=喜欢
    
    public interface Tone {
        final static Integer ONE = 1;
        final static Integer TWO = 2;
        final static Integer THREE = 3;
        final static Integer FOUR = 4;
    }

}
