package com.example.dingtalk.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.request.OapiUserSimplelistRequest;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiUserSimplelistResponse;
import com.example.common.CustomException;
import com.example.common.ErrorCode;
import com.example.dingtalk.FetchAccessTokenService;
import com.example.dingtalk.FetchDepartmentAndUserService;
import com.example.pojo.Department;
import com.example.pojo.DepartmentUser;
import com.example.pojo.User;
import com.example.pojo.converter.DepartmentConverter;
import com.example.pojo.converter.UserConverter;
import com.example.service.DepartmentAndUserService;
import com.taobao.api.ApiException;

import lombok.Getter;
import lombok.Setter;

@Service
public class FetchDepartmentAndUserServiceImpl implements FetchDepartmentAndUserService {
    
    @Autowired
    private DepartmentAndUserService departmentAndUserService;

    @Autowired
    private FetchAccessTokenService fetchAccessTokenService;
    
    @Override
    public void fetchDepartmentAndUser() throws CustomException {
        try {
            List<Department> departmentList = fetchDepartmentList();
            UserAndDepartmentUserList userAndDepartmentUserList = fetchUserAndDepartmentUserList(departmentList);
            List<User> userList = userAndDepartmentUserList.getUserList();
            List<DepartmentUser> departmentUserList = userAndDepartmentUserList.getDepartmentUserList();
            departmentAndUserService.saveDepartmentAndUser(departmentList, userList, departmentUserList);
        } catch (ApiException e) {
            throw new CustomException(ErrorCode.REQUEST_ERROR, e.getErrMsg());
        }
    }
    
    /**获取部门列表*/
    private List<Department> fetchDepartmentList() throws ApiException, CustomException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setFetchChild(true);
        request.setHttpMethod("GET");
        OapiDepartmentListResponse response = null;
        response = client.execute(request, fetchAccessTokenService.getAccessToken());
        if (!response.isSuccess()) {
            throw new CustomException(ErrorCode.REQUEST_ERROR, response.getErrmsg());
        }
        return DepartmentConverter.INSTANCE.convert(response.getDepartment());
    }

    @Getter
    @Setter
    class UserAndDepartmentUserList {
        private List<User> userList = null;
        private List<DepartmentUser> departmentUserList = null;
    }
    
    /**获取用户列表和部门用户列表*/
    private UserAndDepartmentUserList fetchUserAndDepartmentUserList(List<Department> departmentList) throws CustomException, ApiException {
        if (departmentList == null || departmentList.isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_ERROR, "部门列表为空");
        }
        Set<DepartmentUser> departmentUserSet = new HashSet<>();
        Set<User> userSet = new HashSet<>();
        for (Department department : departmentList) {
            List<User> list = fetchUserSimpleListFromDepartment(department.getId(), 0L, 50L);
            userSet.addAll(list);
            for (User user : list) {
                DepartmentUser departmentUser = new DepartmentUser();
                departmentUser.setDepartmentId(department.getId());
                departmentUser.setUserId(user.getId());
                departmentUserSet.add(departmentUser);
            }
        }
        UserAndDepartmentUserList list = new UserAndDepartmentUserList();
        list.setDepartmentUserList(new ArrayList<>(departmentUserSet));
        list.setUserList(new ArrayList<>(userSet));
        return list;
    }
    
    /**获取某部门用户*/
    private List<User> fetchUserSimpleListFromDepartment(Long departmentId, Long offset, Long size) throws ApiException, CustomException {
        OapiUserSimplelistResponse response = null;
        List<User> list = new ArrayList<>();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/simplelist");
        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
        request.setDepartmentId(departmentId);
        request.setOffset(offset);
        request.setSize(size);
        request.setHttpMethod("GET");
        response = client.execute(request, fetchAccessTokenService.getAccessToken());
        if (!response.isSuccess()) {
            throw new CustomException(ErrorCode.REQUEST_ERROR, response.getErrmsg());
        }
        list.addAll(UserConverter.INSTANCE.convert(response.getUserlist()));
        if(response.getHasMore()) {
            list.addAll(fetchUserSimpleListFromDepartment(departmentId, offset + size, size));
        }
        return list;
    }

}
