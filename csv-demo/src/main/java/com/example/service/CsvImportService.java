package com.example.service;

import java.io.IOException;
import java.util.List;

import com.example.model.Record;

public interface CsvImportService {
    
    /**导入文件为记录*/
    List<Record> importFile(String filePath, Integer categoryId) throws IOException ;

}
