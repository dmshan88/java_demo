package com.example.service;

import java.util.List;

import com.example.common.CustomException;
import com.example.pojo.AttendanceRecord;

public interface AttendanceRecordService {
    
    void saveAttendances(List<AttendanceRecord> attendanceList) throws CustomException;

}
