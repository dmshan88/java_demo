package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pojo.QueryTable;
import com.example.pojo.form.QueryTableQueryForm;

@Mapper
public interface QueryTableDAO {
    
    List<QueryTable> query(QueryTableQueryForm query);
    
    List<Object> group(@Param("map") Map<String, Boolean> map);
    
    List<QueryTable> findAll();
    
    QueryTable findById(@Param("id") Integer id);
    
    int insert(@Param("item") QueryTable item);

    int update(@Param("item") QueryTable item);
    
    int delete(@Param("id") Integer id);

}
