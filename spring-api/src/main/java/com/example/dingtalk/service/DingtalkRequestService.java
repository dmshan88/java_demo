package com.example.dingtalk.service;

import java.util.Date;
import java.util.List;

import com.example.common.CustomException;
import com.example.dingtalk.pojo.DingtalkAttendenceStatistic;
import com.example.dingtalk.pojo.DingtalkDepartment;
import com.example.dingtalk.pojo.DingtalkDepartmentUser;
import com.example.dingtalk.pojo.DingtalkUser;

public interface DingtalkRequestService {
    
    List<DingtalkUser> getUserList() throws CustomException;
    
    List<DingtalkDepartment> getDepartmentList() throws CustomException;
    
    List<DingtalkDepartmentUser> getDepartmentUserList() throws CustomException;
    
    List<DingtalkAttendenceStatistic> getAttendenceStatistic(Date startDate, Date endDate) throws CustomException;

}
