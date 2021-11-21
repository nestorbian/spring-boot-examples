package com.nestor.mybatisdemo.service.impl;

import com.nestor.mybatisdemo.dto.StudentDTO;
import com.nestor.mybatisdemo.mapper.StudentMapper;
import com.nestor.mybatisdemo.po.Student;
import com.nestor.mybatisdemo.service.StudentService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生service
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/4/9
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public int insertOne(Student student) {
        return studentMapper.insertOne(student);
    }

    @Override
    public int insertOneWithAnnotation(Student student) {
        return studentMapper.insertOneWithAnnotation(student);
    }

    @Override
    public Student callProcedure(Long id) {
        // 封装map或者DTO传入，否者无法获取OUT参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", id);
        Student student = studentMapper.callProcedure(map);
        System.err.println("enterScore: " + ((BigDecimal) map.get("enterScore")).toPlainString());
        return student;
    }

    @Override
    public List<StudentDTO> selectStudentWithSchool() {
        return studentMapper.selectStudentWithSchool();
    }

    @Override
    public List<StudentDTO> selectStudentWithColumnPrefix() {
        return studentMapper.selectStudentWithColumnPrefix();
    }

    @Override
    public List<Student> selectStudentWithDiscriminator() {
        return studentMapper.selectStudentWithDiscriminator();
    }

    @Override
    public List<StudentDTO> selectStudentWithSchoolUseAnnotation() {
        return studentMapper.selectStudentWithSchoolUseAnnotation();
    }

    @Override
    public int updateStduentSelective(Student student) {
        return studentMapper.updateStduentSelective(student);
    }

    @Override
    public int deleteStudentSelective(String name, Integer age) {
        return studentMapper.deleteStudentSelective(name, age);
    }

    /**
     * batch模式批量插入
     *
     * @param students
     * @return void
     * @date : 2021/11/17 15:42
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Override
    public void batchInsert(List<Student> students) {
        // 无法和声明式事务一起用
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        for (int i = 0; i < students.size(); i++) {
            if ((i + 1) % 50 == 0) {
                sqlSession.flushStatements();
            }
            mapper.insertOne(students.get(i));
        }

        // 事务结束
        sqlSession.commit();
    }
}
