package com.nestor.hibernatevalidation.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文工具类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/2/25
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    // 设置应用程序上下文
    private static ApplicationContext applicationContext;

    // 设置应用程序上下文
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    // 获取应用程序上下文
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
