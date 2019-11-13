package com.example.pojo;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.example.utils.TreeBuilder.TreeNode;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
@Entity
public class Permission implements TreeNode<Permission, Integer> {
    @Id
    private Integer id;
    
    private String name;
    
    private String displayName;
    
    private String url;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;
    
    @Transient
    private Collection<Permission> children;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Permission parent;

    @Override
    public void setChildren(Collection<Permission> children) {
        this.children = children;
    }

    @Override
    public Integer getParentId() {
        return parent == null ? null : parent.getId();
    }
    
}
