package com.nestor.redissonspringbootstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis 客户端配置
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/4/9
 */
@ConfigurationProperties(prefix = "redisson.client")
public class RedissonClientProperties {

    private String host = "localhost";

    private int port = 6379;

    private String password;

    private boolean ssl;

    private int timeout;

    private int database;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }
}
