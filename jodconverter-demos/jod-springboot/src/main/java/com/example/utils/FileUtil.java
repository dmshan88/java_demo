package com.example.utils;

import org.apache.commons.io.FilenameUtils;

public class FileUtil {

    /** 获取文件扩展名 */
    public static String getExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    /** 获取基础文件名 */
    public static String getBaseName(String filename) {
        return FilenameUtils.getBaseName(filename);
    }
    
    public static String getFullPath(String filename) {
        return FilenameUtils.getFullPath(filename);
    }

}
