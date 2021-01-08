package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Record;
import com.example.service.ConvertService;
import com.example.service.CsvImportService;
import com.example.util.FileUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    
    @Autowired
    private CsvImportService csvImportService;
    
    @Autowired
    private ConvertService convertService;

    @Test
    public void testRun() throws IOException {
        Map<Integer, String> categoryFileMap = new HashMap<>();
        categoryFileMap.put(60002, "bjhd.csv");//bjhd-班级活动
        categoryFileMap.put(40002, "czb.csv");//czb-初中部
        categoryFileMap.put(30001, "djzc.csv");//djzc-党建之窗
        categoryFileMap.put(60003, "dyyj.csv");//dyyj-德育研究
        categoryFileMap.put(60008, "lyzt.csv");//lyzt-六一专题
        categoryFileMap.put(60001, "tdjs.csv");//tdjs-团队建设
        categoryFileMap.put(50004, "tsls.csv");//tsls-他山来石
        categoryFileMap.put(20004, "tzgg.csv");//tzgg-通知公告
        categoryFileMap.put(50002, "xbjy.csv");//xbjy-校本教研
        categoryFileMap.put(60005, "xsjz.csv");//xsjz-学生佳作
        categoryFileMap.put(40003, "xxb.csv");//xxb-小学部
        categoryFileMap.put(20003, "xxdt.csv");//xxdt-学校动态
        categoryFileMap.put(60004, "ygyz.csv");//ygyz-阳光驿站
        categoryFileMap.put(60006, "yxxz.csv");//yxxz-优秀学子
        categoryFileMap.put(20005, "zsxx.csv");//zsxx-招生信息
        String path = "D:\\syxx-sitemap\\";
        for (Integer categoryId : categoryFileMap.keySet()) {
            String csvFile = path + categoryFileMap.get(categoryId);
            String sqlFile = FileUtil.changeExtension(csvFile, "sql");
            this.writeToFile(csvFile, sqlFile, categoryId);
        }
    }
    
    private void writeToFile(String csvPath, String sqlPath, Integer categoryId) throws IOException {
        log.info("写入sql文件{}", sqlPath);
        List<Record> records = csvImportService.importFile(csvPath, categoryId);
        String sql = convertService.toInsertSql(records);
        try(FileWriter fw = new FileWriter(sqlPath, false)){
            fw.write(sql);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("写入完成");
    }

}
