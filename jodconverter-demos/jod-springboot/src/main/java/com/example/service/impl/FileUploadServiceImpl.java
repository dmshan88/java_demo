package com.example.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.FileUploadService;
import com.example.utils.FileUtil;
import com.example.utils.HttpServletUtil;
import com.example.utils.RandomUtil;


@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public void uploadMutil(MultipartFile[] multipartFiles, String path, List<String> originFilenames,
            List<String> newFilenames) throws IOException {
        if (multipartFiles != null && originFilenames != null && newFilenames != null) {
            for (MultipartFile multipartFile : multipartFiles) {
                originFilenames.add(multipartFile.getOriginalFilename());
                String ext = FileUtil.getExtension(multipartFile.getOriginalFilename()).toLowerCase();
                String fileName = RandomUtil.randomFileName() + "." + ext;
                newFilenames.add(fileName);
            }
            HttpServletUtil.uploadMutiple(multipartFiles, path, newFilenames.toArray(new String[0]));
        }
    }

}
