package com.nestor.mybatisdemo.mapper;

import com.nestor.mybatisdemo.enums.GradeLevel;
import com.nestor.mybatisdemo.po.GradeParam;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

public interface GradeParamMapper {

    GradeParam selectByNameAndLevel(String name, GradeLevel level);

    int insertOne(GradeParam gradeParam);

    List<GradeParam> listWithFetchSize();

    Cursor<GradeParam> listWithStreaming();

    List<GradeParam> selectByNameLike(String name);
}
