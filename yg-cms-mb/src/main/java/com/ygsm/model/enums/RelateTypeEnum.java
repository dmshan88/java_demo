package com.ygsm.model.enums;

import lombok.Getter;

@Getter
public enum RelateTypeEnum {
    
    UNKNOWN(0, "未知"),
    CATEGORY(1, "目录"), 
    SUB_CATEGORY(2, "子目录");
    
    private Integer index;//索引
    
    private String name;//名称
    
    RelateTypeEnum(Integer index, String name) {
        this.index = index;
        this.name = name;
    }
    
    /**根据索引获取对象*/
    public static RelateTypeEnum getObjectByIndex(Integer index) {
        for (RelateTypeEnum obj : values()) {
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
