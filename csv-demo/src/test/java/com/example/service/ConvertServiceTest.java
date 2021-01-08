package com.example.service;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Record;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConvertServiceTest {
    
    @Autowired
    private ConvertService convertService;
    
    @Test
    public void testRun() {
        System.out.println("ok");
        Record record = new Record();
        record.setAuthor("author1");
        record.setCategoryId(11);
        record.setContent("<div> <h1> aaa</h1> ababab</div>");
        record.setDate("2020-01-02");
        record.setId(123L);
        record.setTitle("test title");
        Record record1 = new Record();
        record1.setAuthor("author2");
        record1.setCategoryId(22);
        record1.setContent("<div> <h1> 11aaa</h1> aba22bab</div>");
        record1.setDate("2020-02-02");
        record1.setId(456L);
        record1.setTitle("test title1");
        String sql = convertService.toInsertSql(Arrays.asList(record, record1));
        System.out.println(sql);
        
    }
}
