package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.CkEditorUploadImageResponse;
import com.example.model.UploadForm;

@RestController
public class MainController {
	
	private final static String uploadPath = "d:/upload_file";
	
	@PostMapping(path = "upload")
	public void upload(@Validated UploadForm form) throws IOException {
		System.out.println("in upload");
		System.out.println(form.getId());
		System.out.println(form.getName());		
		for (MultipartFile file : form.getFiles()) {
			if (file.isEmpty()) {
				continue;
			}
			System.out.println("file");
			InputStream inptStream = file.getInputStream();
			File outFile = new File(uploadPath, file.getOriginalFilename());
			FileUtils.copyInputStreamToFile(inptStream, outFile);
			inptStream.close();
			System.out.println("transpart file" + file.getOriginalFilename());
		}
		System.out.println("finish upload");
	}
	
	@PostMapping(path = "ckupload")
    public CkEditorUploadImageResponse ckupload(@RequestParam("upload") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return CkEditorUploadImageResponse.error("empty");
        }
        InputStream inptStream = file.getInputStream();
        File outFile = new File(uploadPath, file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(inptStream, outFile);
        inptStream.close();
        String uploadUrl = "http://localhost:8080/image?path=" + file.getOriginalFilename();
        return CkEditorUploadImageResponse.success(file.getOriginalFilename(), uploadUrl);
    }
	
	@GetMapping(path = "image")
	public void image(@RequestParam String path, HttpServletResponse response) throws IOException {
	    OutputStream outputStream = response.getOutputStream();
	    File outFile = new File(uploadPath, path);
	    FileUtils.copyFile(outFile, outputStream);
	}
}
