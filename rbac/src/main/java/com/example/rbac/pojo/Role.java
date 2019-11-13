package com.example.rbac.pojo;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Role {
    
    @Id
    private Integer id;
    
    private String name;
    
    @ManyToMany
    @JoinTable(name = "role_permission", 
        joinColumns = {@JoinColumn(name = "role_id")}, 
        inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    Collection<Permission> permissions;
    
    @ManyToMany(mappedBy = "roles")
    Collection<User> users;

}
