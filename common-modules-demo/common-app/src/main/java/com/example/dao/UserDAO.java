package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.common.dao.CustomDAO;
import com.example.pojo.User;

@Mapper
public interface UserDAO extends CustomDAO<User> {

}
