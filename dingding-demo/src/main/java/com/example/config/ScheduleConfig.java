package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.example.compoment.AccessTokenTask;
import com.example.compoment.UpdateAttendanceTask;
import com.example.compoment.UpdateDepartmentAndUserTask;

/**定时任务配置*/
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    private Integer tokenInterval = 3600;
    
    @Value("${app.cron.department}")
    private String updateDepartmentAndUserCron; //每日0点
    
    @Value("${app.cron.attendance}")
    private String updateAttendanceCron; //每日0点10分
    
    @Autowired
    private AccessTokenTask accessTokenTask;
    
    @Autowired
    private UpdateDepartmentAndUserTask updateDepartmentAndUserTask;
    
    @Autowired
    private UpdateAttendanceTask updateAttendanceTask;
    
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addFixedRateTask(accessTokenTask, tokenInterval * 1000);
        taskRegistrar.addCronTask(updateDepartmentAndUserTask, updateDepartmentAndUserCron);
        taskRegistrar.addCronTask(updateAttendanceTask, updateAttendanceCron);
    }
}
