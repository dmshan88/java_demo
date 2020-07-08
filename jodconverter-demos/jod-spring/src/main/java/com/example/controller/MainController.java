package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.office.OfficeException;
import org.jodconverter.core.office.OfficeManager;
import org.jodconverter.core.office.OfficeUtils;
import org.jodconverter.local.JodConverter;
import org.jodconverter.local.office.LocalOfficeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.FileUploadService;
import com.example.service.JodConverterService;
import com.example.utils.FileUtil;

@RestController
public class MainController {

    @Value("${app.path}")
    private String path;
    
    @Value("${app.uploadPath}")
    private String uploadPath;

    @Autowired
//    private DocumentConverter documentConverter;
    private JodConverterService jodConverterService;
    
    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping(path = "/test")
    String test(String name) {
//        File inputFile = new File(path + name + ".doc");
//        File outputFile = new File(path + name + ".pdf");
//        try {
//            documentConverter.convert(inputFile).to(outputFile).execute();
//        } catch (OfficeException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        boolean ok = jodConverterService.convert(path + name);
        return ok ? "ok":"fail";
    }
    
    @PostMapping(path = "/upload")
    public String upload(MultipartFile[] file) {
        System.out.println("upload");
        List<String> fileList = new ArrayList<String>();
        List<String> originalFileList = new ArrayList<String>();
        try {
            fileUploadService.uploadMutil(file, uploadPath, originalFileList, fileList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "上传失败";
        }
        System.out.println(fileList);
        List<String> filePathList = new ArrayList<>();
        for (String filename : fileList) {
            filePathList.add(uploadPath + filename);
        }
        System.out.println(filePathList);
        boolean ok = jodConverterService.convert(filePathList);
        return ok ? "ok":"fail";
    }

}
