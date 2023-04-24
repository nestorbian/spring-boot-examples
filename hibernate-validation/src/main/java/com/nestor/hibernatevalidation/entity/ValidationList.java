package com.nestor.hibernatevalidation.entity;

import lombok.experimental.Delegate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/5/6
 */
public class ValidationList<E> implements List<E> {

    @Delegate
    @Valid
    public List<E> list = new ArrayList<>();

    @Override
    public String toString() {
        return list.toString();
    }
}