package com.nestor.hibernatevalidation.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

/**
 * 模拟数据库配置加载
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/1
 */
@Component
public class DbConfigLoader implements EnvironmentAware {

    private static ConfigurableEnvironment environment;

    public static void reload() {
        // 加载数据配置到environment
        load();

        // 清理RefreshScope的缓存
        RefreshScope.clean();
    }

    public static void load() {
        final Map<String, Object> fromDb = getFromDb();
        final MapPropertySource myDbConfig = new MapPropertySource("myDbConfig", fromDb);
        environment.getPropertySources().addFirst(myDbConfig);
    }

    private static Map<String, Object> getFromDb() {
        // 模拟从DB中获取配置
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("db.name", UUID.randomUUID().toString());
        configMap.put("db.version", new Random().nextInt());
        return configMap;
    }

    @Override
    public void setEnvironment(Environment environment) {
        DbConfigLoader.environment = (ConfigurableEnvironment) environment;
        load();
    }
}
