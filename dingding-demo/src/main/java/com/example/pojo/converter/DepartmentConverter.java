package com.example.pojo.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.pojo.Department;

@Mapper
public interface DepartmentConverter {
    
    DepartmentConverter INSTANCE = Mappers.getMapper(DepartmentConverter.class);
    
    Department convert(com.dingtalk.api.response.OapiDepartmentListResponse.Department po);
    List<Department> convert(List<com.dingtalk.api.response.OapiDepartmentListResponse.Department> list);
}