package com.example.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CustomException;
import com.example.dingtalk.service.DingtalkRequestService;

@RestController
public class MainController {

    @Autowired
    private DingtalkRequestService dingtalkRequestService;
    
    @GetMapping("/test")
    public String test() throws CustomException {
        System.out.println(dingtalkRequestService.getUserList());
        System.out.println(dingtalkRequestService.getDepartmentList());
        System.out.println(dingtalkRequestService.getDepartmentUserList());
        System.out.println(dingtalkRequestService.getAttendenceStatistic(new Date(1572364800000L), new Date(1572364800000L)));
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("token", "123456");
//        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
////        ResponseEntity<CustomResponse> customResponse = 
//        CustomResponse<List<DingtalkUser>> customResponse = restTemplate.exchange("http://47.89.254.104:8802/data/user_list",
//                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<CustomResponse<List<DingtalkUser>>>(){}).getBody();
//        if (customResponse != null && customResponse.getCode() == 0 && customResponse.getData() != null) {
//            List<DingtalkUser> userList = customResponse.getData();
//            System.out.println(userList);
//        }
////        System.out.println(customResponse.getBody());
////        new ParameterizedTypeReference<CustomResponse<List<User>>>(){};
////        restTemplate.ex
        return "test";
    }
}
