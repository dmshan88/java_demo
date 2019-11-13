package com.example.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttendenceStatisticVO {
    
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_DEPARTMENT = "department";
    public static final String ATTRIBUTE_USER_ID = "userId";
    public static final String ATTRIBUTE_DUTY_DAYS = "dutyDays";
    public static final String ATTRIBUTE_REST_DAYS = "restDays";
    public static final String ATTRIBUTE_LATE_TIMES = "lateTimes";
    public static final String ATTRIBUTE_SERIOUS_LATE_TIMES = "seriousLateTimes";
    public static final String ATTRIBUTE_ABSENT_LATE_TIMES = "absentLateTimes";
    public static final String ATTRIBUTE_LEAVE_EARLY_TIMES = "leaveEarlyTimes";
    public static final String ATTRIBUTE_MISS_CARD_DUTY_TIMES = "missCardDutyTimes";
    public static final String ATTRIBUTE_MISS_CARD_UNDUTY_TIMES = "missCardUndutyTimes";
    public static final String ATTRIBUTE_ABSENT_DAYS = "absentDays";

    private String name; //姓名
    
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