package com.nestor.mybatisgeneratordemo.mapper;
import com.nestor.mybatisgeneratordemo.enums.GradeLevel;
import java.time.LocalDateTime;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nestor.mybatisgeneratordemo.po.GradeParam;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class GradeParamMapperTest {

    @Autowired
    private GradeParamMapper gradeParamMapper;

    @Before
    void setUp() {
    }

    @Test
    public void getMethodTest() {
        GradeParam gradeParam = gradeParamMapper.selectByPrimaryKey(1L);
    }

    @Test
    public void updateMethodTest() {
        GradeParam gradeParam = new GradeParam();
        gradeParam.setId(80L);
        gradeParam.setLevel(GradeLevel.THIRD);

        int i = gradeParamMapper.updateByPrimaryKeySelective(gradeParam);
    }
}