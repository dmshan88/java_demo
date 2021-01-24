package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.model.dto.WordDTO;
import com.example.model.pojo.Word;

@Mapper
public interface WordDAO {
    
    List<WordDTO> findAll();
    
    Word findById(@Param("id") Integer id);
    
    Word findByName(String name);
    
    int insert(@Param("item") Word item);
    
    int update(@Param("item") Word item);
    
    int delete(@Param("id") Integer id);

    List<WordDTO> findByTone(Integer tone);

}
