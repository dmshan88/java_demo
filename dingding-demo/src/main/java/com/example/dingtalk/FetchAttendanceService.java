package com.example.dingtalk;

import java.util.Date;

import com.example.common.CustomException;

public interface FetchAttendanceService {
    
    /**获取每日考勤结果*/
    void fetchDailyAttendanceRecord() throws CustomException;
    
    /**获取历史考勤结果*/
    void fetchHostoryAttendanceRecord(Date startDate, Date endDate) throws CustomException;
    
}
