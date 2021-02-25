package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.properties.UserProperties;

@RestController
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    private final UserProperties userProperties;

    
    public MainController(UserProperties userProperties) {
        this.userProperties = userProperties;
    }
    
    @GetMapping("/test")
    public String test() {
        logger.info(userProperties.toString());
        return "ok";
    }
    public class ObjectParam {
        private Integer id;
        private String name;
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "ObjectParam [id=" + id + ", name=" + name + "]";
        }
        
    }
    @PostMapping(value = "/param", produces = MediaType.APPLICATION_JSON_VALUE)
    public String param(Integer id1, ObjectParam param) {
        System.out.println(id1);
        System.out.println(param);
        return "ok";
    }

}
