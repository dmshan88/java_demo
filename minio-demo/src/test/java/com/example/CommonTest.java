package com.example;

import org.junit.Test;

import com.example.util.FileStorageSystem;
import com.example.util.FileStorageUtil;
import com.example.util.FileStorageUtil.FileStorageException;

public class CommonTest {
    
    @Test
    public void test() throws FileStorageException {
    	String sourceFile = "d:/1.txt";
    	String targetFile = "abcd/1.txt";
//        FileStorageUtil fileStorageUtil = new FileStorageMinio("http://localhost:9000", "minioadmin", "minioadmin", "aaa");
    	FileStorageUtil fileStorageUtil = new FileStorageSystem("D:/tmp1");
        fileStorageUtil.upload(sourceFile, targetFile);
        System.out.println(fileStorageUtil.exist(targetFile));
        fileStorageUtil.download(targetFile, "d:/2.txt");
    }

}
