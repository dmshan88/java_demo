package com.example.task;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.util.FileUtil;
import com.example.util.PdfConverterUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ConverPdfTask implements ApplicationRunner {
    
    @Autowired
    private PdfConverterUtil pdfConverterUtil;
    
    @Value("${app.convert-path}")
    private File convertPath; 
    
    private final List<String> docTypeList = Arrays.asList("doc", "docx", "xls", "xlsx", "ppt", "pptx");
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!convertPath.isDirectory()) {
            log.error("转换路径错误:{}", convertPath);
        }

        File[] docFiles = convertPath.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String suffix = FileUtil.getExtension(pathname.getName()).toLowerCase();
                return docTypeList.contains(suffix);
            }
        });
        
//        List<String> toConverFiles = new ArrayList<>();
        for (File file : docFiles) {
            String pdfFileName = FileUtil.changeExtension(file.getAbsolutePath(), "pdf");
            File pdfFile = new File(pdfFileName);
            if (pdfFile.exists() && pdfFile.length() > 5000) {
            } else {
                pdfConverterUtil.convert(file, pdfFile);
            }
        }

    }
    


}
