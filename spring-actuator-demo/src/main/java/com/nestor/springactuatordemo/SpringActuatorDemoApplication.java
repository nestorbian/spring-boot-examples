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
        // 注册MBean
        MBeanServer mBeanServer= ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName=new ObjectName("com.nestor.springactuatordemo:type=SystemInfo");
        SystemInfo SystemInfo =new SystemInfo();
        mBeanServer.registerMBean(SystemInfo,objectName);//注册

        SpringApplication.run(SpringActuatorDemoApplication.class, args);
    }

}
