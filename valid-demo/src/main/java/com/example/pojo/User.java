package com.example.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {
    
    private Integer id;
    

    @NotEmpty
    private String name;
    
    
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

}
