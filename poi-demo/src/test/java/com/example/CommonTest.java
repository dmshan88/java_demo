package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonTest {
    private static final List<UserInfo> usrInfoList = new ArrayList<>();
    {
        UserInfo info = new UserInfo();
        info.setId(5);
        info.setUnitname("滨州一中");
        info.setName("张三");
        usrInfoList.add(info);
        info = new UserInfo();
        info.setId(8);
        info.setUnitname("实验学校");
        info.setName("李四");
        usrInfoList.add(info);
        info = new UserInfo();
        info.setId(9);
        info.setUnitname("学院副小");
        info.setName("王五");
        usrInfoList.add(info);
    }
    
    @Test
    public void test() throws IOException {
        InputStream inp = new FileInputStream("/home/shan/1.xls");
        Workbook workbook = new HSSFWorkbook(inp);
        Sheet sheet = workbook.getSheet("教工信息");
//        System.out.println();
        int startRow = 8;
        int endRow = sheet.getLastRowNum();
        for (int i = endRow; i > startRow; i--) {
            Row row = sheet.getRow(i);
            if (row == null) {
                System.out.println("row is null");
                continue;
            }
            System.out.println(row.getRowNum());
            sheet.removeRow(row);
        }
        for (int i = 0; i< usrInfoList.size(); i++) {
            UserInfo userinfo = usrInfoList.get(i);
            int rowNumber = startRow + i;
            System.out.println(startRow);
            System.out.println(userinfo);
            Row row = sheet.createRow(rowNumber);
            Cell cell = row.createCell(0);
            cell.setCellValue(userinfo.getId());
            System.out.println(cell.getNumericCellValue());
            cell = row.createCell(1);
            cell.setCellValue(userinfo.getUnitname());
            System.out.println(cell.getStringCellValue());
            cell = row.createCell(2);
            cell.setCellValue(userinfo.getName());
            System.out.println(cell.getStringCellValue());
        }
        sheet.setActiveCell(new CellAddress(1, 1));;
        OutputStream outStream = new FileOutputStream("/home/shan/2.xls");
        workbook.write(outStream);
        outStream.close();
        workbook.close();
    }

    class UserInfo {
        private Integer id;
        private String unitname;
        private String name;
        
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getUnitname() {
            return unitname;
        }
        public void setUnitname(String unitname) {
            this.unitname = unitname;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "UserInfo [id=" + id + ", unitname=" + unitname + ", name=" + name + "]";
        }
    }
}
