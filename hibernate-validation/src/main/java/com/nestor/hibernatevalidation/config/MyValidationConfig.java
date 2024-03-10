package com.nestor.hibernatevalidation.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/10/5
 */
@Configuration(proxyBeanMethods = false)
public class MyValidationConfig {

    @Bean(name = "customValidator")
    public LocalValidatorFactoryBean defaultValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(messageSource);
        return factoryBean;
    }

}
