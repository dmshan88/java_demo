package com.example.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CustomException;
import com.example.common.CustomResponse;
import com.example.common.ErrorCode;
import com.example.dingtalk.FetchAttendanceService;
import com.example.dingtalk.FetchDepartmentAndUserService;

@RequestMapping(path = "/fetch")
@RestController
public class FetchController extends BaseController {

    @Autowired
    private FetchAttendanceService fetchAttendanceService;
    
    @Autowired
    private FetchDepartmentAndUserService fetchDepartmentAndUserService;

    /**同步部门、用户*/
    @PostMapping("/department_and_user")
    CustomResponse<Object> fetchDepartmentAndUserPost() throws CustomException {
        fetchDepartmentAndUserService.fetchDepartmentAndUser();
        return new CustomResponse<>(null);
    }
    
    /**同步当日考勤记录*/
    @PostMapping("/daily_attendance")
    CustomResponse<Object> fetchDailyRecordPost() throws CustomException {
        fetchAttendanceService.fetchDailyAttendanceRecord();
        return new CustomResponse<>(null);
    }
    
    /**同步历史考勤记录*/
    @PostMapping("/history_attendance")
    CustomResponse<Object> fetchHistoryRecordPost(String startDate, String endDate) throws CustomException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fetchAttendanceService.fetchHostoryAttendanceRecord(formatter.parse(startDate), formatter.parse(endDate));
        } catch (ParseException e) {
            throw new CustomException(ErrorCode.PARAM_ERROR, "日期格式错误");
        };
        return new CustomResponse<>(null);
    }
}
