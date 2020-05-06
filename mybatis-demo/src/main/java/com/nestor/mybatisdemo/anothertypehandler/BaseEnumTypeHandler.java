package com.nestor.mybatisdemo.anothertypehandler;

import com.nestor.mybatisdemo.enums.BaseEnum;
import com.nestor.mybatisdemo.enums.GradeLevel;
import com.nestor.mybatisdemo.enums.Sex;
import com.nestor.mybatisdemo.util.EnumUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BaseEnum枚举类型处理器
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/22
 */
@MappedTypes({GradeLevel.class, Sex.class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR, JdbcType.TINYINT}, includeNullJdbcType = true)
public class BaseEnumTypeHandler<T extends BaseEnum> extends BaseTypeHandler<T> {

    private Class<T> type;

    public BaseEnumTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.type = type;
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i, t.getValue());
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return (T) EnumUtil.getByCode(resultSet.getObject(s), type);
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return (T) EnumUtil.getByCode(resultSet.getObject(i), type);
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return (T) EnumUtil.getByCode(callableStatement.getObject(i), type);
    }

}
