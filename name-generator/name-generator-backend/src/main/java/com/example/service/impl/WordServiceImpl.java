package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.WordDAO;
import com.example.model.dto.WordDTO;
import com.example.model.pojo.Word;
import com.example.service.WordService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class WordServiceImpl implements WordService {
    @Autowired
    private WordDAO wordDAO;

    @Override
    public boolean save(Word object) {
        Word object1 = wordDAO.findByName(object.getName());
        if (object1 != null) {//审批实例Id存在
            object.setId(object1.getId());
            wordDAO.update(object);
            log.info("更新字 id:{}", object.getId());
            return false;
        } else {
            wordDAO.insert(object);
            log.info("插入字 id:{}", object.getId());
            return true;
        }
    }

    @Override
    public List<WordDTO> findAll() {
        List<WordDTO> list = wordDAO.findAll();
        return list;
    }

    @Override
    public Word findById(Integer id) {
        return wordDAO.findById(id);
    }

    @Override
    public List<WordDTO> findByTone(Integer tone) {
        List<WordDTO> list = wordDAO.findByTone(tone);
        return list;
    }

}
