package com.nestor.hibernatevalidation.service;

import com.nestor.hibernatevalidation.annotation.MyScope;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/1
 */
@Service
@MyScope
// @DependsOn("logServiceImpl")
public class MyService {

    private String uuid = UUID.randomUUID().toString();

    public void print() {
        System.out.println("uuid = " + uuid);
    }

}
