package com.ygsm.model.dto;

import java.util.Collection;

import com.ygsm.common.TreeBuilder.TreeNode;

import lombok.Data;

@Data
public class MenuDTO implements TreeNode<MenuDTO, Integer> {
    
    private Integer id; 

    private String name; //名称

    private String url; //地址

    private Integer priority; //优先级
    
    private Integer parentId;
    
//    private String summary; //简介
    
//    private String image; //缩略图

//    private Menu parent; //父节点
    
    private Collection<MenuDTO> children;

    @Override
    public int compareTo(TreeNode<MenuDTO, Integer> o) {
        return this.getOrder() - o.getOrder();
    }

    @Override
    public void setChildren(Collection<MenuDTO> children) {
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
