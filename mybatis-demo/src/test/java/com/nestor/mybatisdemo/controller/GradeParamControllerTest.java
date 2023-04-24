package com.nestor.mybatisdemo.controller;

import com.nestor.mybatisdemo.po.GradeParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GradeParamControllerTest {
     // * FetchSize需要与流水查询一起用才起作用
     // * 第二种流式查询：需要设置 fetchSize="-2147483648"
     //        * 不需要在url上加useCursorFetch=true
     //        * 如何判断是否生效：ResultSetImpl中的rowData的类型是否为ResultsetRowsStreaming
    @Test
    void listWithFetchSize(@Autowired TestRestTemplate restTemplate) {
        List<GradeParam> gradeParams = restTemplate.getForObject("/grade-params", List.class);
        System.out.println(gradeParams);
    }
}