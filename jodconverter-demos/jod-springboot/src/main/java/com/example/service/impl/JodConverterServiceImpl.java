package com.example.service.impl;

import java.io.File;
import java.util.List;

import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.office.OfficeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service.JodConverterService;
import com.example.utils.FileUtil;

@Service
public class JodConverterServiceImpl implements JodConverterService {
    
    @Autowired
    private DocumentConverter documentConverter;

    @Override
    public boolean convert(File sourceFile, File targetFile) {
        try {
            documentConverter.convert(sourceFile).to(targetFile).execute();
        } catch (OfficeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
        
    }

    @Override
    public boolean convert(String sourceFilename, String targetFilename) {
        File sourceFile = new File(sourceFilename);
        File targetFile = new File(targetFilename);
        return this.convert(sourceFile, targetFile);
        
    }

    @Override
    public boolean convert(String sourceFilename) {
        String targetFilename = FileUtil.getFullPath(sourceFilename) + FileUtil.getBaseName(sourceFilename) + ".pdf";
        return this.convert(sourceFilename, targetFilename);
    }


    @Override
    public boolean convert(List<String> sourceFilenameList) {
        if (sourceFilenameList == null) {
            return false;
        }
        for (String sourceFilename : sourceFilenameList) {
            if (!this.convert(sourceFilename)) {
                return false;
            }
        }
        return true;
        
    }

}
