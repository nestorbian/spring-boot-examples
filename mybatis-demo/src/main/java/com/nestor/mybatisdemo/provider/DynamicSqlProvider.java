package com.nestor.mybatisdemo.provider;

import com.nestor.mybatisdemo.po.Student;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.ObjectUtils;

/**
 * 动态SQL
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/4/28
 */
public class DynamicSqlProvider implements ProviderMethodResolver {

    public String updateStduentSelective(Student student) {
        return new SQL() {
            {
                UPDATE("student");
                if (!ObjectUtils.isEmpty(student.getName())) {
                    SET("name = #{name,jdbcType=VARCHAR}");
                }
                if (!ObjectUtils.isEmpty(student.getAge())) {
                    SET("age = #{age,jdbcType=INTEGER}");
                }
                if (!ObjectUtils.isEmpty(student.getSex())) {
                    SET("sex = #{sex,jdbcType=VARCHAR}");
                }
                if (!ObjectUtils.isEmpty(student.getEnterScore())) {
                    SET("enter_score = #{enterScore,jdbcType=NUMERIC}");
                }
                if (!ObjectUtils.isEmpty(student.getSchoolId())) {
                    SET("school_id = #{schoolId,jdbcType=BIGINT}");
                }
                WHERE("id = #{id,jdbcType=BIGINT}");
            }
        }.toString();
    }


}
