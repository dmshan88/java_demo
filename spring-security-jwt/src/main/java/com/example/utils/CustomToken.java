package com.example.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**jwt token*/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomToken {
    
    private String username;
    
    @JsonIgnore
    private int userId;
    
    @JsonIgnore
    private String name;

}