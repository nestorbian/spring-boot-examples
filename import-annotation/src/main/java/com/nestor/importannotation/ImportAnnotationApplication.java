package com.nestor.importannotation;

import com.nestor.importannotation.annotation.Open;
import com.nestor.importannotation.config.PayConfiguation;
import com.nestor.importannotation.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

@SpringBootApplication
@Import({BuyService.class, PayConfiguation.class})
@Open("ftp")
public class ImportAnnotationApplication implements ApplicationRunner {
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(ImportAnnotationApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.err.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }
}
