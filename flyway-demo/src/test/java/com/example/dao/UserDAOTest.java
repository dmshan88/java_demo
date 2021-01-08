package com.example.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDAOTest {
    
    @Autowired
    private UserDAO userDAO;
    
    @Test
    public void testRun() {
        List<User> userList = userDAO.findAll();
        userList.forEach(obj -> System.out.println(obj));
        
        User user = new User();
        userDAO.insert(user);
    }

}
