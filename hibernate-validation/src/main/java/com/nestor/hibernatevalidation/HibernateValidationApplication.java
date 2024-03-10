package com.nestor.hibernatevalidation;

import com.nestor.hibernatevalidation.entity.MyProperties;
import com.nestor.hibernatevalidation.service.LogServiceImpl;
import com.nestor.hibernatevalidation.service.MyService;
import com.nestor.hibernatevalidation.service.RefreshService;
import com.nestor.hibernatevalidation.support.DbConfigLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
@Import({ MyProperties.class })
@Slf4j
public class HibernateValidationApplication implements ApplicationRunner {

    @Autowired
    private DefaultListableBeanFactory beanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private LogServiceImpl logService;

    @Autowired
    private MyService myService;

    @Autowired
    private RefreshService refreshService;

    /* hello */
    public static void main(String[] args) {
        // System.out.println(HibernateValidationApplication.class.getClassLoader());
        // System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println("classpath=" + HibernateValidationApplication.class.getClassLoader().getResource(""));
        System.out.println("filePath=" + new File("").getAbsolutePath());
        System.out.println("user.dir=" + System.getProperty("user.dir"));
        SpringApplication.run(HibernateValidationApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Hello.class).setScope(
        // BeanDefinition.SCOPE_SINGLETON).getBeanDefinition();
        //
        // String beanName = AnnotationBeanNameGenerator.INSTANCE.generateBeanName(beanDefinition, beanFactory);
        // beanFactory.registerBeanDefinition(beanName, beanDefinition);
        // log.info("=============注册BeanDefinition[{}]", beanName);
        // Object bean = beanFactory.getBean(beanName);
        // log.info("============bean[{}]", bean);
        // beanFactory.removeBeanDefinition(beanName);
        // log.info("=============移除BeanDefinition[{}]", beanName);
        // try {
        // bean = beanFactory.getBean(beanName);
        // log.info("============bean[{}]", bean);
        // } catch (BeansException e) {
        // log.info("============bean[null]...");
        // }

        // // 发送事件
        // applicationContext.publishEvent(new CustomApplicationEvent("started"));
        // applicationContext.publishEvent(new CustomApplicationEvent("started"));
        // System.out.println("logService.getLogDao() = " + logService.getLogDao());
        // System.out.println("logService.getLogDao() = " + logService.getLogDao());
        // System.out.println("logService.getLogDao().getTargetObject() = "
        //         + ((ScopedObject) logService.getLogDao()).getTargetObject());
        // System.out.println("logService.getLogDao().getTargetObject() = "
        //         + ((ScopedObject) logService.getLogDao()).getTargetObject());

        System.out.println("refreshService = " + refreshService);
        System.out.println("refreshService = " + refreshService);
        DbConfigLoader.reload();
        System.out.println("refreshService = " + refreshService);
    }

    // @Override
    // public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    //     configurableListableBeanFactory.registerScope("my", MyScope.INSTANCE);
    // }
}
