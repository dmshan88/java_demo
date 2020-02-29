package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.CustomException;
import com.example.common.CustomPageRequestUtil;
import com.example.common.CustomPageResponse;
import com.example.common.CustomResponse;
import com.example.common.ErrorCode;
import com.example.compoment.JwtUtil;
import com.example.config.properties.AppProperties;
import com.example.dao.UserDAO;
import com.example.dao.UserDAO2;
import com.example.pojo.User;

import lombok.Data;

@RestController
public class MainController {

    @Autowired
    private AppProperties appProperties;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private UserDAO2 userDAO2;
    
    @GetMapping(path = "/test")
    public void test() {
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
    

    
//    @Secured({"ROLE_user"})
    @GetMapping(path = "/test1")
    public String test(@RequestParam String token) {
        System.out.println(token);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        System.out.println(auth.getName()); 
        System.out.println(auth.getPrincipal());
        System.out.println(auth.getAuthorities()); 
        return "ok";
    }
    
    @GetMapping(path = "/login")
    public CustomResponse<String> login(@RequestParam String username, @RequestParam String password) {
        if (username.equals("user1") && password.equals("pwd1")) {
            return CustomResponse.ok(jwtUtil.createJwt(username, 3600 * 1000L));
        }
        return CustomResponse.error(ErrorCode.AUTH_ERROR);
    }

}
