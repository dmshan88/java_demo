package com.example.model.entity;

import java.util.Date;

import com.example.model.enums.UserTypeEnum;

public class User {
    
    private Integer id;
    private String name;
    private UserTypeEnum type;
    private Date createTime;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public UserTypeEnum getType() {
        return type;
    }
    public void setType(UserTypeEnum type) {
        this.type = type;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", type=" + type + ", createTime=" + createTime + "]";
    }
    


}
