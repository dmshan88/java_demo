package com.example.pojo;

import lombok.Data;

@Data
public class User {
    
    public static final String TABLE_NAME = "user";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    
    public static final String ATTRIBUTE_ID = "id";
    public static final String ATTRIBUTE_NAME = "name";
    
    private String id;
    
    private String name;

}
