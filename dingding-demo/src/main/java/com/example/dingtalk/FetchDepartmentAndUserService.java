package com.example.dingtalk;

import com.example.common.CustomException;

public interface FetchDepartmentAndUserService {
    
    /**获取部门、用户*/
    void fetchDepartmentAndUser() throws CustomException;
    
}
