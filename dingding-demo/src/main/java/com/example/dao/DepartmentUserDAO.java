package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.pojo.DepartmentUser;

@Mapper
public interface DepartmentUserDAO {
    
    /**插入*/
    @Insert("INSERT INTO " + DepartmentUser.TABLE_NAME
            + " (" + DepartmentUser.FIELD_DEPARTMENT + ", " + DepartmentUser.FIELD_USER + ") VALUES "
            + "(#{" + DepartmentUser.ATTRIBUTE_DEPARTMENT + "}, #{" + DepartmentUser.ATTRIBUTE_USER + "})")
    Integer insert(DepartmentUser department);
    
    /**批量插入*/
    @Insert("<script>" +
            "INSERT INTO " + DepartmentUser.TABLE_NAME
            + " (" + DepartmentUser.FIELD_DEPARTMENT + ", " + DepartmentUser.FIELD_USER +  ") VALUES "
            + "<foreach collection='list' item='item' index='index' separator=','>"
            + "(#{item." + DepartmentUser.ATTRIBUTE_DEPARTMENT + "}, #{item." + DepartmentUser.ATTRIBUTE_USER + "})"
            + "</foreach>"
        + "</script>")
    Integer batchInsert(List<DepartmentUser> list);
    
    /**更新*/
    @Insert("UPDATE " + DepartmentUser.TABLE_NAME + " SET "
            + DepartmentUser.FIELD_DEPARTMENT + " = #{" +  DepartmentUser.ATTRIBUTE_DEPARTMENT + "}, "
            + "WHERE " + DepartmentUser.FIELD_USER + " = #{" +  DepartmentUser.ATTRIBUTE_USER + "}")
    Integer update(DepartmentUser user);
        
    /**查找所有*/
    @Select("SELECT " + DepartmentUser.FIELD_DEPARTMENT + " as " + DepartmentUser.ATTRIBUTE_DEPARTMENT + ", "
            + DepartmentUser.FIELD_USER + " as " + DepartmentUser.ATTRIBUTE_USER
            + " FROM " + DepartmentUser.TABLE_NAME)
    List<DepartmentUser> findAll();
    
    
    @Delete("DELETE FROM " + DepartmentUser.TABLE_NAME)
    void deleteAll();
}
