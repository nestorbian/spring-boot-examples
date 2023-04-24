package com.nestor.hibernatevalidation.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/11/7
 */
@Data
public class ObjectWrapper<E> {


    @NotBlank
    private String name;

    @Valid
    @NotNull
    private E e;

}
