package com.example.model.pojo;

import lombok.Data;

@Data
public class Name {
    
    private Long id;
    
    private Integer first;
    
    private Integer second;
    
    private Integer level; //等级-1=未评价,0=不喜欢,1=一般,2=喜欢
    
    public interface Level {
        final static Integer NEW = -1;
        final static Integer NORMAL = 1;
        final static Integer NO_LIKE = 0;
        final static Integer LIKE = 2;
    }

}
