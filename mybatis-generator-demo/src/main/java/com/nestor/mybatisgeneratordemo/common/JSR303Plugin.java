package com.nestor.mybatisgeneratordemo.common;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * swagger自定义插件
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/5/31
 */

public class JSR303Plugin extends PluginAdapter {

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        // 增加类注解
        topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
        topLevelClass.addAnnotation("@ApiModel");
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelFieldGenerated(Field field,
                                       TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        if (false == introspectedColumn.isNullable()) {
            if (false == introspectedColumn.isIdentity()) {
                boolean isPrimarkKey = false;//是否为主键
                for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
                    if (introspectedColumn == column) {
                        isPrimarkKey = true;
                    }
                }
                if (!isPrimarkKey) {
                    topLevelClass.addImportedType("javax.validation.constraints.NotNull");
                    field.addAnnotation("@NotNull");
                }
            }
        }

        if (introspectedColumn.isStringColumn()) {
            topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
            field.addAnnotation("@ApiModelProperty(\"" + introspectedColumn.getRemarks() + "\")");
            topLevelClass.addImportedType("javax.validation.constraints.Size");
            field.addAnnotation("@Size(min = 0, max = " + introspectedColumn.getLength() + " , message = " +
                    "\"长度必须在{min}和{max}之间\")");
        } else if ("DECIMAL".equals(introspectedColumn.getJdbcTypeName())) {
            topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
            field.addAnnotation("@ApiModelProperty(value=\"" + introspectedColumn.getRemarks() + "\", example=\"0\")");
        }
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn,
                introspectedTable, modelClassType);
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

}