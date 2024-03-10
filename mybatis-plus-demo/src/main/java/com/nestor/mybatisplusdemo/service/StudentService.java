package com.nestor.mybatisplusdemo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nestor.mybatisplusdemo.entity.Student;
import com.nestor.mybatisplusdemo.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/7/3
 */
@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {

    @Transactional
    public void batchInsert(List<Student> students) {
        baseMapper.insertBatchSomeColumn(students);
    }

}
