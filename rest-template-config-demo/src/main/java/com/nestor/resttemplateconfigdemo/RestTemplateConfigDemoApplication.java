package com.nestor.resttemplateconfigdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RestTemplateConfigDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateConfigDemoApplication.class, args);
    }

}
