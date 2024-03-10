package com.nestor.mybatisdemo.controller;

import com.nestor.mybatisdemo.dto.StudentDTO;
import com.nestor.mybatisdemo.enums.Sex;
import com.nestor.mybatisdemo.po.Student;
import com.nestor.mybatisdemo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生控制类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/4/9
 */
@RestController
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 验证numericScale指定小数是否四舍五入
     * 结果：numericScale没有起作用
     *
     * @param student
     * @return java.lang.String
     * @date : 2020/4/9 19:56
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @PostMapping("/student")
    public String insertOne(@RequestBody Student student) {
        studentService.insertOne(student);
        return "SUCCESS";
    }

    /**
     * 通过@Insert注解插入
     *
     * @param student
     * @return java.lang.Long
     * @date : 2020/4/28 20:20
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @PostMapping("/student-with-annotation")
    public Long insertOneWithAnnotation(@RequestBody Student student) {
        studentService.insertOne(student);
        return student.getId();
    }

    /**
     * 验证存储过程OUT参数
     * 结果：需要将参数封装成map或者DTO传入，否则获取不到
     *
     * @param id
     * @return com.nestor.mybatisdemo.po.Student
     * @date : 2020/4/9 22:48
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/students/{id}")
    public Student callProcedure(@PathVariable Long id) {
        return studentService.callProcedure(id);
    }

    /**
     * 关联的嵌套 Select 查询
     * N+1问题
     *
     * @param
     * @return java.util.List<com.nestor.mybatisdemo.dto.StudentDTO>
     * @date : 2020/4/17 10:14
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/students")
    public List<StudentDTO> selectStudentWithSchool() {
        return studentService.selectStudentWithSchool();
    }

    /**
     * 关联的嵌套查询
     * 使用 columnPrefix 复用resultMap
     * 关联后的列名不能重复，重复列名影响赋值的正确性，只会使用第一个重复列名进行赋值
     *
     * @param
     * @return java.util.List<com.nestor.mybatisdemo.dto.StudentDTO>
     * @date : 2020/4/17 10:14
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/students-with-columnPrefix")
    public List<StudentDTO> selectStudentWithColumnPrefix() {
        return studentService.selectStudentWithColumnPrefix();
    }

    /**
     * 鉴别器查询
     *
     * @param
     * @return java.util.List<com.nestor.mybatisdemo.po.Student>
     * @date : 2020/4/18 11:16
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/students-with-discriminator")
    public List<Student> selectStudentWithDiscriminator() {
        return studentService.selectStudentWithDiscriminator();
    }

    /**
     * 使用@select注解进行嵌套查询
     *
     * @param
     * @return java.util.List<com.nestor.mybatisdemo.dto.StudentDTO>
     * @date : 2020/4/28 20:24
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/students-with-school-use-annotation")
    public List<StudentDTO> selectStudentWithSchoolUseAnnotation() {
        return studentService.selectStudentWithSchoolUseAnnotation();
    }

    /**
     * 验证通过注解实现动态更新
     *
     * @param student
     * @return java.lang.String
     * @date : 2020/5/15 18:28
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @PatchMapping("/student-selective")
    public String updateStduentSelective(@RequestBody Student student) {
        studentService.updateStduentSelective(student);
        return "SUCCESS";
    }

    /**
     * 验证通过注解实现动态删除
     * @param name
     * @param age
     * @return
     */
    @DeleteMapping("/student-selective")
    public String deleteStudentSelective(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) Integer age) {
        studentService.deleteStudentSelective(name, age);
        return "SUCCESS";
    }

    /**
     * 1W 耗时4326ms
     * 加上rewriteBatchedStatements=true 1W 耗时1s
     *
     * 批量插入总结：
     * batchInsert
     *
     * @param
     * @return java.lang.String
     * @date : 2023/7/2 23:42
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("batchInsert")
    public String batchInsert() {
        List<Student> list = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            Student student = new Student();
            student.setId(0L);
            student.setName(String.valueOf(i));
            student.setAge(0);
            student.setSex(Sex.MALE);
            student.setEnterScore(new BigDecimal("0"));
            student.setSchoolId(0L);
            list.add(student);
        }
        studentService.batchInsert(list);
        return "SUCCESS";
    }

    /**
     * 1W 耗时7528ms
     *
     * @param
     * @return java.lang.String
     * @date : 2023/7/2 23:44
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("batchInsert2")
    public String batchInsert2() {
        List<Student> list = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            Student student = new Student();
            student.setId(0L);
            student.setName(String.valueOf(i));
            student.setAge(0);
            student.setSex(Sex.MALE);
            student.setEnterScore(new BigDecimal("0"));
            student.setSchoolId(0L);
            list.add(student);
        }
        studentService.batchInsert2(list);
        return "SUCCESS";
    }

    /**
     * 1W 耗时978ms
     *
     * @param
     * @return java.lang.String
     * @date : 2023/7/2 23:44
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("batchInsert3")
    public String batchInsert3() {
        List<Student> list = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            Student student = new Student();
            student.setId(0L);
            student.setName(String.valueOf(i));
            student.setAge(0);
            student.setSex(Sex.MALE);
            student.setEnterScore(new BigDecimal("0"));
            student.setSchoolId(0L);
            list.add(student);
        }
        studentService.batchInsert3(list);
        return "SUCCESS";
    }

    /**
     * 1000条 55381ms
     *
     * @param
     * @return java.lang.String
     * @date : 2023/7/2 23:44
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("batchInsert4")
    public String batchInsert4() throws Exception {
        List<Student> list = new ArrayList<>(10000);
        for (int i = 0; i < 1000; i++) {
            Student student = new Student();
            student.setId(0L);
            student.setName(String.valueOf(i));
            student.setAge(0);
            student.setSex(Sex.MALE);
            student.setEnterScore(new BigDecimal("0"));
            student.setSchoolId(0L);
            list.add(student);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis-demo?serverTimezone=Asia" +
                "/Shanghai&charEncoding=UTF-8", "root", "123456");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student ( id, name, age, sex, enter_score, school_id ) VALUES ( ?, ?, ?, ?, ?, ? )");
        for (Student student : list) {
            preparedStatement.clearParameters();
            preparedStatement.setLong(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getSex().getCode());
            preparedStatement.setBigDecimal(5, student.getEnterScore());
            preparedStatement.setLong(6, student.getSchoolId());
            int i = preparedStatement.executeUpdate();
        }

        preparedStatement.close();
        connection.close();

        stopWatch.stop();
        log.info("batchInsert4耗时[{}]ms", stopWatch.getTotalTimeMillis());
        return "SUCCESS";
    }
}
