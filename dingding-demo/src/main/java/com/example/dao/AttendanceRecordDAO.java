package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.common.Constant;
import com.example.pojo.AttendanceRecord;
import com.example.pojo.User;
import com.example.pojo.vo.AttendenceStatisticVO;

@Mapper
public interface AttendanceRecordDAO {
    
//    /**插入*/
//    Integer insert(Department department);
    
    /**批量插入*/
    @Insert("<script>" +
            "INSERT INTO " + AttendanceRecord.TABLE_NAME
            + " (" + AttendanceRecord.FIELD_ID + ", " + AttendanceRecord.FIELD_USER_ID + ", " + AttendanceRecord.FIELD_CHECK_TYPE + ", "
            + AttendanceRecord.FIELD_WORK_DATE + ", " + AttendanceRecord.FIELD_BASE_CHECK_TIME + ", " + AttendanceRecord.FIELD_USER_CHECK_TIME + ", "
            + AttendanceRecord.FIELD_LOCATION_RESULT + ", " + AttendanceRecord.FIELD_TIME_RESULT + ", " + AttendanceRecord.FIELD_SOURCE_TYPE + ", " 
            + AttendanceRecord.FIELD_PROC_ID + ") VALUES "
            + "<foreach collection='list' item='item' index='index' separator=','>"
            + "(#{item." + AttendanceRecord.ATTRIBUTE_ID + "}, #{item." + AttendanceRecord.ATTRIBUTE_USER_ID + "}, #{item." + AttendanceRecord.ATTRIBUTE_CHECK_TYPE 
            + "}, #{item." + AttendanceRecord.ATTRIBUTE_WORK_DATE + "}, #{item." + AttendanceRecord.ATTRIBUTE_BASE_CHECK_TIME + "}, #{item." + AttendanceRecord.ATTRIBUTE_USER_CHECK_TIME
            + "}, #{item." + AttendanceRecord.ATTRIBUTE_LOCATION_RESULT + "}, #{item." + AttendanceRecord.ATTRIBUTE_TIME_RESULT + "}, #{item." + AttendanceRecord.ATTRIBUTE_SOURCE_TYPE
            + "}, #{item." + AttendanceRecord.ATTRIBUTE_PROC_ID + "})"
            + "</foreach>"
        + "</script>")
    Integer batchInsert(List<AttendanceRecord> list);
    
    /**更新*/
//    Integer update(Department user);
    
    /**根据id查找*/
//    Department findById(String id);
    
    /**查找所有*/
    @Select("SELECT " + AttendanceRecord.FIELD_ID + " as " + AttendanceRecord.ATTRIBUTE_ID + ", "
            + AttendanceRecord.FIELD_USER_ID + " as " + AttendanceRecord.ATTRIBUTE_USER_ID + ", "
            + AttendanceRecord.FIELD_CHECK_TYPE + " as " + AttendanceRecord.ATTRIBUTE_CHECK_TYPE + ", "
            + AttendanceRecord.FIELD_WORK_DATE + " as " + AttendanceRecord.ATTRIBUTE_WORK_DATE + ", "
            + AttendanceRecord.FIELD_BASE_CHECK_TIME + " as " + AttendanceRecord.ATTRIBUTE_BASE_CHECK_TIME + ", "
            + AttendanceRecord.FIELD_USER_CHECK_TIME + " as " + AttendanceRecord.ATTRIBUTE_USER_CHECK_TIME + ", "
            + AttendanceRecord.FIELD_LOCATION_RESULT + " as " + AttendanceRecord.ATTRIBUTE_LOCATION_RESULT + ", "
            + AttendanceRecord.FIELD_TIME_RESULT + " as " + AttendanceRecord.ATTRIBUTE_TIME_RESULT + ", "
            + AttendanceRecord.FIELD_SOURCE_TYPE + " as " + AttendanceRecord.ATTRIBUTE_SOURCE_TYPE + ", "
            + AttendanceRecord.FIELD_PROC_ID + " as " + AttendanceRecord.ATTRIBUTE_PROC_ID
            + " FROM " + AttendanceRecord.TABLE_NAME)
    List<AttendanceRecord> findAll();
    

    @Delete("<script>"
            + "DELETE FROM " + AttendanceRecord.TABLE_NAME
            +    " WHERE " + AttendanceRecord.FIELD_ID + " IN"
            +    "<foreach collection='ids' item='id' open='(' separator=',' close=')'>"
            +        "#{id}"
            +    "</foreach>"
            + "</script>")
    Integer deleteByIds(@Param("ids") List<Long> ids);

    @Select("<script>"
            + "SELECT "
            + " u." + User.FIELD_NAME + " as " + AttendenceStatisticVO.ATTRIBUTE_NAME + ", "
            + " u." + User.FIELD_ID + " as " + AttendenceStatisticVO.ATTRIBUTE_USER_ID + ", "
            + "SUM(IF(a." + AttendanceRecord.FIELD_TIME_RESULT + " != " + Constant.TIME_NOT_SIGNED 
                + " OR b." + AttendanceRecord.FIELD_TIME_RESULT + " != " + Constant.TIME_NOT_SIGNED 
            + ",1,0))"+ " as " + AttendenceStatisticVO.ATTRIBUTE_DUTY_DAYS + ", "
            + "SUM(IF(a." + AttendanceRecord.FIELD_PROC_ID + " IS NULL " 
                + " AND a." + AttendanceRecord.FIELD_TIME_RESULT + " = " +  Constant.TIME_NOT_SIGNED
                + " AND b." + AttendanceRecord.FIELD_PROC_ID + " IS NULL " 
                + " AND b." + AttendanceRecord.FIELD_TIME_RESULT + " = " +  Constant.TIME_NOT_SIGNED
            + ",1,0)) as " +  AttendenceStatisticVO.ATTRIBUTE_ABSENT_DAYS + ", "
            + "SUM(IF(a." + AttendanceRecord.FIELD_TIME_RESULT + " = " + Constant.TIME_NOT_SIGNED
                + " AND b." + AttendanceRecord.FIELD_TIME_RESULT + " != " +  Constant.TIME_NOT_SIGNED
            + ", 1, 0)) as " + AttendenceStatisticVO.ATTRIBUTE_MISS_CARD_DUTY_TIMES + ","
            + "SUM(IF(a." + AttendanceRecord.FIELD_TIME_RESULT + " != " + Constant.TIME_NOT_SIGNED
                + " AND b." + AttendanceRecord.FIELD_TIME_RESULT + " = " +  Constant.TIME_NOT_SIGNED
            + ", 1, 0)) as " + AttendenceStatisticVO.ATTRIBUTE_MISS_CARD_UNDUTY_TIMES + ","
            + "SUM(IF(a." + AttendanceRecord.FIELD_TIME_RESULT + " = " + Constant.TIME_EARLY
                + " OR b." + AttendanceRecord.FIELD_TIME_RESULT + " = " +  Constant.TIME_EARLY
            + ", 1, 0)) as " + AttendenceStatisticVO.ATTRIBUTE_LEAVE_EARLY_TIMES + ","
            + "SUM(IF(a." + AttendanceRecord.FIELD_TIME_RESULT + " = " + Constant.TIME_SERIOUS_LATE
                + " OR b." + AttendanceRecord.FIELD_TIME_RESULT + " = " +  Constant.TIME_SERIOUS_LATE
            + ", 1, 0)) as " + AttendenceStatisticVO.ATTRIBUTE_SERIOUS_LATE_TIMES + ","
            + "SUM(IF(a." + AttendanceRecord.FIELD_TIME_RESULT + " = " + Constant.TIME_ABSENTEEISM
                + " OR b." + AttendanceRecord.FIELD_TIME_RESULT + " = " +  Constant.TIME_ABSENTEEISM
            + ", 1, 0)) as " + AttendenceStatisticVO.ATTRIBUTE_ABSENT_LATE_TIMES + ","
            + "SUM(IF(a." + AttendanceRecord.FIELD_TIME_RESULT + " = " + Constant.TIME_LATE
                + " OR b." + AttendanceRecord.FIELD_TIME_RESULT + " = " +  Constant.TIME_LATE
            + ", 1, 0)) as " + AttendenceStatisticVO.ATTRIBUTE_LATE_TIMES//+ ","
            + " FROM " + AttendanceRecord.TABLE_NAME + " as a "
            + " INNER JOIN " +  User.TABLE_NAME + " AS u ON a." + AttendanceRecord.FIELD_USER_ID + " = u." + User.FIELD_ID
            + " INNER JOIN " + AttendanceRecord.TABLE_NAME + " AS b " 
                + " ON a." + AttendanceRecord.FIELD_WORK_DATE + " = b." + AttendanceRecord.FIELD_WORK_DATE 
                + " AND a." + AttendanceRecord.FIELD_USER_ID + " = b." + AttendanceRecord.FIELD_USER_ID 
                + " AND a." + AttendanceRecord.FIELD_CHECK_TYPE + " = " + Constant.CHECK_ON_DUTY
                + " AND b." + AttendanceRecord.FIELD_CHECK_TYPE + " = " + Constant.CHECK_OFF_DUTY
            + " WHERE a."+ AttendanceRecord.FIELD_WORK_DATE + " &gt;= #{startDate}"
                + " AND a." + AttendanceRecord.FIELD_WORK_DATE + " &lt;= #{endDate}" 
            + " GROUP BY a." + AttendanceRecord.FIELD_USER_ID
            + "</script>")
    List<AttendenceStatisticVO> getStatistic(@Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate);
}
