package com.example.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {
    @Id
    int id;
    
    int price;
    
    String name;

}
