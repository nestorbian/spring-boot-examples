package com.nestor.hibernatevalidation;

import com.nestor.hibernatevalidation.entity.MyProperties;
import com.nestor.other.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
@Import({MyProperties.class})
@Slf4j
public class HibernateValidationApplication implements ApplicationRunner {

    @Autowired
    private DefaultListableBeanFactory beanFactory;

    public static void main(String[] args) {
        System.out.println(HibernateValidationApplication.class.getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        SpringApplication.run(HibernateValidationApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AbstractBeanDefinition beanDefinition =
                BeanDefinitionBuilder.genericBeanDefinition(Hello.class).setScope(BeanDefinition.SCOPE_SINGLETON).getBeanDefinition();

        String beanName = AnnotationBeanNameGenerator.INSTANCE.generateBeanName(beanDefinition, beanFactory);
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
        log.info("=============注册BeanDefinition[{}]", beanName);
        Object bean = beanFactory.getBean(beanName);
        log.info("============bean[{}]", bean);
        beanFactory.removeBeanDefinition(beanName);
        log.info("=============移除BeanDefinition[{}]", beanName);
        try {
            bean = beanFactory.getBean(beanName);
            log.info("============bean[{}]", bean);
        } catch (BeansException e) {
            log.info("============bean[null]");
        }

    }
}
