package com.example.compoment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.BaseTask;
import com.example.common.CustomException;
import com.example.dingtalk.FetchDepartmentAndUserService;

@Component
public class UpdateDepartmentAndUserTask extends BaseTask {

    @Autowired
    private FetchDepartmentAndUserService dingtalkFetchService;
    
    @Override
    public void work() throws CustomException {
        dingtalkFetchService.fetchDepartmentAndUser();
    }

}
