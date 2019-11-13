package com.example.dingtalk.pojo;

import lombok.Data;

@Data
public class DingtalkAttendenceStatistic {
    
    private String department; //部门
    
//    private String jobNumber; //工号

    private String userId; //
  
    private Integer dutyDays; //出勤天数
  
    private Integer restDays; //休息天数
  
    private Integer lateTimes; //迟到次数
  
    private Integer seriousLateTimes; //严重迟到次数
  
    private Integer absentLateTimes; //旷工迟到次数
  
    private Integer leaveEarlyTimes; //早退次数
  
    private Integer missCardDutyTimes; //上班缺卡次数
  
    private Integer missCardUndutyTimes; //下班缺卡次数
  
    private Integer absentDays; //旷工天数
    
}
