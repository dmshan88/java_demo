package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class HttpServletUtil {

    /**上传 
     * @param multipartFile 文件
     * @param path 上传路径
     * @param filename 新文件名
     * */
    public static void upload(MultipartFile multipartFile, String path, String filename) throws IOException {
        if (multipartFile == null || path == null || path.isEmpty()) {
            throw new RuntimeException("文件路径参数错误");
        }
        String originFilename = multipartFile.getOriginalFilename();
        if (filename == null || filename.isEmpty()) {
            filename = originFilename;//无文件名使用上传文件名
        } else if (FileUtil.getExtension(filename).isEmpty()) {//无扩展名使用上传文件的扩展名
            String ext = FileUtil.getExtension(originFilename);
            if (!ext.isEmpty()) {
                filename += "." + ext;
            }
        }
        try {
            multipartFile.transferTo(new File(path, filename));
        } catch (IllegalStateException e) {
            throw new RuntimeException("程序错误:HttpServletUtil");
        }
    }
    
    /**多文件上传 
     * @param multipartFiles 文件数组
     * @param path 上传路径
     * @param filenames 新文件名数组
     * */
    public static void uploadMutiple(MultipartFile[] multipartFiles, String path, String[] filenames) throws IOException {
        if (multipartFiles == null || multipartFiles.length == 0 || multipartFiles.length != filenames.length) {
            throw new RuntimeException("文件路径参数错误");
        }
        for (int i = 0; i< multipartFiles.length ; i++) {
            upload(multipartFiles[i], path, filenames[i]);
        }
    }
    
    /**下载*/
    public static void download(HttpServletResponse response, String path, String filename) throws IOException {
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        outputFileStream(path, response);

    }
    
    /**图片预览*/
    public static void imagePreview(HttpServletResponse response, String path) throws IOException {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        response.setContentType(MediaType.IMAGE_PNG_VALUE);
//        response.setContentType(MediaType.IMAGE_GIF_VALUE);
        outputFileStream(path, response);
    }
    
    private static void outputFileStream(String path, HttpServletResponse response) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        InputStream in = new FileInputStream(path);
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }
}
