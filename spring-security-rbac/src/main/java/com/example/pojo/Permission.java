package com.example.pojo;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.Collection;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Permission {
    @Id
    private Integer id;
    
    private String name;
    
    private String url;
    
    @ManyToMany(mappedBy = "permissions")
    Collection<Role> roles;
}
