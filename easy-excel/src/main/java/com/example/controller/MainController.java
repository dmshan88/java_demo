package com.example.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.example.excel.DemoDataListener;
import com.example.pojo.DemoData;
import com.example.pojo.converter.DemoDataConverter;
import com.example.pojo.input.DemoDataInput;


@RestController
public class MainController {

    @CrossOrigin
    @GetMapping(path = "/test")
    void test(String token, HttpServletResponse response) throws IOException {
//        System.out.println("input");
//        if (token ==null || !token.equals("aaa")) {
//            response.getWriter().print("wrong token");
//            return;
//        }
//        List<DemoData> list = new ArrayList<DemoData>();
//        for (int i = 0; i < 10; i++) {
//            DemoData data = new DemoData(i+ "", "name" + i, i * 2 + "");
//            list.add(data);
//        }
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
//        String fileName = URLEncoder.encode("测试", "UTF-8");
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
//        EasyExcel.write(response.getOutputStream(), DemoData.class).sheet("模板").doWrite(list);
    }
    @PostMapping("/upload")
    @ResponseBody
    public List<DemoDataInput> upload(MultipartFile file) throws IOException {
        DemoDataListener listener = new DemoDataListener();
        EasyExcel.read(file.getInputStream(), DemoData.class, listener).headRowNumber(4).sheet().doRead();
        System.out.println(listener.getList());
        return DemoDataConverter.INSTANCE.po2vo(listener.getList());
    }
}
