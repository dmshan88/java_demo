package com.example.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class City extends BaseEntity {

    private int id;
    
    private String name;
    
}