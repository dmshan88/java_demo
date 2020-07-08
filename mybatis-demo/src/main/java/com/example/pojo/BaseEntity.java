package com.example.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class BaseEntity {
    
    private Boolean deleted;
    
    private Date createTime;
    
    private Date updateTime;

}
