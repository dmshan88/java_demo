package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.example.common.Constants;
import com.example.task.JmsSendTask;

/** 定时任务配置 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    private JmsTemplate jmsTemplate;

    public ScheduleConfig(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addFixedDelayTask(new JmsSendTask(jmsTemplate, Constants.topic), 5 * 1000);

    }

}
