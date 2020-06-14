package com.nestor.mybatisgeneratordemo.mapper;

import com.nestor.mybatisgeneratordemo.po.GradeParam;
import com.nestor.mybatisgeneratordemo.po.GradeParamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GradeParamMapper extends BaseMapper<GradeParam, GradeParamExample, Long> {
    List<GradeParam> findByName(@Param(value = "name") String name);
}