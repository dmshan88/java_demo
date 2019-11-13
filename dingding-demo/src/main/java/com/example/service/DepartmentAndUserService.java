package com.example.service;

import java.util.List;

import com.example.common.CustomException;
import com.example.pojo.Department;
import com.example.pojo.DepartmentUser;
import com.example.pojo.User;

public interface DepartmentAndUserService {
    
    void saveDepartmentAndUser(List<Department> departemntList, List<User> userList, List<DepartmentUser> departmentUserList) throws CustomException;

    /**获取所有用户*/
    List<User> findAllUser();
    
    /**获取所有部门*/
    List<Department> findAllDepartment();
    
    /**获取部门、用户关系*/
    List<DepartmentUser> findAllDepartmentUser();
}
