package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.BlogDAO;
import com.example.dao.DemoDataDAO;
import com.example.dao.UserDAO;
import com.example.pojo.Blog;
import com.example.pojo.DemoData;
import com.example.pojo.User;

@RestController
public class MainController {

    @Autowired
    private DemoDataDAO dao;
    
    @Autowired
    private BlogDAO blogDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    
    @GetMapping(path = "/test1") 
    void test1() {
        User user = new User();
        user.setName("aa");
        user = userDAO.save(user);
        Blog blog = new Blog();
        blog.setTitle("ttt");
        blog.setContent("ssss");
        blog.setUserId(user.getId());
//        blog.setUser(user);
        blogDAO.save(blog);
        System.out.println(blogDAO.findAll());
    }
    
    @GetMapping(path = "/test2") 
    List<Blog> test2(Integer id, String name) {
//        Blog blog = blogDAO.findByUser_Id(id);
        List<Blog> list = blogDAO.findByUser_Name(name);
        System.out.println(list);
        return list;
    }
    @Transactional
    @GetMapping(path = "/test")
    void test() {
        List<DemoData> list1 = new ArrayList<>();
        List<DemoData> list2 = new ArrayList<>();
        for (int i=0; i < 1000; i++) {
            Random rand = new Random(new Date().getTime());
            list1.add(new DemoData(Integer.valueOf(rand.nextInt()).toString(), rand.nextInt()));
            list2.add(new DemoData(Integer.valueOf(rand.nextInt()).toString(), rand.nextInt()));
        }
        Date date1 = new Date();
        for (DemoData data : list1) {
            dao.save(data);
        }
        Date date2 = new Date();
        dao.save(list2);
        Date date3 = new Date();
        System.out.println("date2 - date1: " + (date2.getTime() - date1.getTime()));
        System.out.println("date3 - date2: " + (date3.getTime() - date2.getTime()));
    }
}
