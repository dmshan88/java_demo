package com.example.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.util.HttpServletUtil;

import lombok.Data;

@RestController
public class MainController {

    @Autowired
    private ServletContext servletContext;

    @Value("${resources_path}")
    private String staticPath;
    
    @Data
    class Response {
        private Integer code;
        private String message;
    }
    
    @CrossOrigin
    @PostMapping(path = "/test0")
    Response test0(Integer id, String name) {
    
        Response response = new Response();
        response.setCode(1);
        response.setMessage("id:" + id + ";name:" + name);
        System.out.println(response);
        return response;
    }

    @CrossOrigin
    @PostMapping(path = "/test")
    void test(MultipartFile file) {
        String path = servletContext.getRealPath("/upload/");
        try {
            HttpServletUtil.upload(file, path, "aa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin
    @PostMapping(path = "/test1")
    void test1(MultipartFile[] files) {
        String path = servletContext.getRealPath("/upload/");
        System.out.println(path);
        int length = files.length;
        String[] filenames = new String[length];
        for (int i = 0; i < length; i++) {
            filenames[i] = "file" + System.currentTimeMillis() + i;
        }
        try {
            HttpServletUtil.uploadMutiple(files, path, filenames);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(path = "/test2")
    void test2(HttpServletResponse response) {
        String path = servletContext.getRealPath("/upload/");
        try {
            HttpServletUtil.download(response, path + "aa.png", "bb.png");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @GetMapping(value = "/get", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] getImage(HttpServletResponse response) throws IOException {
        String path = servletContext.getRealPath("/upload/");
        File file = new File(path + "aa.png");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    @GetMapping(value = "/get1")
    @ResponseBody
    public void getImage1(HttpServletResponse response) throws IOException {
        String path = servletContext.getRealPath("/upload/");
        File file = new File(path + "aa.png");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];

        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        // 关闭响应输出流
        out.close();

    }
    @GetMapping("get2")
    @ResponseBody
    public void showImage(HttpServletResponse response){
        String path = servletContext.getRealPath("/upload/");
        File file = new File(path + "aa.png");
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
    
    @GetMapping("get3")
    @ResponseBody
    public void showImage3(HttpServletResponse response){
        String path = servletContext.getRealPath("/upload/");
        try {
            HttpServletUtil.imagePreview(response, path + "1.bmp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        File file = new File(path + "aa.png");
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        try {
//            ServletOutputStream out;
//            out = response.getOutputStream();
//            InputStream in = new FileInputStream(file);
//            int len = 0;
//            byte[] buffer = new byte[1024];
//            while ((len = in.read(buffer)) > 0) {
//                out.write(buffer, 0, len);
//            }
//            in.close();
//            out.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
////            e.printStackTrace();
//        }
    }
}
