package com.example.pojo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.example.pojo.DemoData;

@StaticMetamodel(DemoData.class)
public class DemoData_ {
    
    public static volatile SingularAttribute<DemoData, Integer> id;
    
    public static volatile SingularAttribute<DemoData, Integer> score;
    
    public static volatile SingularAttribute<DemoData, Integer> userId;
    
}
