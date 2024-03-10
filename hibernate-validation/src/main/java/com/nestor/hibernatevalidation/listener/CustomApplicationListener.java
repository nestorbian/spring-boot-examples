package com.nestor.hibernatevalidation.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nestor.hibernatevalidation.aviator.DateFormatFunction;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/8/27
 */
@Component
@Scope(AbstractBeanDefinition.SCOPE_PROTOTYPE)
public class CustomApplicationListener implements ApplicationListener<CustomApplicationEvent> {
    @Override
    public void onApplicationEvent(CustomApplicationEvent event) {
        System.out.println(event.getSource() + " " + this);
    }

    public static void main(String[] args) throws JsonProcessingException {
        Map<String, Class<?>> map = new HashMap<>();
        map.put("s", DateFormatFunction.class);
        map.put("x", String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(map);
        System.out.println(json);
        Map<String, Class<?>> stringClassMap = objectMapper.readValue(json, new TypeReference<Map<String, Class<?>>>() {
        });
        System.out.println(stringClassMap);
    }
}
