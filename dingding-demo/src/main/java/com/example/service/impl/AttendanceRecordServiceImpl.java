package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.CustomException;
import com.example.common.ErrorCode;
import com.example.dao.AttendanceRecordDAO;
import com.example.pojo.AttendanceRecord;
import com.example.service.AttendanceRecordService;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

    @Autowired
    private AttendanceRecordDAO attendanceRecordDAO;
    
    @Override
    @Transactional
    public void saveAttendances(List<AttendanceRecord> attendanceList) throws CustomException {
        if (attendanceList == null || attendanceList.isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_ERROR, "attendanceList");
        }
        List<Long> ids = new ArrayList<>();
        for (AttendanceRecord attendanceRecord : attendanceList) {
            ids.add(attendanceRecord.getId());
        }
        attendanceRecordDAO.deleteByIds(ids);
        log.info("attendances size" + attendanceList.size());
        log.info("save" + attendanceRecordDAO.batchInsert(attendanceList));
    }

}
