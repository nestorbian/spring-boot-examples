package com.nestor.mybatisdemo.controller;

import com.nestor.mybatisdemo.dto.StudentDTO;
import com.nestor.mybatisdemo.po.Student;
import com.nestor.mybatisdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 学生控制类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/4/9
 */
@RestController
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

    @PatchMapping("/student-selective")
    public String updateStduentSelective(@RequestBody Student student) {
        studentService.updateStduentSelective(student);
        return "SUCCESS";
    }
}
