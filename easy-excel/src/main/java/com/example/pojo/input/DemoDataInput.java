package com.example.pojo.input;

import lombok.Data;

@Data
public class DemoDataInput {
    
    String id;
    
    String name;
    
    Integer duty = 0;
    
    Integer late = 0;
}
