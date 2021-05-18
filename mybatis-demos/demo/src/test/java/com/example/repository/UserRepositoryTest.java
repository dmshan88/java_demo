package com.example.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.entity.User;
import com.example.model.enums.UserTypeEnum;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;
    
    @Test
    public void test() {
        User object = new User();
        object.setName("haha");
        object.setType(UserTypeEnum.Student);
        System.out.println(repository.insert(object));
        System.out.println(object);
        object.setName("haha2");
        System.out.println(repository.update(object));
        System.out.println(object);
        System.out.println(repository.findById(object.getId()));
        repository.findAll().forEach(obj -> System.out.println(obj));
        System.out.println(repository.delete(object.getId()));
        System.out.println("test run");
    }
}
