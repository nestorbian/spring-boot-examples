package com.nestor.springabstractcacheredis.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义key生成策略
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/1/22
 */
@Component
public class CustomKeyGenerator implements KeyGenerator {
    /**
     * Generate a key for the given method and its parameters.
     *
     * @param target the target instance
     * @param method the method being called
     * @param params the method parameters (with any var-args expanded)
     * @return a generated key
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return method.getName() + "(" + StringUtils.join(params, ",") + ")";
    }
}
