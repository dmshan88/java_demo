package com.example.pojo;

import lombok.Data;

@Data
public class Department {

    public static final String TABLE_NAME = "department";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PAREMENT = "parentid";
    
    public static final String ATTRIBUTE_ID = "id";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_PARENT = "parentid";
    
    private Long id;

    private String name;

    private Long parentid;
    
}
