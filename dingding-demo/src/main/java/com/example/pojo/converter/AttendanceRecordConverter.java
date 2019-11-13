package com.example.pojo.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.dingtalk.api.response.OapiAttendanceListResponse.Recordresult;
import com.example.pojo.AttendanceRecord;

@Mapper
public interface AttendanceRecordConverter {
    
    AttendanceRecordConverter INSTANCE = Mappers.getMapper(AttendanceRecordConverter.class);
    
    AttendanceRecord convert(Recordresult po);
    List<AttendanceRecord> convert(List<Recordresult> list);
}