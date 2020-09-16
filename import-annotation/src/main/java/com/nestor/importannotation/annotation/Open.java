package com.nestor.importannotation.annotation;

import com.nestor.importannotation.config.CustomImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomImportBeanDefinitionRegistrar.class)
public @interface Open {

    String value();

}
