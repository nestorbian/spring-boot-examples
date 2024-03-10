package com.nestor.hibernatevalidation.bean;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 需要验证的bean
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/9/26
 */
@Component
public class ValidationBean {

    @NotNull
    private String name;

}
