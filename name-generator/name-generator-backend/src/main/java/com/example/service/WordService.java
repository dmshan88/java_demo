package com.example.service;

import java.util.List;

import com.example.model.dto.WordDTO;
import com.example.model.pojo.Word;

public interface WordService {

    List<WordDTO> findAll();
    
    List<WordDTO> findByTone(Integer tone);
    
    Word findById(Integer id);
    
    boolean save(Word object);
}
