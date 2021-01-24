package com.example.model.dto;

import com.example.model.pojo.Name;

import lombok.Data;

@Data
public class NameDTO {
    
    private Long id;
    
    private Integer first;
    
    private Integer second;
    
    private Integer level; //等级-1=未评价,0=不喜欢,1=一般,2=喜欢
    
    private String fullName;
    
    public String getLevelName() {
        String levelName = "未知";
        if (Name.Level.NEW.equals(level)) {
            levelName = "未评价";
        } else if (Name.Level.NO_LIKE.equals(level)) {
            levelName = "不喜欢";
        } else if (Name.Level.NORMAL.equals(level)) {
            levelName = "一般";
        } else if (Name.Level.LIKE.equals(level)) {
            levelName = "喜欢";
        }
        return levelName;
    }

}
