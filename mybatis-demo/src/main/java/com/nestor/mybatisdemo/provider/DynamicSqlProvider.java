package com.nestor.mybatisdemo.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 动态SQL
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/5/15
 */
public class DynamicSqlProvider {

    public String deleteStudentSelective(String name, Integer age) {
        return new SQL() {
            {
                DELETE_FROM("student");
                if (!StringUtils.isEmpty(name)) {
                    WHERE("name = #{name,jdbcType=VARCHAR}");
                }
                if (!ObjectUtils.isEmpty(age)) {
                    WHERE("age = #{age,jdbcType=INTEGER}");
                }
            }
        }.toString();
    }

}
