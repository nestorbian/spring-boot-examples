package com.nestor.hibernatevalidation.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/5/17
 */
// @Lazy
@Component
@Scope(GenericBeanDefinition.SCOPE_PROTOTYPE)
@Slf4j
public class ScheduleTest implements InitializingBean {

    @Scheduled(cron = "*/5 * * * * ?")
    public void run() {
        log.info("run....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(2);
    }
}
