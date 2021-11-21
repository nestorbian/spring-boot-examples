package com.nestor.mybatisdemo.service;

import com.nestor.mybatisdemo.dto.StudentDTO;
import com.nestor.mybatisdemo.po.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {
    int insertOne(Student student);

    int insertOneWithAnnotation(Student student);

    Student callProcedure(Long id);

    List<StudentDTO> selectStudentWithSchool();

    List<StudentDTO> selectStudentWithColumnPrefix();

    List<Student> selectStudentWithDiscriminator();

    List<StudentDTO> selectStudentWithSchoolUseAnnotation();

    int updateStduentSelective(Student student);

    int deleteStudentSelective(String name, Integer age);

    void batchInsert(List<Student> student);
}
