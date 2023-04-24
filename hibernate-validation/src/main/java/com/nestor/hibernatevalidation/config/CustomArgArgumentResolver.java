package com.nestor.hibernatevalidation.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nestor.hibernatevalidation.entity.CustomArg;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * CustomArg参数解析器
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/10/17
 */
public class CustomArgArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == CustomArg.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
        // String copy = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(request.getInputStream(),
                new TypeReference<Map<String, Object>>() {

                });
        CustomArg customArg = new CustomArg();
        customArg.setName((String) map.get("name"));
        customArg.setAge((Integer) map.get("age"));

        return customArg;
    }
}
