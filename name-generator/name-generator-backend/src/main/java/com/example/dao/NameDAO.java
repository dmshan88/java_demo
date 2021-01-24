package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.model.dto.NameDTO;
import com.example.model.pojo.Name;

@Mapper
public interface NameDAO {
    
    Name findById(@Param("id") Long id);
    
    Name findByFirstAndSecond(Integer first, Integer second);
    
    List<NameDTO> findByLevel(Integer Level);
    
    int insert(@Param("item") Name item);
    
    int update(@Param("item") Name item);
    
    int delete(@Param("id") Long id);


}
