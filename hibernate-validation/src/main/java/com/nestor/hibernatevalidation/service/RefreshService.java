package com.nestor.hibernatevalidation.service;

import com.nestor.hibernatevalidation.annotation.RefreshScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/1
 */
@Service
@RefreshScope
public class RefreshService {

    @Value("${db.name}")
    private String dbName;

    @Value("${db.version}")
    private Integer dbVersion;

    @Override
    public String toString() {
        return "RefreshService{" +
                "dbName='" + dbName + '\'' +
                ", dbVersion=" + dbVersion +
                '}';
    }
}
