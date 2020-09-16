package com.nestor.importannotation.config;

import com.nestor.importannotation.annotation.Open;
import com.nestor.importannotation.service.CephService;
import com.nestor.importannotation.service.FtpService;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/8/10
 */
public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if (!importingClassMetadata.hasAnnotation(Open.class.getName())) {
            return;
        }

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(Open.class.getName());
        if ("ftp".equals(annotationAttributes.get("value"))) {
            registry.registerBeanDefinition("ftpService",
                    BeanDefinitionBuilder.rootBeanDefinition(FtpService.class).getBeanDefinition());
            return;
        }

        registry.registerBeanDefinition("cephService",
                BeanDefinitionBuilder.rootBeanDefinition(CephService.class).getBeanDefinition());
    }

}
