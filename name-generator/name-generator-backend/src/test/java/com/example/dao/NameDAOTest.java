package com.example.dao;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.example.model.pojo.Name;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NameDAOTest {
    
    @Autowired
    private NameDAO nameDAO;
    
    static Random random = new Random();
    static {
        random.setSeed(System.currentTimeMillis());
        
    }

    @Test
    public void testRun() {
        Name object = randomObject(null);
        System.out.println(object);

        Assert.isTrue(nameDAO.insert(object) > 0, "插入失败");
        System.out.println("插入成功");
        
        Assert.notNull(nameDAO.findById(object.getId()), "未找到记录");
        System.out.println("查询成功");
        
        Assert.notNull(nameDAO.findByFirstAndSecond(object.getFirst(), object.getSecond()), "未找到记录");
        System.out.println("查询成功");
//        
//        Assert.notNull(WordDAO.findDtoById(object.getId()), "未找到记录");
//        System.out.println("查询成功");
//        
//        Assert.notEmpty(WordDAO.findInId(Arrays.asList(object.getId()).toArray(new String[0])), "未找到记录");
//        System.out.println("查询成功");
//        
//        Assert.notEmpty(WordDAO.queryIds(null), "未找到记录");
//        System.out.println("查询成功");
//        
        Assert.notEmpty(nameDAO.findByLevel(object.getLevel()), "未找到记录");
        System.out.println("查询成功");

        Assert.isTrue(nameDAO.update(randomObject(object)) > 0, "更新失败");
        System.out.println("更新成功");

        Assert.isTrue(nameDAO.delete(object.getId()) > 0, "删除失败");
        System.out.println("删除成功");
//        
//        Word object1 = randomObject(null);
//        Word object2 = randomObject(null);
//        Assert.isTrue(WordDAO.batchInsert(Arrays.asList(object1, object2)) > 0, "批量插入失败");
//        System.out.println("批量插入成功");
//        
//        Assert.isTrue(WordDAO.batchUpdate(Arrays.asList(randomObject(object1), randomObject(object2))) > 0, "批量更新失败");
//        System.out.println("批量更新成功");
//        
//        Assert.isTrue(WordDAO.batchDelete(Arrays.asList(object1.getId(), object2.getId()).toArray(new String[0])) > 0, "批量删除失败");
//        System.out.println("批量删除成功");
//        
    }
    
    /**随机对象*/
    public static Name randomObject(Name object) {
        Name object1 = new Name();
        if (object == null) {
            object1.setId(random.nextLong());
        } else {
            object1.setId(object.getId());
        }
        object1.setFirst(random.nextInt(10) + 10);
        object1.setSecond(random.nextInt(4) + 10);
        object1.setLevel(random.nextInt(4));
        return object1;
    }

}
