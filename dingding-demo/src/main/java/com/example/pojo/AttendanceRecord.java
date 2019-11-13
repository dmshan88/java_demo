package com.example.pojo;

import java.util.Date;

import com.example.common.Constant;

import lombok.Data;

@Data
public class AttendanceRecord {
    
    public static final String TABLE_NAME = "attendance_record";
    public static final String FIELD_ID = "id";
    public static final String FIELD_USER_ID = "user_id";
    public static final String FIELD_CHECK_TYPE = "check_type";
    public static final String FIELD_WORK_DATE = "work_date";
    public static final String FIELD_BASE_CHECK_TIME = "base_check_time";
    public static final String FIELD_USER_CHECK_TIME = "user_check_time";
    public static final String FIELD_LOCATION_RESULT = "location_result";
    public static final String FIELD_TIME_RESULT = "time_result";
    public static final String FIELD_PROC_ID = "proc_inst_id";
    public static final String FIELD_SOURCE_TYPE = "source_type";
    
    public static final String ATTRIBUTE_ID = "id";
    public static final String ATTRIBUTE_USER_ID = "userId";
    public static final String ATTRIBUTE_CHECK_TYPE = "checkType";
    public static final String ATTRIBUTE_WORK_DATE = "workDate";
    public static final String ATTRIBUTE_BASE_CHECK_TIME = "baseCheckTime";
    public static final String ATTRIBUTE_USER_CHECK_TIME = "userCheckTime";
    public static final String ATTRIBUTE_LOCATION_RESULT = "locationResult";
    public static final String ATTRIBUTE_TIME_RESULT = "timeResult";
    public static final String ATTRIBUTE_PROC_ID = "procInstId";
    public static final String ATTRIBUTE_SOURCE_TYPE = "sourceType";

    
    private Long id;
    
    private String userId; //用户ID

    private Integer checkType = Constant.CHECK_UNKNOWN; //考勤类型 OnDuty：上班 OffDuty：下班

    private Date workDate; //工作日

    private Date baseCheckTime; //基准时间

    private Date userCheckTime; //实际打卡时间

    private Integer locationResult = Constant.LOCATION_UNKNOWN; //位置结果 Normal：范围内=1；Outside：范围外=2；NotSigned：未打卡=3

    //时间结果 Normal：正常=1; Early：早退=2; Late：迟到=3; SeriousLate：严重迟到=4； Absenteeism：旷工迟到=5； NotSigned：未打卡=6
    private Integer timeResult = Constant.TIME_UNKNOWN;

//    private Long groupId; //考勤组ID
//
//    private Long planId; //排班ID
//
//    private Long recordId; //打卡记录ID
//
//    private Long approveId; //关联的审批id
//
    private String procInstId; //关联的审批实例id
//
    /*数据来源 
     * ATM：考勤机 = 1;
     * BEACON：IBeacon = 2;
     * DING_ATM：钉钉考勤机 =3;
     * USER：用户打卡=4;
     * BOSS：老板改签=5;
     * APPROVE：审批系统=6;
     * SYSTEM：考勤系统=7;
     * AUTO_CHECK：自动打卡=8
     */
    private Integer sourceType = Constant.SOURCE_UNKNOWN; 
    
    public void setCheckType(String str) {
        if (str == null) {
            checkType = Constant.CHECK_UNKNOWN;
        } else if (str.equals("OnDuty")) {
            checkType = Constant.CHECK_ON_DUTY;
        } else if (str.equals("OffDuty")) {
            checkType = Constant.CHECK_OFF_DUTY;
        } else {
            checkType = Constant.CHECK_UNKNOWN;
        }
    }
    
    public void setLocationResult(String str) {
        if (str == null) {
            locationResult = Constant.LOCATION_UNKNOWN;
        } else if (str.equals("Normal")) {
            locationResult = Constant.LOCATION_NORMAL;
        } else if (str.equals("Outside")) {
            locationResult = Constant.LOCATION_OUTSIDE;
        } else if (str.equals("NotSigned")) {
            locationResult = Constant.LOCATION_NOTSIGNED;
        } else {
            locationResult = Constant.LOCATION_UNKNOWN;
        }
    }
    
    public void setTimeResult(String str) {
        if (str == null) {
            timeResult = Constant.TIME_UNKNOWN;
        } else if (str.equals("Normal")) {
            timeResult = Constant.TIME_NORMAL;
        } else if (str.equals("Early")) {
            timeResult = Constant.TIME_EARLY;
        } else if (str.equals("Late")) {
            timeResult = Constant.TIME_LATE;
        } else if (str.equals("SeriousLate")) {
            timeResult = Constant.TIME_SERIOUS_LATE;
        } else if (str.equals("Absenteeism")) {
            timeResult = Constant.TIME_ABSENTEEISM;
        } else if (str.equals("NotSigned")) {
            timeResult = Constant.TIME_NOT_SIGNED;
        } else {
            timeResult = Constant.TIME_UNKNOWN;
        }
    }
    
    public void setSourceType(String str) {
        if (str == null) {
            sourceType = Constant.SOURCE_UNKNOWN;
        } else if (str.equals("ATM")) {
            sourceType = Constant.SOURCE_ATM;
        } else if (str.equals("BEACON")) {
            sourceType = Constant.SOURCE_BEACON;
        } else if (str.equals("DING_ATM")) {
            sourceType = Constant.SOURCE_DING_ATM;
        } else if (str.equals("USER")) {
            sourceType = Constant.SOURCE_USER;
        } else if (str.equals("BOSS")) {
            sourceType = Constant.SOURCE_BOSS;
        } else if (str.equals("APPROVE")) {
            sourceType = Constant.SOURCE_APPROVE;
        } else if (str.equals("SYSTEM")) {
            sourceType = Constant.SOURCE_SYSTEM;
        } else if (str.equals("AUTO_CHECK")) {
            sourceType = Constant.SOURCE_AUTO_CHECK;
        } else {
            sourceType = Constant.SOURCE_UNKNOWN;
        }
    }
}
