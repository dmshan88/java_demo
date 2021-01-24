package com.example.service;

import java.util.List;

import com.example.model.dto.NameDTO;
import com.example.model.pojo.Name;

public interface NameService {
    
    Name findById(Long id); 
    
    Name findByFirstAndSecond(Integer first, Integer second);
    
    List<NameDTO> findByLevel(Integer Level);
    
    /**下一个未评级名称*/
    NameDTO next();
    
    /**查询新名字*/
    void checkNewNames();
    
    boolean save(Name object);

}
