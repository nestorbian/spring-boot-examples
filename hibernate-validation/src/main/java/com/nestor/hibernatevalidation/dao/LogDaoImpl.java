package com.nestor.hibernatevalidation.dao;

import org.springframework.aop.scope.ScopedObject;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/9/18
 */
@Repository
@Scope(value = GenericBeanDefinition.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
public class LogDaoImpl implements ScopedObject,LogDao {
    @Override
    public Object getTargetObject() {
        return null;
    }

    @Override
    public void removeFromScope() {

    }
}
