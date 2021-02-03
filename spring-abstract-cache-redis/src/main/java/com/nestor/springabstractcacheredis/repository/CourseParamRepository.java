package com.nestor.springabstractcacheredis.repository;

import com.nestor.springabstractcacheredis.po.CourseParam;

import java.util.Optional;

public interface CourseParamRepository {

    CourseParam getById(Long id);

    CourseParam updateById(CourseParam courseParam);

    int deleteById(Long id);

    Integer updateMany(CourseParam courseParam);

    CourseParam insertOne(CourseParam courseParam);
}
