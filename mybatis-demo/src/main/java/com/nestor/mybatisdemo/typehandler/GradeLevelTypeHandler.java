package com.nestor.mybatisdemo.typehandler;

import com.nestor.mybatisdemo.enums.GradeLevel;
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
 * 年级枚举类型处理器
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/22
 */
@MappedTypes(GradeLevel.class)
@MappedJdbcTypes(value = JdbcType.TINYINT, includeNullJdbcType = true)
public class GradeLevelTypeHandler extends BaseTypeHandler<GradeLevel> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, GradeLevel level, JdbcType jdbcType) throws SQLException {
        System.err.println(String.format("level: %s", level));
        preparedStatement.setInt(i, level.getValue());
    }

    @Override
    public GradeLevel getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return EnumUtil.getByCode(resultSet.getInt(s), GradeLevel.class);
    }

    @Override
    public GradeLevel getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return EnumUtil.getByCode(resultSet.getInt(i), GradeLevel.class);
    }

    @Override
    public GradeLevel getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return EnumUtil.getByCode(callableStatement.getInt(i), GradeLevel.class);
    }
}
