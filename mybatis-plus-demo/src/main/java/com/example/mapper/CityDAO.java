package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.City;

@Mapper
public interface CityDAO  extends BaseMapper<City> {
    City findById(Integer id); 
}