package com.nestor.mybatisdemo.mapper;

import com.nestor.mybatisdemo.dto.StudentDTO;
import com.nestor.mybatisdemo.po.Student;
import com.nestor.mybatisdemo.provider.DynamicSqlProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;
import java.util.Map;

public interface StudentMapper {

    int insertOne(Student student);

    @Insert(value = {"insert into student(name, age, sex, school_id, enter_score) value ",
            "(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, #{sex,jdbcType=VARCHAR}, ",
            "#{schoolId,jdbcType=BIGINT}, #{enterScore,jdbcType=NUMERIC})"})
    @SelectKey(before = false, keyProperty = "id", keyColumn = "id", resultType = Long.class, statement = "select " +
            "last_insert_id()")
    int insertOneWithAnnotation(Student student);

    Student callProcedure(Map<String, Object> map);

    List<StudentDTO> selectStudentWithSchool();

    List<StudentDTO> selectStudentWithColumnPrefix();

    List<Student> selectStudentWithDiscriminator();

    @Select("select id, name, age, sex, enter_score, create_time, update_time, school_id from student")
    @Results(value = {@Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"), @Result(column = "school_id", property = "school", one =
    @One(select = "com.nestor.mybatisdemo.mapper.SchoolMapper.selectById"))})
    List<StudentDTO> selectStudentWithSchoolUseAnnotation();

    @UpdateProvider(DynamicSqlProvider.class)
    int updateStduentSelective(Student student);

}
