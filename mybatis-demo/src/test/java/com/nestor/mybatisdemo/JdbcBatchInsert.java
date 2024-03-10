package com.nestor.mybatisdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/18
 */
@Slf4j
public class JdbcBatchInsert {

    @Test
    public void myBatchInsert() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis-demo?serverTimezone=Asia" +
                "/Shanghai&charEncoding=UTF-8", "root", "123456");
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("insert into t_user(user_name) values ( ? )");
        preparedStatement.setString(1, "a");
        // preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
        preparedStatement.addBatch();
        preparedStatement.setString(1, "b");
        // preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
        preparedStatement.addBatch();
        preparedStatement.addBatch("insert into t_user(user_name, create_time) values ( 'c', now() )");
        // preparedStatement.setString(1, "c");
        // preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
        // preparedStatement.addBatch();

        final int[] ints = preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        connection.commit();

        preparedStatement.close();
        connection.close();
        log.info("影响行数:[{}]", ints);
    }

}
