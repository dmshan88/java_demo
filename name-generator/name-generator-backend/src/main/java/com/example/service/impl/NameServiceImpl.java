package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.config.CacheConfig;
import com.example.dao.NameDAO;
import com.example.model.dto.NameDTO;
import com.example.model.dto.WordDTO;
import com.example.model.pojo.Name;
import com.example.model.pojo.Word;
import com.example.service.NameService;
import com.example.service.WordService;
import com.example.utils.CacheUtil;
import com.github.pagehelper.PageHelper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class NameServiceImpl implements NameService {
    
    @Autowired
    private NameDAO nameDAO;
    
    @Autowired
    private WordService wordService;
    
    @Autowired
    private CacheUtil cacheUtil;

    @Override
    public boolean save(Name object) {
        Name object1 = this.findByFirstAndSecond(object.getFirst(), object.getSecond());
        if (object1 != null) {//审批实例Id存在
            object.setId(object1.getId());
            nameDAO.update(object);
            log.info("插入名字 id:{}", object.getId());
            return false;
        } else {
            nameDAO.insert(object);
            log.info("插入名字 id:{}", object.getId());
            return true;
        }
    }

    @Override
    public Name findById(Long id) {
        Name object = nameDAO.findById(id);
        return object;
    }

    @Override
    public NameDTO next() {
        this.checkNewNames();
        PageHelper.startPage(0, 1);
        List<NameDTO> name =  this.findByLevel(Name.Level.NEW);
        return name.size() > 0 ? name.get(0) : null;
    }

    @Override
    public void checkNewNames() {
        List<WordDTO> oneList = wordService.findByTone(Word.Tone.ONE);
        List<WordDTO> twoList = wordService.findByTone(Word.Tone.TWO);
        List<WordDTO> threeList = wordService.findByTone(Word.Tone.THREE);
        List<WordDTO> fourList = wordService.findByTone(Word.Tone.FOUR);
        // one two
        this.toAddList(oneList.stream().map(WordDTO::getId).collect(Collectors.toList()), 
                twoList.stream().map(WordDTO::getId).collect(Collectors.toList()));
        //two one 
        this.toAddList(oneList.stream().map(WordDTO::getId).collect(Collectors.toList()), 
                twoList.stream().map(WordDTO::getId).collect(Collectors.toList()));
    }
    
    /**待添加的名称*/
    public void toAddList(List<Integer> firstList, List<Integer> secondList) {
        for (Integer first : firstList) {
            for (Integer second : secondList) {
                Name name = this.findByFirstAndSecond(first, second);
                if (name == null) {
                    name = new Name();
                    name.setFirst(first);
                    name.setSecond(second);
                    name.setLevel(Name.Level.NEW);
                    this.save(name);
                }
            }
        }
    }

    @Override
    public Name findByFirstAndSecond(Integer first, Integer second) {
        String key = first + "_" + second;
        Name object = cacheUtil.getValue(CacheConfig.Names.NAME_DTO, key, Name.class);
        if (object == null) {
            object = nameDAO.findByFirstAndSecond(first, second);
            cacheUtil.setValue(CacheConfig.Names.NAME_DTO, key, object);
        }
        return object;
    }

    @Override
    public List<NameDTO> findByLevel(Integer Level) {
        List<NameDTO> names =  nameDAO.findByLevel(Level);
        return names;
    }

}
