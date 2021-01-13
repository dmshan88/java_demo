package com.example.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.example.pojo.QueryTable;
import com.example.pojo.form.QueryTableQueryForm;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryTableDAOTest {
    
    @Autowired
    private QueryTableDAO queryTableDao;
    
    static Random random = new Random();
    
    static {
        random.setSeed(System.currentTimeMillis());
    }
    
    @Test
    public void testRun() {
        QueryTable object = randomObject(null);
        System.out.println(object);
        Assert.isTrue(queryTableDao.insert(object) > 0, "插入失败");
        System.out.println("插入成功");

        Assert.notNull(queryTableDao.findById(object.getId()), "未找到记录");
        System.out.println("查询成功");
        
        Assert.notEmpty(queryTableDao.findAll(), "未找到记录");
        System.out.println("查询成功");
        
        QueryTableQueryForm form = new QueryTableQueryForm();
        form.setQuery1(5);
        List<QueryTable> tables = queryTableDao.query(form);
        System.out.println(tables.size());
        Assert.notEmpty(tables, "未找到记录");
        System.out.println("查询成功");

        Assert.isTrue(queryTableDao.update(randomObject(object)) > 0, "更新失败");
        System.out.println("更新成功");

        Assert.isTrue(queryTableDao.delete(object.getId()) > 0, "删除失败");
        System.out.println("删除成功");
    }
    
    @Test
    public void testRun1() {
        for (int i=0; i< 1000; i++) {
            QueryTable object = randomObject(null);
            queryTableDao.insert(object);
        }
    }
    
    @Test
    public void testRun2() {
        Map<String, Boolean> groupMap = new HashMap<String, Boolean>();
        groupMap.put("group1", true);
        groupMap.put("group2", false);
        groupMap.put("group3", false);
        List<Object> list = queryTableDao.group(groupMap);
        for (Object object : list) {
            System.out.println(object);
        }
    }
    
    /**随机对象*/
    public static QueryTable randomObject(QueryTable object) {
        QueryTable object1 = new QueryTable();
        if (object == null) {
            object1.setId(random.nextInt());
        } else {
            object1.setId(object.getId());
        }
        object1.setQuery1(random.nextInt(10));
        object1.setQuery2(String.valueOf(random.nextLong()));
        object1.setQuery3(new Date(System.currentTimeMillis() + random.nextInt()));
        object1.setGroup1(random.nextInt(10));
        object1.setGroup2(String.valueOf(random.nextLong()));
        object1.setGroup3(new Date(System.currentTimeMillis() + random.nextInt()));
        return object1;
    }
}
