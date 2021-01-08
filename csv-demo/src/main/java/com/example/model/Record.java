package com.example.model;

import lombok.Data;

/**记录*/

@Data
public class Record {
    
    private Long id;
    
    private String title;
    
    private String author;
    
    private String content;
    
    private Integer categoryId;
    
    private String date;

}
