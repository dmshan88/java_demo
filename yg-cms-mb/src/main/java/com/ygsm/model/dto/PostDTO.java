package com.ygsm.model.dto;

import com.ygsm.model.enums.PostTypeEnum;

import lombok.Data;

/**文章*/
@Data
public class PostDTO {
    
    private Long id;
    
    private String title;//标题
    
    private String content;//内容
    
    private String summary;//简介
    
    private String thumbnail;//缩略图
    
    private Integer categoryId; //分类ID
    
    private String categoryName; //分类名称
    
    private Integer type; //类型:1=文章;2=资源
    
    /**类型名称*/
    public String getTypeName() {
        return PostTypeEnum.getNameByIndex(this.getType());
    }
    

}
