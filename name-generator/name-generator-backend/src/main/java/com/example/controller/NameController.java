package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CustomResponse;
import com.example.model.dto.NameDTO;
import com.example.model.pojo.Name;
import com.example.service.NameService;

@RestController
@RequestMapping(path = "/name")
public class NameController {
    
    @Autowired
    private NameService nameService;
    
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomResponse<Object> save(Name name){
        nameService.save(name);
        return CustomResponse.ok(null);
    }
    
    @PostMapping(value = "/changeLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomResponse<Object> changeLevel(Long id, Integer level){
        Name object = nameService.findById(id);
        object.setLevel(level);
        nameService.save(object);
        return CustomResponse.ok(null);
    }
    
    @PostMapping(value = "/next", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomResponse<NameDTO> next(){
        NameDTO next = nameService.next();
        return CustomResponse.ok(next);
    }
    
    @PostMapping(value = "/findByLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomResponse<List<NameDTO>> findByLevel(Integer level){
        List<NameDTO> list = nameService.findByLevel(level);
        return CustomResponse.ok(list);
    }
    
}
