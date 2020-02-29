package com.example.mybatis.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jerryjin
 * @since 2020-02-07
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", name=" + name +
        "}";
    }
}
