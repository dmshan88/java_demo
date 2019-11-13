package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.CustomException;
import com.example.common.ErrorCode;
import com.example.dao.DepartmentDAO;
import com.example.dao.DepartmentUserDAO;
import com.example.dao.UserDAO;
import com.example.pojo.Department;
import com.example.pojo.DepartmentUser;
import com.example.pojo.User;
import com.example.service.DepartmentAndUserService;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class DepartmentAndUserServiceImpl implements DepartmentAndUserService {

    @Autowired
    private DepartmentDAO departmentDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private DepartmentUserDAO departmentUserDAO;
    
    @Override
    @Transactional
    public void saveDepartmentAndUser(List<Department> departemntList, List<User> userList,
            List<DepartmentUser> departmentUserList) throws CustomException {
        if (departemntList == null || userList == null || departmentUserList == null
            || departemntList.isEmpty() || userList.isEmpty() || departmentUserList.isEmpty()) {
            System.out.println(departemntList);
            System.out.println(userList);
            System.out.println(departmentUserList);
            throw new CustomException(ErrorCode.EMPTY_ERROR, "departemnt/user");
        }
        departmentUserDAO.deleteAll();
        userDAO.deleteAll();
        departmentDAO.deleteAll();
        departmentDAO.batchInsert(departemntList);
        userDAO.batchInsert(userList);
        departmentUserDAO.batchInsert(departmentUserList);
        log.info("saveDepartmentAndUser");
    }

    @Override
    public List<User> findAllUser() {
        return userDAO.findAll();
    }

    @Override
    public List<Department> findAllDepartment() {
        return departmentDAO.findAll();
    }

    @Override
    public List<DepartmentUser> findAllDepartmentUser() {
        return departmentUserDAO.findAll();
    }

}
