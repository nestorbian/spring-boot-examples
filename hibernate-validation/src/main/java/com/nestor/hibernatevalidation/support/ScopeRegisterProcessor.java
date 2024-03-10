package com.nestor.hibernatevalidation.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/1
 */
@Component
public class ScopeRegisterProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // configurableListableBeanFactory.registerScope("my", MyScope.INSTANCE);
    }
}
