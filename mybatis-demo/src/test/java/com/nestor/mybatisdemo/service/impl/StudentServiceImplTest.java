package com.nestor.mybatisdemo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import com.nestor.mybatisdemo.MybatisDemoApplication;
import com.nestor.mybatisdemo.enums.Sex;
import com.nestor.mybatisdemo.po.Student;
import com.nestor.mybatisdemo.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = MybatisDemoApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    List<Student> list = new ArrayList<>(100);

    @Before
    public void before() {
        log.info("before...");
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setId(0L);
            student.setName(String.valueOf(i));
            student.setAge(0);
            student.setSex(Sex.MALE);
            student.setEnterScore(new BigDecimal("0"));
            student.setSchoolId(0L);
            list.add(student);
        }
    }

    /**
     * batchInsert耗时[17299]ms
     *
     * @param
     * @return void
     * @date : 2021/11/21 17:01
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Test
    public void batchInsert() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        studentService.batchInsert(list);
        stopWatch.stop();
        log.info("batchInsert耗时[{}]ms", stopWatch.getTotalTimeMillis());
    }

    /**
     * batchInsert2耗时[74900]ms
     *
     * @param
     * @return void
     * @date : 2021/11/21 17:02
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Test
    public void batchInsert2() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (Student student : list) {
            studentService.insertOne(student);
        }

        stopWatch.stop();
        log.info("batchInsert2耗时[{}]ms", stopWatch.getTotalTimeMillis());
    }
}