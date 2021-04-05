package com.example.controller;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.util.FileStorageSystem;
import com.example.util.FileStorageUtil;

@RestController
public class MainController {
	
	private final String uploadPath = "d:\\upload";
	
	private FileStorageUtil fileStorageUtil =  new FileStorageSystem(uploadPath);

	@PostMapping("upload")
	public void upload(MultipartFile[] files, String title) throws Exception {
		for (MultipartFile file : files) {
//			File newFile = new File(uploadPath, file.getOriginalFilename());
//			file.transferTo(newFile);
			fileStorageUtil.upload(file.getInputStream(), "/aa/"+file.getOriginalFilename());
		}
		
	}
	@GetMapping("download")
	public void download(HttpServletResponse response, String fileName) throws Exception {
		
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName); 
//        response.setContentType("multipart/form-data");  
//		response.setContentType(MediaType.TEXT_PLAIN_VALUE);
      //读取要下载的文件，保存到文件输入流
       InputStream in =  fileStorageUtil.download(fileName);
     //创建输出流
       OutputStream out = response.getOutputStream();
       IOUtils.copy(in, out);
       //创建缓冲区
//       byte buffer[] = new byte[8192];
//       int len = 0;
//     //循环将输入流中的内容读取到缓冲区当中
//        while ((len = in.read(buffer, 0, 8192)) != -1) {
//        	//输出缓冲区的内容到浏览器，实现文件下载
//        	out.write(buffer, 0, len);
//        	}
        //关闭输出流
//        out.close();
      //关闭文件输入流
        in.close();
	}
}
