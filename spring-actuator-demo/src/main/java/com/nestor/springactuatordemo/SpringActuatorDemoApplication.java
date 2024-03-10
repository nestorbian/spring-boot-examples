package com.nestor.springactuatordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@SpringBootApplication
public class SpringActuatorDemoApplication {

    public static void main(String[] args) throws Exception {
        // 注册MBean，不会展示在actuator上
        // @EndPoint监控断点既可以在jmx和http方式访问
        MBeanServer mBeanServer= ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName=new ObjectName("com.nestor.springactuatordemo:type=SystemInfo");
        SystemInfo SystemInfo =new SystemInfo();
        mBeanServer.registerMBean(SystemInfo,objectName);//注册

        SpringApplication.run(SpringActuatorDemoApplication.class, args);
    }

}
