package com.example.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MainController {
    @Value("${resources_path}")
    private String staticPath;

    @CrossOrigin
    @PostMapping(path = "/test")
    void test(MultipartFile file) throws IllegalStateException, IOException {
        String newPath = staticPath + file.getOriginalFilename();
        System.out.println(file.getSize());
        System.out.println(newPath);
        file.transferTo(new File(newPath));
    }
}
