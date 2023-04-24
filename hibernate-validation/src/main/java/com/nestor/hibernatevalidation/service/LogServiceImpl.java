package com.nestor.hibernatevalidation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/5/12
 */
@Service
public class LogServiceImpl implements AbstractLogService {

    @Override
    public void log(String str) {
        // super.log(str);
        // SLF4J配置<logger name="com.nestor.hibernatevalidation.service.AbstractLogService" level="DEBUG"/>
        // 使用子类LoggerFactory.getLogger("com.nestor.hibernatevalidation.service.LogServiceImpl");不起作用
        // 子类起作用，可以配置如下：<logger name="com.nestor.hibernatevalidation.service" level="DEBUG"/>
        Logger logger = LoggerFactory.getLogger("com.nestor.hibernatevalidation.service.AbstractLogService");
        // 控制台打印方式无法打印到日志文件中
        System.err.println("LogServiceImpl log info:" + str);
        logger.debug("LogServiceImpl debug log info:" + str);
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSXXX").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSXX").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSX").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZZ").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZZZ").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSz").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSzz").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSzzz").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSzzzz").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSzzzzzzzzz").format(new Date()));
    }
}
