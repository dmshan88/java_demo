package com.example.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.config.properties.ConfigProperties;
import com.example.config.properties.RecordProperties;
import com.example.model.Record;
import com.example.service.ConvertService;

@Service
public class ConvertServiceImpl implements ConvertService {
    
    @Autowired
    private RecordProperties recordProperties;
    
    @Autowired
    private ConfigProperties configProperties;

    @Override
    public String toInsertSql(Record record) {
        String fields = "";
        String values = "";
        Map<String, Object> fieldValuesMap = new HashMap<>();
        if (configProperties.getEnableIdColumn()) {
            fieldValuesMap.put(recordProperties.getIdColumn(), record.getId());
        }
        fieldValuesMap.put(recordProperties.getTitleColumn(), record.getTitle());
        fieldValuesMap.put(recordProperties.getCategoryIdColumn(), record.getCategoryId());
        fieldValuesMap.put(recordProperties.getContentColumn(), record.getContent());
        fieldValuesMap.put(recordProperties.getDateColumn(), record.getDate());
        fieldValuesMap.put(recordProperties.getAuthorColumn(), record.getAuthor());
        fieldValuesMap.putAll(recordProperties.getAdditionalColumns());
        Iterator<Map.Entry<String, Object>> iterator = fieldValuesMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            fields += "`" + entry.getKey() +"`";
            Object value = entry.getValue();
            if (needQuate(value)) {
                values += "'" + value +"'";
            } else {
                values += value;
            }
            if (iterator.hasNext()) {
                fields += ",";
                values += ",";
            }
        }
        String sql = String.format("INSERT INTO `%s`(%s) VALUES(%s)", configProperties.getTableName(), fields, values);
        return sql;
    }
    
    /**需要加引号*/
    private boolean needQuate(Object obj) {
        if (obj instanceof String) {
            return true;
        } else if (isPrimitive(obj)) {
            return false;
        } else {
            return true;
        }
    }
    
    /**判断一个对象是否是基本类型或基本类型的封装类型*/
    private boolean isPrimitive(Object obj) {
        try {
            return ((Class<?>)obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toInsertSql(List<Record> records) {
        String sql = "";
        for (Record record : records) {
            String singleSql = this.toInsertSql(record);
            sql += singleSql + ";" + System.getProperty("line.separator");
        }
        return sql;
    }
}
