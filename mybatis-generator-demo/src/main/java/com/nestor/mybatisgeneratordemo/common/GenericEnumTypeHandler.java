package com.nestor.mybatisgeneratordemo.common;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.helpers.MessageFormatter;

import com.nestor.mybatisgeneratordemo.enums.BaseEnum;

/**
 * 公用枚举类型处理器可以处理自定义以及通用枚举
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/4/22
 */
public class GenericEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private Class<E> typeClass;

    public GenericEnumTypeHandler(Class<E> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        // 判断枚举是否为应用自定义
        if (parameter instanceof BaseEnum) {
            ps.setObject(i, ((BaseEnum) parameter).getValue());
        } else {
            ps.setObject(i, parameter.name());
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object result = rs.getObject(columnName);
        return getEnum(result);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object result = rs.getObject(columnIndex);
        return getEnum(result);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object result = cs.getObject(columnIndex);
        return getEnum(result);
    }

    /**
     * 根据class类型来获取枚举
     *
     * @param value
     * @return E
     * @date : 2020/5/6 21:10
     * @author : Nestor.Bian
     * @since : 1.0
     */
    private E getEnum(Object value) {
        E result = null;

        if (Objects.isNull(value)) {
            return null;
        }

        Class<?>[] interfaces = typeClass.getInterfaces();
        boolean isBaseEnumType = Arrays.asList(interfaces).contains(BaseEnum.class);
        E[] enumConstants = typeClass.getEnumConstants();
        if (isBaseEnumType) {
            Method getValue;
            try {
                getValue = typeClass.getMethod("getValue");
                for (E enumConstant : enumConstants) {
                    Object invoke = getValue.invoke(enumConstant);
                    if (value.equals(invoke)) {
                        result = enumConstant;
                        break;
                    }
                }
            } catch (Exception e) {
                throw new CommonException("000000", "获取枚举失败", e);
            }
        } else {
            for (E enumConstant : enumConstants) {
                if (value.equals(enumConstant.name())) {
                    result = enumConstant;
                    break;
                }
            }
        }

        if (Objects.isNull(result)) {
            throw new CommonException("000001", MessageFormatter.arrayFormat("未知枚举值,枚举类[{}]中不存在[{}]",
                    new Object[]{typeClass.getSimpleName(), value}).toString());
        }

        return result;
    }
}
