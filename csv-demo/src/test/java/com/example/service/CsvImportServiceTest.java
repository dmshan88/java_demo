package com.example.service;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Record;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvImportServiceTest {
    
    @Autowired
    private CsvImportService csvImportService;
    
    @Test
    public void testRun() throws IOException {
        List<Record> records = csvImportService.importFile("D:\\syxx-sitemap\\zsxx.csv", 1);
        records.forEach(obj -> System.out.println(obj));
    }
}
