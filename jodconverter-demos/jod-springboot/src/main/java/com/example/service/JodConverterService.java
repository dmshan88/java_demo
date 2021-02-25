package com.example.service;

import java.io.File;
import java.util.List;

public interface JodConverterService {
   
    /**转换*/
    boolean convert(File sourceFile, File targetFile);
    
    /**转换*/
    boolean convert(String sourceFilename, String targetFilename);
    
    /**转换为pdf格式*/
    boolean convert(String sourceFilename);
    
    /**转换为pdf格式*/
    boolean convert(List<String> sourceFilenameList);

}
