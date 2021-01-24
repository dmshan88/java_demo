package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CustomResponse;
import com.example.model.dto.WordDTO;
import com.example.model.pojo.Word;
import com.example.service.WordService;

@RestController
@RequestMapping(path = "/word")
public class WordController {
    
    @Autowired
    private WordService wordService;
    
    @PostMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomResponse<List<WordDTO>> findAll(){
        return CustomResponse.ok(wordService.findAll());
    }
    
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomResponse<Object> save(Word word){
        wordService.save(word);
        return CustomResponse.ok(null);
    }
    
    @PostMapping(value = "/changeLevel", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomResponse<Object> changeLevel(Integer id, Integer level){
        Word word = wordService.findById(id);
        word.setLevel(level);
        wordService.save(word);
        return CustomResponse.ok(null);
    }
}
