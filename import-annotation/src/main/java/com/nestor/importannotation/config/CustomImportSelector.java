package com.nestor.importannotation.config;

import com.nestor.importannotation.annotation.Open;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/8/10
 */
public class CustomImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        if (importingClassMetadata.hasAnnotation(Open.class.getName())) {
            if ("ftp".equals(importingClassMetadata.getAnnotationAttributes(Open.class.getName()).get("value"))) {
                return new String[] {"com.nestor.importannotation.service.FtpService"};
            }
            else {
                return new String[] {"com.nestor.importannotation.service.CephService"};
            }
        }
        return new String[]{};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }
}
