package com.example.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CustomException;
import com.example.common.CustomResponse;
import com.example.common.ErrorCode;
import com.example.dao.AttendanceRecordDAO;
import com.example.pojo.Department;
import com.example.pojo.DepartmentUser;
import com.example.pojo.User;
import com.example.pojo.vo.AttendenceStatisticVO;
import com.example.service.DepartmentAndUserService;

@RequestMapping(path = "/data")
@RestController
public class DataController extends BaseController {
    
    @Autowired
    private DepartmentAndUserService departmentAndUserService;
    
    @Autowired
    private AttendanceRecordDAO attendanceRecordDAO;
    
    /**获取所有用户*/
    @GetMapping("/user_list")
    CustomResponse<List<User>> userListGet() throws CustomException {
        return new CustomResponse<>(departmentAndUserService.findAllUser());
    }
    
    /**获取所有部门*/
    @GetMapping("/department_list")
    CustomResponse<List<Department>> departmentListGet() throws CustomException {
        return new CustomResponse<>(departmentAndUserService.findAllDepartment());
    }
    
    /**获取所有部门用户*/
    @GetMapping("/department_user_list")
    CustomResponse<List<DepartmentUser>> departmentUserListGet() throws CustomException {
        return new CustomResponse<>(departmentAndUserService.findAllDepartmentUser());
    }
    
    /**获取所有部门用户*/
    @GetMapping("/attendence_statistic")
    CustomResponse<List<AttendenceStatisticVO>> attendenceStatisticGet(@RequestParam String startDate, @RequestParam String endDate) throws CustomException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = formatter.parse(startDate);
            Date date2 = formatter.parse(endDate);
            if (date1 == null || date2 == null || date1.after(date2)) {
                throw new CustomException(ErrorCode.PARAM_ERROR, "结束日期应大于开始日期");
            }
        } catch (ParseException e) {
            throw new CustomException(ErrorCode.PARAM_ERROR, "日期格式错误");
        }
        return new CustomResponse<>(attendanceRecordDAO.getStatistic(startDate, endDate));
    }
}
