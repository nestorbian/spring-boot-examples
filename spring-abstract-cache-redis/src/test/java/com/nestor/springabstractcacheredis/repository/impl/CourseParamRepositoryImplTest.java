package com.nestor.springabstractcacheredis.repository.impl;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.nestor.springabstractcacheredis.po.CourseParam;
import com.nestor.springabstractcacheredis.repository.CourseParamRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class CourseParamRepositoryImplTest {

    @Autowired
    private CourseParamRepository courseParamRepository;

    @Test
    void getById() {
        CourseParam byId = courseParamRepository.getById(3L);
        log.info(byId.toString());
    }

    @Test
    void updateById() {
        CourseParam courseParam = new CourseParam();
        courseParam.setId(30L);
        courseParam.setLessionPeriod(new BigDecimal("800"));

        courseParamRepository.updateById(courseParam);
    }

    @Test
    void deleteById() {
        courseParamRepository.deleteById(3L);
    }

    @Test
    void updateMany() {
    }
}