package com.example.dingtalk.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAttendanceListRequest;
import com.dingtalk.api.response.OapiAttendanceListResponse;
import com.example.common.CustomException;
import com.example.common.ErrorCode;
import com.example.dingtalk.FetchAccessTokenService;
import com.example.dingtalk.FetchAttendanceService;
import com.example.pojo.AttendanceRecord;
import com.example.pojo.User;
import com.example.pojo.converter.AttendanceRecordConverter;
import com.example.service.AttendanceRecordService;
import com.example.service.DepartmentAndUserService;
import com.taobao.api.ApiException;

@Service
public class FetchAttendanceServiceImpl implements FetchAttendanceService {
    
    @Autowired
    private AttendanceRecordService attendanceRecordService;
    
    @Autowired
    private FetchAccessTokenService fetchAccessTokenService;
    
    @Autowired
    private DepartmentAndUserService departmentAndUserService;
    
    @Override
    public void fetchDailyAttendanceRecord() throws CustomException {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        fetchAttendanceRecord(departmentAndUserService.findAllUser(), yesterday, now);
        // TODO 异常数据更新
    }
    

    @Override
    public void fetchHostoryAttendanceRecord(Date startDate, Date endDate) throws CustomException {
        fetchAttendanceRecord(departmentAndUserService.findAllUser(), startDate, endDate);
    }

    /**获取指定用户组、日期考勤结果*/
    private void fetchAttendanceRecord(List<User> userList, Date startDate, Date endDate) throws CustomException {
        if (userList == null || startDate == null || endDate == null) {
            throw new CustomException(ErrorCode.EMPTY_ERROR, "userlist/date");
        }
        if (endDate.before(startDate)) {
            throw new CustomException(ErrorCode.PARAM_ERROR, "endDate > startDate !");
        }
        System.out.println("startDate " + startDate);
        System.out.println("endDate " + endDate);
        Set<AttendanceRecord> attendanceSet = new HashSet<>();
        List<String> userIds = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        for (int i = 0; i < userList.size(); i++) {
            userIds.add(userList.get(i).getId());
            if (userIds.size() == 50 || i == userList.size() - 1) {
                Date curDate = startDate;
                do {
                    calendar.setTime(curDate);
                    calendar.add(Calendar.DATE, 6);
                    Date nextPeriod = calendar.getTime();
                    Date lastCurDate = curDate;
                    curDate = nextPeriod.after(endDate) ? endDate : nextPeriod;
                    try {
                        attendanceSet.addAll(fetchAttendanceRecord(userIds, format.format(lastCurDate), format.format(curDate), 0L, 50L));
                    } catch (ApiException e) {
                        throw new CustomException(ErrorCode.REQUEST_ERROR, e.getErrMsg());
                    }
                } while(curDate.before(endDate));
                userIds.clear();
            }
        }
        attendanceRecordService.saveAttendances(new ArrayList<>(attendanceSet));
    }
    
    /**获取指定用户组考勤结果*/
    private List<AttendanceRecord> fetchAttendanceRecord(List<String> userIds, String startDate, 
            String endDate, Long offset, Long size) throws ApiException, CustomException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/list");
        OapiAttendanceListRequest request = new OapiAttendanceListRequest();
        request.setWorkDateFrom(startDate);
        request.setWorkDateTo(endDate);
        request.setUserIdList(userIds);
        request.setOffset(offset);
        request.setLimit(size);
        List<AttendanceRecord> list = new ArrayList<>();
        OapiAttendanceListResponse response = client.execute(request, fetchAccessTokenService.getAccessToken());
        if (!response.isSuccess()) {
            throw new CustomException(ErrorCode.REQUEST_ERROR, response.getErrmsg());
        }
        list.addAll(AttendanceRecordConverter.INSTANCE.convert(response.getRecordresult()));
        if(response.getHasMore()) {
            list.addAll(fetchAttendanceRecord(userIds,startDate, endDate, offset + size, size));
        }
        return list;
    }

}
