package com.ygsm.model.dto;

import java.util.Collection;

import com.ygsm.common.TreeBuilder.TreeNode;

import lombok.Data;

@Data
public class MenuTreeDTO implements TreeNode<MenuTreeDTO, Integer> {
    
    private Integer id; 

    private String name; //名称

    private String url; //地址

    private Integer priority; //优先级
    
    private Integer parentId;
    
//    private String summary; //简介
    
//    private String image; //缩略图

//    private Menu parent; //父节点
    
    private Collection<MenuTreeDTO> children;

    @Override
    public int compareTo(TreeNode<MenuTreeDTO, Integer> o) {
        return this.getOrder() - o.getOrder();
    }

    @Override
    public void setChildren(Collection<MenuTreeDTO> children) {
        this.children = children;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getParentId() {
        return parentId;
    }

    @Override
    public Integer getOrder() {
        return priority;
    }

}
