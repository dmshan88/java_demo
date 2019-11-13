package com.example.pojo;

import lombok.Data;

@Data
public class DepartmentUser {
    
    public static final String TABLE_NAME = "department_user";
    public static final String FIELD_DEPARTMENT = "department_id";
    public static final String FIELD_USER = "user_id";
    public static final String ATTRIBUTE_DEPARTMENT = "departmentId";
    public static final String ATTRIBUTE_USER = "userId";
    
    private Long departmentId;
    
    private String userId;

}
