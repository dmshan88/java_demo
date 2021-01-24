package com.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.pojo.Word;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordServiceTest {
    
    @Autowired
    private WordService wordService;
    

    static final String words1 = "安斌冰昌超冲初登敦恩方芳飞丰甘光亨辉佳坚娟均康期千谦清山诗书双思通希香逍心欣星一英昭真知中";
    static final String words2 = "昂白博诚澄淳存达迪凡恒环吉杰连良辽梅盟明铭平齐琪前晴全仁荣实腾文祥学宜怡云哲直竹卓";
   
    @Test
    public void insertRun() {
        for (int i = 0; i < words1.length(); i++) {
            String name = "" + words1.charAt(i);
            Word word = new Word();
            word.setName(name);
            word.setLevel(-1);
            word.setTone(1);
            wordService.save(word);
        }
        for (int i = 0; i < words2.length(); i++) {
            String name = "" + words2.charAt(i);
            Word word = new Word();
            word.setName(name);
            word.setLevel(-1);
            word.setTone(2);
            wordService.save(word);
        }
    }
}
