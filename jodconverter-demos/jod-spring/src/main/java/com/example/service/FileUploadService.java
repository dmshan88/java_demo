package com.example.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    
    /**上传文件*/
    void uploadMutil(MultipartFile[] multipartFiles, String path, 
            List<String> originFilenames, List<String> newFilenames) throws IOException;

}
