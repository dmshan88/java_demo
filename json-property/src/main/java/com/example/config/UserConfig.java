package com.example.config;

import java.io.File;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.model.User;
import com.example.utils.JsonUtils;

@Configuration
public class UserConfig {

	public static final String path = "json/config.json";

	@Bean
	public User user() throws IOException {
		User user = null;
		File configFile = new File(UserConfig.path);
		if (!configFile.exists()) {//初始化配置文件

			if(!configFile.getParentFile().exists()) {//创建父级目录
	            if(!configFile.getParentFile().mkdirs()) {  
	                System.out.println("create path fail"); 
	            }  
	        }
			configFile.createNewFile();
			user = new User();
			JsonUtils.saveJsonToFile(user, configFile);
		} else {
			user = JsonUtils.readJsonFromFile(configFile, User.class);//从json文件中读取配置
		}
		return user;
	}
}
