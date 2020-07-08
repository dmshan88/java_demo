package com.example.util;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpServletUtilTest {
    
    
    @Test
    public void test() {
//        HttpServletUtil.upload(file, path, filename);
//        Assert.notNull(assessmentScoreDao, "not null");
        String path = "aa";
        String filename = "bb";
        System.out.println(new File(path, filename).getAbsolutePath());
        path = "aa/";
        filename = "bb";
        System.out.println(new File(path, filename).getAbsolutePath());
        System.out.println(new File(path, filename).getAbsolutePath());
        path = "";
        filename = "bb";
        System.out.println(new File(path, filename).getAbsolutePath());
        path = "/";
        filename = "bb";
        System.out.println(new File(path, filename).getAbsolutePath());
        path = "/aa";
        filename = "/cc/bb";
        System.out.println(new File(path, filename).getAbsolutePath());
        path = null;
        filename = "/cc/bb";
        System.out.println(new File(path, filename).getAbsolutePath());
        path = "";
        filename = "bb";
        System.out.println(new File(path, filename).getAbsolutePath());

    }
    
    @Test
    public void test1() {
        List<String> list = null;
//        for 
//        for (String str : list) {
//            System.out.println(str);
//        }
        System.out.println(list);
    }

}