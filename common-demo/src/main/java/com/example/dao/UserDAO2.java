package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.pojo.User;

@Mapper
public interface UserDAO2 extends BaseMapper<User> {
    
    IPage<User> selectPageVo(IPage<?> page, @Param(value = "name") String name);
}
