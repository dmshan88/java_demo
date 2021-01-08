package com.example.service;

import java.util.List;

import com.example.model.Record;

public interface ConvertService {
    
    /**转换记录为Sql*/
    String toInsertSql(Record record);
    
    /**批量转换记录为Sql*/
    String toInsertSql(List<Record> records);

}
