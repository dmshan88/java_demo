package com.example.service.impl;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.config.properties.ConfigProperties;
import com.example.config.properties.CsvHeaderProperties;
import com.example.model.Record;
import com.example.service.CsvImportService;
import com.example.util.DateUtil;
import com.example.util.FileUtil;
import com.example.util.HtmlUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CsvImportServiceImpl implements CsvImportService {
    
    @Autowired
    private CsvHeaderProperties csvHeaderProperties;
    
    @Autowired
    private ConfigProperties configProperties;

    @Override
    public List<Record> importFile(String filePath, Integer categoryId) throws IOException {
        log.info("导入csv:{}, 分类{}", filePath, categoryId);
        Reader reader = new FileReader(filePath);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        List<Record> recordList = new ArrayList<>();
        for (CSVRecord csvRecord : records) {
            Record record = this.parseCsvRow(csvRecord, categoryId);
            if (this.needRecord(record)) {
                recordList.add(record);
            }
        }
        reader.close();
        log.info("导入成功");
        return recordList;
    }
    
    /**csv中解析一条记录*/
    private Record parseCsvRow(CSVRecord csvRecord, Integer categoryId) {
        String author = csvRecord.get(csvHeaderProperties.getAuthorColumn());
        String content = csvRecord.get(csvHeaderProperties.getContentColumn());
        if (configProperties.getEnableSanitizeHtml()) {
            content = HtmlUtils.sanitizeHtml(content);
        }
        String title = csvRecord.get(csvHeaderProperties.getTitleColumn());
        String url = csvRecord.get(csvHeaderProperties.getUrlColumn());
        String dateString = csvRecord.get(csvHeaderProperties.getDateColumn());
        //解析ID，date,author
        String id = FileUtil.getBaseName(url);
        Date date = DateUtil.parse(dateString, "发布时间：yyyy年MM月dd日");
        author = author.substring("发布者：".length());
        Record record = new Record();
        record.setId(Long.valueOf(id));
        record.setContent(content);
        record.setAuthor(author);
        record.setDate(DateUtil.format(date, DateUtil.FORMAT_DATE));
        record.setTitle(title);
        record.setCategoryId(categoryId);
        return record;
    }
    
    /**排除不需要的记录*/
    private boolean needRecord(Record record) {

        return true;
    }

}
