package com.ygsm.model.enums;

import lombok.Getter;

@Getter
public enum PostTypeEnum {
    
    UNKNOWN(0, "未知"),
    POST(1, "文章"), 
    RESOURCE(2, "资源");
    
    private Integer index;//索引
    
    private String name;//名称
    
    PostTypeEnum(Integer index, String name) {
        this.index = index;
        this.name = name;
    }
    
    /**根据索引获取对象*/
    public static PostTypeEnum getObjectByIndex(Integer index) {
        for (PostTypeEnum obj : values()) {
            if (obj.getIndex() == index) {
                return obj;
            }
        }
        return UNKNOWN;
    }
    
    /**根据索引获取名称*/
    public static String getNameByIndex(Integer index) {
        return getObjectByIndex(index).getName();
    }
    
    /**验证index*/
    public static Integer vaildIndex(Integer index) {
        return getObjectByIndex(index).getIndex();
    }
}
