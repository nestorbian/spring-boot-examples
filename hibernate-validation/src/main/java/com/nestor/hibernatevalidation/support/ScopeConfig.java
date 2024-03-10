package com.nestor.hibernatevalidation.support;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/1
 */
@Configuration(proxyBeanMethods = false)
public class ScopeConfig {

    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        final CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.addScope("my", MyScope.INSTANCE);
        customScopeConfigurer.addScope(RefreshScope.SCOPE_REFRESH, RefreshScope.getInstance());
        return customScopeConfigurer;
    }

}
