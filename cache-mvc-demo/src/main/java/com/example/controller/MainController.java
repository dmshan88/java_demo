package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@Autowired 
	private CacheManager cacheManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(String key, Model model) {
		Cache cache = cacheManager.getCache("book");
		if (key == null) {
			key = cache.get("key", String.class);
		} else {			
			cache.put("key", key);
		}
		System.out.println(key);
		
		return "index";
	}
}
