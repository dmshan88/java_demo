package com.example.pojo.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.dingtalk.api.response.OapiUserSimplelistResponse.Userlist;
import com.example.pojo.User;

@Mapper
public interface UserConverter {
    
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);
    
    @Mapping(target = User.ATTRIBUTE_ID, source = "userid")
    User convert(Userlist po);
    List<User> convert(List<Userlist> list);
}