package com.nestor.mybatisplusdemo.service;

import com.nestor.mybatisplusdemo.entity.Student;
import com.nestor.mybatisplusdemo.enums.Sex;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testSaveOne() {
        List<Student> list = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            Student student = new Student();
            student.setId(0L);
            student.setName(String.valueOf(i));
            student.setAge(0);
            student.setSex(Sex.MALE);
            student.setEnterScore(new BigDecimal("0"));
            student.setSchoolId(0L);
            list.add(student);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Student> tempList = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++) {
            tempList.add(list.get(i - 1));
            if (i % 1000 == 0) {
                studentService.batchInsert(tempList);
                tempList.clear();
            }
        }

        if (!CollectionUtils.isEmpty(tempList)) {
            studentService.batchInsert(tempList);
            tempList.clear();
        }

        stopWatch.stop();
        log.info("batchInsert耗时[{}]ms", stopWatch.getTotalTimeMillis());
    }

}