package com.nestor.springabstractcacheredis.repository;

import com.nestor.springabstractcacheredis.enums.GradeLevel;
import com.nestor.springabstractcacheredis.po.GradeParam;

import java.util.List;

public interface GradeParamRepository {

    GradeParam getById(Long id);

    GradeParam updateById(GradeParam gradeParam);

    int deleteById(Long id);

    Integer updateMany(GradeParam gradeParam);

    List<GradeParam> selectByNameAndLevel(String name, GradeLevel level);
}
