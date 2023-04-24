package com.nestor.hibernatevalidation.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/7/8
 */
// @Component
@Data
@ConfigurationProperties(prefix = "my-properties")
public class MyProperties implements ImportAware {

    private String name;

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        // 拿到当前bean被@Import注入地方的类的注解元素据
        System.out.println(importMetadata.getAnnotationTypes());
    }
}
