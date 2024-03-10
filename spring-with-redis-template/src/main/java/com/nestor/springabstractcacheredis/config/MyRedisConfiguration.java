package com.nestor.springabstractcacheredis.config;

import java.net.UnknownHostException;

import com.nestor.springabstractcacheredis.controller.GradeParamController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/7/24
 */
@Configuration(proxyBeanMethods = false)
// @ConditionalOnClass(RedisOperations.class)
@ConditionalOnBean(RedisTemplate.class)
public class MyRedisConfiguration {

    // @Bean
    // @ConditionalOnMissingBean(name = "redisTemplate")
    // public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    //         throws UnknownHostException {
    //     RedisTemplate<Object, Object> template = new RedisTemplate<>();
    //     System.err.println("redisTemplate....");
    //     template.setConnectionFactory(redisConnectionFactory);
    //     return template;
    // }
    //
    // @Bean
    // @ConditionalOnMissingBean
    // public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory)
    //         throws UnknownHostException {
    //     StringRedisTemplate template = new StringRedisTemplate();
    //     System.err.println("stringRedisTemplate....");
    //     template.setConnectionFactory(redisConnectionFactory);
    //     return template;
    // }

}