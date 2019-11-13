package com.example.common;

public interface Constant {
    
    static final public String REQUEST_PARAM_TOKEN = "token";
    
    static final public int CHECK_UNKNOWN = 0; //考勤类型  未知
    static final public int CHECK_ON_DUTY = 1; //考勤类型  上班
    static final public int CHECK_OFF_DUTY = 2; //考勤类型  下班
    
    static final public int LOCATION_UNKNOWN = 0; //位置结果 未知
    static final public int LOCATION_NORMAL = 1; //位置结果 范围内
    static final public int LOCATION_OUTSIDE = 2; //位置结果 未知
    static final public int LOCATION_NOTSIGNED = 3; //位置结果 未知
    
    static final public int TIME_UNKNOWN = 0; //时间结果 未知
    static final public int TIME_NORMAL = 1; //时间结果 正常
    static final public int TIME_EARLY = 2; //时间结果 早退
    static final public int TIME_LATE = 3; //时间结果 迟到
    static final public int TIME_SERIOUS_LATE = 4; //时间结果 严重迟到
    static final public int TIME_ABSENTEEISM = 5; //时间结果 旷工迟到
    static final public int TIME_NOT_SIGNED = 6; //时间结果 未打卡
    
    static final public int SOURCE_UNKNOWN = 0; //考勤来源:未知
    static final public int SOURCE_ATM = 1; //考勤来源：IBeacon;
    static final public int SOURCE_BEACON = 2; //考勤来源：IBeacon;
    static final public int SOURCE_DING_ATM = 3; //考勤来源：钉钉考勤机;
    static final public int SOURCE_USER = 4; //考勤来源：用户打卡;
    static final public int SOURCE_BOSS = 5; //考勤来源：老板改签;
    static final public int SOURCE_APPROVE = 6; //考勤来源：审批系统;
    static final public int SOURCE_SYSTEM = 7; //考勤来源：考勤系统;
    static final public int SOURCE_AUTO_CHECK = 8; //考勤来源：自动打卡

}
