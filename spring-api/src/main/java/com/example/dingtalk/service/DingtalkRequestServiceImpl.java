package com.example.dingtalk.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.common.CustomException;
import com.example.common.CustomResponse;
import com.example.common.ErrorCode;
import com.example.dingtalk.pojo.DingtalkAttendenceStatistic;
import com.example.dingtalk.pojo.DingtalkDepartment;
import com.example.dingtalk.pojo.DingtalkDepartmentUser;
import com.example.dingtalk.pojo.DingtalkUser;

@Service
public class DingtalkRequestServiceImpl implements DingtalkRequestService {
    
    private static final String SERVER = "http://47.89.254.104:8802";

    @Override
    public List<DingtalkUser> getUserList() throws CustomException {
        RestTemplate restTemplate = new RestTemplate();
        CustomResponse<List<DingtalkUser>> customResponse = restTemplate.exchange(SERVER + "/data/user_list",
                HttpMethod.GET, httpEntity(), new ParameterizedTypeReference<CustomResponse<List<DingtalkUser>>>(){}).getBody();
        if (customResponse == null || !ErrorCode.OK.getCode().equals(customResponse.getCode()) || customResponse.getData() == null) {
            throw new CustomException(ErrorCode.REQUEST_ERROR);
        }
        return customResponse.getData();
    }

    @Override
    public List<DingtalkDepartment> getDepartmentList() throws CustomException {
        RestTemplate restTemplate = new RestTemplate();
        CustomResponse<List<DingtalkDepartment>> customResponse = restTemplate.exchange(SERVER + "/data/department_list",
                HttpMethod.GET, httpEntity(), new ParameterizedTypeReference<CustomResponse<List<DingtalkDepartment>>>(){}).getBody();
        if (customResponse == null || !ErrorCode.OK.getCode().equals(customResponse.getCode())) {
            throw new CustomException(ErrorCode.REQUEST_ERROR);
        }
        return customResponse.getData();
    }

    @Override
    public List<DingtalkDepartmentUser> getDepartmentUserList() throws CustomException {
        RestTemplate restTemplate = new RestTemplate();
        CustomResponse<List<DingtalkDepartmentUser>> customResponse = restTemplate.exchange(SERVER + "/data/department_user_list",
                HttpMethod.GET, httpEntity(), new ParameterizedTypeReference<CustomResponse<List<DingtalkDepartmentUser>>>(){}).getBody();
        if (customResponse == null || !ErrorCode.OK.getCode().equals(customResponse.getCode())) {
            throw new CustomException(ErrorCode.REQUEST_ERROR);
        }
        return customResponse.getData();
    }

    @Override
    public List<DingtalkAttendenceStatistic> getAttendenceStatistic(Date startDate, Date endDate) throws CustomException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> paramMap = new HashMap<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        paramMap.put("startDate", format.format(startDate));
        paramMap.put("endDate", format.format(endDate));
        CustomResponse<List<DingtalkAttendenceStatistic>> customResponse = restTemplate.exchange(SERVER + "/data/attendence_statistic?startDate={startDate}&endDate={endDate}",
                HttpMethod.GET, httpEntity(), new ParameterizedTypeReference<CustomResponse<List<DingtalkAttendenceStatistic>>>(){}, paramMap).getBody();
        if (customResponse == null || !ErrorCode.OK.getCode().equals(customResponse.getCode())) {
            System.out.println(customResponse);
            throw new CustomException(ErrorCode.REQUEST_ERROR);
        }
        return customResponse.getData();
    }
    
    private HttpEntity<String> httpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "123456");
        return new HttpEntity<>(null, headers);
    }

}
