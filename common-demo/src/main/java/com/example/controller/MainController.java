package com.example.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.CustomException;
import com.example.common.CustomPageRequestUtil;
import com.example.common.CustomPageResponse;
import com.example.common.CustomResponse;
import com.example.common.ErrorCode;
import com.example.config.properties.AppProperties;
import com.example.dao.UserDAO;
import com.example.dao.UserDAO2;
import com.example.pojo.User;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
public class MainController {

    @Autowired
    private AppProperties appProperties;
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private UserDAO2 userDAO2;
    
    @GetMapping(path = "/test")
    void test() {
        System.out.println("test1");
        throw new CustomException(ErrorCode.ERROR, "aa");
    }
    
    @Data
    public class Demo {
        private String d1;
        private String d2;
    }
//    @Transactional()
    @GetMapping(path = "/user_list")
    public CustomPageResponse<User> listAllUser(String name, 
            @RequestParam(value = CustomPageRequestUtil.REQUEST_PARAM_PAGE, required = false) Integer page,
            @RequestParam(value = CustomPageRequestUtil.REQUEST_PARAM_SIZE, required = false) Integer size) {
//        User user1 = new User();
//        String name = RandomUtil.randomString(10);
//        user1.setName(name);
//        try {
//            userDAO2.insert(user1);
//        } catch (DataAccessException e) {
//            throw new CustomException(ErrorCode.ERROR, e);
//            System.out.println("DataAccessException" + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Exception" + e.getMessage());
//        }
//            User user2 = new User();
//            user2.setName(name);
//            userDAO2.insert(user2);
//        System.out.println(demo);
//        System.out.println(userDAO.findByName(CustomPageRequestUtil.build(page, size).toPageable(), name).getContent());
        Page<User> users1 = userDAO.findByName(CustomPageRequestUtil.build(page, size).toPageable(), name);
        IPage<User> users = userDAO2.selectPageVo(CustomPageRequestUtil.build(page, size).toIPage(), name);
        System.out.println(CustomPageResponse.ok(users1));
        System.out.println(CustomPageResponse.ok(users));
        return CustomPageResponse.ok(users1);
    }

}
