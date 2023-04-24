package com.nestor.hibernatevalidation.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/5/17
 */
@Component
@Slf4j
public class ScheduleTest implements SchedulingConfigurer {

    @Scheduled(cron = "*/5 * * * * ?")
    public void run() {
        log.info("run....");
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        boolean a = true == Integer.valueOf("1") instanceof Integer;
    }
}
