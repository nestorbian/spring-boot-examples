package com.nestor.springabstractcacheredis;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.nestor.springabstractcacheredis.mapper")
@EnableTransactionManagement
@EnableSwagger2Doc
public class SpringAbstractCacheRedisApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SpringAbstractCacheRedisApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("running....");
	}
}
