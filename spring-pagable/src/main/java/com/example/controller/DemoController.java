package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.BookDAO;
import com.example.pojo.Book;

@RestController
public class DemoController {

    public static final String PARAM_PAGE_NAME = "page";
    public static final String PARAM_SIZE_NAME = "size";
    
    @Autowired
    private BookDAO dao;
    
    @GetMapping(path = "/test")
    List<Book> test(@RequestParam(name = PARAM_PAGE_NAME) Integer page, 
            @RequestParam(name = PARAM_SIZE_NAME) Integer size, Pageable pageable) {
        System.out.println("test:");
        System.out.println(pageable);
        System.out.println("page" + page);
        System.out.println("size" + size);
        Page<Book> pages = dao.findByNameNotNull(pageable);
        System.out.println(pages.getTotalElements());
        System.out.println(pages.getSize());
        return pages.getContent();
       
    }
}
