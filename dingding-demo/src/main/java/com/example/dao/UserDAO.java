package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.pojo.User;

@Mapper
public interface UserDAO {
    
    /**插入*/
    @Insert("INSERT INTO " + User.TABLE_NAME
            + " (" + User.FIELD_ID + ", " + User.FIELD_NAME + ") VALUES "
            + "(#{" + User.ATTRIBUTE_ID + "}, #{" + User.ATTRIBUTE_NAME + "})")
    Integer insert(User user);
    
    /**批量插入*/
    @Insert("<script>" +
            "INSERT INTO " + User.TABLE_NAME
            + " (" + User.FIELD_ID + ", " + User.FIELD_NAME + ") VALUES "
            + "<foreach collection='list' item='item' index='index' separator=','>"
            + "(#{item." + User.ATTRIBUTE_ID + "}, #{item." + User.ATTRIBUTE_NAME+ "})"
            + "</foreach>"
        + "</script>")
    Integer batchInsert(List<User> userList);
    
    /**更新*/
    @Insert("UPDATE " + User.TABLE_NAME + " SET "
            + User.FIELD_NAME + " = #{" +  User.ATTRIBUTE_NAME + "} "
            + "WHERE " + User.FIELD_ID + " = #{" +  User.ATTRIBUTE_ID + "}")
    Integer update(User user);
    
    /**根据id查找*/
    @Select("SELECT " + User.FIELD_ID + " as " + User.ATTRIBUTE_ID + ", "
            + User.FIELD_NAME + " as " + User.ATTRIBUTE_NAME
            + " FROM " + User.TABLE_NAME 
            + "WHERE " + User.FIELD_ID + " = #{" +  User.ATTRIBUTE_ID + "}")
    User findById(String id);
    
    /**查找所有*/
    @Select("SELECT " + User.FIELD_ID + " as " + User.ATTRIBUTE_ID + ", "
            + User.FIELD_NAME + " as " + User.ATTRIBUTE_NAME
            + " FROM " + User.TABLE_NAME)
    List<User> findAll();
    
    @Delete("DELETE FROM " + User.TABLE_NAME)
    void deleteAll();

}
