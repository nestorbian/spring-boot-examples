package com.nestor.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nestor.mybatisplusdemo.entity.Student;

import java.util.List;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/7/3
 */
public interface StudentMapper extends BaseMapper<Student> {

    int insertBatchSomeColumn(List<Student> entityList);

}
