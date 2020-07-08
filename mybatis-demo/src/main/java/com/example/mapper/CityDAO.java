package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.pojo.City;

@Mapper
public interface CityDAO {

    City findById(long id);
    
    List<City> findAll();
    
    Integer insert(City city);

}