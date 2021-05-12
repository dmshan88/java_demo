package com.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {
    public static <T> T readJsonFromClassPath(String path, Type type) throws IOException {

        ClassPathResource resource = new ClassPathResource(path);
        if (resource.exists()) {
            return JSON.parseObject(resource.getInputStream(), StandardCharsets.UTF_8, type,
                    // 自动关闭流
                    Feature.AutoCloseSource,
                    // 允许注释
                    Feature.AllowComment,
                    // 允许单引号
                    Feature.AllowSingleQuotes,
                    // 使用 Big decimal
                    Feature.UseBigDecimal);
        } else {
            throw new IOException();
        }
    }
    
    public static <T> T readJsonFromFile(File file, Type type) throws IOException {
        if (file.exists()) {
        	FileInputStream fis = new FileInputStream(file);
            return JSON.parseObject(fis, StandardCharsets.UTF_8, type,
                    // 自动关闭流
                    Feature.AutoCloseSource,
                    // 允许注释
                    Feature.AllowComment,
                    // 允许单引号
                    Feature.AllowSingleQuotes,
                    // 使用 Big decimal
                    Feature.UseBigDecimal);
        } else {
            throw new IOException("resource read error");
        }
    }
    
    public static void saveJsonToFile(Object object, File file) throws IOException {
    	if (file.exists()) {    		
    		FileOutputStream fos = new FileOutputStream(file);
    		JSON.writeJSONString(fos, object, SerializerFeature.PrettyFormat);
    		fos.close();
    	} else {
            throw new IOException("resource write error");
        }
    }
}
