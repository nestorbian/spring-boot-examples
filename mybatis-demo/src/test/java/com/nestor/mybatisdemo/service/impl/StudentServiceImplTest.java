package com.nestor.mybatisdemo.service.impl;
import com.nestor.mybatisdemo.MybatisDemoApplication;
import com.nestor.mybatisdemo.po.Student;
import com.nestor.mybatisdemo.service.StudentService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@SpringBootTest(classes = MybatisDemoApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void update() {
        Student student = new Student();
        student.setId(6L);
        student.setAge(11);

        log.info("影响行数:[{}]", studentService.updateStduentSelective(student));
    }


    @Test
    @SneakyThrows
    public void update2() {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis-demo?serverTimezone=Asia" +
                "/Shanghai&charEncoding=UTF-8", "root", "123456");
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET age = ? WHERE id = ?");
        preparedStatement.setInt(1, 11);
        preparedStatement.setLong(2, 6L);
        int i = preparedStatement.executeUpdate();
        connection.commit();

        preparedStatement.close();
        connection.close();
        log.info("影响行数:[{}]", i);
    }
}