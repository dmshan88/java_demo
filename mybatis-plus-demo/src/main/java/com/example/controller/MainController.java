package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapper.CityDAO;

@RestController
public class MainController {

    @Autowired
    private CityDAO cityMapper;
    
    @GetMapping(path = "/test")
    void test() {
//        City city = new City();
//        city.setName1("San Francisco");
//        city.setState1("CA");
//        city.setCountry1("US");
//        
//      
//        System.out.println(cityMapper.insert(city));
//        System.out.println(city.getId());
        System.out.println(cityMapper.selectById(1));
        System.out.println(cityMapper.findById(1));
    }
}
