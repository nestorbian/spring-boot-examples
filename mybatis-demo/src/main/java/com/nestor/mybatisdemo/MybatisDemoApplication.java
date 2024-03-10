package com.nestor.mybatisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
// 可以不加@MapperScan来扫描Mapper，通过@AutoConfigurationPakage + @Mapper注解实现。补充：@MapperScan直接扫描接口类，无需加@Mapper注解
// @MapperScan("com.nestor.mybatisdemo.mapper")
// @EnableTransactionManagement
// @EnableAspectJAutoProxy
public class MybatisDemoApplication implements ApplicationRunner {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.err.println(dataSource.getClass().getCanonicalName());
    }
}
