package com.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.pojo.City;

@Mapper
public interface CityDAO {

    @Insert("INSERT INTO city (name, state, country) VALUES(#{name1}, #{state1}, #{country1})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(City city);
    
    @Select("SELECT id, name as name1, state, country FROM city WHERE id = #{id}")
    City findById(long id);

}