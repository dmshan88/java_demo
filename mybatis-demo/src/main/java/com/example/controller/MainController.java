package com.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapper.CityDAO;
import com.example.pojo.City;

@RestController
public class MainController {

    @Autowired
    private CityDAO cityMapper;
    
    @GetMapping(path = "/find")
    City find(Integer id) {
        return cityMapper.findById(id);
    }
    
    @GetMapping(path = "/findAll")
    List<City> findAll() {
        return cityMapper.findAll();
    }
    
    @GetMapping(path = "/insert")
    Integer insert(String name) {
        City city = new City();
        city.setName(name);
        city.setCreateTime(new Date());
        return cityMapper.insert(city);
    }
}
