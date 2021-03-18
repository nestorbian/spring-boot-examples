package com.nestor.mybatisgeneratordemo.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;

/**
 * 自定义databaseId provider
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/4/18
 */
public class CustomDatabaseIdProvider implements DatabaseIdProvider {
    private static final String DATABASE_MYSQL = "MySQL";
    private static final String DATABASE_POSTGRESQL = "PostgreSQL";
    private static final String DATABASE_ORACLE = "Oracle";
    private static final String DATABASE_DB2 = "DB2";

    @Override
    public void setProperties(Properties p) {
    }

    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        System.err.println("正在使用自定义DatabaseIdProvider");
        Connection conn = dataSource.getConnection();
        String dbName = conn.getMetaData().getDatabaseProductName();
        String dbAlias = "";
        switch (dbName) {
            case DATABASE_MYSQL:
                dbAlias = "mysql";
                break;
            case DATABASE_POSTGRESQL:
                dbAlias = "pg";
                break;
            case DATABASE_ORACLE:
                dbAlias = "oracle";
                break;
            case DATABASE_DB2:
                dbAlias = "db2";
                break;
            default:
                break;
        }
        return dbAlias;
    }
}
