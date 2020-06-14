package com.nestor.mybatisgeneratordemo.po;

import com.nestor.mybatisgeneratordemo.enums.Sex;
import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * student
 */
public class Student extends BaseModel implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 年龄
     *
     * @mbg.generated
     */
    private Short age;

    /**
     * 性别
     *
     * @mbg.generated
     */
    private Sex sex;

    /**
     * 学校
     *
     * @mbg.generated
     */
    private Long schoolId;

    /**
     * 入学成绩
     *
     * @mbg.generated
     */
    private BigDecimal enterScore;

    /**
     * student
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 年龄
     * @return age 年龄
     */
    public Short getAge() {
        return age;
    }

    /**
     * 年龄
     * @param age 年龄
     */
    public void setAge(Short age) {
        this.age = age;
    }

    /**
     * 性别
     * @return sex 性别
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * 性别
     * @param sex 性别
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * 学校
     * @return school_id 学校
     */
    public Long getSchoolId() {
        return schoolId;
    }

    /**
     * 学校
     * @param schoolId 学校
     */
    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 入学成绩
     * @return enter_score 入学成绩
     */
    public BigDecimal getEnterScore() {
        return enterScore;
    }

    /**
     * 入学成绩
     * @param enterScore 入学成绩
     */
    public void setEnterScore(BigDecimal enterScore) {
        this.enterScore = enterScore;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}