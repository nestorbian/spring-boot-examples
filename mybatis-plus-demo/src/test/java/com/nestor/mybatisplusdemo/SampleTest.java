package com.nestor.mybatisplusdemo;

import com.nestor.mybatisplusdemo.entity.User;
import com.nestor.mybatisplusdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/10/8
 */
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        System.out.println(("----- Insert method test ------"));
        User user = new User();
        user.setAge(10);
        int rows = userMapper.insert(user);
        System.err.println("insert rows:" + rows);
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}
