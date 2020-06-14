package com.nestor.mybatisgeneratordemo;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis java自动生成类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/5/31
 */
public class MybatisJavaGenerator {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                MybatisJavaGenerator.class.getResourceAsStream("/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}
