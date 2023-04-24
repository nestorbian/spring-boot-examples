package com.nestor.hibernatevalidation.entity;

import lombok.Data;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/5/17
 */
@Data
public class ListWrapper<E> {

    @Valid
    private List<E> list;

}
