package com.example.compoment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.BaseTask;
import com.example.common.CustomException;
import com.example.dingtalk.FetchAttendanceService;

@Component
public class UpdateAttendanceTask extends BaseTask {

    @Autowired
    private FetchAttendanceService fetchAttendanceService;
    
    @Override
    public void work() throws CustomException {
        fetchAttendanceService.fetchDailyAttendanceRecord();
    }

}
