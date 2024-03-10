package com.nestor.hibernatevalidation.support;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * 自定义Scope
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/1
 */
public class MyScope implements Scope {

    public static final MyScope INSTANCE = new MyScope();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        System.out.println("==========MyScope get " + name + "==========");
        return objectFactory.getObject();
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
