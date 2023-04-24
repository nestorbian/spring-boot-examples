package com.nestor.mybatisdemo.service.impl;

import com.nestor.mybatisdemo.MybatisDemoApplication;
import com.nestor.mybatisdemo.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MybatisDemoApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
class SchoolServiceImplTest {

    @Autowired
    private SchoolService schoolService;

    @Test
    void moreSelect() {
        System.err.println(schoolService.moreSelect(1L));
    }
}