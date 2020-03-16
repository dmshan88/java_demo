package com.example.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.MyResponse;
import com.example.common.core.CustomException;
import com.example.common.core.CustomPageRequestUtil;
import com.example.common.core.CustomPageResponse;
import com.example.common.core.CustomResponse;
import com.example.common.core.ErrorCode;
import com.example.common.jwt.JwtUtil;
import com.example.dao.UserDAO;
import com.example.pojo.User;

@RestController
public class MainController {
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    JwtUtil jwtUtil;
    
    @GetMapping("/test")
    public MyResponse<List<User>> test() {
//        List<User> userList = userDAO.findAll();
        List<User> userList = userDAO.selectList(null);
        return MyResponse.ok(userList);
    }
    
    @RequiresPermissions(value = {"user1"})
//    @Secured({"ROLE_user"})
    @GetMapping("/test0")
    public CustomPageResponse<User> test0(@RequestParam(value = CustomPageRequestUtil.REQUEST_PARAM_PAGE, required = false) Integer page,
            @RequestParam(value = CustomPageRequestUtil.REQUEST_PARAM_SIZE, required = false) Integer size) {
//        Page<User> userList = userDAO.findAll(CustomPageRequestUtil.build(page, size).toPageable());
        IPage<User> userList = userDAO.selectPage(CustomPageRequestUtil.build(page, size).toIPage(), null);
        return CustomPageResponse.ok(userList);
    }
    
    @GetMapping("/test1")
    public CustomResponse<Object> test1() {
//        return MyResponse.error(ErrorCode.AUTH_ERROR);
//        throw new CustomException(ErrorCode.AUTH_ERROR);
        User user = new User();
        user.setName("aa");
//        userDAO.save(user);
        userDAO.insert(user);
        return CustomResponse.ok(null);
//        throw new RuntimeException();
    }
    
    @GetMapping("/test2")
    public CustomResponse<Object> test2() {
//        return MyResponse.error(ErrorCode.AUTH_ERROR);
        throw new CustomException(ErrorCode.AUTH_ERROR);

    }
    
//    @GetMapping(path = "/login")
//    public CustomResponse<String> login(@RequestParam String username, @RequestParam String password) {
//        if (username.equals("user1") && password.equals("pwd1")) {
//            return CustomResponse.ok(jwtUtil.createJwt(username, 3600 * 1000L));
//        }
//        return CustomResponse.error(ErrorCode.AUTH_ERROR);
//    }
}
