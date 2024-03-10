package com.nestor.mybatisdemo.service;

import com.nestor.mybatisdemo.enums.GradeLevel;
import com.nestor.mybatisdemo.po.GradeParam;

import java.util.List;

// @MyLog不起作用
public interface GradeParamService {

    GradeParam selectByNameAndLevel(String name, GradeLevel level);

    int insertOne(GradeParam gradeParam);

    List<GradeParam> listWithFetchSize();

    List<GradeParam> listWithStreaming();

    List<GradeParam> selectByNameLike(String name);
}
