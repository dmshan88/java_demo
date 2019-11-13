package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.pojo.Department;

@Mapper
public interface DepartmentDAO {
    
    /**插入*/
    @Insert("INSERT INTO " + Department.TABLE_NAME
            + " (" + Department.FIELD_ID + ", " + Department.FIELD_NAME + ", " + Department.FIELD_PAREMENT + ") VALUES "
            + "(#{" + Department.ATTRIBUTE_ID + "}, #{" + Department.ATTRIBUTE_NAME + "}, #{" + Department.ATTRIBUTE_PARENT + "})")
    Integer insert(Department department);
    
    /**批量插入*/
    @Insert("<script>" +
            "INSERT INTO " + Department.TABLE_NAME
            + " (" + Department.FIELD_ID + ", " + Department.FIELD_NAME + ", " + Department.FIELD_PAREMENT + ") VALUES "
            + "<foreach collection='list' item='item' index='index' separator=','>"
            + "(#{item." + Department.ATTRIBUTE_ID + "}, #{item." + Department.ATTRIBUTE_NAME + "}, #{item." + Department.ATTRIBUTE_PARENT + "})"
            + "</foreach>"
        + "</script>")
    Integer batchInsert(List<Department> list);
    
    /**更新*/
    @Insert("UPDATE " + Department.TABLE_NAME + " SET "
            + Department.FIELD_NAME + " = #{" +  Department.ATTRIBUTE_NAME + "}, "
            + Department.FIELD_PAREMENT + " = #{" +  Department.ATTRIBUTE_PARENT + "} "
            + "WHERE " + Department.FIELD_ID + " = #{" +  Department.ATTRIBUTE_ID + "}")
    Integer update(Department user);
    
    /**根据id查找*/
    @Select("SELECT " + Department.FIELD_ID + " as " + Department.ATTRIBUTE_ID + ", "
            + Department.FIELD_NAME + " as " + Department.ATTRIBUTE_NAME + ", "
            + Department.FIELD_PAREMENT + " as " + Department.ATTRIBUTE_PARENT
            + " FROM " + Department.TABLE_NAME 
            + "WHERE " + Department.FIELD_ID + " = #{" +  Department.ATTRIBUTE_ID + "}")
    Department findById(String id);
    
    /**查找所有*/
    @Select("SELECT " + Department.FIELD_ID + " as " + Department.ATTRIBUTE_ID + ", "
            + Department.FIELD_NAME + " as " + Department.ATTRIBUTE_NAME + ", "
            + Department.FIELD_PAREMENT + " as " + Department.ATTRIBUTE_PARENT
            + " FROM " + Department.TABLE_NAME)
    List<Department> findAll();
    
    @Delete("DELETE FROM " + Department.TABLE_NAME)
    void deleteAll();
}
